<script setup>
import {LayTab, LayTabItem} from '@layui/layui-vue'
import router from "@/router/index.js";
import {ArrowRight} from "@element-plus/icons-vue";
import {RightCircleFilled} from '@ant-design/icons-vue'
import {ref,onMounted} from "vue";
import {admissionTime, volunteerTime} from "@/static/index.js";
import {selectSchoolNews} from "@/api/schoolNew.js";
const sdDataList=ref([])
const current=ref('1')
onMounted(async ()=>{
  let query={
    publishPlace:"山东"
  }
  const res1 = await selectSchoolNews(query)
  sdDataList.value=res1.data.rows.slice(0,5)
})

</script>

<template>
  <el-col :span="16">
    <div class="leftBox">
      <div class="one" v-if="sdDataList.length!==0">
        <div class="information">
          <img src="/Icon/hot.png" width="130" alt="">
          <span class="up-to-date">{{ sdDataList[0].newsTitle }}</span>
        </div>
        <ul>
          <li v-for="item in sdDataList.slice(1,sdDataList.length)">
            <el-link :underline="false" :href="item.newsUrl">{{ item.newsTitle }}</el-link>
          </li>
        </ul>
      </div>
      <div class="two">
        <div class="left">
          <div class="link" @click="router.push('/inquireMajor')">
            <div class="title">
              热门专业
            </div>
            <div class="iconBox">
              <span>MORE</span>
              <RightCircleFilled class="icon"/>
            </div>
          </div>
          <div class="leftIcon"/>
        </div>
        <div class="right">
          <ul>
            <li>
              <img src="/Icon/home/jrsx.png" width="50" alt="">
              <span class="majorName" style="margin-left: -3%;">金融数学</span>
            </li>
            <li>
              <img src="/Icon/home/sjkx.png" width="50" alt="">
              <span class="majorName" style="margin-left: -27%;">数据科学与大数据技术</span>
            </li>
            <li>
              <img src="/Icon/home/rjgc.png" width="50" alt="">
              <span class="majorName" style="margin-left: -3%;">软件工程</span>
            </li>
            <li>
              <img src="/Icon/home/lsjz.png" width="50" alt="">
              <span class="majorName" style="margin-left: -20%;">历史建筑保护工程</span>
            </li>
            <li>
              <img src="/Icon/home/csgl.png" width="50" alt="">
              <span class="majorName" style="margin-left: -3%;">城市管理</span>
            </li>
            <li>
              <img src="/Icon/home/xxdk.png" width="50" alt="">
              <span class="majorName" style="margin-left: -10%;">信息对抗技术</span>
            </li>
            <li>
              <img src="/Icon/home/rgzn.png" width="50" alt="">
              <span class="majorName" style="margin-left: -3%;">人工智能</span>
            </li>
            <li>
              <img src="/Icon/home/llyy.png" width="50" alt="">
              <span class="majorName" style="margin-left: -15%;">理论与应用力学</span>
            </li>
            <li>
              <img src="/Icon/home/cbgc.png" width="50" alt="">
              <span class="majorName" style="margin-left: -10%;">船舶工程技术</span>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </el-col>
  <el-col :span="8">
    <div class="rightBox">
      <lay-tab :activeBarTransition="true" class="lab" type="brief" v-model="current" >
        <lay-tab-item title="成绩查询" id="1">
          <div class="buttonBox">
            <div class="iconBox">
              <img src="/Icon/home/timeCj.png" alt="" width="30">
              <span class="describe">查询时间</span>
            </div>
            <div class="specific">
              6月25日15:45
            </div>
          </div>
          <div>
            <a href="Https://cx.sdzk.cn" class="buttonBoxA">
              <div class="iconBox">
                <img src="/Icon/home/DocumentSearch.png" alt="" width="30">
                <span class="describe">官方查询</span>
              </div>
              <div class="specific">考试院官方地址</div>
              <el-icon class="rightIcon2" size="17">
                <ArrowRight/>
              </el-icon>
            </a>
          </div>
        </lay-tab-item>
        <lay-tab-item title="志愿填报" id="2">
          <div class="head">
            <span>填报批次</span>
            <span>填报时间</span>
          </div>
          <div class="content">
            <ul>
              <el-scrollbar class="scrollbar">
                <li v-for="item in volunteerTime" :key="item.batch">
                  <span>{{item.batch}}</span>
                  <span>{{item.time}}</span>
                </li>
                <div>
                  <a href="https://www.sdzk.cn/" class="buttonBoxA">
                    <div class="iconBox">
                      <img src="/Icon/home/wzIcon.png" alt="" width="30">
                      <span class="describe">官网填报</span>
                    </div>
                    <div class="specific">进入官网</div>
                    <el-icon class="rightIcon2" size="17">
                      <ArrowRight/>
                    </el-icon>
                  </a>
                </div>
              </el-scrollbar>
            </ul>
          </div>
        </lay-tab-item>
        <lay-tab-item title="录取时间" id="3">
            <div class="head">
              <span>填报批次</span>
              <span>查询时间</span>
            </div>
              <div class="content">
                <ul>
                  <el-scrollbar class="scrollbar">
                    <li v-for="item in admissionTime" :key="item.batch">
                      <span>{{ item.batch }}</span>
                      <span>{{item.time}}:00</span>
                    </li>
                    <div>
                      <a href="https://www.sdzk.cn/" class="buttonBoxA">
                        <div class="iconBox">
                          <img src="/Icon/home/wzIcon.png" alt="" width="30">
                          <span class="describe">官网填报</span>
                        </div>
                        <div class="specific">进入官网</div>
                        <el-icon class="rightIcon2" size="17">
                          <ArrowRight/>
                        </el-icon>
                      </a>
                    </div>
                  </el-scrollbar>
                </ul>
              </div>
        </lay-tab-item>
        <lay-tab-item title="通知书查询" id="4">
          <div>
            <a href="https://www.ems.com.cn/" class="buttonBoxA">
              <div class="iconBox">
                <img src="/Icon/home/wl.png" alt="" width="30">
                <span class="describe">通知书查询</span>
              </div>
              <div class="specific">
                EMS官网
              </div>
              <el-icon class="rightIcon1" size="17">
                <ArrowRight/>
              </el-icon>
            </a>
          </div>
        </lay-tab-item>
      </lay-tab>
    </div>
  </el-col>
