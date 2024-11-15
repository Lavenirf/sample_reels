<script setup>
import {LayRadio, LayRadioGroup,LayProgress} from "@layui/layui-vue";
import {listTopic, judgment} from "@/api/topic.js";
import {ref, onMounted, watchEffect, computed} from 'vue';
import {useUserStore} from "@/store/useUserStore.js";
import {ElMessage} from "element-plus";
import router from "@/router/index.js";

const page = ref(1);
const UserAnswers = ref([]);
const Form = ref([]);
const radio1 = ref([]);
const userStore = useUserStore()
const queryParams = {
  pageNum: 1,
  pageSize: 6,
  topicContent: null,
  topicType: null,
  topicAnswer: null,
};
const showText = ref(true)
const handleUpload = async () => {
  let count = 0
  Form.value.forEach(() => count++)
  if (count < page.value * 6) {
    ElMessage.warning('请检查当前页题目是否完成！')
  } else {
    const res = await judgment(Form.value)
    console.log(res)
    userStore.setHollandResultId(res.data.hollandResultId)
    await router.go(0)
  }
}

const selectValue = (index, topicId, topicType, value) => {
  if (value != null) {
    if (!Form.value[index]) {
      Form.value[index] = {}
    }
    Form.value[index].topicId = topicId
    Form.value[index].topicType = topicType
    Form.value[index].value = value
    radio1[index] = topicId - 4
  }
};

const listTopicData = async (queryParams) => {
  const res = await listTopic(queryParams);
  if (res && res.data) {
    UserAnswers.value = res.data
    restoreSelections()
  }
};

const restoreSelections = () => {
  if (UserAnswers.value.length > 0) {
    UserAnswers.value.forEach((item, index) => {
      if (!radio1.value[index]) {
        radio1[index] = null // 初始化radio1数组元素
      }
      if (Form.value[index] && Form.value[index].topicId === item.topicId) {
        radio1.value[index] = Form.value[index].value // 恢复之前的选择
      }
    });
  }
};

const pagination = () => {
  let count = 0
  Form.value.forEach(() => count++)
  if (count < page.value * 6) {
    ElMessage.warning("请检查当前页题目是否完成！")
  } else {
    page.value++
    queryParams.pageNum = page.value
    queryParams.pageSize = 6
    listTopicData(queryParams)
    document.documentElement.scrollTop = 0;
  }
}
const prevQuestion = () => {
  if (page.value > 1) {
    page.value--;
    queryParams.pageNum = page.value;
    queryParams.pageSize = 6;
    listTopicData(queryParams);
    document.documentElement.scrollTop = 0;
  }
};

onMounted(() => {
  radio1.value = new Array(60).fill(null); // 初始化radio1数组
  listTopicData(queryParams);
});

watchEffect(() => {
  if (UserAnswers.value.length === 0) {
    listTopicData(queryParams);
  }
})
const submitComputed = computed(() => page.value < 10)
const scheduleComputed =computed(()=>(page.value-1)*10)
</script>

<template>
  <el-row justify="space-around" class="row">
    <el-col :span="24" class="title">
      <svg aria-hidden="true" viewBox="0 0 1920 155">
        <polygon points="1920 823 0 823 0 0 396 101 835 31 1574 100 1920 9 1920 823" style="fill: #33a474;"></polygon>
      </svg>
      <p>职 业 倾 向 测 试</p>
    </el-col>
    <el-col class="centerTitle" :span="6">
      <div class="ep-bg-purple" style=" background-color: rgba(120,209,238,0.6);">
        <img src="/Holland/test-header-2.svg" alt="">
        <br>
      </div>
      <div class="describe">
        <span>做你自己，诚实回答，<br>找出你的性格类型。</span>
      </div>
    </el-col>
    <el-col class="centerTitle" :span="6">
      <div class="ep-bg-purple" style="background-color: rgba(236,229,213,0.7)">
        <img style="width:130px;margin-top: 5px" src="/Holland/theory.svg" alt="">
        <br>
      </div>
      <div class="describe">
        <span>了解你的性格类型如何在你生活的各个领域中产生影响。</span>
      </div>
    </el-col>
    <el-col class="centerTitle" :span="6">
      <div class="ep-bg-purple" style="background-color: rgba(237,222,244,0.7)">
        <img style="width:130px;margin-top: 5px" src="/Holland/exercise.svg" alt="">
        <br>
      </div>
      <div class="describe">
        <span class="describe">借助我们的精选材料，<br>塑造你想要的自我。</span>
      </div>
    </el-col>
    <el-col :span="24">
      <lay-progress style="width: 65%;margin: 30px auto" :percent="scheduleComputed" :show-text="showText"></lay-progress>
    </el-col>
  </el-row>
  <div class="topic">
    <div v-if="UserAnswers.length > 0">
      <template v-for="(item, index) in UserAnswers" :key="item.topicId">
        <el-row justify="center">
          <el-col :span="18">
            <div class="CenterEffect">
              <p>{{ item.topicContent }}</p>
            </div>
          </el-col>
        </el-row>
        <el-row justify="center">
          <el-col :span="24" style="text-align: center">
            <lay-radio-group style="justify-content: center" v-model="radio1[index+((page-1)*6)]" name="action"
                             @change="selectValue(index+((page-1)*6),item.topicId, item.topicType, $event)">
              <span class="agree">同意</span>
              <lay-radio id="colorLeft" size="lg" value="-3"/>
              <lay-radio id="colorLeft" value="-2"/>
              <lay-radio id="colorLeft" size="sm" value="-1"/>
              <lay-radio size="xs" value="0"/>
              <lay-radio id="colorRight" size="sm" value="1"/>
              <lay-radio id="colorRight" value="2"/>
              <lay-radio id="colorRight" size="lg" value="3"/>
              <span class="oppose">反对</span>
            </lay-radio-group>
          </el-col>
        </el-row>
      </template>
    </div>
    <div v-else>
      <div id="cssLoader1" class="main-wrap">
        <div class="child-common child1"></div>
        <div class="child-common child2"></div>
        <div class="child-common child3"></div>
        <div class="child-common child4"></div>
        <div class="child-common child5"></div>
        <div class="child-common child6"></div>
      </div>
    </div>
  </div>
  <el-row v-if="UserAnswers.length > 0" justify="center" style="margin-top: 15px;padding-bottom: 20px">
    <el-col :span="24" style="text-align: center">
      <el-button class="btn1" size="large" @click="prevQuestion">上一页</el-button>
      <el-button class="btn2" size="large" v-if="submitComputed" @click="pagination">下一页</el-button>
      <el-button class="btn2" size="large" v-else @click="handleUpload">提交</el-button>
    </el-col>
  </el-row>
