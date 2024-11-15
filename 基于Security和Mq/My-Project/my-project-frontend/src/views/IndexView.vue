<script setup>
import {logOut} from "@/net/index.js";
import router from "@/router/index.js";

function userLogOut(){
  logOut(()=>router.push('/'))
}
</script>

<template>
  <div>
    <el-button @click="userLogOut">退出登录</el-button>
  </div>
</template>

<style scoped>

</style>