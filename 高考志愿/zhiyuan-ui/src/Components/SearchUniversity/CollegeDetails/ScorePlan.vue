<script setup>
import {ref, onMounted, toRef} from 'vue';
import {LaySelect, LaySelectOption} from "@layui/layui-vue";
import {region_NotAll} from "@/static/index.js";
import {selectEnrollmentPlan, selectMscores, selectSchoolScope} from "@/api/schoolScopePlan.js";
/*
* 分数计划
* */
const props = defineProps(["schoolId"])
const scopeYear = ref('2024')
const scopeCity = ref("山东")
const specialYear = ref('2024')
const specialCity = ref("山东")
const planYear = ref('2024')
const planCity = ref("山东")
const type = ref("综合")
const years = ["2024", "2023", "2022", "2021", "2020", "2019"]
const schoolId = toRef(props, 'schoolId')
const scopeLine = ref([])
const specialList = ref({rows: [], total: 0})
const specialPage = ref(1)
const planList = ref({rows: [], total: 0})
const planPage = ref(1)
const getScopeLine = async () => {
  let query = {
    schoolId: schoolId.value,
    year: scopeYear.value,
    localProvinceName: scopeCity.value,
    localTypeName: type.value,
  }
  const res = await selectSchoolScope(query)
  scopeLine.value = res.rows || [] // 如果没有数据，确保scopeLine是空数组
}

const getSpecialLine = async () => {
  let query = {
    pageNum: planPage.value,
    schoolId: schoolId.value,
    year: specialYear.value,
    localProvinceName: specialCity.value,
    localTypeName: type.value,
  }
  const res = await selectMscores(query);
  specialList.value = res || {rows: [], total: 0}  // 确保specialList结构一致，即使没有数据
}
const getPlan = async () => {
  let query = {
    pageNum: specialPage.value,
    schoolId: schoolId.value,
    year: planYear.value,
    area: planCity.value,
  }
  const res = await selectEnrollmentPlan(query);
  planList.value = res.data || {rows: [], total: 0}  // 确保specialList结构一致，即使没有数据
}

onMounted(async () => {
  await getScopeLine()
  await getSpecialLine()
  await getPlan()
})
</script>

