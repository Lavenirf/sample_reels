
<script setup>
import {ref,onMounted} from 'vue';
import 'animate.css';
import {useCollectStore} from "@/store/useCollectStore.js";
import {delCollect, getCollectList} from "@/api/collect.js";
import {useUserStore} from "@/store/useUserStore.js";

const activeKey = ref('1');
const pageSize = ref(5);
const currentPage = ref(1);
const collegeStore= useCollectStore()
const userStore = useUserStore()
const schoolCollect=ref([])
const specialCollect=ref([])
const logos =ref([])
const isRemoving = ref(false);
const removingIndex = ref(-1);
const  handleSizeChange=(val)=> {
  pageSize.value = val;
}
const handleCurrentChange=(val)=> {
  currentPage.value = val;
}

const  removeSchool=async (index,id)=> {
  isRemoving.value = true;
  removingIndex.value = index;
  setTimeout(async () => {
    await delCollect(id)
    await getCollectData()
    isRemoving.value = false;
    removingIndex.value = -1;
  }, 500); // 动画时间
}

const currentPage2 = ref(1);

const removeMajor=(index,id)=> {
  isRemoving.value = true;
  removingIndex.value = index;
  setTimeout(async () => {
    await delCollect(id)
    await getCollectData()
    isRemoving.value = false;
    removingIndex.value = -1;
  }, 500);
}
//获取收藏数据
const getCollectData= async ()=>{
  const res = await getCollectList(userStore.getData.userId)
  schoolCollect.value=res.data.schoolReduceVoList
  specialCollect.value=res.data.specialReduceVoList
  collegeStore.setSchoolsCollectList(res.data.schoolReduceVoList)
  collegeStore.setSpecialCollectList(res.data.specialReduceVoList)
}
onMounted(async ()=>{
  if(collegeStore.getSchoolsCollectList.length===0&&collegeStore.getSpecialCollectList.length===0){
   await getCollectData()
  }else {
    schoolCollect.value=collegeStore.getSchoolsCollectList
    specialCollect.value=collegeStore.getSpecialCollectList
  }

})

