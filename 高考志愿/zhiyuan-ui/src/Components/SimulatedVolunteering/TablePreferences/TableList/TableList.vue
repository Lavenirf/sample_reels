<script setup>
/*
* 志愿单列表
* */
import {onMounted, ref, toRef, computed, watchEffect} from "vue"
import {deleteVolunteer, getUserVolunteerFormDetails} from "@/api/volunteerForm.js";
import {useVolunteerStore} from "@/store/useVolunteerStore.js";
import {ArrowDown, Delete, EditPen} from "@element-plus/icons-vue";
import router from "@/router/index.js";
import {ElMessage} from 'element-plus'
import TreeVolunteer from "@/Components/SimulatedVolunteering/TablePreferences/TreeVolunteer/TreeVolunteer.vue";
import {useUserStore} from "@/store/useUserStore.js";
import {conversionRules} from "@/static/index.js";
import {selectPlanGap} from "@/api/schoolScopePlan.js";

const userStore = useUserStore()
const volunteerStore = useVolunteerStore()
const activeIndex = ref(null)
const volunteerFormId = ref()
const props = defineProps(["dataList", "item"])
const dataList = toRef(props.dataList)
const item = ref(0)
const emit = defineEmits(['changeDataList'])
const planList = ref([])
// 定义一个 Map 来缓存处理后的计划数据
const planMap = ref(new Map());
//志愿顺序
const findItem = computed(() => {
  return (order) => {
    if (Array.isArray(dataList.value)) {
      return dataList.value.find(item => item.order === order);
    }
    return null;
  };
})
//添加志愿
const handleAdd = () => {
  volunteerStore.setParagraph(props.item)
  router.push("/chooseInstitution")
}
//志愿顺序
const handleChange = (index) => {
  activeIndex.value = activeIndex.value === index ? null : index;
}
//删除志愿
const handleDel = async (id) => {
  const res = await deleteVolunteer(id)
  if (res.code === 200) {
    ElMessage.success("删除成功")
    const res = await getUserVolunteerFormDetails(volunteerFormId.value, item.value)
    if (res.code === 200) {
      dataList.value = res.data
      if (item.value === 1) {
        volunteerStore.setVolunteerDetails1(res.data)
      } else {
        volunteerStore.setVolunteerDetails2(res.data)
      }
    }
  } else {
    ElMessage.error("删除失败")
  }
}
onMounted(async () => {
  volunteerFormId.value = userStore.getData.volunteerFormId
})
//调用上上级的更新信息方法
const getVolunteerDataList = () => {
  emit("changeDataList")
}
//院校代码转换
const codeComputed = computed(() => (code) => {
  const prefix = code.toString().substring(0, 2);
  const suffix = code.toString().substring(2);
  const convertedPrefix = conversionRules[prefix] || prefix; // 使用 || 运算符提供默认值
  return `${convertedPrefix}${suffix}`;
})
// 预处理 planList 数据
const processPlanList = () => {
  planMap.value.clear();
  planList.value.forEach(planArray => {
    if (planArray.length === 0) return;

    const spname = planArray[0].spname;

    if (!planMap.value.has(spname)) {
      planMap.value.set(spname, []);
    }

    const currentPlans = planMap.value.get(spname);
    const seen = new Set();

    planArray.forEach(plan => {
      const key = `${plan.specialId}-${plan.year}-${plan.min}`;
      if (!seen.has(key)) {
        seen.add(key);
        currentPlans.push(plan);
      }
    });

    // 按年份降序排序
    currentPlans.sort((a, b) => b.year - a.year);

    // 保留前 3 个计划
    planMap.value.set(spname, currentPlans.slice(0, 3));
  });
}

// 获取计划
const getPlan = async (list) => {
  const newList = list.map(item => ({
    specialId: item.specialId,
    spname: item.specialName
  }));

  // 并行请求数据
  const [res] = await Promise.all([
    selectPlanGap(newList),
  ]);

  // 处理返回的数据
  planList.value = res.data;
  // 预处理 planList 数据
  processPlanList();
};

//获取当前计划
// 获取当前计划的函数，不再使用 computed
const planListFilter = (item) => {
  return planMap.value.get(item) || [];
}
//监听志愿单信息变化
watchEffect(async () => {
  dataList.value = props.dataList
  item.value = props.item
  if (dataList.value.length !== 0) {
    await getPlan(dataList.value)
  }
})
</script>

