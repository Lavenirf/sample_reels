<script setup>
/*
* 专业概况
* */
import {computed, onMounted, ref} from 'vue'
import {selectSpecialDetails} from "@/api/schoolSpecial.js";
import {useRoute} from "vue-router";
import {useSpecialStore} from "@/store/useSpecialChatStore.js";

const route = useRoute();
const specialData = ref([])

const rateSplice = computed(() => (value) => {
  if (typeof value === 'string' ) {
    return value.split(':');
  } else {
    return ['', ''];
  }
});

const malePercentage = ref(0);
const femalePercentage = ref(0);
const artPercentage = ref(0);
const sciencePercentage = ref(0);
const store = useSpecialStore()
const updatePercentages = () => {
  const sexRate = rateSplice.value(specialData.value.sexRate);
  const artScienceRate = rateSplice.value(specialData.value.artScienceRate);
  if (sexRate.length ===2){
    malePercentage.value = parseFloat(sexRate[0])
    femalePercentage.value = parseFloat(sexRate[1])
  }
 if (artScienceRate.length===2){
   artPercentage.value = parseFloat(artScienceRate[0])
   sciencePercentage.value = parseFloat(artScienceRate[1])
 }
}
const getSpecialInfo = async () => {
  const res = await selectSpecialDetails(route.query.id)
  if (res.data !== null) {
    store.setAreas(res.data.areas)
    store.setDistribution(res.data.distribution)
    store.setPositions(res.data.positions)
    store.setThisFreshGraduates(JSON.parse(res.data.thisFreshGraduates))
    store.setOutFreshGraduates(JSON.parse(res.data.outFreshGraduates))
    delete res.data.areas
    delete res.data.distribution
    delete res.data.positions
    delete res.data.thisFreshGraduates
    delete res.data.outFreshGraduates
    store.setSpecialDetail(res.data)
    specialData.value = res.data
  }
}
onMounted(async () => {
  if (store.getSpecialDetail.length === 0) {
    await getSpecialInfo()
  } else {
    specialData.value = store.getSpecialDetail
  }
  updatePercentages();
});
</script>


<template>
  <div style="padding-left: 30px;">
    <el-row>
      <el-col :span="24">
        <p class="title">专业简介</p>
      </el-col>
      <el-col :span="24" style="margin-top: 15px">
        <div class="contentBox1">
          <el-col :span="24">
            <div style="" class="what">·是什么</div>
            <div style="" class="content">
              {{ specialData.isWhat }}
            </div>
          </el-col>
          <el-col :span="24">
            <div class="what">·学什么</div>
            <div class="content">
              {{ specialData.learnWhat }}
            </div>
          </el-col>
          <el-col :span="24">
            <div class="what">·干什么</div>
            <div class="content">
              {{ specialData.doWhat }}
            </div>
          </el-col>
        </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24" style="margin-top: 20px">
        <p class="title">详解</p>
      </el-col>
      <el-col :span="24" style="margin-top: 15px">
        <div class="contentBox1" style="background: #f8f8f8">
          <div style="font-size: 15px;padding: 20px;text-indent: 2em;" v-html="specialData.content ">
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24" style="margin-top: 20px">
        <p class="title">选考学科建议：{{ specialData.selAdv }}</p>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24" style="margin-top: 20px">
        <p class="title">就业率（近三年）</p>
      </el-col>
      <el-col :span="24" style="margin-top: 15px">
        <div class="contentBox2">
          <div class="flexBox">
            <ul class="job_ul">
              <li class="job_li_a color1" style="background: #007aff">
                <span>{{ specialData.work21 }}</span>
              </li>
              <li class="year1" style="background: #e9f3ff;color: #1686ff;">2021年</li>
            </ul>
            <ul class="job_ul">
              <li class="job_li_a color2" style="background: #ff5605">
                <span>{{ specialData.work22 }}</span>
              </li>
              <li class="year" style="background: #ffeee6;color: #ff5605">2022年</li>
            </ul>
            <ul class="job_ul">
              <li class="job_li_a color3" style="background: #00a333">
                <span>{{ specialData.work23 }}</span>
              </li>
              <li class="year color3" style="background: #f1ffe9;color: #17a94a">2023年</li>
            </ul>
          </div>
        </div>
      </el-col>
    </el-row>
    <el-row style="margin-top: 20px" :gutter="20">
      <el-col :span="9">
        <p class="title">男女比例</p>
        <div class="genderRatio">
          <div class="avatar">
            <div
                style="background: url('/Avatar/man.png') 2px 15px no-repeat;background-size: 60px;width: 72px;height: 75px">
            </div>
            <span style="margin:10px;font-size: 18px">男生</span>
          </div>
          <div class="proportionBox">
            <div class="proportion">
              <div class="progress-bar" :style="{ width: malePercentage + '%', backgroundColor: '#00c8ff' }"></div>
              <div class="progress-bar"
                   :style="{ width: femalePercentage + '%', backgroundColor: '#ff5a00', left: malePercentage + '%' }"></div>
            </div>
          </div>

          <div class="avatar">
            <div
                style="background: url('/Avatar/woman.png') 2px 15px no-repeat;background-size: 60px;width: 72px;height: 75px"></div>
            <span style="margin:10px;font-size: 18px">女生</span>
          </div>
        </div>
        <div class="left">{{ malePercentage }}%</div>
        <div class="right">{{ femalePercentage }}%</div>
      </el-col>
      <el-col :span="9">
        <p class="title">文理比例</p>
        <div class="genderRatio">
          <div class="avatar">
            <el-avatar :size="60" src="/Avatar/wen.png"/>
            <span style="margin:10px;font-size: 18px">文科</span>
          </div>
          <div class="proportionBox">
            <div class="proportion">
              <div class="progress-bar" :style="{ width: artPercentage + '%', backgroundColor: '#00c8ff' }"></div>
              <div class="progress-bar"
                   :style="{ width: sciencePercentage + '%', backgroundColor: '#ff5a00', left: artPercentage + '%' }"></div>
            </div>
          </div>
          <div class="avatar">
            <el-avatar :size="60" src="/Avatar/li.png"/>
            <span style="margin:10px;font-size: 18px">理科</span>
          </div>
        </div>
        <div class="left">{{ artPercentage }}%</div>
        <div class="right">{{ sciencePercentage }}%</div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24" style="margin-top: 20px">
        <p class="title">考研方向</p>
      </el-col>
      <el-col :span="24">
        <p style="font-size: 15px;padding-top: 15px">{{ specialData.direction }}</p>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24" style="margin-top: 20px">
        <p class="title">开设课程</p>
      </el-col>
      <el-col :span="24">
        <p style="font-size: 15px;padding-top: 15px">{{ specialData.course }}</p>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24" style="margin-top: 20px">
        <p class="title">社会名人</p>
      </el-col>
      <el-col :span="24">
        <p style="font-size: 15px;padding-top: 15px">{{ specialData.celebrity }}</p>
      </el-col>
    </el-row>
  </div>
