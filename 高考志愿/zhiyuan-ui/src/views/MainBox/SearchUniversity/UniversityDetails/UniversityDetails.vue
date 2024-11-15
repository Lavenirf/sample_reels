<script setup>
import 'animate.css';
import {GlobalOutlined, PhoneOutlined, MailOutlined} from "@ant-design/icons-vue"
import {ref, computed, onMounted} from "vue"
import {Location} from "@element-plus/icons-vue"
import SchoolProfile from "@/Components/SearchUniversity/CollegeDetails/SchoolProfile.vue"
import ScorePlan from "@/Components/SearchUniversity/CollegeDetails/ScorePlan.vue"
import EstablishingMajors from "@/Components/SearchUniversity/CollegeDetails/EstablishingMajors.vue"
import {useRouter} from 'vue-router'
import {LayTab, LayTabItem,LayTransition} from "@layui/layui-vue"
import {ElLoading, ElMessage} from "element-plus"
import {getSchoolById} from "@/api/school.js"
import router from "@/router/index.js"
import {addCollect, delCollect, getCollectList} from "@/api/collect.js";
import {useCollectStore} from "@/store/useCollectStore.js";
import {useUserStore} from "@/store/useUserStore.js";
import {useDialogVisibleStore} from "@/store/useDialogVisibleStore.js";
const disableStore=useDialogVisibleStore()
const collectStore = useCollectStore()
const userStore = useUserStore()
const route = useRouter()
const data = ref({})
const current = ref("1")
const isCollect = ref(false)
const logo = ref("")
const rankList = ref([])
const schoolCollect = ref([])
const collectData = ref({})
const schoolId = Number(route.currentRoute.value.query.id)
const handClickCollect = async () => {
  if (userStore.showLogin){
    if (isCollect.value) {
      //调用删除收藏接口
      const res = await delCollect(collectData.value.id)
      if (res.code === 200) {
        ElMessage.success("取消收藏")
        isCollect.value = false
      } else {
        ElMessage.error("取消收藏失败")
      }
    } else {
      //调用添加接口
      const data = {
        "userId": userStore.getData.userId,
        "collectType": "0",
        "schoolId": schoolId
      }
      const res = await addCollect(data)
      if (res.code === 200) {
        ElMessage.success("收藏成功")
        isCollect.value = true
      } else {
        ElMessage.error("收藏失败，请稍后重试！")
      }
    }
    const res = await getCollectList(userStore.getData.userId)
    //更新pinia
    collectStore.setSchoolsCollectList(res.data.schoolReduceVoList)
    await isThisCollect()
  } else {
    ElMessage.warning("请登录后进行操作！")
    disableStore.setDialogVisibleLogin(true)
  }
}
const isThisCollect = async () => {
  schoolCollect.value = await collectStore.getSchoolsCollectList
  //判断当院校是否在收藏中
  let thisCollect = schoolCollect.value.find(s => s.schoolId === schoolId)
  if (thisCollect !== undefined) {
    collectData.value = thisCollect
    //设置为红色心
    isCollect.value = true
  }
}
onMounted(async () => {
  const loadingInstance = ElLoading.service({
    lock: true,
    text: 'Loading',
    background: 'rgba(0, 0, 0, 0.7)',
  })
  if (schoolId) {
    const res = await getSchoolById(schoolId)
    if (res.code === 200) {
      data.value = res.data
      rankList.value = res.data.rankList.filter(r => r.rankType !== '泰晤士（大陆）')
    }
  } else {
    await router.push("/searchUniversity")
  }
  if (userStore.showLogin){
    //获取收藏数据
    const res = await getCollectList(userStore.getData.userId)
    //存储pinia
    collectStore.setSchoolsCollectList(res.data.schoolReduceVoList)
    await isThisCollect()
  }
  loadingInstance.close()
})
const natureComputed = computed(() => (item) => {
  if (item === '普通本科') {
    return '本科'
  } else {
    return '专科'
  }
})
const rankTypeComputed = computed(() => (name) => {
  if (name === '软科') {
    return '软科排名'
  } else if (name === '校友会') {
    return '校友会综合'
  } else return name
})
</script>

