<script setup>
import { ref, watch, onMounted } from 'vue';
import {getCodeImg, login, register} from '@/api/login.js';
import { setToken } from '@/utils/auth.ts';
import { useUserStore } from '@/store/useUserStore.js';
import { useDialogVisibleStore } from "@/store/useDialogVisibleStore.js";
import { getUserInfo } from "@/api/userDetails.js";
import { useCollectStore } from "@/store/useCollectStore.js";
import { useVolunteerStore } from "@/store/useVolunteerStore.js";
import {getUserVolunteerFormDetails, getUseVolunteerForm} from "@/api/volunteerForm.js";
import {ElMessage} from "element-plus";
import { ElLoading } from 'element-plus'
const dialogVisibleStore = useDialogVisibleStore();
const volunteerStore = useVolunteerStore();
const collectStore = useCollectStore();
const userStore = useUserStore();
//注册参数
const loading = ref(false);
const registerFormRef = ref();
const uuid= ref("")
//登录参数
const codeUrl = ref("");
const captchaEnabled = ref(true);
const FormRef = ref();
//切换参数
const container = ref(null);
const isSignUp = ref(false);
const togglePanel = (signUp) => {
  isSignUp.value = signUp;
};
//注册业务
const registerForm = ref({
  username: "",
  password: "",
  confirmPassword: "",
  code: "",
  uuid: ""
});

const registerRules = {
  username: [{ required: true, message: '请输入账号', trigger: 'blur' }],
  password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
  confirmPassword: [{ required: true, message: '请确认密码', trigger: 'blur' }],
  code: [{ required: true, message: '请输入验证码', trigger: 'blur' }]
};
//注册
const handleRegister = async () => {
  if (registerForm.value.password !== registerForm.value.confirmPassword) {
    ElMessage.error('两次输入的密码不一致');
    return;
  }
  const res = await register({
    username: registerForm.value.username,
    password: registerForm.value.password,
    code: registerForm.value.code,
    uuid: uuid.value
  });
  if (res.code === 200) {
    ElMessage.success("注册成功")
    togglePanel(false)
  } else {
    ElMessage.error(res.message)
  }
}
//登录业务
const LoginForm = ref({
  username: "",
  password: "",
  rememberMe: false,
  code: "",
  uuid: ""
});
const rules = {
  username: [
    { required: true, message: '请输入账号', trigger: 'blur' },
    { min: 3, max: 15, message: '账号长度应在 3 到 15 个字符之间', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, max: 20, message: '密码长度应在 6 到 20 个字符之间', trigger: 'blur' }
  ]
};
const submitForm = async () => {
  if (!FormRef.value) return
  FormRef.value.validate(async (valid) => {
    if (valid) {
      try {
        const res = await login(LoginForm.value.username, LoginForm.value.password, LoginForm.value.code, uuid.value)
        setToken(res.data.token)
        resetForm()
        const loadingInstance = ElLoading.service({
          lock: true,
          text: 'Loading',
          background: 'rgba(0, 0, 0, 0.7)',
        })
        setTimeout(() => {
          loadingInstance.close()
        }, 1500)
        dialogVisibleStore.setDialogVisibleLogin(false)
        await getUserData()
        userStore.toggleShowLogin(true)
        ElMessage.success("登录成功")
      } catch (error) {
        console.error('登录失败:', error)
      }
    } else {
      console.log('信息提错误!')
    }
  })
};
//设置数据
const getUserData = async () => {
  const info = await getUserInfo()
  //设置学校收藏
  collectStore.setSchoolsCollectList(info.data.schoolsCollectList)
  //设置专业收藏
  collectStore.setSpecialCollectList(info.data.specialCollectList)
  //删除多余数据后存储user信息
  delete info.data.specialCollectList
  delete info.data.specialCollectList
  userStore.setData(info.data)
  //如果用户已有使用的志愿表，则获取该志愿表
  await getVolunteer(info.data)
}
//获取志愿表
const getVolunteer = async (value) => {
  if (value.volunteerFormId){
    const res = await getUseVolunteerForm(value.volunteerFormId)
    volunteerStore.setVolunteerForm(res.data)
    await getVolunteerDataList(value.volunteerFormId)
  }
};
//获取志愿单的详细信息
const getVolunteerDataList = async (id) => {
    const res1 = await getUserVolunteerFormDetails(id, 1)
    const res2 = await getUserVolunteerFormDetails(id, 2)
    if (res1.code === 200 && res2.code === 200) {
      volunteerStore.setVolunteerDetails1(res1.data)
      volunteerStore.setVolunteerDetails2(res2.data)
  }
}
//获取验证码
const getCode = async () => {
  try {
    const res = await getCodeImg()
    captchaEnabled.value = res.data.captchaEnabled !== undefined ? res.data.captchaEnabled : true
    if (captchaEnabled.value) {
      codeUrl.value = "data:image/gif;base64," + res.data.img
      uuid.value = res.data.uuid
    }
  } catch (error) {
    console.error('获取验证码失败:', error)
  }
};
//清空
const resetForm = () => {
  if (!FormRef.value) return
  FormRef.value.resetFields()
};
watch(() => isSignUp.value, (newValue, oldValue) => {
  getCode();
});

