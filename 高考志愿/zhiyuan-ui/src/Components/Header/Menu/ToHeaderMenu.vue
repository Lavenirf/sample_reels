<script setup>
import router from "@/router/index.js";
import {useDialogVisibleStore} from "@/store/useDialogVisibleStore.js";
import { ref, watchEffect} from "vue";

import {useUserStore} from "@/store/useUserStore.js";
const dialogVisibleStore = useDialogVisibleStore()
import {removeToken} from "@/utils/auth.ts";
import {ArrowRight} from "@element-plus/icons-vue";
const props=defineProps(["showLogin","info"])
const userStore = useUserStore()
const info = ref(props.info)

const showLogin = ref(props.showLogin)

//退出登录
const logout = async () => {
  userStore.$reset()
  userStore.showLogin = false
  removeToken()
}
// 监听是否登录
watchEffect(() => {
  showLogin.value = props.showLogin
  info.value=props.info
})
</script>

<template>
  <template v-if="showLogin">
    <a-dropdown>
      <div>
        <el-avatar @click="router.push('/personalInformation')" style="background-color: white;"
                   :src="!info.avatar?'/Avatar/defaultAvatar.png':info.avatar" :size="45"
                   fit="fill"/>
      </div>
      <template #overlay>
        <a-menu style="width: 400px">
          <div id="menu-item" style="display: flex;justify-content: space-between;margin-top: 10px">
            <span style="margin-left: 15px;">个人账号：<span style="padding-left: 5px;">{{ info.userName }}</span></span>
            <a style="margin-right: 15px;color: #ff7315" @click="router.push('/personalInformation')">
              <span>个人资料</span>
              <el-icon>
                <ArrowRight/>
              </el-icon>
            </a>
          </div>
          <a-menu-item style="margin-top: 10px">
            <a-card :bordered="false">
              <a-card-grid style="width: 33%;" @click="router.push('/holland')">职业倾向</a-card-grid>
              <a-card-grid style="width: 33%;" @click="router.push('/MyFavorites')">收藏</a-card-grid>
              <a-card-grid style="width: 33%;" @click="router.push('/volunteerList')">所有志愿表</a-card-grid>
              <a-card-grid style="width: 33%;" @click="router.push('/ai')">高考助手</a-card-grid>
              <a-card-grid style="width: 33%;">消息</a-card-grid>
              <a-card-grid style="width: 33%;">意见反馈</a-card-grid>
              <a-card-grid style="width: 33%;">隐私政策</a-card-grid>
              <a-card-grid style="width: 33%;">用户协议</a-card-grid>
              <a-card-grid style="width: 33%;">下载APP</a-card-grid>
            </a-card>
          </a-menu-item>
          <a-menu-item id="logout">
            <el-button plain @click="logout">退出登录</el-button>
          </a-menu-item>
        </a-menu>
      </template>
    </a-dropdown>
  </template>
  <template v-else >
    <div style="padding-top: 10px;margin-right: 30px">
      <el-button @click="dialogVisibleStore.setDialogVisibleLogin(true)" color="#10239e">登录/注册</el-button>
    </div>
  </template>
</template>

<style scoped lang="scss">
:deep(#logout) {
  text-align: center;
}

:deep(#menu-item) {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.router-link-active{
  padding-bottom: 5px;
  border-bottom: 2px solid #061178;
}
</style>
