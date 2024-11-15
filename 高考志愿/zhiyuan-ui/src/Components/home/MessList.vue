<script setup>

import router from "@/router/index.js";
import {Reading, Timer} from "@element-plus/icons-vue";

const props = defineProps(["data"])
const handleHref=(item)=>{
  window.open(item.newsUrl)
}
</script>

<template>
  <ul>
    <li @click="handleHref(item)" v-for="item in props.data">
      <span class="title">{{ item.newsTitle }}</span>
      <div class="derivationBox">
       <span class="derivation">
         <el-icon class="icon"><Reading/></el-icon>
         <span class="name">{{ item.publishPlace }}</span>
       </span>
        <span class="derivation">
          <el-icon class="icon"> <Timer/></el-icon>
          <span class="name">{{ item.publishDate }}</span>
        </span>
      </div>
    </li>
  </ul>
  <el-link :underline="false" @click="router.push('/information')">查看更多</el-link>
</template>

<style scoped lang="scss">
ul {
  width: 100%;
  margin-left: 15px;

  li {
    width: 100%;
    height: 80px;
    cursor: pointer;
    white-space:nowrap;
    text-overflow:ellipsis;
    overflow:hidden;
    .title {
      width: 100%;
      font-size: 18px;
      line-height: 35px;

    }

    .title:hover {
      color: #fa541c;
    }

    .derivationBox {
      margin-top: 5px;
      margin-left: 5px;
      font-size: 13px;
      color: #666;
      display: flex;

      .derivation {
        display: flex;
        width: 150px;

        .icon {
          margin-top: 4px;
        }

        .name {
          margin-left: 5px
        }
      }
    }
  }
}

.el-link {
  width: 150px;
  height: 45px;
  border: 1px solid #2f54eb;
  display: block;
  margin: 5px auto;
  text-align: center;
  line-height: 45px;
  border-radius: 10px;
  color: #2f54eb;
}

.el-link:hover {
  color: #fff;
  background: #2f54eb;
}
</style>
