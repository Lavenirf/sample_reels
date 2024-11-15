<script setup >
import {ref,onMounted} from "vue";
import {selectSchoolNews} from "@/api/schoolNew.js";
import MessList from "@/Components/home/MessList.vue";
const activeKey = ref('1')
const sdDataList=ref([])
const gxDataList=ref([])
const zyDataList=ref([])
const allDataList =ref([])
onMounted(async ()=>{
  let query={
    publishPlace:"山东"
  }
  let query2={
    newsTitle:"投档线"
  }
  let query3={
    newsTitle:"填报"
  }
  const res1 = await selectSchoolNews(query)
  const all = await selectSchoolNews()
  const res2 = await selectSchoolNews(query2)
  const res3 = await selectSchoolNews(query3)
  sdDataList.value=res1.data.rows.slice(0,5)
  gxDataList.value=res2.data.rows.slice(0,5)
  zyDataList.value=res3.data.rows.slice(0,5)
  allDataList.value=all.data.rows.slice(0,5)
})
</script>

<template>
  <el-col :span="18">
    <div class="leftBox">
      <a-tabs v-model:activeKey="activeKey">
        <a-tab-pane key="1" tab="山东">
          <MessList :data="sdDataList"/>
        </a-tab-pane>
        <a-tab-pane key="2" tab="高考推荐" force-render>
          <MessList :data="allDataList"/>
        </a-tab-pane>
        <a-tab-pane key="3" tab="高校资讯">
         <MessList :data="gxDataList"/>
        </a-tab-pane>
        <a-tab-pane key="4" tab="志愿填报">
          <MessList :data="zyDataList"/>
        </a-tab-pane>
      </a-tabs>
    </div>
  </el-col>
  <el-col :span="6">
    <div style="width: 100%;height:100%;background: #f5f8ff;  border-radius: 5px;">
    </div>
  </el-col>
</template>

<style scoped lang="scss">
.leftBox{
  width: 100%;
  height:100%;
  background: #f5f8ff;
  border-radius: 5px;
}
:deep(.ant-tabs-tab) {
  width: 100px;
}

:deep(.ant-tabs-tab-btn) {
  margin: 0 auto;
}

:deep(.ant-tabs-nav-list) {
  margin-top: 5px;
}
</style>
