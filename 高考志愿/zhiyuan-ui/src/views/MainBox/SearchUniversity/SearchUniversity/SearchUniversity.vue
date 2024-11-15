<script setup>
/*
* 查询大学页面
* */
import {ref} from 'vue'
import CollegeLibrary from "@/Components/SearchUniversity/CollegeInquiry/CollegeLibrary.vue";
import PointsLeaderboard from "@/Components/SearchUniversity/CollegeInquiry/PointsLeaderboard.vue";


const current1 = ref('1')

</script>

<template>
  <div class="card-container">
    <a-tabs v-model="current1" type="card" :animated="true">
      <a-tab-pane key="1" tab="院校库" style="width: 100%;height: 800px">
        <CollegeLibrary style="margin: 0 auto"/>
      </a-tab-pane>
      <a-tab-pane key="2" tab="分数榜">
        <PointsLeaderboard/>
      </a-tab-pane>
    </a-tabs>
  </div>
</template>

<style scoped>

:deep(.ant-tabs-tab) {
  width: 110px !important;
  background: none !important;
  border: none !important;
  margin-left: 45px !important;
}

:deep(.ant-tabs-tab-btn) {
  margin: 0 auto !important;
}

:deep(.ant-tabs-tab-active) {
  background-color: #1d39c4 !important;
  color: #fff !important;
}

:deep(.ant-tabs-tab-active .ant-tabs-tab-btn) {
  color: #fff !important;
}

</style>
