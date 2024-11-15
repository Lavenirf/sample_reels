<script setup>
import {onMounted, ref} from "vue";
import {getUserVolunteerForm} from "@/api/volunteerForm.js";
import {useUserStore} from "@/store/useUserStore.js";
import {useVolunteerStore} from "@/store/useVolunteerStore.js"
import {getUserInfo, updateUserInfo} from "@/api/userDetails.js";
import {ElMessage} from "element-plus";
/*
* 志愿列表
* */
const userStore = useUserStore()
const volunteerStore = useVolunteerStore()
const userId = ref()
const dataList = ref([])
const voId = ref()

//设置正在使用的志愿表到pinia中
const setVoid=()=>{
  voId.value =userStore.getData.volunteerFormId
  if (voId.value!==undefined){
    volunteerStore.setVolunteerForm(dataList.value.filter(v=> v.id===voId.value))
  }
}
//格式化时间
const formatDate=(isoString)=>{
  // 创建Date对象
  const date = new Date(isoString);
  // 获取年、月、日、小时、分钟和秒
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0'); // 月份从0开始，因此加1
  const day = String(date.getDate()).padStart(2, '0');
  const hours = String(date.getHours()).padStart(2, '0');
  const minutes = String(date.getMinutes()).padStart(2, '0');
  const seconds = String(date.getSeconds()).padStart(2, '0');
  // 返回格式化后的日期时间字符串
  return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
}
//使用志愿表
const useVolunteer = async (value)=>{
  let data={
    userId:userId.value,
    volunteerFormId: value.id
  }
 const res = await updateUserInfo(data)
  if (res.code ===200){
    await getUserData()
    volunteerStore.setVolunteerForm(value)
    volunteerStore.setVolunteerDetails1({})
    volunteerStore.setVolunteerDetails2({})
    setVoid()
    ElMessage.success("使用成功")
  }
}
//获取用户数据
const getUserData = async () => {
  const res = await getUserInfo()
  userStore.setData(res.data)
}
onMounted(async () => {
 await getVolunteerFormList()
  setVoid()
})
const getVolunteerFormList=async ()=>{
  userId.value=userStore.getData.userId
  //根据用户id查询用户的志愿表信息
  const res = await getUserVolunteerForm(userId.value)
  dataList.value = res.data
  //格式化时间
  dataList.value.forEach(v=>{
    v.createTime=formatDate(v.createTime)
  })
}
</script>

<template>
  <div style="width: 100%;">
    <el-header>
      <div style="width: 100%;height: 45px;border-radius: 8px;background: #fff;  box-shadow: 0 2px 20px #fff;">
        <div style="margin-left: 20px;font-size: 18px;font-weight: bold;padding-top: 10px">我的志愿表</div>
      </div>
    </el-header>
    <el-main style="margin-top: -20px">
      <div class="table">
        <el-table :data="dataList" table-layou="flex" :border="true" :cell-style="{textAlign: 'center'}"
                  :header-cell-style="{'text-align': 'center'}" empty-text="无志愿表" >
          <el-table-column prop="name" label="志愿表名称"/>
          <el-table-column prop="result" label="使用成绩"/>
          <el-table-column prop="createTime" label="生成时间"/>
          <el-table-column label="志愿表状态">
            <div class="buttonGroup">
              <span v-if="1">使用</span>
              <span v-else>未使用</span>
            </div>
          </el-table-column>
          <el-table-column label="操作">
            <template #default="scope">
              <div class="buttonGroup">
                <el-button type="primary" text @click="useVolunteer(scope.row)" :disabled="scope.row.id===voId">使用</el-button>
                <el-button type="success" text>修改</el-button>
                <el-button type="danger" text>删除</el-button>
              </div>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </el-main>
  </div>
</template>

<style scoped>
.buttonGroup{
  display: flex;
  justify-content: space-around;
  .el-button{
    margin: 0 auto 0;
  }
}
:deep(.el-table){
  border: none;
}
.table{
  box-shadow: 0 2px 20px #fff;
}
</style>