onMounted(() => {
  // 获取初始验证码
  getCode();
});
</script>

<template>
  <el-dialog class="dialog" style="width: 800px;height: 420px; padding: 15px 0 0 0 !important;border-radius: 0.7rem" v-model="dialogVisibleStore.dialogVisibleLogin" center>
    <div style="margin-top: -31px" :class="{ 'container': true, 'right-panel-active': isSignUp }">
      <div class="container__form container--signup" v-show="isSignUp">
        <el-form :model="registerForm" :rules="registerRules" class="form" id="form1"  @keyup.enter="handleRegister">
          <h2 class="form__title">注册</h2>
          <el-form-item class="form-item" prop="username">
            <el-input v-model="registerForm.username" type="text" placeholder="用户名" class="input" />
          </el-form-item>
          <el-form-item  class="form-item" prop="password">
            <el-input v-model="registerForm.password" type="password" placeholder="密码" class="input" />
          </el-form-item>
         <el-form-item  class="form-item" prop="confirmPassword">
           <el-input v-model="registerForm.confirmPassword" type="password" placeholder="再次输入密码" class="input" />
         </el-form-item>
          <el-form-item class="form-item" v-if="captchaEnabled" prop="code">
            <el-input v-model="registerForm.code" type="text" placeholder="验证码" style="width: 60%;" class="input" />
            <div style="width: 40%;height:100%;">
              <img alt="" :src="codeUrl" @click="getCode" style="height: 70%;width:100%;margin-top: 10px;margin-left: 5px"/>
            </div>
          </el-form-item>
          <el-button class="btn" @click="handleRegister" :loading="loading">注册</el-button>
        </el-form>
      </div>

      <div class="container__form container--signin" v-show="!isSignUp">
        <el-form ref="FormRef" :model="LoginForm" :rules="rules" class="form" id="form2" @keyup.enter="submitForm">
          <h2 class="form__title">登录</h2>
          <el-form-item class="form-item" prop="username">
            <el-input v-model="LoginForm.username" type="text" placeholder="用户名" class="input" />
          </el-form-item>
          <el-form-item class="form-item" prop="password">
            <el-input v-model="LoginForm.password" type="password" placeholder="密码" class="input" />
          </el-form-item>
          <el-form-item class="form-item" v-if="captchaEnabled" prop="code">
            <el-input v-model="LoginForm.code" type="text" placeholder="验证码" style="width: 60%;" class="input" />
            <div style="width: 40%;height:100%;">
              <img alt="" :src="codeUrl" @click="getCode" style="height: 70%;width:100%;margin-top: 10px;margin-left: 5px"/>
            </div>
          </el-form-item>
          <a href="#" class="link">忘记密码?</a>
          <el-button  class="btn" @click="submitForm">登录</el-button>
        </el-form>

      </div>

      <div class="container__overlay">
        <div class="overlay">
          <div class="overlay__panel overlay--left">
            <button class="btn" @click="togglePanel(false)">登录</button>
          </div>
          <div class="overlay__panel overlay--right">
            <button class="btn" @click="togglePanel(true)">注册</button>
          </div>
        </div>
      </div>
    </div>
  </el-dialog>