</template>
<style lang="scss" scoped>
.el-row {
  .el-col {
    .title {
      font-size: 21px;
      font-weight: bold;
      border-left: 2px solid #ff8324;
      padding-left: 10px;
    }

    .contentBox1 {
      height: auto;
      width: 75%;
      background: #f8f8f8;
      box-shadow: 0 2px 10px #e9e7ef;
      padding: 3px 0 ;
      .what {
        font-size: 17px;
        font-weight: 540;
        margin: 20px 20px;
      }

      .content {
        font-size: 15px;
        margin: 20px 20px;
      }
    }

    .contentBox2 {
      height: 300px;
      width: 75%;
      border: 1px solid #eee;
      background: #fff;
      box-shadow: 0 2px 10px #e9e7ef;

      .flexBox {
        font-size: 15px;
        padding: 20px;
        display: flex;
        justify-content: space-between;
        margin-top: 65px;
        position: relative;

        .job_ul {
          margin: 0 10%;

          .job_li_a {
            font-size: 18px;
            color: white;
            text-align: center;
            width: 100px;
            height: 50px;
            line-height: 50px;
            margin-bottom: 15px;
            margin-left: -20px;
            border-radius: 10px;
            position: relative;
          }

          .job_li_a::after {
            content: "";
            width: 0;
            height: 0;
            border-left: 10px solid transparent;
            border-right: 10px solid transparent;
            border-top: 10px solid;
            position: absolute;
            bottom: -6px;
            left: 50%;
            transform: translateX(-50%);
          }

          .job_li_a.color1::after {
            border-top-color: #007aff; /* Color for 2021 */
          }

          .job_li_a.color2::after {
            border-top-color: #ff5605; /* Color for 2022 */
          }

          .job_li_a.color3::after {
            border-top-color: #00a333; /* Color for 2023 */
          }
        }

        .year1 {
          width: 60px;
          height: 60px;
          text-align: center;
          line-height: 60px;
          border-radius: 50%;
        }

        .year {
          width: 60px;
          height: 60px;
          text-align: center;
          line-height: 60px;
          border-radius: 50%;
          position: relative; // 添加相对定位
          margin-top: 15px; // 调整间距使虚线居中
          &::before, &::after {
            content: "";
            position: absolute;
            top: 50%;
            width: 150px;
            border-top: 1px dashed #ccc;
          }

          &::before {
            left: -360%;
          }

          &::before {
            display: none;
          }

          &::before {
            display: none;
          }
        }
      }
    }
  }
}

.genderRatio {
  height: 140px;
  box-shadow: 0 2px 10px #e9e7ef;
  display: flex;
  justify-content: space-around;
  margin-top: 20px;

  .avatar {
    width: 65px;
    display: flex;
    flex-direction: column;
    margin: auto 20px;
  }

  .proportionBox {
    flex-grow: 1;
    display: flex;
    align-items: center;
    justify-content: center;
    position: relative;

    .proportion {
      width: 90%;
      height: 5px;
      position: relative;
      border-radius: 10px;
      overflow: hidden;
      margin-top: 20px;

      .progress-bar {
        height: 100%;
        position: absolute;
        top: 0;
        border-radius: 10px;
      }
    }
  }


}

.left {
  width: 20px;
  height: 20px;
  float: left;
  margin-top: -100px;
  margin-left: 30%;
}

.right {
  width: 20px;
  height: 20px;
  float: left;
  margin-top: -100px;
  margin-left: 65%;
}
</style>
