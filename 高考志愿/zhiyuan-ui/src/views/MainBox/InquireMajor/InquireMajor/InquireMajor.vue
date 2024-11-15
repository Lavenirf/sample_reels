<script setup>
import echarts from "@/utils/echarts";
import { onMounted, ref, computed, reactive } from 'vue';
import { majorData } from '@/static/index.js';
import { selectSchoolSpecial } from "@/api/schoolSpecial.js";
import router from "@/router/index.js";

const showAside = ref(true)
const showMain = ref(false)
const option = ref({})
const queryParams = ref({
  pageNum: 1,
  pageSize: 10,
  level1Name: null,
  level2Name: null,
  level3Name: null,
});
const specialData = reactive({
  state: {
    data: [],
    total: 10
  }
});

// 用于控制样式的响应式变量
const isShifted = ref(false)

const handleCurrentChange = async () => {
  await selectSpecial(queryParams.value.level1Name,queryParams.value.level2Name,queryParams.value.level3Name)
};

option.value = {
  series: {
    type: 'sunburst',
    data: majorData,
    radius: [0, '95%'],
    emphasis: {
      focus: 'ancestor'
    },
    levels: [
      {},
      {
        r0: '15%',
        r: '35%',
        itemStyle: {
          borderWidth: 2
        },
        label: {
          rotate: 'tangential',
        }
      },
      {
        r0: '35%',
        r: '70%',
        label: {
          align: 'right'
        }
      },
      {
        r0: '70%',
        r: '72%',
        label: {
          position: 'outside',
          padding: 3,
          silent: false,
        },
        itemStyle: {
          borderWidth: 3,
        }
      }
    ]
  }
};

const selectSpecial = async (level1Name, level2Name, level3Name) => {
  queryParams.value.level1Name = level1Name
  queryParams.value.level2Name = level2Name
  queryParams.value.level3Name = level3Name
  const res = await selectSchoolSpecial(queryParams.value)
  if (res !== null) {
    specialData.state.data = res.data.rows
    specialData.state.total = res.data.total
  }
};

const handleSunburstClick = async (params) => {
  toggleStyle()
  if (params.treePathInfo.length === 2) {
    await selectSpecial(params.name);
  } else if (params.treePathInfo.length === 3) {
    await selectSpecial(params.data.nick, params.data.name)
  } else if (params.treePathInfo.length === 4) {
    await selectSpecial(params.treePathInfo[1].name, params.treePathInfo[2].name, params.name)
  } else {
    if (queryParams.value.level3Name) {
      queryParams.value.level3Name = null
    } else if (queryParams.value.level2Name) {
      queryParams.value.level2Name = null
    } else {
      queryParams.value.level1Name = null
    }
    await selectSpecial(queryParams.value.level1Name, queryParams.value.level2Name, queryParams.value.level3Name);
  }
  showMain.value = true
};

const handlePushDetail = (specialId) => {
  router.push({ path: 'professionalDetails', query: { id: specialId } })
  showAside.value = false
  showMain.value = false
}
// 切换样式的方法
const toggleStyle = () => {
  isShifted.value = !isShifted.value
};

onMounted(async () => {
  let chartDom = document.getElementById('main');
  let myChart = echarts.init(chartDom)
  // 添加点击事件监听器
  myChart.on('click', handleSunburstClick);
  option && myChart.setOption(option.value)
});

const pageLength = computed(() => Math.ceil(specialData.state.total / queryParams.value.pageSize))
</script>

<template>
  <div :class="['mainBox', { shifted: isShifted }]">
    <el-container>
      <div v-if="showAside">
        <div id="main" class="box"></div>
      </div>
      <el-main v-if="showMain">
        <el-scrollbar>
          <div style="margin-left: 0;">
            <div style="height: 970px;padding: 0 20px">
              <div v-for="item in specialData.state.data" :key="item.specialId" class="item-container">
                <div class="item-content" @click="handlePushDetail(item.specialId)">
                  <div style="display: flex">
                    <div class="item-name">{{ item.name }}</div>
                    <div class="item-code">
                      <div style="background:url('/Icon/code.png') no-repeat;width: 15px;height: 15px;background-size: 15px;margin: 2px 10px 5px 10px"></div>
                      <div style="padding-right: 20px">专业代码:{{ item.spcode }}</div></div>
                  </div>
                  <div style="display: flex;margin-top: 10px;">
                    <div class="lgTitle">修业年限:{{ item.limitYear }}</div>
                    <div class="lgTitle" v-if="item.degree!==''">授予学位:{{ item.degree }}</div>
                    <div class="lgTitle" v-if="item.boyRate!=='0'">男女比例:{{ item.boyRate }}:{{ item.girlRate }}</div>
                    <div class="lgTitle" v-if="item.fivesalaryavg!==0">平均薪酬:￥{{ item.fivesalaryavg }}</div>
                  </div>
                </div>
              </div>
            </div>
            <el-pagination
                v-if="specialData.state.data.length > 0 && pageLength > 1"
                layout="prev, pager, next"
                v-model:currentPage="queryParams.pageNum"
                v-model:page-size="queryParams.pageSize"
                @current-change="handleCurrentChange"
                :total="specialData.state.total"
                class="pagination-container"
            />
          </div>
        </el-scrollbar>
      </el-main>
    </el-container>
    <router-view/>
  </div>
