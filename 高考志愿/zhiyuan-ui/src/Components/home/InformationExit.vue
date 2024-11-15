<script setup>

import {cities, grade, region_NotAll} from "@/static";
import {useUserStore} from "@/store/useUserStore.js";
import {onMounted, reactive, ref} from "vue";
import {EditPen} from "@element-plus/icons-vue";
import {ElMessage} from "element-plus";
import {getUserInfo, updateUserInfo} from "@/api/userDetails.js";
import router from "@/router/index.js";

const userStore = useUserStore()
let ruleForm = ref({})
const courseInformation = ref(['物理','化学','生物'])
const ruleFormRef = ref()
const rules = reactive({
  examinationArea: [
    {required: true, message: '请选择高考地区', trigger: 'blur'},
  ],
  fraction: [
    {required: true, message: '请填写分数', trigger: 'blur'},
    {
      validator: (rule, value, callback) => {
        if (value < 0 || value > 750) {
          callback(new Error('分数范围应在0-750之间'));
        } else {
          callback();
        }
      },
      trigger: 'blur'
    }
  ],
  ranking: [
    {required: true, message: '请填写位次', trigger: 'blur'},
    {
      validator: (rule, value, callback) => {
        if (value <= 0) {
          callback(new Error('位次必须大于0'));
        } else {
          callback();
        }
      },
      trigger: 'blur'
    }
  ],
})
const disableExit=ref(false)
const handleEdit = async () => {
  if (courseInformation.value.length !== 3) {
    ElMessage({
      message: "请选择三门学科",
      type: "error",
      offset: window.screen.height / 3,
      duration: 1500
    })
    return
  }
  ruleForm.value.subject1 = courseInformation.value[0]
  ruleForm.value.subject2 = courseInformation.value[1]
  ruleForm.value.subject3 = courseInformation.value[2]
  ruleFormRef.value.validate(async (valid) => {
    if (valid) {
      const res = await updateUserInfo(ruleForm.value)
      if (res.data === 2) {
        await getUserData()
        await ElMessage.success("修改成功")
        disableExit.value=false
      } else {
        ElMessage.error("服务器忙，请稍后再试")
      }
    } else {
      console.log(ruleForm.value)
      console.log('表单验证失败');
      return false;
    }
  });
}
const getUserData = async () => {
  const res = await getUserInfo()
  userStore.setData(res.data)
}
onMounted(() => {
  if (userStore.showLogin){
    ruleForm.value =userStore.getData
    if(ruleForm.value.subject1!==null&&ruleForm.value.subject1!==''){
      courseInformation.value[0] = ruleForm.value.subject1
    }
    if(ruleForm.value.subject2!==null&&ruleForm.value.subject2!==''){
      courseInformation.value[1] = ruleForm.value.subject2
    }
    if(ruleForm.value.subject3!==null&&ruleForm.value.subject3!==''){
      courseInformation.value[2] = ruleForm.value.subject3
    }
  }
})
</script>

