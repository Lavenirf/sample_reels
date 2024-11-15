<script setup>
import { ArrowDown } from "@element-plus/icons-vue";
import { useUserStore } from "@/store/useUserStore.js";
import { onMounted, ref, toRef, computed, watch } from "vue";
import { useVolunteerStore } from "@/store/useVolunteerStore.js";
import { getSchoolByIds } from "@/api/school.js";
import { deleteVolunteer, getUserVolunteerFormDetails, insertVolunteerDetails } from "@/api/volunteerForm.js";
import {ElLoading, ElMessage} from "element-plus";
import { conversionRules } from "@/static/index.js";
import { selectPlanGap } from "@/api/schoolScopePlan.js";

const props = defineProps(['data'])
const userStore = useUserStore();
const info = userStore.getData;
const dataList = ref([]);
const schoolList = ref([]);
const allList = toRef(props.data);
const volunteerStore = useVolunteerStore()
const volunteerDetail = ref([])
const planList = ref([])
const newPlan = ref([])

// 定义一个 Map 来缓存处理后的计划数据
const planMap = ref(new Map());

// 对本页的一组数据去重
const removeDuplicate = (arr) => {
  const newArr = []
  arr.forEach((item) => {
    if (!newArr.includes(item.schoolId)) {
      newArr.push(item.schoolId)
    }
  })
  return newArr;
}

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
  const nList = list.map(item => ({
    specialId: item.specialId,
    spname: item.specialName,
    year: "2024"
  }));

  // 并行请求数据
  const [res, nRes] = await Promise.all([
    selectPlanGap(newList),
    selectPlanGap(nList)
  ]);

  // 处理返回的数据
  planList.value = res.data;
  newPlan.value = nRes.data;

  // 预处理 planList 数据
  processPlanList();
};

// 获取推荐专业院校的基本信息
const getDetails = async (page, pageSize) => {
  const loadingInstance = ElLoading.service({
    lock: true,
    text: 'Loading',
    background: 'rgba(0, 0, 0, 0.7)',
  })
  // 获取分页数据
  const paginatedData = allList.value.slice(page * pageSize - pageSize, page * pageSize);

  // 并行处理数据和学校信息请求
  const [planResult, schoolResult] = await Promise.all([
    getPlan(paginatedData),
    getSchoolByIds(removeDuplicate(paginatedData))
  ]);

  // 更新数据列表和学校列表
  dataList.value = paginatedData;
  schoolList.value = schoolResult.data;
  loadingInstance.close()
}

// 获取志愿单的详细信息
const getVolunteerDataList = async () => {
  if (info.volunteerFormId !== null && info.volunteerFormId !== '') {
    const res1 = await getUserVolunteerFormDetails(info.volunteerFormId, volunteerStore.getParagraph)
    if (res1.code === 200) {
      volunteerStore.setVolunteerDetails1(res1.data)
      volunteerDetail.value = res1.data
    }
  }
}

// 添加志愿
const handleAdd = async (item) => {
  if (info.volunteerFormId !== null) {
    let data = {
      mcid: item.mcid,
      specialId: item.specialId,
      schoolId: item.schoolId,
      volunteerFromId: info.volunteerFormId,
      paragraph: volunteerStore.getParagraph,
      glRatio: Math.round(item.glRatio * 100)
    }
    const res = await insertVolunteerDetails(data)
    if (res.code === 200) {
      ElMessage.success("添加成功")
    } else {
      ElMessage.error("请稍后重试！")
    }
    await getVolunteerDataList()
  } else {
    ElMessage.error("请新建志愿表后重试！")
  }
}

// 分页
const handlePage = (currentPage, pageSize) => {
  getDetails(currentPage, pageSize)
}

// 获取基本信息
const computedSchoolInfo = (id) => {
  const school = schoolList.value.find((s) => s.schoolId === id)
  if (!school) return {}
  return {
    name: school.name,
    province: school.provinceName,
    type: school.typeName.slice(0, 2),
    nature: school.natureName,
    code: school.codeEnroll,
  }
}

// 是否已经被添加
const computedAdd = (item) => {
  const match = volunteerDetail.value.find(v => v.schoolId === item.schoolId && v.specialId === item.specialId);
  return match ? match.order : null;
}

// 删除志愿
const handleDel = async (item) => {
  const match = volunteerDetail.value.find(v => v.schoolId === item.schoolId && v.specialId === item.specialId);
  if (match.id !== null) {
    const res = await deleteVolunteer(match.id)
    if (res.code === 200) {
      await getVolunteerDataList()
    } else {
      ElMessage.error("删除失败")
    }
  }
}

// 院校代码转换
const codeComputed = computed(() => (code) => {
  if (code) {
    const prefix = code.toString().substring(0, 2);
    const suffix = code.toString().substring(2);
    const convertedPrefix = conversionRules[prefix] || prefix; // 使用 || 运算符提供默认值
    return `${convertedPrefix}${suffix}`;
  }
})

// 获取当前计划的函数，不再使用 computed
const planListFilter = (item) => {
  return planMap.value.get(item) || [];
}

// 获取当前 2024 计划
const newPlanListFilter = computed(() => (item) => {
  if (item !== undefined) {
    return newPlan.value[item] ? newPlan.value[item] : []
  }
})

// 监听 planList 的变化，重新处理 planMap
watch(planList, () => {
  processPlanList();
})

// 监听 props.data 的变化
watch(() => props.data, () => {
  if (props.data.length !== 0) {
    allList.value = props.data
    getDetails(1, 10)
  } else {
    allList.value = []
  }
})

