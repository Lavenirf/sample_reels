<script setup>
import {onMounted, ref} from "vue";
import axios from "axios";
import {
  Location,
  User,
  HomeFilled,
  Key,
  List,
  UploadFilled
} from '@element-plus/icons-vue'
import {useRoute} from "vue-router";
import {useUserStore} from "../../store/useUserStore.js";

const datalist = ref([])
const route = useRoute()

onMounted(() => {
  getList()
})
const getList = async () => {
  const res = await axios.get("/api/rights/list")
  // console.log(res.data)
  datalist.value = res.data.data
}
const {user} = useUserStore()
const checkAuth = (path) => {
  return user.role.rights.includes(path)
}
//图标映射
const mapIcons = {
  Location,
  User,
  HomeFilled,
  Key,
  List,
  UploadFilled
}
</script>

<template>
  <el-aside width="200px">
    <el-scrollbar>
      <el-menu :default-active="route.fullPath" class="el-menu-vertical-demo" style="height:100vh;" :router="true">

        <template v-for="data in datalist" :key="data.path">
          <el-sub-menu :index="data.path" v-if="data.children.length && checkAuth(data.path)">
            <template #title>
              <el-icon>
                <component :is="mapIcons[data.icon]"></component>
              </el-icon>
              <span>{{ data.title }}</span>
            </template>
            <template v-for="item in data.children" :key="item.path">
              <el-menu-item :index="item.path" v-if="checkAuth(item.path)">
                <el-icon>
                  <component :is="mapIcons[item.icon]"></component>
                </el-icon>
                <span>{{ item.title }}</span>
              </el-menu-item>

            </template>
          </el-sub-menu>
          <el-menu-item :index="data.path" v-else-if="checkAuth(data.path)">
            <el-icon>
              <component :is="mapIcons[data.icon]"></component>
            </el-icon>
            <span>{{ data.title }}</span>
          </el-menu-item>
        </template>
      </el-menu>
    </el-scrollbar>
  </el-aside>
</template>

<style scoped>

</style>