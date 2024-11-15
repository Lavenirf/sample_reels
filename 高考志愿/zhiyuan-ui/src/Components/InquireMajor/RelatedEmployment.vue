<script setup>
/*
* 相关就业
* */
import echarts from "@/utils/echarts";
import {onMounted, ref, markRaw, nextTick} from 'vue'
import {useSpecialStore} from "@/store/useSpecialChatStore.js";

const store = useSpecialStore()
//通用配置
const grid = ref({
  top: '35%',
  left: '3%',
  right: '3%',
  bottom: '3%',
  containLabel: true
})
const specialData = ref({})
//薪酬数据
const chartPay = ref({})
const optionPay = ref({})
const thisMajor = ref([])
const allMajor = ref([])
const maxY = ref(0)
//就业行业分布数据
const chatIndustryDistribution = ref({})
const optionIndustryDistribution = ref({})
const industryDistributionData = ref([])
//岗位分布数据
const chatPositions = ref({})
const optionPositions = ref({})
const positionsData = ref([])
const detailJob = ref('')
const detailPos = ref('')
//地区分布数据
const chatRegionalDistribution = ref({})
const optionRegionalDistribution = ref({})
const regionalData = ref([])

//薪酬echarts
const loadPayChat = () => {
  chartPay.value = markRaw(echarts.init(document.getElementById('pay')))
  optionPay.value = {
    title: {
      text: '薪酬',
      top: "30",
      left: '30',
      textStyle: {
        color: 'black',
        fontWeight: 'bold'
      }
    },
    tooltip: {
      trigger: 'axis'
    },
    legend: {
      data: [{
        name: '本专业',
        itemStyle: {
          color: '#50ca81'
        }
      }, {
        name: '所有专业',
        itemStyle: {
          color: '#ff6600'
        }
      }],
      icon: 'circle',
      right: '0',
      top: '20%'
    },
    grid: grid.value,
    xAxis: {
      type: 'category',
      boundaryGap: false,
      data: ['应届生', '2年经验', '5年经验', '10年经验']
    },
    yAxis: {
      type: 'value',
      min: '0',
      max: maxY.value,
      interval: maxY.value / 5,
    },
    series: [
      {
        name: '本专业',
        type: 'line',
        data: thisMajor.value,
        lineStyle: {
          color: '#50ca81',
          width: 3,
          type: 'solid'
        },
      },
      {
        name: '所有专业',
        type: 'line',
        data: allMajor.value,
        lineStyle: {
          color: '#ff6600',
          width: 2,
          type: 'solid'
        },
      }
    ]
  }
  chartPay.value.setOption(optionPay.value)
}
//行业分布echarts
const loadIndustryDistributionChat = () => {
  industryDistributionData.value.sort((a, b) => b.rate - a.rate)
  chatIndustryDistribution.value = markRaw(echarts.init(document.getElementById("industryDistribution")))
  optionIndustryDistribution.value = {
    title: {
      text: '就业行业分布',
      top: "30",
      left: '30',
      textStyle: {
        color: 'black',
        fontWeight: 'bold'
      },
    },
    tooltip: {
      trigger: 'axis',
      formatter: '{b}: {c}%'
    },
    grid: grid.value,
    xAxis: {
      type: 'category',
      data: industryDistributionData.value.map(d => d.name)
    },
    yAxis: {
      type: 'value',
      name: '比例（100%）',
      min: '0',
      max: 100,
      nameTextStyle: {
        color: '#000',
        fontSize: 15
      },
      axisLabel: {
        formatter: '{value}%',
        color: '#000'
      },
      splitLine: {
        lineStyle: {
          type: 'dashed'
        }
      }
    },
    series: {
      data: industryDistributionData.value.map(d => d.rate),
      type: 'bar',
      itemStyle: {
        color: '#ff6500',
        borderRadius: 50
      },
      barWidth: '15',
      label: {
        show: true,
        position: 'top',
        formatter: function (data) {
          return data.value + '%'
        }
      }
    },
  }
  chatIndustryDistribution.value.setOption(optionIndustryDistribution.value)
}
//岗位分布echarts
const loadPositions = () => {
  chatPositions.value = markRaw(echarts.init(document.getElementById("Positions")))
  optionPositions.value = {
    title: {
      text: '就业岗位分布',
      top: "30",
      left: '30',
      textStyle: {
        color: 'black',
        fontWeight: 'bold'
      },
    },
    tooltip: {
      trigger: 'item',
      formatter: (params) => {
        return params.name + '：' + params.value
      }
    },
    legend: {
      top: 'center',
      right: '-1%',
      formatter: (name) => {
        const item = positionsData.value.find(d => d.name === name)
        return item ? `${name} (${item.value}%)` : name
      },
      orient: 'horizontal',
      width: '45%',
      itemGap: 20,
      textStyle: {
        color: "#000",
        fontSize: 15
      }
    },
    series: [
      {
        type: 'pie',
        radius: ['40%', '70%'],
        center: ['28%', '60%'],
        avoidLabelOverlap: false,
        itemStyle: {
          borderRadius: 10,
          borderColor: '#fff',
          borderWidth: 2,
        },
        label: {
          show: false
        },
        labelLine: {
          show: false
        },
        data: positionsData.value
      }
    ]
  }
  chatPositions.value.setOption(optionPositions.value)
  chatPositions.value.on('mouseover', (params) => {
    handlePositionsClick(params)
  })
}
const handlePositionsClick = (params) => {
  detailJob.value = params.data.detail_job
  detailPos.value = params.data.detail_pos
}
//地区分布echarts
const loadRegionalDistribution = () => {
  regionalData.value.sort((a, b) => a.value - b.value)
  chatRegionalDistribution.value = markRaw(echarts.init(document.getElementById("regionalDistribution")))
  optionRegionalDistribution.value = {
    title: {
      text: '就业地区分布',
      top: "30",
      left: '30',
      textStyle: {
        color: 'black',
        fontWeight: 'bold'
      },
    },
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow'
      }
    },
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      containLabel: true
    },
    xAxis: {
      type: 'value',
      min: 0,
      max: 100
    },
    yAxis: {
      type: 'category',
      data: regionalData.value.map(d => d.name)
    },
    dataset: {
      source: regionalData.value
    },
    series: [
      {
        type: 'bar',
        encode: { // 设置 encode 以适应 dataset
          x: 'value',
          y: 'name'
        },
        data: regionalData.value.map(d => d.value),
        itemStyle: {
          color: '#ff6500',
          borderRadius: 50,
        },
        barWidth: '15',
        label: {
          show: true,
          position: "right",
          formatter: function (data) {
            return data.value + '%'
          }
        },
      }
    ]
  }
  chatRegionalDistribution.value.setOption(optionRegionalDistribution.value)
}
//加载数据
const loadData = () => {
  specialData.value = store.getSpecialDetail
  console.log(store.getThisFreshGraduates.map(Number))
  thisMajor.value = store.getThisFreshGraduates.map(Number)
  allMajor.value = store.getOutFreshGraduates.map(Number)
  maxY.value = Math.max(...thisMajor.value, ...allMajor.value) + 3000
  industryDistributionData.value = store.getDistribution
  positionsData.value = store.getPositions.map(item => {
    const {rate, detail_pos, name, ...rest} = item;
    return {...rest, value: rate, name: detail_pos, detail_pos: name};
  });
  detailJob.value = positionsData.value.length !== 0 ? positionsData.value[0].detail_job : ''
  detailPos.value = positionsData.value.length !== 0 ? positionsData.value[0].detail_pos : ''
  regionalData.value = store.getAreas.map(item => {
    const {rate, area, name, ...rest} = item
    return {...rest, value: rate, name: area, area: name}
  })
}
onMounted(async () => {
  loadData()
  await nextTick()
})
nextTick(async () => {
  loadPayChat()
  loadIndustryDistributionChat()
  loadPositions()
  loadRegionalDistribution()
})
</script>

