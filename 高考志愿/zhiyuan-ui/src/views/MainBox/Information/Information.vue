<script setup>
import {ref,onMounted} from 'vue'
import {selectSchoolNews} from "@/api/schoolNew.js";
const current1 = ref(1)
const dataList =ref([])
const handleHref =(item)=>{
  window.open(item.newsUrl)
}
onMounted(async ()=>{
  const res = await selectSchoolNews()
  dataList.value=res.data
})
</script>

<template>
<div class="box">
  <span class="title">政策资讯</span>
  <ul>
    <li v-for="item in dataList.rows" @click="handleHref(item)">
      <div class="title">{{item.newsContent}}</div>
      <div class="content">
        <span>{{item.newsContent}}</span>
      </div>
      <div class="publish">
        <span>{{ item.publishPlace }}</span>
        <span style="padding-left: 40px">{{ item.publishDate }}</span>
      </div>
    </li>
  </ul>
  <a-pagination style="text-align: center;margin-top: 20px" v-model:current="current1"  :total="dataList.total"  />
</div>
</template>

<style scoped lang="scss">
.box{
  height: 1600px;
  width: 80%;
  .title{
    font-size: 20px;
    font-weight: bold;
  }
  ul{
    margin-top: 20px;
    background: #fff;
    font-size: 17px;
    border-radius: 10px;
    li{
      height: 150px;
      cursor: pointer;
      border-bottom: 1px #999 solid;
      padding: 20px;
      .title{
        white-space:nowrap;
        text-overflow:ellipsis;
        overflow:hidden;
      }
      .content{
        padding-top: 10px;
        font-size: 14px;
        color: #595959;
        letter-spacing:2px;
        line-height: 20px;
      }
      .publish{
        font-size: 13px;
        margin-top: 15px;
        color: #999
      }
    }
  }
}
</style>
