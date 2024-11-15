<script setup>

import {
  ADMIN,
  APPROVE,
  AUDIT,
  BookState,
  BookStateType,
  ClassType,
  CollegesColorType, REJECT,
  TEACHER
} from "../../util/type.js";
import moment from 'moment'
import {computed, onMounted, reactive, ref} from "vue";
import {useUserStore} from "../../store/useUserStore.js";
import axios from "axios";
import {ElMessage} from "element-plus";

const disableSelect = ref([])
const {user} = useUserStore()
const dialogUpdateVisible = ref(false)
const tableData = ref([])
const fromRef = ref()
const search = ref("")
const form = reactive({
  bookTime: "",
  bookClass: "",
  bookReason: ""
})
onMounted(() => {
  getList()
})
const reset = () => {
  dialogUpdateVisible.value = false
}
//搜索预约人
const filterTableDate = computed(() => {
  return tableData.value.filter((item) => !search.value || item.useName.toLowerCase().includes(search.value.toLowerCase()))
})
//获取当前用户的预约信息
const getList = async () => {
  const res = await axios.get(`/api/books?bookState=${AUDIT}`)
  tableData.value = res.data.data
}
//预览功能
const currentItem = ref([])
const dialogVisible = ref(false)
const handleLocation = (item) => {
  dialogVisible.value = true
  currentItem.value = item.lab
}
//更新数据渲染
const handleUpdate =async (item) => {
  const res =await axios.put(`/api/books/${item.id}`,{
    bookState: APPROVE
  })
  const {code} =res.data
  if (code===200) ElMessage.success('批准成功')
  else ElMessage.error('失败')
  await getList()
}
//映射课次方法
const class_method = (value) => {
  return ClassType.find(item => item.value === value).label
}
//撤销
const handleDelete = async (item) => {
  const res = await axios.put(`/api/books/${item.id}`,{
    bookState: REJECT
  })
  let {code} = res.data
  if (code === 200) {
    ElMessage.success("驳回成功")
    await getList()
  } else ElMessage.error("驳回失败")
}
</script>

<template>
  <el-table :data="filterTableDate" style="width: 100%" row-key="id">
    <el-table-column prop="title" label="名称">
      <template #default="scope">
        <el-link type="primary" @click="handleLocation(scope.row)">{{ scope.row.lab.title }}</el-link>
      </template>
    </el-table-column>
    <el-table-column prop="title" label="预约人">
      <template #header>
        <el-input v-model="search" size="small" placeholder="请搜索"/>
      </template>
      <template #default="scope">
        <div>{{ scope.row.useName }}</div>
      </template>
    </el-table-column>
    <el-table-column prop="title" label="预约时间">
      <template #default="scope">
        <el-tag style="height: 40px;line-height: 20px" :color="CollegesColorType[scope.row.collegeType]">
          {{ moment(scope.row.bookTime).format("YYYY-MM-DD") }}
          <br/>
          {{ class_method(scope.row.bookClass) }}
        </el-tag>
      </template>
    </el-table-column>
    <el-table-column prop="title" label="预约状态">
      <template #default="scope">
        <el-tag :type="BookStateType[scope.row.bookState]">
          {{ BookState[scope.row.bookState] }}
        </el-tag>
      </template>
    </el-table-column>
    <el-table-column label="操作">
      <template #default="scope">
        <el-button size="small" type="primary" round @click="handleUpdate(scope.row)">批准</el-button>
        <el-button size="small" type="danger" @click="handleDelete(scope.row)" round>驳回</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<style scoped>

</style>