<template>
  <div style="padding-left: 30px;">
    <el-row>
      <el-col :span="24" style="margin-top: 15px">
        <div class="contentBox1">
          <div id="pay"></div>
          <div style="display: flex;justify-content: space-between">
            <div class="card">
              <p class="cardTitle1">{{ specialData.graduateFiveSalary }}</p>
              <p class="cardTitle2">毕业五年月薪</p>
            </div>
            <div class="card">
              <p class="cardTitle1">{{ specialData.post }}</p>
              <p class="cardTitle2">最多就业岗位</p>
            </div>
            <div class="card">
              <p class="cardTitle1">{{ specialData.industry }}</p>
              <p class="cardTitle2">最多就业行业</p>
            </div>
            <div class="card">
              <p class="cardTitle1">{{ specialData.mentarea }}</p>
              <p class="cardTitle2">最多就业地区</p>
            </div>
          </div>
        </div>
        <div class="contentBox2">
          <div id="industryDistribution"></div>
        </div>
        <div class="contentBox3" >
          <div id="Positions"></div>
          <div>
            <div style="margin: 10px 5%;">
              <p style="font-size: 15px;font-weight: bold">具体职位：</p>
              <p style="font-size: 14px">{{ detailJob }}</p>
            </div>
            <div style="margin: 10px 5%">
              <p style="font-size: 15px;font-weight: bold">所在行业：</p>
              <p style="font-size: 14px">{{ detailPos }}</p>
            </div>
          </div>
        </div>
        <div class="contentBox4">
          <div id="regionalDistribution"></div>
        </div>
        <div class="contentBox5">
          <div style="font-size: 15px;font-weight: bold;padding: 20px 5%;">
            专业就业
          </div>
          <div style="font-size: 15px;padding: 5px 5%;text-indent: 2em;" v-html="specialData.job">
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<style lang="scss" scoped>

.contentBox1 {
  height: 570px;
  width: 80%;
  box-shadow: 0 2px 20px #f8f8f8;

  #pay {
    margin: 5px auto;
    width: 95%;
    height: 450px;
  }

  .card {
    width: 200px;
    height: 70px;
    background: #f8f8f8;
    text-align: center;
    line-height: 20px;
    margin: 20px 20px;
    border-radius: 5px;

    .cardTitle1 {
      height: 40px;
      font-size: 18px;
      font-weight: 500;
      line-height: 40px;
    }

    .cardTitle2 {
      font-size: 13px;
      color: #767676;
    }
  }
}

.contentBox2 {
  height: 400px;
  width: 80%;
  margin-top: 20px;
  box-shadow: 0 2px 20px #f8f8f8;

  #industryDistribution {
    margin: 5px auto;
    width: 95%;
    height: 100%;
  }
}

.contentBox3 {
  height: 680px;
  width: 80%;
  margin-top: 20px;
  box-shadow: 0 2px 20px #f8f8f8;

  #Positions {
    margin: 5px auto;
    width: 95%;
    height: 80%;
  }
}

.contentBox4 {
  height: 630px;
  width: 80%;
  margin-top: 20px;
  box-shadow: 0 2px 20px #f8f8f8;

  #regionalDistribution {
    margin: 5px auto;
    width: 95%;
    height: 100%;
  }
}

.contentBox5 {
  height: auto;
  padding: 20px 0;
  width: 80%;
  margin-top: 20px;
  box-shadow: 0 2px 20px #f8f8f8;
}


</style>