onMounted(async () => {
  allList.value = props.data
  await getDetails(1, 10)
  await getVolunteerDataList()
})
</script>

<template>
  <div>
    <template v-if="allList.length !== 0">
      <div class="item" v-for="(item, index) in dataList" :key="index">
        <Transition>
          <div class="badgeBox">
            <div class="icon">
              <img :src="`/schoolLogo/${item.schoolId}.jpg`" alt="">
            </div>
            <div v-if="computedSchoolInfo(item.schoolId).name" class="probabilityBox">
              <span>{{ Math.round(item.glRatio * 100) }}%</span>
            </div>
          </div>
        </Transition>
        <Transition>
          <div v-if="planList.length !== 0" class="contentBox">
            <div class="title"><span>{{ computedSchoolInfo(item.schoolId).name }}</span></div>
            <ul class="type">
              <li>{{ computedSchoolInfo(item.schoolId).province }}</li>
              <li>{{ computedSchoolInfo(item.schoolId).type }}</li>
              <li>{{ computedSchoolInfo(item.schoolId).nature }}</li>
            </ul>
            <div class="specialBox">
              <span class="name">{{ item.specialName }}</span>
            </div>
            <ul class="messageBox">
              <li>
                <span>2024 招生计划</span>
                <span v-if="newPlanListFilter(index)">{{ newPlanListFilter(index).num }}</span>
              </li>
              <li>
                <span>院校代码</span>
                <span>{{ codeComputed(computedSchoolInfo(item.schoolId).code) }}</span>
              </li>
              <li>
                <span>学费/学制</span>
                <span v-if="newPlanListFilter(index)">{{
                    newPlanListFilter(item.specialName).tuition
                  }}/{{ newPlanListFilter(item.specialName).length }}</span>
              </li>
              <li>
                <span>2024年选科</span>
                <span v-if="newPlanListFilter(index)">{{ newPlanListFilter(item.specialName).spInfo }}</span>
              </li>
            </ul>
            <ul class="planBox">
              <template v-if="planList.length !== 0">
                <li v-for="plan in planListFilter(item.specialName)" :key="plan.mcid">
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
                  <span style="width: 30%;" v-if="plan.gap > 0">
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
        </Transition>
        <Transition>
          <div class="btnBox">
            <div class="btn">
              <div class="order" @click="handleDel(item)" v-if="computedAdd(item)">
                <div>志愿{{ computedAdd(item) }}</div>
              </div>
              <div class="add" v-else @click="handleAdd(item)">
                <div>填 报</div>
              </div>
            </div>
            <div class="other">
              <span>其他专业</span>
              <el-icon style="margin-left: 10px">
                <ArrowDown/>
              </el-icon>
            </div>
          </div>
        </Transition>
      </div>
      <div style="display: flex; justify-content: center; margin-top: 25px">
        <el-pagination style="margin: 0 auto" background layout="prev, pager, next" :total="allList.length"
                       @change="handlePage"/>
      </div>
    </template>
    <template v-else>
      <div style="text-align: center; margin-top: 30px;"><span>暂无数据</span></div>
    </template>
  </div>
</template>

<style scoped lang="scss">
.item {
  height: 240px;
  box-shadow: 0 2px 10px #e9e7ef;
  display: flex;
  justify-content: space-between;

  .badgeBox {
    width: 10%;

    .icon {
      width: 100%;
      height: 50px;
      text-align: center;
      margin-top: 30px;
      border-radius: 5px;

      img {
        width: 50px;
      }
    }

    .probabilityBox {
      text-align: center;
      padding-top: 10px;
      font-size: 15px;
    }
  }

  .contentBox {
    width: 75%;

    .title {
      margin-top: 25px;
      font-size: 17px;
      font-weight: bold;
    }

    .type {
      display: flex;
      font-size: 13px;
      color: #595959;
      margin-top: 3px;

      li {
        padding-right: 5px;
      }
    }
  }

  .specialBox {
    margin-top: 5px;
    font-size: 15px;

    .name {
      font-weight: bold;
    }

    .fit {
      color: #595959;
      font-size: 14px;
    }
  }

  .messageBox {
    display: flex;
    font-size: 13px;
    color: #696969;
    margin-top: 3px;

    li {
      padding-right: 5px;

      span:last-child {
        padding-left: 5px;
        font-weight: bold;
        color: #000;
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

  .btnBox {
    width: 15%;
    margin-top: 35px;

    .btn {
      .addY {
        font-size: 16px;
        color: #fff;
        width: 120px;
        height: 40px;
        border: 1px solid #ff8324;
        text-align: center;
        line-height: 40px;
        border-radius: 10px;
        background: #ff6600;
        margin-right: 30px;
        cursor: pointer;
        font-weight: bold;
      }

      .order {
        font-size: 16px;
        color: #fff;
        background: #ff6600;
        width: 120px;
        height: 40px;
        border: 1px solid #ff8324;
        text-align: center;
        line-height: 40px;
        border-radius: 10px;
        margin-right: 30px;
        cursor: pointer;
        font-weight: bold;
      }

      .add {
        font-size: 16px;
        color: #ff6600;
        width: 120px;
        height: 40px;
        border: 1px solid #ff8324;
        text-align: center;
        line-height: 40px;
        border-radius: 10px;
        margin-right: 30px;
        cursor: pointer;
        font-weight: bold;
      }
    }
  }

  .other {
    width: 120px;
    text-align: center;
    margin-top: 10px;
    color: #ff6600;
    cursor: pointer;
  }
}

.v-enter-active,
.v-leave-active {
  transition: opacity 1s ease;
}

.v-enter-from,
.v-leave-to {
  opacity: 0;
}
</style>
