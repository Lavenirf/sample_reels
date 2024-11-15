<template>
  <el-table :data="tableData" style="width: 100%" row-key="id">
    <el-table-column prop="roleName" label="角色名称" width="180">
      <template #default="scope">
        <el-popover placement="right" title="权限详情" :width="200" trigger="hover"
                    @before-enter="handleHover(scope.row)"
        >
          <template #reference>
            <el-button class="m-2">{{ scope.row.roleName }}</el-button>
          </template>
          <template #default>
            <el-tree
                style="max-width: 600px"
                :data="rightList"
                :props="defaultProps"
                :default-expand-all="true"
                :render-content="renderContent"
            />
          </template>
        </el-popover>
      </template>
    </el-table-column>

    <el-table-column label="操作">
      <template #default="scope">
        <el-button size="small" round @click="handleUpdate(scope.row)">更新</el-button>
        <el-popconfirm title="你确定要删除吗?" @confirm="handleDel(scope.row)" confirm-button-text="确定"
                       cancel-button-text="取消">
          <template #reference>
            <el-button size="small" type="danger" round>删除</el-button>
          </template>
        </el-popconfirm>
      </template>
    </el-table-column>
  </el-table>
  <el-dialog v-model="dialogVisible" title="更新权限" width="500">
    <template #footer>
      <div class="dialog-footer">
        <el-form ref="updateFromRef" :model="updateForm" :rules="rules" label-width="80px" class="ruleForm" status-icon>
          <el-form-item label="角色名称" prop="roleName">
            <el-input v-model="updateForm.roleName"/>
          </el-form-item>
          <el-form-item label="角色权限" prop="rights">
            <el-tree style="max-width: 600px" show-checkbox node-key="path"
                     :data="rightList"
                     :props="defaultProps"
                     ref="treeRef"
                     :check-strictly="true"
            />
          </el-form-item>
        </el-form>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleConfirm">更新</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup>
import {nextTick, onMounted, reactive, ref} from "vue";
import axios from "axios";
import {ElMessage} from "element-plus";

const currentItem = ref({})
const updateFromRef = ref()
const rightList = ref([])
const treeRef = ref()
const updateForm = reactive({
  roleName: '',
})
const rules = reactive({
  roleName: [
    {required: true, message: '请输入角色名称', trigger: 'blur'},
  ],
})
const dialogVisible = ref(false)
const tableData = ref([])
const currentRights = ref([])
const defaultProps = {
  children: 'children',
  label: 'title'
}
onMounted(() => {
  getList()
  getRightList()
})
const getList = async () => {
  const res = await axios.get("/api/roles/list")
  console.log(res.data)
  tableData.value = res.data.data
}
const getRightList = async () => {
  const res = await axios.get("/api/rights/list")
  rightList.value = res.data.data
}
//更新
const handleUpdate = (item) => {
  dialogVisible.value = true
  currentItem.value = item
  updateForm.roleName = item.roleName
  updateForm.rights = item.rights
  //设置rights给treeRef
  nextTick(()=>{
    console.log(item.rights)
    treeRef.value.setCheckedKeys(JSON.parse(item.rights))
  })
}
//校验,弹出框确定方法
const handleConfirm = () => {
  updateFromRef.value.validate(async (valid, fields) => {
    if (valid) {
      const res= await axios.put(`/api/roles/${currentItem.value.id}`,{
        roleName:updateForm.roleName,
        rights:JSON.stringify(treeRef.value.getCheckedKeys())
      })
      let {code,msg} = res.data
      if (code===200) ElMessage.success("更新成功")
      else ElMessage.error(msg)
      dialogVisible.value = false
      await getList()
    } else {
      console.log('error submit!', fields)
    }
  })

}
//删除
const handleDel = async (item) => {
  console.log(item.id)
  await axios.delete(`/api/roles/${item.id}`)
  await getList()
}

const handleHover = ({rights}) => {
  currentRights.value = rights
}
//自定义tree节点回调函数
const renderContent = (
    h,
    {
      node,
      data,
      store,
    }
) => {
  return h(
      'span',
      {
        class: `custom-tree-node ${currentRights.value.includes(data.path) ? 'active' : ''}`,
      },
      h('span', null, node.label),
  )
}
</script>
<style lang="scss" scoped>
:deep(.active) {
  color: #f56c6c;
}
</style>
