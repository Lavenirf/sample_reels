<script setup>
/*
* 专业详情
* */
import {ref,onMounted} from "vue";
import ProfessionalOverview from "@/Components/InquireMajor/ProfessionalOverview.vue";
import RelatedEmployment from "@/Components/InquireMajor/RelatedEmployment.vue";
import {selectSpecialDetails} from "@/api/schoolSpecial.js";
import {useRoute} from "vue-router";
import router from "@/router/index.js";
import {useSpecialStore} from "@/store/useSpecialChatStore.js";
import { ArrowRight } from '@element-plus/icons-vue'
import {useCollectStore} from "@/store/useCollectStore.js";
import {addCollect, delCollect, getCollectList} from "@/api/collect.js";
import {ElLoading, ElMessage} from "element-plus";
import {useUserStore} from "@/store/useUserStore.js";
import {useDialogVisibleStore} from "@/store/useDialogVisibleStore.js";
const isCollect =ref(false)
const specialStore =useSpecialStore()
const collectStore =useCollectStore()
const userStore =useUserStore()
const specialCollect=ref([])
const route = useRoute()
const current = ref("1")
const specialData= ref({})
const disableStore =useDialogVisibleStore()
//请求专业数据
const getSpecialInfo=async ()=>{
  const res =await selectSpecialDetails(route.query.id)
  if (res.data!==null){
    //设置echarts数据到pinia
    specialStore.setAreas(res.data.areas)
    specialStore.setDistribution(res.data.distribution)
    specialStore.setPositions(res.data.positions)
    specialStore.setThisFreshGraduates(JSON.parse(res.data.thisFreshGraduates))
    specialStore.setOutFreshGraduates(JSON.parse(res.data.outFreshGraduates))
    //删除掉echarts中的数据 将基础数据设置到pinia
    delete res.data.areas
    delete res.data.distribution
    delete res.data.positions
    delete res.data.thisFreshGraduates
    delete res.data.outFreshGraduates
    specialStore.setSpecialDetail(res.data)
    specialData.value=res.data
  }
}
const handClickCollect= async ()=>{
  if (userStore.showLogin){
    if (isCollect.value){
      //调用删除收藏接口
      const res = await delCollect(specialData.value.collectId)
      if(res.code===200){
        ElMessage.success("取消收藏")
        isCollect.value =false
      }else {
        ElMessage.error("取消收藏失败")
      }
    }else {
      //调用添加接口
      const data = {
        "userId": userStore.getData.userId,
        "collectType": "1",
        "specialId": specialData.value.specialId
      }
      const res = await addCollect(data)
      if (res.code ===200){
        ElMessage.success("收藏成功")
        isCollect.value = true
      }else {
        ElMessage.error("收藏失败，请稍后重试！")
      }
    }
    const res =await getCollectList(userStore.getData.userId)
    //更新pinia
    collectStore.setSpecialCollectList(res.data.specialReduceVoList)
    await isThisCollect()
  }
  else {
    ElMessage.warning("请登录后进行操作！")
    disableStore.setDialogVisibleLogin(true)
  }
}
const isThisCollect = async ()=>{
  specialCollect.value = await collectStore.getSpecialCollectList
  //判断当专业是否在收藏中
  let thisCollect = specialCollect.value.find(s => s.specialId === specialData.value.specialId)
  if (thisCollect!==undefined){
    //在当前专业数据中设置收藏的id
    specialData.value.collectId = thisCollect.id
    //设置为红色心
    isCollect.value=true
  }
}
const handleConsult=(activeKey)=>{
  console.log(activeKey==='3')
  if (activeKey==='3'){
    router.push('/ai')
  }

}
onMounted(async ()=>{
  const loadingInstance = ElLoading.service({
    lock: true,
    text: 'Loading',
    background: 'rgba(0, 0, 0, 0.7)',
  })
  //判断路由中是否存在专业id
  if (route.query.id===undefined){
    await router.push("/inquireMajor")
  }else {
    await getSpecialInfo()
  }
  if (userStore.showLogin){
    //获取收藏数据
    const res =await getCollectList(userStore.getData.userId)
    //存储pinia
    collectStore.setSpecialCollectList(res.data.specialReduceVoList)
    await isThisCollect()
  }
  loadingInstance.close()
})
</script>

