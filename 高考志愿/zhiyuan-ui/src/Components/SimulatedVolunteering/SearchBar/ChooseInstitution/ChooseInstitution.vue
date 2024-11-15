<script setup>
/*
* 选院校
* */
import { ref } from 'vue';
import { region, institutionType, universityLevel } from '@/static/index.js';
import {useRecommendedParameters} from "@/store/useRecommendedParameters.js";

const selectedRegion = ref(region[0]);
const selectedInstitutionType = ref(institutionType[0]);
const selectedUniversityLevel = ref(universityLevel[0]);
const parametersStore= useRecommendedParameters()
const handleRegionClick = (item) => {
  selectedRegion.value = item
  parametersStore.setRegion(item)
}

const handleInstitutionTypeClick = (item) => {
  selectedInstitutionType.value = item
  parametersStore.setInstitutionType(item)
}

const handleUniversityLevelClick = (item) => {
  selectedUniversityLevel.value = item
  parametersStore.setInstitutionLevel(item)
}
</script>

<template>
  <div class="box">
    <div class="select" style="padding-top: 20px;">
      <div class="leftTitle">就读地域</div>
      <div class="reg">
        <el-link
            v-for="item in region"
            :key="item"
            :underline="false"
            @click="handleRegionClick(item)"
            :class="{ selected: item === selectedRegion }"
        >
          {{ item }}
        </el-link>
      </div>
    </div>
    <div class="select">
      <div class="leftTitle">院校类型</div>
      <div class="reg">
        <el-link
            v-for="item in institutionType"
            :key="item"
            :underline="false"
            @click="handleInstitutionTypeClick(item)"
            :class="{ selected: item === selectedInstitutionType }"
        >
          {{ item }}
        </el-link>
      </div>
    </div>
    <div class="select">
      <div class="leftTitle">院校层次</div>
      <div class="reg">
        <el-link v-for="item in universityLevel" :key="item" :underline="false" @click="handleUniversityLevelClick(item)"
                 :class="{ selected: item === selectedUniversityLevel }">
          {{ item }}
        </el-link>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.box{
  box-shadow: 0 2px 10px #e9e7ef;
  padding-left: 20px;
  margin-top: 20px;
  .select{
    width: 100%;
    display: flex;
    .leftTitle {
      width: 65px;
      height: 60px;
      line-height: 30px;
    }
  }
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
      color: #fa541c;
    }
  }
}
</style>
