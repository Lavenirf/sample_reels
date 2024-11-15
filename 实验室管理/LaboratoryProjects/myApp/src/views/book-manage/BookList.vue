<script setup>

import {BookState, ClassType, CollegesColorType, BookStateType, ADMIN, TEACHER} from "../../util/type.js";
import LabMap from "../../components/lab-manage/LabMap.vue";
import {computed, onMounted, reactive, ref} from "vue";
import axios from "axios";
import moment from 'moment'
import {useUserStore} from "../../store/useUserStore.js";
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
const rules = reactive({
  title: [
    {required: true, message: '请输入实验室名称', trigger: 'blur'},
  ],
  capacity: [
    {required: true, message: '请输入容纳人数', trigger: 'blur'},
  ],
  labType: [
    {required: true, message: '请输入实验室类型', trigger: 'blur'},
  ],
  collegeType: [
    {required: true, message: '请输入实验室类型', trigger: 'blur'},
  ]
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
  let res
  if (user.role.roleType === ADMIN) {
    res = await axios.get(`/api/books`)
  } else {
    res = await axios.get(`/api/books?bookUser=${user.id}`)
    // console.log(res.data)
  }
  tableData.value = res.data.data
  console.log(tableData.value)
}
//预览功能
const currentItem = ref([])
const dialogVisible = ref(false)
const handleLocation = (item) => {
  dialogVisible.value = true
  currentItem.value = item.lab
}
//更新数据渲染
const handleUpdate = (item) => {
  dialogUpdateVisible.value = true
  form.bookTime = moment(item.bookTime).toISOString()
  form.bookClass = item.bookClass
  form.bookReason = item.bookReason
  currentItem.value = item
}
//映射课次方法
const class_method = (value) => {
  return ClassType.find(item => item.value === value).label
}
//发起请求
const handleConfirm = (item) => {
  fromRef.value.validate(async (valid) => {
    if (valid) {
      const res = await axios.put(`/api/books/${currentItem.value.id}`, {
        ...form,
        bookUser: user.id,
        labId: currentItem.value.labId
      })
      const {code, msg} = res.data
      if (code === 200) {
        ElMessage.success("修改成功")
        await getList()
      } else {
        ElMessage.error(msg)
      }
      reset()

    }
  })
}
//改变日期的回调
const handleSelectChange = async () => {
  form.bookClass = ""
  const res = await axios.post(`/api/books/select`, {
    bookTime: form.bookTime,
    labId: currentItem.value.id
  })
  disableSelect.value = res.data.data.map(item => item.bookClass)
  console.log(disableSelect.value)
}
//禁用日期选择
const disabledDate = (time) => {
  return time.getTime() < Date.now() || time.getTime() > (Date.now() + 3600 * 1000 * 24 * 7)
}
//撤销
const handleDelete = async (item) => {
  const res = await axios.delete(`/api/books/${item.id}`)
  let {code, msg} = res.data
  if (code === 200) {
    ElMessage.success("删除成功")
    await getList()
  } else ElMessage.error(msg)
}
//创建指令
const vTeacher = {
  mounted(el) {
    if (user.role.roleType === ADMIN) el.remove()
  }
}
const vAdmin = {
  mounted(el) {
    if (user.role.roleType === TEACHER) el.remove()
  }
}
</script>
<template>
  <el-table :data="filterTableDate" style="width: 100%" row-key="id">
    <el-table-column prop="title" label="名称">
      <template #default="scope">
        <el-link type="primary" @click="handleLocation(scope.row)">{{ scope.row.lab.title }}</el-link>
      </template>
    </el-table-column>
    <el-table-column prop="title" label="预约人" v-admin>
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
    <el-table-column label="操作" v-teacher>
      <template #default="scope">
        <el-button v-if="scope.row.bookState===2" size="small" round @click="handleUpdate(scope.row)">更新</el-button>
        <el-button v-else size="small" type="danger" @click="handleDelete(scope.row)" round>撤销</el-button>
      </template>
    </el-table-column>
  </el-table>
  <el-dialog v-model="dialogVisible" title="实验室位置" :fullscreen="true" v-if="dialogVisible">
    <LabMap :x="currentItem.x" :y="currentItem.y" :t="currentItem.title"></LabMap>
  </el-dialog>
  <el-dialog v-model="dialogUpdateVisible" title="更新预约" width="500" @close="reset">
    <template #footer>
      <div class="dialog-footer">
        <el-form ref="fromRef" :model="form" :rules="rules" label-width="100px" class="ruleForm" status-icon>
          <el-form-item label="预约日期" prop="bookTime">
            <el-date-picker v-model="form.bookTime" type="date" placeholder="请选择日期" style="width: 100%"
                            :disabled-date="disabledDate" @change="handleSelectChange"/>
          </el-form-item>
          <el-form-item label="预约课节" prop="bookClass">
            <el-select v-model="form.bookClass" size="large" style="width: 100%" placeholder="请选择课节">
              <el-option v-for="item in ClassType" :key="item.value" :label="item.label" :value="item.value"
                         :disabled="disableSelect.includes(item.value)"/>
            </el-select>
          </el-form-item>
          <el-form-item label="预约原因" prop="bookReason">
            <el-input v-model="form.bookReason" type="textarea"/>
          </el-form-item>
        </el-form>
        <el-button @click="reset">取消</el-button>
        <el-button type="primary" @click="handleConfirm">确定</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<style scoped>

</style>