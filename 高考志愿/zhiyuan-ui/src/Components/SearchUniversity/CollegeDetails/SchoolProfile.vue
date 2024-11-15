<script setup>
/*
* 院校概况
* */
import {ref, toRef, computed, onMounted} from "vue"
import {getSchoolProfileById} from "@/api/school.js";

const props = defineProps(['data', 'id'])
const data = toRef(props, 'data');
const schoolId = toRef(props, 'id');
const profile = ref("")
onMounted(async () => {
  const res = await getSchoolProfileById(schoolId.value)
  profile.value = res.data
})
const numComputer = computed(() => (num) => {
  if (num === '0') {
    return "--"
  } else return num
})
</script>

<template>
  <div>
    <div class="basicInformation">
      <div class="title"><span>基本信息</span></div>
      <ul class="faculty" style="">
        <li>
          <div>
            <img src="/Icon/schoolDetail/hat.png" alt="" width="23"/>
            <span class="subtitle">保研星级</span>
          </div>
          <div style="margin: 16px auto;width: 80%">
            <svg viewBox="64 64 896 896" class="icon" data-icon="star" width="22" height="22" fill="currentColor">
              <path
                  d="M908.1 353.1l-253.9-36.9L540.7 86.1c-3.1-6.3-8.2-11.4-14.5-14.5-15.8-7.8-35-1.3-42.9 14.5L369.8 316.2l-253.9 36.9c-7 1-13.4 4.3-18.3 9.3a32.05 32.05 0 0 0 .6 45.3l183.7 179.1-43.4 252.9a31.95 31.95 0 0 0 46.4 33.7L512 754l227.1 119.4c6.2 3.3 13.4 4.4 20.3 3.2 17.4-3 29.1-19.5 26.1-36.9l-43.4-252.9 183.7-179.1c5-4.9 8.3-11.3 9.3-18.3 2.7-17.5-9.5-33.7-27-36.3z"></path>
            </svg>
            <svg style="margin-left: 5%" viewBox="64 64 896 896" class="icon" data-icon="star" width="22" height="22"
                 fill="currentColor">
              <path
                  d="M908.1 353.1l-253.9-36.9L540.7 86.1c-3.1-6.3-8.2-11.4-14.5-14.5-15.8-7.8-35-1.3-42.9 14.5L369.8 316.2l-253.9 36.9c-7 1-13.4 4.3-18.3 9.3a32.05 32.05 0 0 0 .6 45.3l183.7 179.1-43.4 252.9a31.95 31.95 0 0 0 46.4 33.7L512 754l227.1 119.4c6.2 3.3 13.4 4.4 20.3 3.2 17.4-3 29.1-19.5 26.1-36.9l-43.4-252.9 183.7-179.1c5-4.9 8.3-11.3 9.3-18.3 2.7-17.5-9.5-33.7-27-36.3z"></path>
            </svg>
            <svg style="margin-left: 5%" viewBox="64 64 896 896" class="icon" data-icon="star" width="22" height="22"
                 fill="currentColor">
              <path
                  d="M908.1 353.1l-253.9-36.9L540.7 86.1c-3.1-6.3-8.2-11.4-14.5-14.5-15.8-7.8-35-1.3-42.9 14.5L369.8 316.2l-253.9 36.9c-7 1-13.4 4.3-18.3 9.3a32.05 32.05 0 0 0 .6 45.3l183.7 179.1-43.4 252.9a31.95 31.95 0 0 0 46.4 33.7L512 754l227.1 119.4c6.2 3.3 13.4 4.4 20.3 3.2 17.4-3 29.1-19.5 26.1-36.9l-43.4-252.9 183.7-179.1c5-4.9 8.3-11.3 9.3-18.3 2.7-17.5-9.5-33.7-27-36.3z"></path>
            </svg>
            <svg style="margin-left: 5%" viewBox="64 64 896 896" class="icon" data-icon="star" width="22" height="22"
                 fill="currentColor">
              <path
                  d="M908.1 353.1l-253.9-36.9L540.7 86.1c-3.1-6.3-8.2-11.4-14.5-14.5-15.8-7.8-35-1.3-42.9 14.5L369.8 316.2l-253.9 36.9c-7 1-13.4 4.3-18.3 9.3a32.05 32.05 0 0 0 .6 45.3l183.7 179.1-43.4 252.9a31.95 31.95 0 0 0 46.4 33.7L512 754l227.1 119.4c6.2 3.3 13.4 4.4 20.3 3.2 17.4-3 29.1-19.5 26.1-36.9l-43.4-252.9 183.7-179.1c5-4.9 8.3-11.3 9.3-18.3 2.7-17.5-9.5-33.7-27-36.3z"></path>
            </svg>
            <svg style="margin-left: 5%" viewBox="64 64 896 896" class="icon" data-icon="star" width="22" height="22"
                 fill="currentColor">
              <path
                  d="M908.1 353.1l-253.9-36.9L540.7 86.1c-3.1-6.3-8.2-11.4-14.5-14.5-15.8-7.8-35-1.3-42.9 14.5L369.8 316.2l-253.9 36.9c-7 1-13.4 4.3-18.3 9.3a32.05 32.05 0 0 0 .6 45.3l183.7 179.1-43.4 252.9a31.95 31.95 0 0 0 46.4 33.7L512 754l227.1 119.4c6.2 3.3 13.4 4.4 20.3 3.2 17.4-3 29.1-19.5 26.1-36.9l-43.4-252.9 183.7-179.1c5-4.9 8.3-11.3 9.3-18.3 2.7-17.5-9.5-33.7-27-36.3z"></path>
            </svg>
          </div>
        </li>
        <li>
          <div>
            <img src="/Icon/schoolDetail/bo.png" alt="" width="23"/>
            <span>博士点<span>（一级/二级）</span></span>
          </div>
          <div>
            <span>{{ data.numDoctor }}</span>
            <span style="font-size: 13px;color: #999">/</span>
            <span>&nbsp;{{ numComputer(data.numDoctor2) }}</span>
            <span style="font-size: 13px;color: #999">&nbsp;个</span>
          </div>
        </li>
        <li>
          <div>
            <img src="/Icon/schoolDetail/suo.png" alt="" width="23"/>
            <span>硕士点<span>（一级/二级）</span></span>
          </div>
          <div>
            <span>{{ data.numMaster }}</span>
            <span style="font-size: 13px;color: #999">/</span>
            <span>&nbsp;{{ numComputer(data.numMaster2) }}</span>
            <span style="font-size: 13px;color: #999">&nbsp;个</span>
          </div>
        </li>
        <li>
          <div>
            <img src="/Icon/schoolDetail/zd.png" alt="" width="23"/>
            <span>国家重点学科</span>
          </div>
          <div>
            <span>{{ data.numSubject }}</span>
            <span style="font-size: 13px;color: #999">&nbsp;个</span>
          </div>
        </li>
      </ul>
      <ul class="information">
        <li>
          <div>
            <img src="/Icon/schoolDetail/createTime.png" alt="" width="20">
            <span>建校时间：{{ data.createDate }}</span>
          </div>
        </li>
        <li>
          <div>
            <img src="/Icon/schoolDetail/area.png" alt="" width="20">
            <span>占地面积：{{ data.area }}亩</span>
          </div>
        </li>
        <li>
          <div>
            <img src="/Icon/schoolDetail/division.png" alt="" width="20">
            <span>主管部门：{{ data.belong }}</span>
          </div>
        </li>
        <li>
          <div>
            <img src="/Icon/schoolDetail/address.png" alt="" width="20">
            <span>学校地址：{{ data.address }}</span>
          </div>
        </li>
      </ul>
    </div>
    <div class="synopsis">
      <div class="title"><span>学校简介</span></div>
      <div v-if="profile" class="content" >
        <span v-html="profile.schoolDetail"></span>
      </div>
      <div v-else class="content" style="padding-top: 20px">
        暂无数据
      </div>
    </div>
    <div class="synopsis">
      <div class="title"><span>食宿条件</span></div>
      <div>
        <div class="stay">
          <span>住宿条件</span>
        </div>
        <div v-if="profile" class="content" >
          <span v-html="profile.accommodation"></span>
        </div>
        <div class="content" v-else style="padding-top: 20px">
          暂无数据
        </div>
      </div>
      <div>
        <div class="stay">
          <span>食堂</span>
        </div>
        <div v-if="profile"  class="content" >
          <span v-html="profile.diet"></span>
        </div>
        <div class="content" v-else style="padding-top: 20px">
          暂无数据
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped lang="scss">
.basicInformation {
  width: 80%;
  height: 300px;
  padding-left: 20px;
  padding-right: 20px;
  margin-top: -20px;
  background: #F9F9F9;
  border-radius: 10px;

  .faculty {
    display: flex;
    margin-top: 15px;

    li {
      width: 25%;
      height: 100px;
      background: #f5f5f5;
      margin-left: 15px;
      border-radius: 5px;

      div:first-child {
        margin-left: 10%;
        margin-top: 20px;

        span {
          padding-left: 10px;
          font-size: 14px;

          span {
            color: #999;
          }
        }
      }

      div:nth-child(2) {
        width: 80%;
        font-size: 21px;
        margin: 15px auto;

        span:nth-child(2) {
          font-size: 13px;
          color: #999;
        }
      }
    }
  }

  .information {
    width: 98.5%;
    height: 100px;
    display: flex;
    margin-left: 15px;
    flex-wrap: wrap;
    margin-top: 15px;
    background: #f8f8f8;

    li {
      width: 48%;
      margin-left: 15px;
      margin-top: 20px;

      span {
        padding-left: 10px;
        color: #333;
      }
    }
  }
}

