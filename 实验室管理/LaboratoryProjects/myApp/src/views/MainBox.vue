<script setup>
import TopHeader from "../components/MainBox/HeaderTop.vue";
import SideMenu from "../components/MainBox/SideMenu.vue";

</script>

<template>
  <div>
    <el-container style="height: 100vh">
      <SideMenu></SideMenu>
      <el-container direction="vertical">
        <TopHeader></TopHeader>
        <el-main>
          <el-scrollbar>
            <router-view></router-view>
          </el-scrollbar>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<style scoped>

</style>