<template>
<el-container style="margin-left: 5%">
  <el-header class="header">
    <el-row class="headerBox">
      <el-col style="display: flex;justify-content: space-between">
        <div class="MajorTitle" >{{specialData.specialName}}</div>
        <div style="width:40px;margin-right: 0" @click="handClickCollect">
          <transition enter-active-class="animate__heartBeat">
          <el-link :underline="false" v-if="!isCollect">
            <div class="loseHeart"/>
          </el-link>
          <el-link :underline="false" v-else>
            <div class="redHeart"/>
          </el-link>
          </transition>
        </div>
      </el-col>
      <el-col>
        <el-breadcrumb :separator-icon="ArrowRight">
          <el-breadcrumb-item>{{ specialData.level1Name }}</el-breadcrumb-item>
          <el-breadcrumb-item>{{ specialData.type }}</el-breadcrumb-item>
          <el-breadcrumb-item>{{  specialData.typeDetail  }}</el-breadcrumb-item>
          <el-breadcrumb-item>{{ specialData.specialName }}</el-breadcrumb-item>
        </el-breadcrumb>
      </el-col>
    </el-row>
    <el-row justify="start" :gutter="10" class="sketch1">
      <el-col :span="24">
        <div class="box">
          <div style="display: flex">
            <div class="sketchIcon" style="background: url('/Icon/zy.png')20% 35% no-repeat;background-size: 30px;"></div>
            <div class="sketchTitle">专业代码</div>
          </div>
          <div class="commonSize1 common" style="margin-left: 18px">
            <p>{{specialData.code}}</p>
          </div>
        </div>
        <div class="box">
          <div style="display: flex">
            <div class="sketchIcon" style="background: url('/Icon/timeIcon.png') 50% 20% no-repeat;background-size: 33px;"></div>
            <div class="sketchTitle">修学年限</div>
          </div>
          <div class="commonSize2 common">
            <p>{{specialData.limitYear}}</p>
          </div>
        </div>
        <div class="box">
          <div style="display: flex">
            <div class="sketchIcon" style="background: url('/Icon/bsm.png') no-repeat;background-size: 40px;"></div>
            <div class="sketchTitle">授予学位</div>
          </div>
          <div class="commonSize2 common">
            <p>{{specialData.degree}}</p>
          </div>
        </div>
        <div class="box">
          <div style="display: flex">
            <div class="sketchIcon" style="background: url('/Icon/xz.png') 50% 30% no-repeat;background-size: 35px"></div>
            <div class="sketchTitle">平均年薪</div>
          </div>
          <div class="commonSize1 common">
            <p>{{specialData.avgSalary}}</p>
          </div>
        </div>
      </el-col>
    </el-row>
  </el-header>
  <el-main >
      <a-tabs v-model:activeKey="current"  @change="handleConsult" type="card" :animated="true">
        <a-tab-pane key="1" tab="专业概况">
          <ProfessionalOverview :specialData="specialData"/>
        </a-tab-pane>
        <a-tab-pane key="2" tab="相关就业">
          <RelatedEmployment/>
        </a-tab-pane>
        <a-tab-pane key="3"  tab="专业咨询" >
        </a-tab-pane>
      </a-tabs>
  </el-main>
</el-container>
</template>

<style lang="scss" scoped>
.header{
  width:85%;
  height: 250px;
  margin-left: 30px;
  .headerBox{
    height:100px;
    border-bottom: 1px solid #CCC;
    .loseHeart{
      width: 35px;
      height:35px;background: url('/Icon/hX.png') no-repeat;
      background-size: 35px;
    }
    .redHeart{
      width: 35px;
      height:35px;
      background: url('/Icon/redX.png') no-repeat;
      background-size: 35px;
    }
  }
  .sketch1{
    margin-top: 30px;
    font-size: 15px;
    .el-col{
      display: flex;
      justify-content: space-between;
      .box{
        height: 85px;
        width: 23%;
        background:#f8f8f8;
        border-radius: 10px;
        box-shadow: 0 2px 10px #e9e7ef;
        .sketchIcon{
          width:40px;
          height:40px;
          margin: 5px 5px;
        }
        .sketchTitle{
          line-height: 50px;
          color: #666666;
          font-size: 14px
        }
        .common{
          margin: 2px 11px;
          color: #666666;
        }
        .commonSize2{
          font-size: 16px
        }
        .commonSize1{
          font-size: 18px
        }
      }
    }
  }
}
.MajorTitle{
  font-size: 30px;
  font-weight: bold;
  height: 45px;
  width: 700px;
}
:deep(.a-tabs){
  width: 80% !important;
}
:deep(.ant-tabs-tab) {
  width: 150px !important;
  height: 55px !important;
  background: none !important;
  border: none !important;
  margin-left: 30px !important;
  border-radius: 0 !important;
}
:deep(.ant-tabs-tab-btn){
  margin: 0 auto !important;
}
:deep(.ant-tabs-tab-active)  {
  background-color:#1d39c4 !important;
  color: #fff !important;
}
:deep(.ant-tabs-tab-active .ant-tabs-tab-btn) {
  color: #fff !important;
}

</style>