.synopsis {
  width: 80%;
  height: auto;
  padding: 0 20px 20px;
  background: -webkit-linear-gradient(#F9F9F9 60%, #F0F0F0 100%);
  border-radius: 10px;

  ul {
    font-size: 15px;
    margin-top: 15px;

    li:first-child {
      display: flex;
      color: #999;
    }

    li {
      display: flex;
      margin-top: 15px;

      span:first-child {
        width: 40%;
      }

      span:last-child {
        width: 60%;
      }
    }
  }

  .content {
    font-size: 15px;
    line-height: 35px;
    color: #666;
    text-indent: 2em;
  }

  .stay {
    font-size: 15px;
    font-weight: bold;
    border-left: 5px solid #00b359;
    padding-left: 8px;
    margin: 15px 6px 5px 0;
  }

  .title2 {
    width: 100%;
    margin-top: 20px;
    font-size: 18px;
    font-weight: bold;
    padding-top: 15px;
    display: flex;
    justify-content: space-between;

    div {
      margin-right: 0;
      font-size: 13px;
      font-weight: normal;

      .rightIcon {
        margin-left: 5px;
      }
    }
  }
}

.title {
  width: 100%;
  margin-top: 20px;
  font-size: 18px;
  font-weight: bold;
  padding-top: 15px;
}

svg path {
  fill: #ff9b4c;
}
</style>