<template>
  <div class="box">
    <div class="title">
      <span>模拟志愿填报</span>
      <el-link v-if="!disableExit" @click="disableExit=true" class="exit" :disabled="!userStore.showLogin">修改</el-link>
      <el-link v-else @click="disableExit=false" class="exit">取消</el-link>
    </div>
    <el-form
        ref="ruleFormRef"
        style="max-width: 500px"
        :model="ruleForm"
        :rules="rules"
        class="demo-ruleForm"
        status-icon
    >
      <div class="left" style="margin-top: 13px;">
        <div>
          <span class="subtitle">选科</span>
          <span style="font-size: 14px;color: #999">（6选3）</span>
        </div>
        <el-form-item class="subTop">
          <el-checkbox-group v-model="courseInformation" size="large" :max="3">
            <el-checkbox-button  v-for="city in cities" :key="city" :value="city">
              {{ city }}
            </el-checkbox-button>
          </el-checkbox-group>
        </el-form-item>
      </div>
      <div class="left" style="margin-top: 10px">
        <span class="subtitle">分数</span>
        <div class="display flex" style="margin-top: 13px;margin-left: 2px">
          <el-form-item prop="fraction">
            <el-input :disabled="!disableExit" v-model="ruleForm.fraction" style="width:150px">
              <template #prefix>
                <el-icon class="el-input__icon">
                  <EditPen/>
                </el-icon>
              </template>
              <template #suffix>
                <span>分</span>
              </template>
            </el-input>
          </el-form-item>
          <el-form-item prop="ranking" style="margin-left: 20px">
            <el-input :disabled="!disableExit" v-model="ruleForm.ranking" style="width: 150px">
              <template #prefix>
                <el-icon class="el-input__icon">
                  <EditPen/>
                </el-icon>
              </template>
              <template #suffix>
                <span>位</span>
              </template>
            </el-input>
          </el-form-item>
        </div>
      </div>
      <div class="left flex">
        <span class="subtitle" style="margin-top: 10px">高考地区</span>
        <el-form-item class="left" prop="examinationArea" style="margin-top: 5px">
          <el-select :disabled="!disableExit" v-model="ruleForm.examinationArea" placeholder="请选择地区" style="width: 200px">
            <el-option
                v-for="item in region_NotAll"
                :key="item"
                :label="item"
                :value="item"
            />
          </el-select>
        </el-form-item>
      </div>
      <div class="left flex" style="margin-top: 10px">
        <span class="subtitle subTop">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级</span>
        <el-form-item class="left" prop="grade">
          <el-select :disabled="!disableExit" placeholder="请选择年级" v-model="ruleForm.grade" style="width: 200px">
            <el-option
                v-for="item in grade"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            />
          </el-select>
        </el-form-item>
      </div>
      <div class="left flex" style="margin-top: 10px">
        <span class="subtitle" style="margin-top: 5px">所属高中</span>
        <el-form-item class="left" prop="schoolName" >
          <el-input :disabled="!disableExit" v-model="ruleForm.schoolName" style="width: 200px"/>
        </el-form-item>
      </div>
    </el-form>
    <div>
      <el-link class="link" v-if="!disableExit" @click="router.push('/intelligentRecommendation')">
        智慧推荐大学
      </el-link>
      <el-link class="link" @click="handleEdit" v-else>确定</el-link>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.box {
  height: 100%;
  width: 100%;
  background: -webkit-linear-gradient(#F5F8FB 0%, #FBFEFF 100%);
  border-radius: 5px;
  .title {
    height: 50px;
    margin-left: 15px;
    padding-top: 15px;
    font-size: 17px;
    font-weight: bold;
    display: flex;
    justify-content: space-between;
    .exit{
      width: 65px;
      height: 35px;
      background:#3949AB;
      margin-right: 20px;
      color: #fff;
      font-size: 14px;
      border-radius: 5px;
    }
  }
  .link {
    display: block;
    width: 70%;
    height: 45px;
    background: #3F51B5;
    margin: 5px auto;
    text-align: center;
    border-radius: 10px;
    font-size: 15px;
    color: #fff;
    line-height: 45px;
  }
}

.left {
  margin-left: 20px;
}

.subtitle {
  font-size: 15px;
}

.subTop {
  margin-top: 5px;
  margin-left: 2px;
}

.flex {
  display: flex;
}

.el-checkbox-group {
  display: flex;
  flex-wrap: wrap;

  .el-checkbox-button:first-child {
    margin-left: 0;
  }

  .el-checkbox-button:nth-child(4) {
    margin-left: 0;
  }

  .el-checkbox-button {
    height: 34px;
    margin: 10px 12px;
  }
}

:deep(.el-checkbox-button__inner) {
  width: 100px;

}

:deep(.el-checkbox-button) {
  --el-checkbox-button-checked-bg-color: #2248E0 !important;
  --el-checkbox-button-checked-border-color: #fff !important;
  --el-checkbox-button-checked-text-color: #fff !important;
}
</style>