</template>

<style lang="scss" scoped>
.leftBox {
  width: 100%;
  height: 100%;
  background: -webkit-linear-gradient(#F5F8FB 0%, #FBFEFF 100%);
  .one {
    height: 30%;

    .information {
      height: 35%;
      padding-left: 20px;
      line-height: 45px;
      background: #f0f5ff;

      .up-to-date {
        padding-left: 30px;
        font-size: 17px;
        font-weight: bold;
        cursor: pointer;
      }
      .up-to-date:hover{
        color: #303F9F;
      }
    }

    ul {
      margin-left: 20px;
      display: flex;
      flex-wrap: wrap;
      background: #F5F8FF;
      padding-top: 15px;

      li {
        width: 45%;
        height: 40px;
        font-size: 14px;
        white-space:nowrap;
        text-overflow:ellipsis;
        overflow:hidden;
        .el-link {
          width: 100%;
          color: #999;
        }
        .el-link:hover{
          color: #303F9F;
        }
      }
    }
  }

  .two {
    height: 70%;
    display: flex;

    .left {
      width: 30%;
      background: #F1F5FF;
      position: relative;
    }

    .right {
      width: 70%;
      background: #f9fcfe;

      ul {
        display: flex;
        flex-wrap: wrap;
        li:first-child{
          margin-left: 10%;
        }
        li:nth-child(4){
          margin-left: 10%;
        }
        li:nth-child(7){
          margin-left: 10%;
        }

        li {
          width: 25%;
          height: 85px;
          margin-left: 40px;
          margin-top: 25px;
          display: flex;
          flex-direction: column;

          .majorName {
            margin-top: 5px;
          }
        }
      }
    }

    .link {
      position: absolute;
      top: 35%;
      left: 30%;
      cursor: pointer;

      .title {
        width: 100%;
        height: 35px;
        font-size: 21px;
        font-weight: bold;
      }

      .iconBox {
        width: 100%;
        height: 50px;
        margin-left: 10px;

        .icon {
          margin-left: 5px
        }
      }
    }

    .leftIcon {
      width: 100%;
      height: 150px;
      background: url('/Icon/hotSpecialBg.png') no-repeat;
      background-size: 130px;
      position: absolute;
      bottom: 0;
      left: 0;
    }
  }
}

.rightBox {
  width: 100%;
  height: 100%;
  background: #F1F5FF;

  .lab {
    height: 100%;
    margin-top: -1px;
    border-radius: 5px;
    .buttonBoxA {
      width: 80%;
      height: 90px;
      display: block;
      background: #E5EBFB;
      padding-top: 15px;
      padding-left: 15px;
      margin: 20px auto;
      border: 1px solid #C1CEEE;
      border-radius: 10px;
      position: relative;

      .iconBox {
        width: 150px;
      }

      .rightIcon1 {
        position: absolute;
        right: 20px;
        bottom: 37px
      }

      .rightIcon2 {
        position: absolute;
        right: 20px;
        bottom: 37px;
      }
    }

    .buttonBox {
      width: 80%;
      height: 90px;
      padding-top: 15px;
      padding-left: 15px;
      margin: 20px auto;
      background: #E5EBFB;
      border: 1px solid #C1CEEE;
      border-radius: 10px;
      display: flex;
      flex-direction: column;

      .iconBox {
        width: 100px;
      }
    }

    .head {
      color: #5B5D62;
      display: flex;
      justify-content: space-between;
      padding: 10px 10px 8px;
      border-bottom: 1px solid #5A7FDB;

    }

    .content {
      display: flex;
      justify-content: space-between;

      ul {
        width: 100%;
        padding-left: 10px;
        color: #353638;
        .scrollbar{
          height: 380px;
          li {
            padding: 10px 0;
            margin-top: 10px;
            display: flex;
            span:first-child {
              width: 55%;
            }
            span:last-child {
              width: 45%;
              text-align: right;
              padding-right: 10px;
            }
          }
        }

      }
    }


  }
}

.specific {
  width: 100px;
  margin-top: 10px;
  color: #666;
}

.describe {
  margin-left: 10px;
  font-size: 15px;
}

:deep(.layui-tab-head) {
  margin-top: 10px;
  width: 100%;
  text-align: center;
  background: #f1f5ff;
  border-radius: 10px;
}

:deep(.layui-tab-brief>.layui-tab-head>.layui-tab-title .layui-this) {
  background: #1d39c4;
  color: #fff;
  border-radius: 5px;
}

:deep(.layui-tab-content) {
  padding: 0;
}
</style>