<template>
  <div class="box">
    <div class="title">
      <span>院校分数线</span>
      <div class="selectBox">
        <lay-select class="select" v-model="scopeCity" placeholder="请选择" @change="getScopeLine">
          <lay-select-option class="option" v-for="item in region_NotAll" :value="item"
                             :label="item"></lay-select-option>
        </lay-select>
        <lay-select class="select" v-model="scopeYear" placeholder="请选择" @change="getScopeLine">
          <lay-select-option class="option" v-for="item in years" :value="item" :label="item"></lay-select-option>
        </lay-select>
        <lay-select class="select" v-model="type" placeholder="请选择" @change="getScopeLine">
          <lay-select-option class="option" value="综合" label="综合"></lay-select-option>
        </lay-select>
      </div>
    </div>
    <ul class="ul1">
      <li>
        <span>年份</span>
        <span>录取批次</span>
        <span>招生类型</span>
        <span>最低分/最低位次</span>
        <span>省控线</span>
      </li>
      <template v-if="scopeLine.length">
        <li v-for="item in scopeLine" :key="item.id">
          <span style="width: 20%">{{ item.year }}</span>
          <span style="width: 20%">{{ item.localBatchName }}</span>
          <span style="width: 20%">{{ item.zslxName }}</span>
          <span style="width: 20%">{{ item.min }}/{{ item.minSection }}</span>
          <span style="width: 20%;text-align: right">{{ item.proscore }}</span>
        </li>
      </template>
      <template v-else>
        <div class="noList">暂无数据</div>
      </template>
    </ul>
  </div>
  <div class="box">
    <div class="title">
      <span>专业分数线</span>
      <div class="selectBox">
        <lay-select class="select" v-model="specialCity" placeholder="请选择" @change="getSpecialLine">
          <lay-select-option class="option" v-for="item in region_NotAll" :value="item"
                             :label="item"></lay-select-option>
        </lay-select>
        <lay-select class="select" v-model="specialYear" placeholder="请选择" @change="getSpecialLine">
          <lay-select-option class="option" v-for="item in years" :value="item" :label="item"></lay-select-option>
        </lay-select>
        <lay-select class="select" v-model="type" placeholder="请选择" @change="getSpecialLine">
          <lay-select-option class="option" value="综合" label="综合"></lay-select-option>
        </lay-select>
      </div>
    </div>
    <ul class="ul2">
      <li>
        <span>专业名称</span>
        <span>录取批次</span>
        <span>最低分/最低位次</span>
        <span>选科要求</span>
      </li>
      <template v-if="specialList.rows.length">
        <li v-for="item in specialList.rows" :key="item.mcid">
          <span>{{ item.spname }}</span>
          <span>{{ item.localBatchName }}</span>
          <span>{{ item.min }}/{{ item.minSection }}</span>
          <span>{{ item.sgInfo||item.gpInfo }}</span>
        </li>
      </template>
      <template v-else>
        <div class="noList">暂无数据</div>
      </template>
    </ul>
    <a-pagination v-show="specialList.total" class="page" v-model:current="specialPage" :total="specialList.total"
                  @change="getSpecialLine" show-less-items/>
  </div>
  <div class="box">
    <div class="title">
      <span>招生计划</span>
      <div class="selectBox">
        <lay-select class="select" v-model="planCity" placeholder="请选择" @change="getPlan">
          <lay-select-option class="option" v-for="item in region_NotAll" :value="item"
                             :label="item"></lay-select-option>
        </lay-select>
        <lay-select class="select" v-model="planYear" placeholder="请选择" @change="getPlan">
          <lay-select-option class="option" v-for="item in years" :value="item" :label="item"></lay-select-option>
        </lay-select>
        <lay-select class="select" v-model="type" placeholder="请选择" @change="getPlan">
          <lay-select-option class="option" value="综合" label="综合"></lay-select-option>
        </lay-select>
      </div>
    </div>
    <ul class="ul2">
      <li>
        <span>专业名称</span>
        <span>计划招生</span>
        <span>学制</span>
        <span>学费</span>
        <span>选科要求</span>
      </li>
      <template v-if="planList.rows.length">
        <li v-for="item in planList.rows">
          <span>{{ item.spname }}</span>
          <span>{{ item.num }}</span>
          <span>{{ item.length }}</span>
          <span>{{ item.tuition }}/年</span>
          <span>{{ item.spInfo }}</span>
        </li>
      </template>
      <template v-else>
        <div class="noList">暂无数据</div>
      </template>
    </ul>
    <a-pagination v-show="planList.total" class="page" v-model:current="planPage" :total="planList.total"
                  @change="getPlan" show-less-items/>
  </div>
</template>

<style scoped lang="scss">
.box {
  width: 80%;
  height: auto;
  padding: 0 20px 20px;
  background: -webkit-linear-gradient(#FAFAFA 60%, #F6F6F6 100%);
  border-radius: 10px;

  .title {
    width: 100%;
    margin-top: 20px;
    font-size: 18px;
    font-weight: bold;
    padding-top: 15px;
    display: flex;
    justify-content: space-between;

    .selectBox {
      display: flex;

      .select {
        margin-left: 10px;
        width: 80px;
        font-weight: normal;
        font-size: 13px;
        height: 40px;

        .option {
          max-height: 160px;
        }
      }
    }
  }

  .ul1, .ul2 {
    li:first-child {
      display: flex;
      margin-top: 20px;
      color: #ababab;
    }

    li {
      display: flex;
      margin-top: 25px;
      font-size: 15px;

      span:last-child {
        text-align: right;
      }

      span {
        width: 25%;
      }
    }
  }

  .page {
    margin-top: 40px;
    text-align: center;
  }
}

.noList {
  margin: 20px auto 10px;
  text-align: center;
  color: #ababab;
  font-size: 13px;
}

:deep(.layui-input[size=md]) {
  height: 30px;
}
</style>