</script>
<template>
  <div class="card-container">
    <a-tabs v-model:activeKey="activeKey" type="card" style="margin-top: 10px; height: 100%">
      <a-tab-pane key="1" tab="收藏院校">
        <div v-if="schoolCollect.length > 0" style="height: 700px;margin-top: -30px">
          <ul>
            <li v-for="(item, index) in schoolCollect" :key="item.id"
                :class="['animated-item', isRemoving && removingIndex === index ? 'animate__animated animate__backOutRight' : '']"
                style="margin: 50px 50px 10px; border-bottom: rgba(26,23,23,0.1) solid 2px"
            >
              <a href="#">
                <img :src="`/schoolLogo/${item.schoolId}.jpg`" alt="sdzfdx" style="width: 70px; float: left">
              </a>
              <div style="margin-left: 100px">
                <span style="font-size: 20px">{{ item.name }}</span>
                <span style="border: rgba(26,23,23,0.1) solid 1px; margin-left: 15px; color: #707070">
                  {{item.provinceName}}{{ item.cityName }}
                </span>
              </div>
              <div style="float: right">
                <el-button @click="removeSchool(index,item.id)" type="success" round>
                  <svg viewBox="64 64 896 896" class="" data-icon="star" width="0.8em" height="0.8em"
                       fill="currentColor" aria-hidden="true" focusable="false">
                    <path
                        d="M908.1 353.1l-253.9-36.9L540.7 86.1c-3.1-6.3-8.2-11.4-14.5-14.5-15.8-7.8-35-1.3-42.9 14.5L369.8 316.2l-253.9 36.9c-7 1-13.4 4.3-18.3 9.3a32.05 32.05 0 0 0 .6 45.3l183.7 179.1-43.4 252.9a31.95 31.95 0 0 0 46.4 33.7L512 754l227.1 119.4c6.2 3.3 13.4 4.4 20.3 3.2 17.4-3 29.1-19.5 26.1-36.9l-43.4-252.9 183.7-179.1c5-4.9 8.3-11.3 9.3-18.3 2.7-17.5-9.5-33.7-27-36.3zM664.8 561.6l36.1 210.3L512 672.7 323.1 772l36.1-210.3-152.8-149L417.6 382 512 190.7 606.4 382l211.2 30.7-152.8 148.9z"></path>
                  </svg>
                  已关注
                </el-button>
              </div>
              <br>
              <div style="margin-left: 100px; color: #707070;padding-bottom: 15px">
                {{ item.levelName }}
                <div
                    style="width: 5px; height: 10px; border-left: 1px solid red; margin-left: 5px; display: inline-block"></div>
                {{ item.typeName }}
                <div
                    style="width: 5px; height: 10px; border-left: 1px solid red; margin-left: 5px; display: inline-block"></div>
                {{ item.natureName }}
              </div>
            </li>
          </ul>
          <div class="pagination-container">
            <el-pagination
                v-model:current-page="currentPage"
                :page-size="pageSize"
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
            />
          </div>
        </div>
        <div v-else
             style="height: 700px; display: flex; flex-direction: column; align-items: center; justify-content: center;">
          <img src="https://www.gaokao.cn/static/media/focus_school.3e553614.png" alt="No data"
               style="margin-bottom: 20px;">
          <p>你没有收藏任何院校</p>
        </div>
      </a-tab-pane>
      <a-tab-pane key="2" tab="收藏专业">
        <div v-if="specialCollect.length > 0" style="height: 700px;margin-top: -30px">
          <ul>
            <li v-for="(item, index) in specialCollect" :key="item.name"
                :class="['animated-item', isRemoving && removingIndex === index ? 'animate__animated animate__backOutRight' : '']"
                style="margin: 50px 50px 10px; border-bottom: rgba(26,23,23,0.1) solid 2px">
              <div>
                <span style="font-size: 15px">
                    <b>{{ item.specialName }}</b>
                </span>
                <span style="border: rgba(26,23,23,0.1) solid 1px; margin-left: 15px; color: #707070">
                  {{ item.degree }}
                </span>
              </div>
              <div style="float: right">
                <el-button @click="removeMajor(index,item.id)" type="success" round>
                  <svg viewBox="64 64 896 896" class="" data-icon="star" width="0.8em" height="0.8em"
                       fill="currentColor" aria-hidden="true" focusable="false">
                    <path
                        d="M908.1 353.1l-253.9-36.9L540.7 86.1c-3.1-6.3-8.2-11.4-14.5-14.5-15.8-7.8-35-1.3-42.9 14.5L369.8 316.2l-253.9 36.9c-7 1-13.4 4.3-18.3 9.3a32.05 32.05 0 0 0 .6 45.3l183.7 179.1-43.4 252.9a31.95 31.95 0 0 0 46.4 33.7L512 754l227.1 119.4c6.2 3.3 13.4 4.4 20.3 3.2 17.4-3 29.1-19.5 26.1-36.9l-43.4-252.9 183.7-179.1c5-4.9 8.3-11.3 9.3-18.3 2.7-17.5-9.5-33.7-27-36.3zM664.8 561.6l36.1 210.3L512 672.7 323.1 772l36.1-210.3-152.8-149L417.6 382 512 190.7 606.4 382l211.2 30.7-152.8 148.9z"></path>
                  </svg>
                  已关注
                </el-button>
              </div>
              <br>
              <div style=" color: #707070;padding-bottom: 15px">
                专业代码: {{ item.code }}
                <div
                    style="width: 5px; height: 10px; border-left: 1px solid red; margin-left: 5px; display: inline-block"></div>
                修业年限: {{ item.limitYear }}
                <div
                    style="width: 5px; height: 10px; border-left: 1px solid red; margin-left: 5px; display: inline-block"></div>
                男女比例: {{ item.sexRate }}
              </div>
            </li>
          </ul>
          <div class="pagination-container">
            <el-pagination
                :hide-on-single-page="specialCollect.length===1"
                v-model:current-page="currentPage2"
                :page-size="pageSize"
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
            />
          </div>
        </div>
        <div v-else
             style="height: 700px; display: flex; flex-direction: column; align-items: center; justify-content: center;">
          <img src="https://www.gaokao.cn/static/media/focus_special.aaeead45.png" alt="No data"
               style="margin-bottom: 20px;">
          <p>你没有收藏任何专业</p>
        </div>
      </a-tab-pane>
    </a-tabs>
  </div>
</template>
<style scoped>


.card-container p {
  margin: 0;
}

.card-container > .ant-tabs-card .ant-tabs-content {
  height: 120px;
  margin-top: -16px;
}

.card-container > .ant-tabs-card .ant-tabs-content > .ant-tabs-tabpane {
  padding: 16px;
  background: #fff;
}

.card-container > .ant-tabs-card .ant-tabs-tab,
[data-theme='compact'] .card-container > .ant-tabs-card .ant-tabs-tab {
  background: transparent;
  border-color: transparent;
}

.card-container > .ant-tabs-card .ant-tabs-tab-active,
[data-theme='compact'] .card-container > .ant-tabs-card .ant-tabs-tab-active {
  border: 1px solid #1890ff;
}

.animated-item {
  transition: opacity 0.5s ease;
}

.card-container > .ant-tabs-card .ant-tabs-tab-active,
[data-theme='compact'] .card-container > .ant-tabs-card .ant-tabs-tab-active {
  background: #fff;
  border-color: #fff;
}

#components-tabs-demo-card-top .code-box-demo {
  padding: 24px;
  overflow: hidden;
  background: #f5f5f5;
}

[data-theme='compact'] .card-container > .ant-tabs-card .ant-tabs-content {
  height: 120px;
  margin-top: -8px;
}

[data-theme='dark'] .card-container > .ant-tabs-card .ant-tabs-tab {
  background: transparent;
  border-color: transparent;
}

[data-theme='dark'] #components-tabs-demo-card-top .code-box-demo {
  background: #000;
}

[data-theme='dark'] .card-container > .ant-tabs-card .ant-tabs-content > .ant-tabs-tabpane {
  background: #141414;
}

[data-theme='dark'] .card-container > .ant-tabs-card .ant-tabs-tab-active {
  background: #141414;
  border-color: #141414;
}

.card-container {
  margin-top: -10px;
}

.pagination-container {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}
</style>