</template>

<style scoped lang="scss">
.main-wrap {
  margin-top: 30px;
  color: #2b4661;
  height: auto;
  display: flex;
  justify-content: center;
  align-items: center;
  padding-bottom: 20px;
}

.main-wrap--white {
  background-color: #ffffff;
}

#cssLoader1.main-wrap .child-common {
  width: 8px;
  height: 50px;
  /*margin-right:5px;*/
  margin-right: 3px;
  background-color: #2b4661;
  -webkit-animation: animate1 1s infinite;
  animation: animate1 1s infinite;
  float: left;
}

#cssLoader1.main-wrap .child1 {
  margin-right: 3px;
}

#cssLoader1.main-wrap .child6 {
  -webkit-animation-delay: 0.5s;
  animation-delay: 0.5s;
}

#cssLoader1.main-wrap .child5 {
  -webkit-animation-delay: 0.4s;
  animation-delay: 0.4s;
}

#cssLoader1.main-wrap .child4 {
  -webkit-animation-delay: 0.3s;
  animation-delay: 0.3s;
}

#cssLoader1.main-wrap .child3 {
  -webkit-animation-delay: 0.2s;
  animation-delay: 0.2s;
}

#cssLoader1.main-wrap .child2 {
  -webkit-animation-delay: 0.1s;
  animation-delay: 0.1s;
}

@-webkit-keyframes animate1 {
  50% {
    -ms-transform: scaleY(0);
    -webkit-transform: scaleY(0);
    transform: scaleY(0);

  }
}

@keyframes animate1 {
  50% {
    -ms-transform: scaleY(0);
    -webkit-transform: scaleY(0);
    transform: scaleY(0);
  }
}

.btn1 {
  color: black;
  border-radius: 10px;
  border: 2px solid transparent;
  background-clip: padding-box, border-box;
  background-origin: padding-box, border-box;
  background-image: linear-gradient(to right, #F6FFF4, #F6FFF4),
  linear-gradient(#44D095 0%, #57B199 43.54%);
}

.btn2 {
  margin-left: 80px;
  color: black;
  border-radius: 10px;
  border: 2px solid transparent;
  background-clip: padding-box, border-box;
  background-origin: padding-box, border-box;
  background-image: linear-gradient(to right, #FFF7F8, #FFF7F8),
  linear-gradient(#C8B7D0 0%, #A97CBE 100%)
}

.row {
  border-radius: 10px;

  .title {
    padding-top: 20px;

    svg {
      position: absolute;
      width: 100%;
      height: 100%;
      top: 80px;
      left: 0;
      z-index: -1;
    }

    p {
      font-size: 60px;
      text-align: center;
      color: #fff;
      font-weight: bold;
    }
  }
}

.CenterEffect {
  margin: 0 auto;
  text-align: center;

  p {
    font-size: 25px;
    font-weight: bold;
    padding-top: 25px;
  }
}

.topic {
  padding-top: 15px;
  background: -webkit-linear-gradient(#EDF3F1 6.15%, #FFFEFF 100%);

}

.layui-radio-group {
  display: flex;
}

.agree {
  font-size: 20px;
  padding-right: 15px;
  font-weight: bold;
  display: inline-block;
  margin-top: 5px;
}

.oppose {
  font-size: 20px;
  font-weight: bold;
  display: inline-block;
  margin-top: 5px;
}

:deep(#colorLeft) {
  .layui-icon {
    color: #33a474;
  }
}

:deep(#colorRight) {
  .layui-icon {
    color: #88619a;
  }
}

.layui-radio-group {
  margin-top: 40px;
  height: 80px;
}

:deep(.layui-radio[size=lg]) {
  .layui-icon {
    font-size: 60px;
    font-weight: 500;
  }
}

:deep(.layui-radio[size=md]) {
  .layui-icon {
    font-size: 50px;
    font-weight: 500;
  }
}

:deep(.layui-radio[size=sm]) {
  .layui-icon {
    font-size: 40px;
    font-weight: 500;
  }
}

:deep(.layui-radio[size=xs]) {
  .layui-icon {
    font-size: 34px;
    font-weight: 500;
  }
}

.el-row.is-justify-space-around {
  background: linear-gradient(to bottom, #33a474 43%, #f9f9f9 80%);

  .centerTitle {
    text-align: center;
    padding-top: 20px;

    .ep-bg-purple {
      border-radius: 15px 15px 0 0;
      background-color: rgba(120, 209, 238, 0.6);

      img {
        width: 130px;
        margin-top: 5px;
      }
    }

    .describe {
      margin-top: 15px;
      font-size: large;
      font-weight: bolder;
    }
  }
}
</style>
