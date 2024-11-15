<script setup >

import {LayCarousel, LayCarouselItem} from "@layui/layui-vue";
import {ref} from "vue";
const activeAnmi = ref("1")
</script>

<template>
  <el-col :span="16" style="">
    <lay-carousel  v-model="activeAnmi" anim="fade" style="width:100%;height: 100%;border-radius: 10px">
      <lay-carousel-item id="1">
        <div style="width: 100%;height: 100%;background: url('/carousel/2.jpg')0 -15px no-repeat;background-size: 100%"/>
      </lay-carousel-item>
      <lay-carousel-item id="2">
        <div style="width: 100%;height: 100%;background: url('/carousel/1.png')0 -15px no-repeat;background-size: 100%"/>
      </lay-carousel-item>
      <lay-carousel-item id="3">
        <div style="width: 100%;height: 100%;background: url('/carousel/2.jpg')0 -15px no-repeat;background-size: 100%"/>
      </lay-carousel-item>
      <lay-carousel-item id="4">
        <div style="width: 100%;height: 100%;background: url('/carousel/1.png')0 -15px no-repeat;background-size: 100%"/>
      </lay-carousel-item>
    </lay-carousel>
  </el-col>
</template>

<style scoped lang="scss">
</style>