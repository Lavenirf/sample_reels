<template>
  <el-button type="primary" @click="handleAdd">新增用户</el-button>
  <el-table :data="tableData" style="width: 100%" row-key="id">
    <el-table-column prop="username" label="用户名" width="180">
      <template #default="scope">
        <div>{{scope.row.username}}</div>
      </template>
    </el-table-column>

    <el-table-column prop="roleName" label="角色名称" width="180">
      <template #default="scope">
        <div>{{scope.row.role.roleName}}</div>
      </template>
    </el-table-column>

    <el-table-column label="操作">
      <template #default="scope">
        <el-button size="small" round @click="handleUpdate(scope.row)" :disabled="!!scope.row.isDefault">更新</el-button>
        <el-popconfirm title="你确定要删除吗?" @confirm="handleDel(scope.row)" confirm-button-text="确定" cancel-button-text="取消">
          <template #reference>
            <el-button size="small" type="danger" round :disabled="!!scope.row.isDefault">删除</el-button>
          </template>
        </el-popconfirm>
      </template>
    </el-table-column>
  </el-table>
  <el-dialog v-model="dialogVisible" :title="diTitle" width="500" @close="reset">
    <template #footer>
      <div class="dialog-footer">
        <el-form ref="updateFromRef" :model="form" :rules="rules" label-width="80px" class="ruleForm" status-icon>
          <el-form-item label="用户名" prop="username">
            <el-input v-model="form.username"/>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="form.password"/>
          </el-form-item>
          <el-form-item label="角色" prop="roleId">
            <el-select
                v-model="form.roleId"
                size="large"
                style="width: 240px"
                placeholder="请选择角色"
            >
              <el-option
                  v-for="item in roleList"
                  :key="item.value"
                  :label="item.roleName"
                  :value="item.id"
              />
            </el-select>
          </el-form-item>
        </el-form>
        <el-button @click="reset">取消</el-button>
        <el-button type="primary" @click="handleConfirm">确定</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import axios from "axios";
import {ElMessage} from "element-plus";
const diTitle =ref("")
const currentItem = ref({})
const updateFromRef = ref()
const roleList = ref([])
const form = reactive({
  username:"",
  password:"",
  roleId:"",
  isDefault:""
})
const rules = reactive({
  username:[
    {required: true, message: '请输入账号', trigger: 'blur'},
  ],
  password:[
    {required: true, message: '请输入密码', trigger: 'blur'},
  ]
})
const dialogVisible = ref(false)
const tableData = ref([])
onMounted(() => {
  getList()
  getRoleList()
})
const getList = async () => {
  const res = await axios.get("/api/users/list")
  console.log(res.data)
  tableData.value = res.data.data
}
//新增用户
const handleAdd=()=>{
  diTitle.value="添加用户"
  dialogVisible.value=true
}
//更新
const handleUpdate = (item) => {
  diTitle.value="更新用户"
  dialogVisible.value = true
  currentItem.value = item
  form.username = item.username
  form.roleId =item.roleId
}
//获取角色列表
const getRoleList= async ()=>{
  const res = await axios.get("/api/roles/list")
  roleList.value=res.data.data
}
//校验,弹出框确定方法
const handleConfirm = () => {
  if (diTitle.value==="更新用户"){
    updateFromRef.value.validate(async (valid, fields) => {
      if (valid) {
        const res = await axios.put(`/api/users/${currentItem.value.id}`,{
          username:form.username,
          roleId:form.roleId,
          isDefault:currentItem.value.isDefault
        })
        let {code,msg} = res.data
        if (code===200) {
          reset()
          ElMessage.success("更新成功")
        }
        else ElMessage.error(msg)
        await getList()
      } else {
        console.log('error submit!', fields)
      }
    })
  }else {
    updateFromRef.value.validate(async (valid, fields) => {
      if (valid) {
        const res = await axios.post(`/api/users`,{
          username:form.username,
          password:form.password,
          roleId:form.roleId
        })
        let {code,msg} = res.data
        if (code===200) {
          reset()
          ElMessage.success("新增成功")
        }
        else ElMessage.error(msg)
        await getList()
      } else {
        console.log('error submit!', fields)
      }
    })
  }
}
const reset =()=>{
  form.username = ""
  form.roleId = ""
  dialogVisible.value=false
}
//删除
const handleDel = async (item) => {
  const res = await axios.delete(`/api/users/${item.id}`)
  let {code,msg} = res.data
  if (code===200) ElMessage.success("删除成功")
  else ElMessage.error(msg)
  await getList()
}

</script>
<style lang="scss" scoped>
:deep(.active) {
  color: #f56c6c;
}
</style>
