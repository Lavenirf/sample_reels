<template>
  <el-table :data="tableData" style="width: 100%" row-key="id">
    <el-table-column prop="title" label="权限名称" width="180"/>
    <!--    <el-table-column prop="icon" label="图标" width="180" />-->
    <!--    自定义-->
    <el-table-column label="图标" width="180">
      <template #default="scope">
        <div style="display: flex; align-items: center">
          <el-icon size="20">
            <component :is="mapIcons[scope.row.icon]"></component>
          </el-icon>
        </div>
      </template>
    </el-table-column>
    <el-table-column label="操作">
      <template #default="scope">
        <el-button size="small" round @click="handleUpdate(scope.row)">更新</el-button>
        <el-popconfirm title="你确定要删除吗?" @confirm="handleDel(scope.row)" confirm-button-text="确定" cancel-button-text="取消">
          <template #reference>
        <el-button size="small" type="danger" round>删除</el-button>
          </template>
        </el-popconfirm>
      </template>
    </el-table-column>
  </el-table>
  <el-dialog
      v-model="dialogVisible"
      title="更新权限"
      width="500"
  >
    <template #footer>
      <div class="dialog-footer">
        <el-form ref="updateFromRef" :model="updateForm" :rules="rules" label-width="80px" class="ruleForm" status-icon>
          <el-form-item label="权限名称" prop="title">
            <el-input v-model="updateForm.title"/>
            <el-select
                v-model="updateForm.icon"
                placeholder=""
                size="large"
                style="margin-top: 5px"
            >
              <el-option
                  v-for="item in options"
                  :key="item.label"
                  :label="item.label"
                  :value="item.label"
                  style="display: flex;"
              >
                <el-icon style="margin-left: 30px;" size="20">
                  <component style="padding-top: 14px" :is="item.value"></component>
                </el-icon>
                <span style="padding-left: 10px;font-size: 15px">{{ item.label }}</span>
              </el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleConfirm">更新</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import axios from "axios";
import {HomeFilled, Key, List, UploadFilled, User} from "@element-plus/icons-vue";
const currentItem = ref({})
const updateFromRef = ref()
const updateForm = reactive({
  title: "",
  icon:""
})
const rules = reactive({
  title: [
    {required: true, message: '请输入标题', trigger: 'blur'},
  ],
})
const dialogVisible = ref(false)
const tableData = ref([])
onMounted(() => {
  getList()
})
const getList = async () => {
  const res = await axios.get("/api/rights/list")
  tableData.value = res.data.data
}
//更新
const handleUpdate = (item) => {
  currentItem.value = item
  updateForm.title = item.title
  updateForm.icon = item.icon
  dialogVisible.value = true
}
//校验
const handleConfirm = () => {
  updateFromRef.value.validate(async (valid, fields) => {
    if (valid) {
      await axios.put(`/api/rights/${currentItem.value.id}`, updateForm)
      await  getList()
      dialogVisible.value = false
    } else {
      console.log('error submit!', fields)
    }
  })

}
//删除
const handleDel = async (item)=>{
  console.log(item.id)
  await axios.delete(`/api/rights/${item.id}`)
  await getList()
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
const options = [
  {
    value: List,
    label: 'List',
  },
  {
    value: HomeFilled,
    label: 'HomeFilled',
  },
  {
    value: UploadFilled,
    label: 'UploadFilled',
  },
  {
    value: User,
    label: "User"
  }
]
</script>
