<script setup>
import {useUserStore} from "../../store/useUserStore.js";
import {useRouter} from "vue-router";
import {ArrowDown} from "@element-plus/icons-vue";
import axios from "axios";
import {ElMessage} from "element-plus";
const {changeUser,user,changeToken} =useUserStore()
const router =useRouter()
const handleExit=async ()=>{
  const res = await axios.get(`/api/users/logout`)
  const {code,msg} = res.data
  console.log(res.data)
  if (code ===200) {
    ElMessage.success("退出成功")

  }else {
    ElMessage.error(msg)
  }
  changeUser({})
  localStorage.removeItem("Authorization")
  router.push("/login")
  changeUser(false)
}
//public方式
// const circleUrl ="/avatar.png"
//assets方式
const circleUrl = new URL('../../assets/avatar.png',import.meta.url).href
</script>

<template>
  <el-header>
    <div>实验室管理系统</div>
    <div>
      <span style="line-height: 40px;margin-right: 10px">欢迎{{user.username}}回来</span>
      <el-dropdown>
    <span class="el-dropdown-link">
     <el-avatar :size="40" :src="circleUrl" />
    </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item>{{user.role.roleName}}</el-dropdown-item>
            <el-dropdown-item @click="handleExit">退出</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </el-header>
</template>

<style lang="scss" scoped>
.el-header{
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: #0d47a1;
  color: white;
}
</style>