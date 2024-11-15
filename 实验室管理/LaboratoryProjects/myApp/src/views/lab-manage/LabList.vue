<script setup>
import LabMap from "../../components/lab-manage/LabMap.vue";
import {collegeType, labType,CollegesColorType,LabColorType} from "../../util/type.js";
import {onMounted, reactive, ref} from "vue";
import axios from "axios";
import {ElMessage} from "element-plus";

const dialogUpdateVisible=ref(false)
const tableData=ref([])
const fromRef=ref()

const form = reactive({
  title: "",
  capacity: "",
  labType: "",
  collegeType: ""
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
const reset = () => {
  dialogUpdateVisible.value=false
}
onMounted(()=>{
  getList()
})
const getList= async ()=>{
  const res =await axios.get(`/api/labs/list`)
  tableData.value=res.data.data
}
const college_method=(value)=>{
  return collegeType.find(item => item.value === value).label;
}
const lab_method =(value)=>{
  return labType.find(item=>item.value===value).label
}
//预览功能
const currentItem= ref([])
const dialogVisible=ref(false)
const handleLocation=(item)=>{
  dialogVisible.value=true
  currentItem.value=item
}
const handleUpdate =(item)=>{
  dialogUpdateVisible.value=true
  form.title=item.title
  form.capacity=item.capacity
  form.collegeType=item.collegeType
  form.labType=item.labType
  currentItem.value=item
}
const handleConfirm = (item) => {
  fromRef.value.validate( async (valid) => {
    if (valid) {
      const res = await axios.put(`/api/labs/${currentItem.value.id}`,form)
      let {code,msg} =res.data
      if (code===200) ElMessage.success("更新成功")
      else ElMessage.error(msg)
      dialogUpdateVisible.value=false
      await getList()
    }
  })
}
const handleDel = async (item) =>{
  const res =await axios.delete(`/api/labs/${item.id}`)
  const {code,msg} =res.data
  if (code===200) ElMessage.success("删除成功")
  else ElMessage.error(msg)
  await getList()
}
</script>

<template>
  <el-table :data="tableData" style="width: 100%" row-key="id">
    <el-table-column prop="title" label="名称" width="180">
      <template #default="scope">
        <el-link type="primary" @click="handleLocation(scope.row)">{{scope.row.title}}</el-link>
      </template>
    </el-table-column>
    <el-table-column prop="collegeType" label="所属学院" width="180">
      <template #default="scope">
        <el-tag :color="CollegesColorType[scope.row.collegeType]">{{college_method(scope.row.collegeType)}}</el-tag>
      </template>
    </el-table-column>
    <el-table-column prop="labType" label="实验室类型" width="180">
      <template #default="scope">
        <el-tag :color="LabColorType[scope.row.labType]">{{lab_method(scope.row.labType)}}</el-tag>
      </template>
    </el-table-column>
    <el-table-column prop="capacity" label="容纳人数" width="180">
      <template #default="scope">
        <div>{{scope.row.capacity}}</div>
      </template>
    </el-table-column>
    <el-table-column label="操作">
      <template #default="scope">
        <el-button size="small" round @click="handleUpdate(scope.row)" >更新</el-button>
        <el-popconfirm title="你确定要删除吗?" @confirm="handleDel(scope.row)" confirm-button-text="确定" cancel-button-text="取消">
          <template #reference>
            <el-button size="small" type="danger" round >删除</el-button>
          </template>
        </el-popconfirm>
      </template>
    </el-table-column>
  </el-table>
  <el-dialog v-model="dialogVisible" title="实验室位置" :fullscreen="true" v-if="dialogVisible">
    <LabMap :x="currentItem.x" :y="currentItem.y" :t="currentItem.title"></LabMap>
  </el-dialog>
  <el-dialog v-model="dialogUpdateVisible" title="更新实验室" width="500" @close="reset">
    <template #footer>
      <div class="dialog-footer">
        <el-form ref="fromRef" :model="form" :rules="rules" label-width="100px" class="ruleForm" status-icon>
          <el-form-item label="实验室名称" prop="title">
            <el-input v-model="form.title"/>
          </el-form-item>
          <el-form-item label="容纳人数" prop="capacity">
            <el-input v-model.number="form.capacity" type="number"/>
          </el-form-item>
          <el-form-item label="实验室类型" prop="labType">
            <el-select v-model="form.labType" size="large" style="width: 100%" placeholder="请选择实验室类型">
              <el-option v-for="item in labType" :key="item.value" :label="item.label" :value="item.value"/>
            </el-select>
          </el-form-item>
          <el-form-item label="所属学院" prop="collegeType">
            <el-select v-model="form.collegeType" size="large" style="width: 100%" placeholder="请选择实验室类型">
              <el-option v-for="item in collegeType" :key="item.value" :label="item.label" :value="item.value"/>
            </el-select>
          </el-form-item>
        </el-form>
        <el-button @click="reset">取消</el-button>
        <el-button type="primary" @click="handleConfirm">确定</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<style lang="scss" scoped>
:deep(.el-tag){
  color: white;
}
</style>