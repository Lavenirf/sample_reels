<script setup>
/*
  *我的资料
  * */
import {Menu as IconMenu, Message, Setting} from '@element-plus/icons-vue'
import {onMounted, ref,watchEffect} from 'vue'
import {useUserStore} from '@/store/useUserStore.js'
import router from "@/router/index.js";
const userStore = useUserStore();
const info = ref({})
console.log(userStore)
const menuItems = ref([
  {index: 1, icon: IconMenu, title: '隐私政策', link: '/mersonalData'},
  {index: 2, icon: IconMenu, title: '个人资料', link: '/personalData'},
  {index: 3, icon: Message, title: '我的收藏', link: '/MyFavorites'},
  {index: 4, icon: Setting, title: '我的志愿表', link: '/volunteerList'},
  {index: 5, icon: Setting, title: '我的消息', link: '/mersonalData'},
  {index: 6, icon: Setting, title: '意见反馈', link: '/mersonalData'},
  {index: 7, icon: Setting, title: '用户协议', link: '/mersonalData'},
  {index: 8, icon: Setting, title: '下载APP', link: '/mersonalData'}
])

onMounted(() => {
  if (userStore.showLogin){
    info.value = userStore.getData
  }else {
    router.push("/")
  }
})
watchEffect(()=>{
  info.value = userStore.getData
})
</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-container>
        <el-aside style="margin-top:20px;margin-left: 50px;cursor: default; flex-direction: row; height: 800px; margin-right: 10px"
                  width="200px">
          <el-menu default-active="2">
            <el-menu-item
                style="height: 100px;  background-color: transparent;cursor: default ;margin-bottom: 10px ; ">
              <!--                设置头像和用户名 -->
              <div id="avatar">
                <!--                存放头像-->
                <img :src="info.avatar" alt="头像" style="width: 65px;height: 65px;border-radius: 50% "/>
              </div>
              <!--              存放用户名-->
              <div id="username" style="  font-weight: bold;margin-left: 15px;font-size: 20px;align-self: center">
                {{info.userName}}
              </div>
            </el-menu-item>
            <router-link v-for="(item, index) in menuItems" :key="index" :to="item.link">
              <el-menu-item style="margin-top: 5px; font-size: 15px" :index="`${item.index}`">
                <el-icon>
                  <component :is="item.icon"/>
                </el-icon>
                {{ item.title }}
              </el-menu-item>
            </router-link>
          </el-menu>
        </el-aside>
        <el-container>
          <el-main>
            <router-view/>
          </el-main>
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>


<style>
.el-container {
  max-width: 100%; /* 确保容器适应视口宽度 */
  overflow: hidden; /* 确保容器没有滚动条 */
}

.el-aside {
  avatar {
    cursor: none;
  }

  li:hover {
    background-color: orange;

  }

}

.el-menu-item .el-menu-tooltip__trigger {
  align-items: center;
  box-sizing: border-box;
  display: inline-flex;
  height: 100%;
  left: 0;
  position: absolute;
  top: 0;
  width: 100%;
}

.common-layout::-webkit-scrollbar {
  display: none;
}


</style>
