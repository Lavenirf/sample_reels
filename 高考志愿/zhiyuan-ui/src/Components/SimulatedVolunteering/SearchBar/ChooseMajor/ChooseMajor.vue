<script setup>
/*
* 选专业
* */
import { ref } from 'vue';
import { professionalCategory } from '@/static/index.js';
import {useRecommendedParameters} from "@/store/useRecommendedParameters.js";

// 初始化选中项
const selectedProfessionalCategory = ref(professionalCategory[0]);
const parametersStore= useRecommendedParameters()
// 点击处理函数
const handleClick = (item) => {
  selectedProfessionalCategory.value = item;
  parametersStore.setlevel2name(item);

};
</script>

<template>
  <div style="height: 60px;width: 100%; display: flex;box-shadow: 0 2px 10px #e9e7ef;padding-left: 20px;padding-top: 15px;margin-top: 20px">
    <div class="leftTitle">专业门类</div>
    <div class="reg">
      <el-link
          v-for="item in professionalCategory"
          :key="item"
          :underline="false"
          @click="handleClick(item)"
          :class="{ selected: item === selectedProfessionalCategory }"
      >
        {{ item }}
      </el-link>
    </div>
  </div>
</template>

<style scoped>
.leftTitle {
  width: 65px;
  height: 60px;
  line-height: 30px;
}

.reg {
  display: flex;
  width: 90%;
  flex-wrap: wrap;

  .el-link {
    text-align: center;
    height: 20px;
    margin: 5px 5px 6px 4px;
    &.selected {
      color: orange;
    }
  }
}
</style>
