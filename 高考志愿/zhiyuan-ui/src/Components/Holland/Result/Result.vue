<script setup>

import {ArrowRight} from "@element-plus/icons-vue";
import {onMounted, ref,markRaw,defineEmits } from "vue";
import {ElLoading} from "element-plus";
import {getResult} from "@/api/topic.js";
import {useUserStore} from "@/store/useUserStore.js";
import echarts from "@/utils/echarts";
const userStore = useUserStore()
const hollandResultId = ref(null)
const resultOption = ref({})
const resultChat = ref({})
const resultData = ref([])
const personalityList = ref([])
const job = ref("")
const morphology =ref("")
const emit=defineEmits(['reset'])
const tableData = ref([{
  label1: "",
  label2: "",
  label3: ""
}])
// 动态生成表头标签
const header = () => {
  personalityList.value.push(tableData.value[0].label1.substring(0, 3))
  personalityList.value.push(tableData.value[0].label2.substring(0, 3))
  personalityList.value.push(tableData.value[0].label3.substring(0, 3))
}
const getResultData = async () => {
  const res = await getResult(hollandResultId.value)
  resultData.value.push(Math.round(res.data.r * 10))
  resultData.value.push(Math.round(res.data.i * 10))
  resultData.value.push(Math.round(res.data.a * 10))
  resultData.value.push(Math.round(res.data.c * 10))
  resultData.value.push(Math.round(res.data.e * 10))
  resultData.value.push(Math.round(res.data.s * 10))
  tableData.value[0].label1 = (res.data.personality1)
  tableData.value[0].label2 = (res.data.personality2)
  tableData.value[0].label3 = (res.data.personality3)
  job.value = res.data.typicalProfession
  morphology.value=res.data.comparisontableMorphology
}
//加载echarts
const chatResult = () => {
  resultChat.value = markRaw(echarts.init(document.getElementById('result')))
  resultOption.value = {
    color: ['#ffec3d'],
    radar: [
      {
        indicator: [
          {name: 'R（实际型）',  min: 0},
          {name: 'I（研究型）',  min: 0},
          {name: 'A（艺术型）',  min: 0},
          {name: 'C（传统型）',  min: 0},
          {name: 'E（企业型）',  min: 0},
          {name: 'S（社会型）',  min: 0}
        ],
        max: 10,
        radius: 120,
        startAngle: 90,
        splitNumber: 4,
        shape: 'circle',
        axisName: {
          formatter: '{value}',
          color: '#428BD4'
        },
        splitArea: {
          areaStyle: {
            color: ['#9AC5FF', '#FFC2EC', '#597ef7', '#3F51B5'],
            shadowColor: 'rgba(0, 0, 0, 0.2)',
            shadowBlur: 10
          }
        },

      },
    ],
    series: [
      {
        type: 'radar',
        emphasis: {
          lineStyle: {
            width: 4
          }
        },
        data: [
          {
            value: resultData.value,
            label: {
              show: true,
              formatter: (params) => {
                return params.value
              },
            },
          },
        ],
        label: {
          show: true,
          color: '#000', // 拐点字体颜色
          fontWeight: 'bold',
          fontSize: 14,
          padding: [0, 20, 0, 0]
        }
      },
    ]
  }
  resultChat.value.setOption(resultOption.value)
}
onMounted(async () => {
  const loadingInstance = ElLoading.service({
    background: 'rgba(0, 0, 0, 0.7)',
    lock: true,
    text: 'Loading',
  })
  hollandResultId.value = userStore.getData.hollandResultId
  if (hollandResultId.value !== null) {
    await getResultData()
    header()
    setTimeout(() => {
      chatResult()
      loadingInstance.close()
    }, 1000)
  }
})
</script>

<template>
  <div class="contentBox">
    <div class="title">
      <p>您的职业代码为：{{ morphology }}</p>
    </div>
    <div style="margin-top: 10px;font-size: 16px;">
      <span>建议您的职业为：</span>
      <br/>
      <p style="font-weight: bold; text-indent: 2em;padding-top: 10px;line-height: 30px;">
        {{ job }}
      </p>
    </div>
    <div style="font-size: 18px;font-weight: bold;margin-top: 20px">
      <span>维度解析</span>
    </div>
    <div style="margin-top: 10px">
      <el-table :data="tableData" border style="width: 100%;">
        <el-table-column :label="personalityList[0]" prop="label1" style="line-height: 90px"/>
        <el-table-column :label="personalityList[1]" prop="label2" style="line-height: 90px"/>
        <el-table-column :label="personalityList[2]" prop="label3" style="line-height: 90px"/>
      </el-table>
    </div>
  </div>
  <div style="width: 35%;height: 700px;">
    <div id="result" style="height: 400px"></div>
    <div class="reset" @click="emit('reset')">
      <span>重新填写</span>
      <el-icon style="padding-top: 5px;margin-left: 5px"><ArrowRight /></el-icon>
    </div>
  </div>
</template>

<style scoped lang="scss">
.contentBox{
  width: 64%;
  height: 700px;
  .title{
    font-size: 20px;
    font-weight: bold;
    margin-top: 20px;
  }
}
.reset{
  text-align: center;
  display: flex;
  margin: 20px auto 0;
  width: 80px;
  cursor: pointer;
}
.reset:hover{
  color: #f9541c;
}
:deep(.el-table .cell) {
  line-height: 60px;
}
</style>