</template>

<style scoped lang="scss">
:deep(.el-input__wrapper) {
  box-shadow: none;
}
:deep(.el-dialog__header){
  background: black;
  z-index: 999;
}
:global(.el-dialog__headerbtn){
  z-index: 999;
}
:global(.el-dialog__headerbtn .el-dialog__close){
  color: #000;
}
:root {
  font-size: 16px;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
  Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}

.form__title {
  font-weight: 300;
  margin: 0 0 1.25rem;
}

.link {
  color: #333;
  font-size: 0.9rem;
  margin: 1.5rem 0;
  text-decoration: none;
}
.form-item{
  width: 100%;
  margin-bottom: 0;
}
.container {
  border-radius:  0.7rem;
  height:420px;
  max-width: 800px;
  overflow: hidden;
  position: relative;
  width: 100%;
}

.container__form {
  height: 100%;
  position: absolute;
  top: 0;
  transition: all 0.6s ease-in-out;
}

.container--signin {
  left: 0;
  width: 50%;
  z-index: 2;
}

.container.right-panel-active .container--signin {
  transform: translateX(100%);
}

.container--signup {
  left: 0;
  opacity: 0;
  width: 50%;
  z-index: 1;
}

.container.right-panel-active .container--signup {
  animation: show 0.6s;
  opacity: 1;
  transform: translateX(100%);
  z-index: 5;
}

.container__overlay {
  height: 100%;
  left: 50%;
  overflow: hidden;
  position: absolute;
  top: 0;
  transition: transform 0.6s ease-in-out;
  width: 50%;
  z-index: 100;
}

.container.right-panel-active .container__overlay {
  transform: translateX(-100%);
}

.overlay {
  background:url("/login/bg.png")  no-repeat fixed center;
  background-size: cover;
  height: 100%;
  left: -100%;
  position: relative;
  transform: translateX(0);
  transition: transform 0.6s ease-in-out;
  width: 200%;
}

.container.right-panel-active .overlay {
  transform: translateX(50%);
}

.overlay__panel {
  align-items: center;
  display: flex;
  flex-direction: column;
  height: 100%;
  justify-content: center;
  position: absolute;
  text-align: center;
  top: 0;
  transform: translateX(0);
  transition: transform 0.6s ease-in-out;
  width: 50%;
}

.overlay--left {
  transform: translateX(-20%);
}

.container.right-panel-active .overlay--left {
  transform: translateX(0);
}

.overlay--right {
  right: 0;
  transform: translateX(0);
}

.container.right-panel-active .overlay--right {
  transform: translateX(20%);
}

.btn {
  background-color: #0367a6;
  background-image: linear-gradient(90deg, #0367a6 0%, #008997 74%);
  border-radius: 20px;
  border: 1px solid #0367a6;
  color: #e9e9e9;
  cursor: pointer;
  font-size: 0.8rem;
  font-weight: bold;
  letter-spacing: 0.1rem;
  padding: 0.9rem 4rem;
  text-transform: uppercase;
  transition: transform 80ms ease-in;
  height: 47.07px;
}

.form>.btn {
  margin-top: 1.5rem;
}

.btn:active {
  transform: scale(0.95);
}

.btn:focus {
  outline: none;
}

.form {
  background-color: #e9e9e9;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  padding: 0 3rem;
  height: 100%;
  text-align: center;
}

.input {
  background-color: #fff;
  border: none;
  //padding: 0.9rem 0.9rem;
  margin: 0.5rem 0;
  width: 100%;
  height: 35px;
  border-radius: 5px;
}

@keyframes show {

  0%,
  49.99% {
    opacity: 0;
    z-index: 1;
  }

  50%,
  100% {
    opacity: 1;
    z-index: 5;
  }
}
</style>