<template>
  <div>
    <div v-for="index in 96" :key="index" class="volunteer-box">
      <div class="box">
        <div style="width: 80%">
          <template v-if="findItem(index)">
            <div class="contentY">
              <div class="probability">{{ findItem(index).glRatio }}%</div>
              <div style="margin: auto 20px;width: 100% ">
                <span style="font-size: 20px;color: #000"> {{ findItem(index).schoolName }}</span>
                <ul class="collegeOverview">
                  <li style="margin-left: 0">{{ findItem(index).cityName }}</li>
                  <li>{{ findItem(index).typeName }}</li>
                  <li>{{ findItem(index).natureName }}</li>
                  <li v-if="findItem(index).belong">{{ findItem(index).belong }}</li>
                  <li style="line-height: 19px" v-if="findItem(index).f211===1">211</li>
                  <li style="line-height: 19px" v-if="findItem(index).f985===1">985</li>
                  <li v-if="findItem(index).tagName">{{ findItem(index).tagName }}</li>
                </ul>
                <span class="specialName">{{ findItem(index).specialName }}</span>
                <ul class="detail">
                  <li style="margin-left: 0">
                    <span>院校代码</span>
                    <span>{{ codeComputed(findItem(index).codeEnroll) }}</span>
                  </li>
                  <li>
                    <span>学制</span>
                    <span>{{ findItem(index).limitYear }}</span>
                  </li>
                  <li>
                    <span>专业限选</span>
                    <span>{{ findItem(index).selAdv }}</span>
                  </li>
                </ul>
                <ul class="planBox">
                  <template v-if="planList.length!==0">
                    <li v-for="plan in planListFilter(findItem(index).specialName)" :key="plan.mcid">
                      <span style="width: 8%">{{ plan.year }}</span>
                      <span style="width: 18%;">
                  <span>招生人数</span>
                  <span>{{ plan.num }}名</span>
                </span>
                      <span style="width: 18%;">
                  <span>最低分</span>
                  <span>{{ plan.min }}分</span>
                </span>
                      <span style="width: 22%;">
                  <span>最低位次</span>
                  <span>{{ plan.minSection }}名</span>
                </span>
                      <span style="width: 30%;" v-if="plan.gap>0">
                  <span>比我的名次靠前</span>
                  <span>{{ plan.gap }}名</span>
                </span>
                      <span style="width: 30%;" v-else>
                  <span>比我的名次靠后</span>
                  <span>{{ Math.abs(plan.gap) }}名</span>
                </span>
                    </li>
                  </template>
                </ul>
              </div>
            </div>
          </template>
          <template v-else>
            <div class="contentNo">
              志愿{{ index }}
            </div>
          </template>
        </div>
        <div style="margin: 25px 20px 0 0; position: relative;">
          <template v-if="findItem(index)">
            <div class="addY" @click="() => handleChange(index)">
              <div>志愿{{ index }}</div>
              <div>
                <el-icon>
                  <ArrowDown/>
                </el-icon>
              </div>
            </div>
            <div v-show="activeIndex === index" class="floating-box">
              <div class="sj"></div>
              <TreeVolunteer :item="item" :volunteerFormId="volunteerFormId" @changeDataList="getVolunteerDataList"/>
            </div>
            <div class="exitDel">
              <el-icon size="21" color="#95989e">
                <EditPen/>
              </el-icon>
              <el-popconfirm
                  width="220"
                  confirm-button-text="确定"
                  cancel-button-text="取消"
                  title="您确定删除该志愿吗！"
                  @confirm="handleDel(findItem(index).id)"
              >
                <template #reference>
                  <el-icon size="21" color="#95989e">
                    <Delete/>
                  </el-icon>
                </template>
              </el-popconfirm>

            </div>
          </template>
          <template v-else>
            <div class="addN" @click="handleAdd">添加</div>
          </template>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.box {
  width: 100%;
  box-shadow: 0 2px 2px 4px rgba(0, 0, 0, 0.01), 0 4px 2px rgba(0, 0, 0, 0.01);
  background: #fff;
  margin-top: 20px;
  display: flex;
  justify-content: space-between;
  border-radius: 8px;

  .contentY {
    width: auto;
    height: auto;
    padding: 20px 0;
    margin: auto 20px;
    display: flex;

    .probability {
      padding-top: 30px;
      font-size: 18px;
      font-weight: bold;
      color: #ff8324;
    }

    .collegeOverview {
      display: flex;
      justify-content: start;
      color: #595959;
      margin-top: 5px;
      font-size: 13px;

      li {
        margin-left: 13px;
      }
    }

    .specialName {
      display: inline-block;
      margin-top: 5px;
      font-size: 18px
    }

    .detail {
      display: flex;
      justify-content: start;
      margin-top: 5px;

      li {
        margin-left: 15px;
        margin-top: 5px;
        font-size: 14px;
        color: #595959;

        span:first-child {
          padding-right: 3px;
          color: black;
        }

        span:last-child {
          line-height: 20px;
          display: inline-block;
          height: 20px;
        }
      }
    }

    .planBox {
      font-size: 13px;
      color: #696969;
      margin-top: 15px;

      li {
        padding-right: 5px;
        display: flex;
        margin-top: 5px;

        span span:last-child {
          color: #000;
          padding-left: 5px;
        }
      }
    }
  }

  .contentNo {
    line-height: 100px;
    height: 100px;
    margin: auto 20px;
  }

  .addY {
    width: 120px;
    height: 40px;
    border: 1px solid #ff8324;
    text-align: center;
    line-height: 40px;
    border-radius: 10px;
    display: flex;
    justify-content: space-between;
    background: #ff6600;
    margin-right: 30px;
    cursor: pointer;

    div:first-child {
      color: #fff;
      font-size: 15px;
      margin-left: 20px
    }

    div:last-child {
      margin-right: 20px;
      margin-top: 2px
    }
  }

  .exitDel {
    display: flex;
    width: 120px;
    height: 40px;
    justify-content: center;
    margin-top: 15px;

    .el-icon {
      margin: 0 10px;
      cursor: pointer;
    }
  }

  .addN {
    width: 120px;
    height: 40px;
    border: 1px solid #ff8324;
    text-align: center;
    line-height: 40px;
    border-radius: 10px;
    margin-right: 30px;
    font-size: 15px;
    font-weight: 545;
    color: #ff8324;
    cursor: pointer;
  }

  .floating-box {
    position: absolute;
    top: 50px; /* 调整此值以将浮动框放置在addY的下面 */
    right: 15px;
    z-index: 100;
    width: 150px;
    height: 300px;
    background: #fff;
    border-radius: 5px;
    border: 1px solid #eeeeee;
    box-shadow: 0 2px 20px #eee;

    .sj {
      float: left;
      margin-top: -20px;
      width: 0;
      margin-left: 65px;
      height: 0;
      border-bottom: 10px solid #eeeeee;
      border-right: 10px solid transparent;
      border-left: 10px solid transparent;
      border-top: 10px solid transparent;
    }
  }
}
</style>
