<script setup>

import {ArrowRight} from "@element-plus/icons-vue";
import {getPopularSchool} from "@/api/school.js";
import {onMounted, ref, computed} from 'vue'
import {useSchoolStore} from "@/store/useSchoolStore.js";
import router from "@/router/index.js";

const schoolStore = useSchoolStore()
const popularData = ref([])
const badgeList = ref([])
const getDataList = async () => {
  popularData.value=schoolStore.getPopularSchoolData
  badgeList.value=schoolStore.getBadgeList
  //获取热门学校
  if (Object.keys(popularData.value)) {
    const res = await getPopularSchool('')
    popularData.value = res.data
    //排序
    popularData.value.sort((a, b) => b.viewTotalNumber - a.viewTotalNumber);
  }
}
onMounted(async () => {
  await getDataList()
})

const popularDataComputed = computed(() => {
  return popularData.value.filter((item, index) => index !== 0)
})

</script>

<template>
  <div>
    <div class="titleBox">
      <div class="title" style="">
        <span>热门院校</span>
      </div>
    </div>
    <div class="content" v-if="popularData.length>0">
      <div class="leftBox">
        <div class="schoolName"
             @click="router.push({ path: '/universityDetails', query: { id: popularData[0].schoolId }})">
          <span>{{ popularData[0].name }}</span>
        </div>
        <div class="labelBox">
          <span class="label">{{ popularData[0].typeName }}</span>
          <span class="label">{{ popularData[0].natureName }}</span>
        </div>
        <div class="badge">
          <img :src="`/schoolLogo/${popularData[0].schoolId}.jpg`" alt="" width="160">
        </div>
      </div>
      <div class="rightBox">
        <ul>
          <li v-for="(item,index) in popularDataComputed"
              @click="router.push({ path: '/universityDetails', query: { id: item.schoolId }})">
            <div class="badge">
              <img :src="`/schoolLogo/${item.schoolId}.jpg`" alt="" width="70">
            </div>
            <div class="schoolName">
              <div class="name">
                <span>{{ item.name }}</span>
              </div>
              <div class="labelBox">
                <span class="label">{{ item.typeName }}</span>
                <span class="iconBox">
                  <span>查看院校</span>
                  <el-icon class="icon"><ArrowRight/></el-icon>
                </span>
              </div>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </div>

</template>

<style scoped lang="scss">
.titleBox {
  height: 10%;

  .title {
    width: 100%;
    font-size: 22px;
    font-weight: bold;
    margin: 5px 0 0 10px;
  }
}

.content {
  height: 84%;
  width: 100%;
  margin-top: 5px;
  display: flex;

  .leftBox {
    width: 28%;
    position: relative;
    background: -webkit-linear-gradient(#FFF1EB 10%, #ACE0F9 100%);
    border-radius: 8px;
    margin-left: 10px;
    cursor: pointer;

    .schoolName {
      position: absolute;
      top: 25%;
      left: 50%;
      transform: translate(-50%, -50%);
      font-size: 20px;
      font-weight: bold;
      text-align: center;
    }

    .labelBox {
      position: absolute;
      top: 32%;
      left: 50%;
      transform: translateX(-50%);
      text-align: center;

      .label {
        background: #fff;
        padding: 5px;
        margin-left: 5px;
      }
    }

    .badge {
      position: absolute;
      top: 42%;
      left: 50%;
      transform: translateX(-50%);
      text-align: center;
      background: #fff;
      padding: 5px;
      border-radius: 15px;
    }
  }

  .rightBox {
    width: 72%;
    margin-left: 10px;

    ul {
      width: 100%;
      height: 100%;
      display: flex;
      flex-wrap: wrap;

      li:nth-child(-n+3) {
        margin-top: 0;
      }

      li {
        width: 31%;
        height: 28%;
        margin-left: 1.5%;
        margin-top: 3%;
        background: #fff;
        border-radius: 8px;
        display: flex;
        cursor: pointer;

        .badge {
          margin-top: 5px;
          width: 37%;
          height: 100%;

          img {
            margin: 12% 10%;
          }
        }

        .schoolName {
          width: 63%;
          height: 100%;

          .name:hover {
            color: #fa541c;
          }

          .name {
            font-size: 16px;
            margin-top: 12%;
          }
        }

        .labelBox {
          margin-top: 15px;
          font-size: 13px;

          .label {
            padding: 5px 8px;
            background: #f5f6f7;
          }

          .iconBox {
            margin-left: 10px;
            position: relative;

            .icon {
              position: absolute;
              top: 1px;
              left: 110%;
            }
          }
        }
      }
    }
  }
}
</style>
