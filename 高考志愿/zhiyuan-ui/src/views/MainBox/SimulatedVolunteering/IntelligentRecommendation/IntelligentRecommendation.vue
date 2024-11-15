<script setup>
import {ref,watch} from "vue"
import {Collection, MessageBox, Search} from "@element-plus/icons-vue";
import InstitutionsList from "@/Components/SimulatedVolunteering/InstitutionsList/InstitutionsList.vue";
import {useUserStore} from "@/store/useUserStore.js";
import {useRecommendedParameters} from "@/store/useRecommendedParameters.js";

const userStore = useUserStore()
const schoolName=ref('')
const recommendedParameters=useRecommendedParameters()
const ListDom=ref(null)
const handleSearch =()=>{
  ListDom.value.handleSearch()
}
const handleQuery=()=>{
  ListDom.value.handleQuery()
}
watch(() => [recommendedParameters.getInstitutionLevel,recommendedParameters.getInstitutionType,recommendedParameters.getRegion], () => {
  handleQuery()
})
</script>

<template>
  <el-container v-if="userStore.showLogin">
    <el-header class="header">
      <el-row justify="space-between">
        <el-col :span="10">
          <div style="width: 400px">
            <router-link to="/chooseInstitution">
              <el-button class="button" color="#f8f8f8" type="primary" >
                <el-icon>
                  <Collection/>
                </el-icon>
                <span>选院校</span>
              </el-button>
            </router-link>
            <router-link to="/chooseMajor">
              <el-button class="button" color="#f8f8f8">
                <el-icon>
                  <MessageBox/>
                </el-icon>
                <span>选专业</span>
              </el-button>
            </router-link>
          </div>
        </el-col>
        <el-col :span="10">
          <div style="width: 320px; margin-left: 20%">
            <el-input
                v-model="schoolName"
                style="width: 240px"
                size="large"
                placeholder="请输入院校名称"
                :prefix-icon="Search"
            />
            <el-button @click="handleSearch" size="large" style="margin: 0" color="#3F51B5">搜索</el-button>
          </div>
        </el-col>
      </el-row>
      <router-view/>
    </el-header>
    <el-main>
      <div>
        <InstitutionsList ref="ListDom" :search="schoolName"/>
      </div>
    </el-main>
  </el-container>
</template>

<style scoped>
.button{
  height: 40px;
  width: 100px;
  border-radius: 10px;
}
.el-header {
  height: auto;
}

.el-main {
  margin-top: -5px;
}

:deep(a) {
  margin-left: 15px;
}

.router-link-active .el-button {
  background: #1d39c4;
  color: #fff;
}
</style>
