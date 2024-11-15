<script setup>
import 'animate.css';
import TopHeader from "@/Components/Header/TopHeader/TopHeader.vue";
import Login from "@/Components/login/Login.vue";
/*
* 最外部父盒子
* */

</script>

<template>
  <div style="margin: 0 auto;background: -webkit-linear-gradient(#EAECEE 0%,#FFFFFF 70.47%);">
    <el-container style="margin-left: 0.5%">
      <el-header height="70px" style="width: 100%;margin: 20px auto 0">
        <TopHeader/>
      </el-header>
      <el-main class="main-content" >
        <el-scrollbar>
          <Transition mode="out-in" enter-active-class="animate__animated animate__slideInRight"  leave-active-class="animate__animated animate__slideOutRight">
            <router-view/>
          </Transition>
        </el-scrollbar>
      </el-main>
      <Login/>
    </el-container>
  </div>
</template>

<style lang="scss" scoped>
el-container {
  display: flex;
  flex-direction: column;
  align-items: stretch;
  margin: 30px auto 0;
}
.main-content {
  width: 90%;
  margin: 0 auto;

}
</style>