</template>
<style scoped>
.box {
  width: 950px;
  height: 1200px;
}

.mainBox {
  margin-left: 10px;
  transition: margin-left 0.5s;
}

.mainBox.shifted {
  margin-left: -50%;
}
:deep(.el-pager li.is-active){
  border: 1px solid #4096ff;
  border-radius: 5px;
}
:deep(.main-content) {
  width: 100% !important;
}

.el-main {
  margin-left: 10%;
  width: auto;
  padding: 0 20px;
  height: 1200px;
  background: #fff;
  box-shadow: 0 2px 20px #f8f8f8;
  margin-top: 80px;
  border-radius: 10px;
}

.item-container {
  width: auto;
  margin: 20px 0;
  cursor: pointer;
}

.item-content {
  width: 100%;
  height: 70px;
  margin: 30px auto;
}

.item-name {
  margin-left: 10px;
  line-height: 25px;
  font-size: 16px;
  color: black;
}

.item-name:hover {
  color: #fa541c;
}

.item-code {
  margin-top: 5px;
  margin-left: 10px;
  line-height: 20px;
  height: 20px;
  border: 1px solid black;
  border-radius: 15px;
  text-align: center;
  display: flex;
}

.lgTitle {
  line-height: 30px;
  text-align: center;
  padding: 0 10px;
  height: 30px;
  margin-left: 5px;
  background: #f5f6f7;
  border-radius: 5px;
}

.pagination-container {
  margin: 40px 0 0 20px;
}
@media (max-width: 3000px) {
  .mainBox.shifted {
    margin-left: 0;
  }
  :deep(canvas) {
    left: 2% !important;
    top: -15% !important;
  }
  .el-main {
    margin-top: 0;
    margin-left: 5%;
  }
}

@media (max-width: 2500px) {
  .mainBox.shifted {
    margin-left: 0;
  }
  :deep(canvas) {
    left: 0 !important;
    top: -14% !important;
  }
  .el-main {
    margin-top: 0;
    margin-left: 5%;
  }
}
@media (max-width: 1920px) {
  .mainBox.shifted {
    margin-left:0;
  }
  :deep(canvas) {
    left: 5% !important;
    top: -12% !important;
  }
  .el-main {
    margin-top: 0;
    margin-left: 4%;
  }
}

@media (max-width: 1680px) {
  .mainBox.shifted {
    margin-left: -30%;
  }
  :deep(canvas) {
    left: -1% !important;
    top: -11% !important;
  }
  .el-main {
    margin-left: 3%;
  }
}
@media (max-width: 1600px) {
  .mainBox.shifted {
    margin-left: -30%;
  }
  :deep(canvas) {
    left: 0 !important;
    top: -11% !important;
  }

  .el-main {
    margin-left: 2%;
  }
}

@media (max-width: 1400px) {
  .mainBox.shifted {
    margin-left: -40%;
  }
  :deep(canvas) {
    left: -2% !important;
    top: -8% !important;
  }
  .el-main {
    margin-left: 3%;
  }
}

@media (max-width: 1366px) {
  .mainBox.shifted {
    margin-left: -43%;
  }
  :deep(canvas) {
    left: 0 !important;
    top: -7% !important;
  }
  .el-main {
    margin-left: 1%;
  }
}

@media (max-width: 1280px) {
  .mainBox.shifted {
    margin-left: -46%;
  }
  :deep(canvas) {
    left: -1% !important;
    top: -7% !important;
  }
  .el-main {
    margin-left: 0;
    width: 300px;
  }
}
@media (max-width: 1110px) {
  .mainBox.shifted {
    margin-left: -60%;
  }
  :deep(canvas) {
    left: 6% !important;
    top: -3% !important;
  }
  .el-main {
    margin-left: 4%;
    width: 300px;
  }
}
@media (max-width: 1024px) {
  .mainBox.shifted {
    margin-left: -65%
  }
  :deep(canvas) {
    left: 0 !important;
    top: -3% !important;
  }
  .el-main {
    margin-left: -10%;
    width: 300px;
  }
}
</style>