<template>
  <div class="header">
    <div class="row" style="display: flex;background: #fffffB;padding: 30px 0 30px  20px;border-radius: 10px">
      <div>
        <img v-if="!schoolId" src="/Icon/imgLoading.gif" alt="" style="width: 90px;height: 90px">
        <img v-else :src="`/schoolLogo/${schoolId}.jpg`" alt="" style="width: 90px;height: 90px">
      </div>
      <div class="title">
        <div style="display: flex">
          <span style="color: #000;font-size: 25px;">{{ data.name }}</span>
          <div class="location">
            <el-icon :size="18" style="margin: 2px 5px">
              <Location/>
            </el-icon>
            <div class="locationTitle">{{ data.provinceName }}{{ data.cityName }}</div>
          </div>
        </div>
        <div class="little">
          <span style="margin-left: 0">{{ natureComputed(data.levelName) }}</span>
          <span>{{ data.typeName }}</span>
          <span>{{ data.natureName }}</span>
          <span v-if="data.f211===1">211工程</span>
          <span v-if="data.f985===1">985工程</span>
          <span v-if="data.dualClassName">双一流</span>
        </div>
      </div>
      <div style="width:40px;margin-left: 45%;margin-top: 20px" @click="handClickCollect">
        <transition enter-active-class="animate__heartBeat">
          <el-link :underline="false" v-if="!isCollect">
            <div style=" width: 35px;height:35px;background: url('/Icon/hX.png') no-repeat;background-size: 35px"/>
          </el-link>
          <el-link :underline="false" v-else>
            <div style=" width: 35px;height:35px;background: url('/Icon/redX.png') no-repeat;background-size: 35px"/>
          </el-link>
        </transition>
      </div>
    </div>
    <div class="row" style="margin-top: 10px;display: flex;height: 300px;background: #fff;padding: 25px 20px;">
      <div>
        <img :src="`/schoolBg/${schoolId}.jpg`" alt="" style="width: 442px;height: 254px;border-radius: 5px">
      </div>
      <div class="informationBox">
        <ul v-show="rankList.length>1">
          <li v-for="item in rankList">
            <span>{{ item.rank }}</span>
            <span style="padding-top: 5px">{{ rankTypeComputed(item.rankType) }}</span>
          </li>
        </ul>
        <div class="addressInformation">
          <div>
            <GlobalOutlined style="margin: 5px 10px"/>
            <span>官网地址：</span>
            <span>{{ data.schoolSite }} &nbsp; &nbsp; &nbsp; &nbsp;{{ data.site }}</span>
          </div>
          <div>
            <PhoneOutlined style="margin: 5px 10px"/>
            <span>招生电话：</span>
            <span>{{ data.phone }}</span>
          </div>
          <div>
            <MailOutlined style="margin: 5px 10px"/>
            <span>电子邮箱：</span>
            <span>{{ data.schoolEmail }}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="main" style="margin-left: 20px;">
    <div>
      <lay-transition type="fade">
      <lay-tab type="button" v-model="current" >
          <lay-tab-item title="学校概况" id="1">
            <SchoolProfile :data="data" :id="schoolId"/>
          </lay-tab-item>
          <lay-tab-item title="分数/计划" id="2">
            <ScorePlan :schoolId="schoolId"/>
          </lay-tab-item>
<!--          <lay-tab-item title="开设专业" id="3">
            <EstablishingMajors :schoolId="schoolId"/>
          </lay-tab-item>-->
      </lay-tab>
      </lay-transition>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.header {
  width: 100%;
  min-height: 480px;
  border-radius: 10px;

  .row {
    margin-left: 20px;

    .title {
      margin-left: 50px;
      margin-top: 8px;

      .location {
        width: auto;
        height: 35px;
        margin: 2px 30px;
        border: 1px solid #ccc;
        padding: 5px;
        border-radius: 20px;
        display: flex;

        .locationTitle {
          color: #000;
          font-size: 12px;
          margin: 4px 0;
          padding-right: 5px
        }
      }

      .little {
        display: flex;

        span {
          width: auto;
          height: auto;
          padding: 5px;
          background: #f5f6f7;
          color: #707070;
          margin: 10px 5px;
          border-radius: 5px;
        }
      }
    }

    .informationBox {
      width: 100%;

      ul {
        list-style: none;
        display: flex;
        margin-left: 20px;

        li {
          display: flex;
          flex-direction: column;
          padding: 10px 30px;
          height: 60px;
          background: #fff9f2;
          text-align: center;
          margin: 0 8px;
          border-radius: 10px;

          span:first-child {
            color: #ff7317;
            font-size: 17px;
          }
        }
      }

      .addressInformation {
        margin-left: 30px;
        display: flex;
        flex-direction: column;
        margin-top: 30px;

        div {
          height: 50px;
          font-size: 16px;
          color: #666;
        }
      }
    }
  }
}

:deep(.layui-tab-head) {
  margin-top: 5px;
  height: 40px;
}

:deep(.layui-tab-title li) {
  width: 150px;
  height: 50px;
  margin-left: 10px;
  border-radius: 10px;
}

:deep(.layui-this) {
  background: #1d39c4 !important;
  color: #fff !important;
}

</style>
