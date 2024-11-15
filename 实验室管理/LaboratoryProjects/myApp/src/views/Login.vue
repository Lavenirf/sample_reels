<script setup>
import {useUserStore} from "../store/useUserStore.js";
import {useRouter} from "vue-router";
import {reactive, ref} from "vue";
import { loadSlim } from "tsparticles-slim";
import {config} from "../util/config.js";
import {ElMessage} from "element-plus";
import axios  from "axios";
const {changeUser,changeToken} =useUserStore()
const router =useRouter()
const ruleFromRef=ref()
const ruleForm = reactive({
  username:"",
  password:""
})
const rules = reactive({
  username:[
    { required: true, message: '请输入用户名', trigger: 'blur' },
  ],
  password:[
    { required: true, message: '请输入密码', trigger: 'blur' },
  ],
})
//登录回调
const submitForm= async (formEl)=>{
  if (!formEl) return
  await formEl.validate(async (valid, fields) => {
    if (valid) {
      const res = await axios.post(`/api/users/login`,ruleForm)
      let {code,data,msg} =res.data
      if (code===200){
        console.log(data.token)
        changeUser(data.user)
        localStorage.setItem("Authorization",data.token)
        await router.push("/")
      }else {
        ElMessage.error(msg)
      }
      console.log(res.data)
    } else {
      console.log('error submit!', fields)
    }
  })
}
//粒子显示
const particlesInit = async engine => {
  //await loadFull(engine);
  await loadSlim(engine);
};

</script>

<template>
  <vue-particles id="tsparticles" :particlesInit="particlesInit" :options="config"/>
  <div class="formContainer">
    <h1>智慧实验室平台</h1>
    <el-form ref="ruleFromRef" style="max-width: 600px" :model="ruleForm" :rules="rules" label-width="120px" class="ruleForm"
             status-icon
    >
      <el-form-item label="账号" prop="username">
        <el-input v-model="ruleForm.username" />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="ruleForm.password" type="password"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm(ruleFromRef)">
          登录
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<style lang="scss" scoped>
.formContainer {
  width: 500px;
  height: 300px;
  position: fixed;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  color: white;
  text-shadow: 2px 2px 5px black;
  text-align: center;
  z-index: 100;
  .ruleForm {
    margin-top: 50px;
  }

  h3 {
    font-size: 40px;
  }

  :deep(.el-form-item__label) {
    color: white;
    font-size: 18px;
    font-weight: 700;
  }

}
</style>