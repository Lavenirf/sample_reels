<script setup>

import InformationExit from "@/Components/home/InformationExit.vue";
import SecondLevel from "@/Components/home/SecondLevel.vue";
import ThirdLayer from "@/Components/home/ThirdLayer.vue";
import FourthLevel from "@/Components/home/FourthLevel.vue";
import Statement from "@/Components/home/Statement.vue";
import Carousel from "@/Components/home/Carousel.vue";
</script>
<template>
  <!--首页-->
  <div style="width: 100%;">
    <el-row class="firstFloor" :gutter="10">
      <Carousel/>
      <el-col :span="8">
        <informationExit/>
      </el-col>
    </el-row>
    <el-row class="secondFloor" :gutter="10">
      <SecondLevel/>
    </el-row>
    <el-row class="thirdLayer">
      <ThirdLayer/>
    </el-row>
    <el-row class="fourthLayer" :gutter="10">
      <FourthLevel/>
    </el-row>
    <el-row class="fifthLayer">
      <Statement/>
    </el-row>
  </div>

</template>


<style lang="scss" scoped>
.firstFloor {
  width: 100%;
  min-height: 540px;
}

.secondFloor {
  width: 100%;
  min-height: 480px;
  margin-top: 10px;
}

.thirdLayer {
  width: 100%;
  min-height: 450px;
  background: #f5f8ff;
  margin-top: 10px;
}

.fourthLayer {
  width: 100%;
  height: 530px;
  margin-top: 10px;
}

.fifthLayer {
  width: 100%;
  height: 250px;
  background: #f5f8ff;
  margin-top: 10px;
}


</style>
