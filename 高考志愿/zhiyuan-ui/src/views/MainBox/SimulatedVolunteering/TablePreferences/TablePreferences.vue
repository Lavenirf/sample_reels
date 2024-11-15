<script setup>
import {ref, onMounted, computed} from "vue";
import {LayTab, LayTabItem} from '@layui/layui-vue'
import {useUserStore} from "@/store/useUserStore";
import TableList from "@/Components/SimulatedVolunteering/TablePreferences/TableList/TableList.vue";
import {useVolunteerStore} from "@/store/useVolunteerStore.js";
import {
  exportVolunteerForm,
  getUserVolunteerForm, getUserVolunteerFormDetails,
  getUseVolunteerForm,
  insertVolunteerForm,
  updateVolunteerForm
} from "@/api/volunteerForm.js";
import {ElLoading, ElMessage} from "element-plus";
import {dayjs} from 'element-plus'
import isSameOrBefore from 'dayjs/plugin/isSameOrBefore'
import isSameOrAfter from 'dayjs/plugin/isSameOrAfter'
import {getUserInfo, updateUserInfo} from "@/api/userDetails.js";
/*
* 单个志愿表详情页面
* */
dayjs.extend(isSameOrBefore)
dayjs.extend(isSameOrAfter)
const volunteerStore = useVolunteerStore()
const volunteerForm = ref({})
const current1 = ref("1")
const userStore = useUserStore()
const volunteerFormId = ref()
const fraction = ref(0)
const dialogVisibleAdd = ref(false)
const volunteerName = ref("")
const userId = ref()
const oldVolunteerName = ref("")
const disableSwitch = ref(false)
const volunteerList = ref([])
const volunteerDetail1 = ref([])
const volunteerDetail2 = ref([])
const handleExport = async () => {
  const res = await exportVolunteerForm(volunteerFormId.value, volunteerStore.getParagraph)
  if (res.data.size === 0) {
    ElMessage.error("暂无数据")
  } else {
    const blob = new Blob([res.data], {type: 'application/vnd.ms-excel'})
    const url = window.URL.createObjectURL(blob)
    const link = document.createElement('a')
    link.href = url
    const disposition = res.headers.get("Content-Disposition")
    console.log(disposition)
    let fileName = ""
    if (disposition) {
      const filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/
      const matches = filenameRegex.exec(disposition)
      if (matches != null && matches[1]) {
        fileName = decodeURIComponent(matches[1].replace(/['"]/g, ''))
      }
    }
    link.download = fileName
    document.body.appendChild(link)
    link.click()
    document.body.removeChild(link)
    window.URL.revokeObjectURL(url)
  }
}
//更新志愿表名称
const handleUpdateName = async () => {
    if (volunteerForm.value.name) {
      const data = {
        id: volunteerFormId.value,
        name: volunteerForm.value.name
      }
      const res = await updateVolunteerForm(data)
      if (res.code === 200) {
        await getVolunteer()
        ElMessage.success("保存成功")
      } else {
        ElMessage.error("保存失败")
      }
    } else {
      ElMessage.error("志愿名称不能为空")
    }
  }
// 获取当前使用的志愿表信息
const getVolunteer = async () => {
  if (volunteerFormId.value) {
    const res = await getUseVolunteerForm(volunteerFormId.value)
    if (res.code === 200) {
      volunteerForm.value = res.data
      oldVolunteerName.value = res.data.name
      volunteerStore.setVolunteerForm(res.data)
    } else {
      ElMessage.error("服务器超时，请重试！")
    }
  }
}
//弹出新建窗口并随机名字
const handleDisableAdd = () => {
  volunteerName.value = "志愿" + dayjs(new Date()).format("YYMMDD")
  dialogVisibleAdd.value = true
}
// 清除数据
const reset = () => {
  volunteerName.value = ""
  dialogVisibleAdd.value = false
}
//新增志愿表
const handleAdd = async () => {
  const res = await getUserVolunteerForm(userStore.getData.userId)
  let temp = res.data.find(d => d.name === volunteerName.value)
  if (!temp) {
    const data = {
      name: volunteerName.value,
      result: `成绩:${userStore.getData.fraction} ${userStore.getData.subject1}${userStore.getData.subject2}${userStore.getData.subject3}`,
      userId: userStore.getData.userId
    }
    const res = await insertVolunteerForm(data)
    if (res.code === 200) {
      ElMessage.success("新建成功")
      if (volunteerFormId.value===null){
        let value={
          id:res.data
        }
        await useVolunteer(value)
      }
      await getVolunteer()
      await getVolunteerFormList()
      reset()
    } else {
      ElMessage.error("新建失败，请稍后重试！")
    }
  }
}
//使用志愿表
const useVolunteer = async (value) => {
  let data = {
    userId: userId.value,
    volunteerFormId: value.id
  }
  const res = await updateUserInfo(data)
  if (res.code === 200) {
    await getUserData()
    setVolunteerForm()
    await getVolunteer()
    await getVolunteerFormList()
    volunteerStore.setVolunteerDetails1([])
    volunteerStore.setVolunteerDetails2([])
    await getVolunteerDataList()
    ElMessage.success("使用成功")
  }
}
//设置正在使用的志愿表到pinia中
const setVolunteerForm = () => {
  volunteerFormId.value = userStore.getData.volunteerFormId
  if (volunteerFormId.value !== undefined) {
    volunteerStore.setVolunteerForm(volunteerList.value.filter(v => v.id === volunteerFormId.value))
  }
}
//获取用户数据
const getUserData = async () => {
  const res = await getUserInfo()
  userStore.setData(res.data)
}
//获取用户的志愿表列表
const getVolunteerFormList = async () => {
  userId.value = userStore.getData.userId
  //根据用户id查询用户的志愿表信息
  const res = await getUserVolunteerForm(userId.value)
  volunteerList.value = res.data
  //格式化时间
  volunteerList.value.forEach(v => {
    v.createTime = formatDate(v.createTime)
  })
}
//获取志愿单的详细信息
const getVolunteerDataList = async () => {
  if (volunteerFormId.value !== undefined) {
    const res1 = await getUserVolunteerFormDetails(volunteerFormId.value, 1)
    const res2 = await getUserVolunteerFormDetails(volunteerFormId.value, 2)
    if (res1.code === 200 && res2.code === 200) {
      volunteerDetail1.value = res1.data
      volunteerStore.setVolunteerDetails1(res1.data)
      volunteerStore.setVolunteerDetails2(res2.data)
      volunteerDetail2.value = res2.data
    }
  }
}
const judgingChanges = computed(() => {
  return oldVolunteerName.value === volunteerForm.value.name
})
//格式化时间
const formatDate = (isoString) => {
  // 创建Date对象
  const date = new Date(isoString);
  // 获取年、月、日、小时、分钟和秒
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0'); // 月份从0开始，因此加1
  const day = String(date.getDate()).padStart(2, '0');
  const hours = String(date.getHours()).padStart(2, '0');
  const minutes = String(date.getMinutes()).padStart(2, '0');
  const seconds = String(date.getSeconds()).padStart(2, '0');
  // 返回格式化后的日期时间字符串
  return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
}
onMounted(async () => {
  const loadingInstance = ElLoading.service({
    lock: true,
    text: 'Loading',
    background: 'rgba(0, 0, 0, 0.7)',
  })
  userId.value = userStore.getData.userId

  fraction.value = userStore.getData.fraction
  volunteerFormId.value = userStore.getData.volunteerFormId
  console.log(volunteerFormId.value)
  if (volunteerFormId.value!==null){
    await getVolunteer()
    await getVolunteerFormList()
    setVolunteerForm()
    await getVolunteerDataList()
  }else {
    ElMessage.warning("请先创建志愿表")
  }
  loadingInstance.close()
})
</script>

<template>
  <el-container>
    <el-header>
      <div class="headerTop">
        <div class="item1">
          <div style="border: 1px solid #ccc;border-radius: 8px;margin: auto 10px">
            <el-input v-model="volunteerForm.name" class="inputDeep" style="width: 250px;"/>
            <el-button type="warning" :disabled="judgingChanges" @click="handleUpdateName" text>保存</el-button>
          </div>
        </div>
        <div class="item1" style="width: 358px">
          <el-link>
            <el-button @click="disableSwitch=true">切换</el-button>
          </el-link>
          <el-link>
            <el-button @click="handleDisableAdd">新建</el-button>
          </el-link>
          <el-link>
            <el-button @click="handleExport">导出</el-button>
          </el-link>
        </div>
      </div>
    </el-header>
    <el-main>
      <lay-tab v-model="current1" type="button">
        <lay-tab-item title="普通类一段" v-if="fraction>444" id="1">
          <TableList :dataList="volunteerDetail1" @changeDataList="getVolunteerDataList" :item="1"/>
        </lay-tab-item>
        <lay-tab-item title="普通类二段" id="2">
          <TableList :dataList="volunteerDetail2" @changeDataList="getVolunteerDataList" :item="2"/>
        </lay-tab-item>
      </lay-tab>
    </el-main>
    <el-dialog v-model="dialogVisibleAdd" width="500" draggable style="border-radius: 5px;height: 150px">
      <div style="display: flex">
        <span style="line-height: 32px;margin-left: 20px">志愿名称</span>
        <el-input type="text" style="width: 300px;margin-left: 20px" v-model="volunteerName"/>
      </div>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="reset">取消</el-button>
          <el-button type="primary" @click="handleAdd">确定</el-button>
        </div>
      </template>
    </el-dialog>
    <el-drawer
        v-model="disableSwitch"
        title="志愿列表"
        direction="rtl"
        size="50%"
    >
      <el-table :data="volunteerList" table-layou="flex" :border="true" :cell-style="{textAlign: 'center'}"
                :header-cell-style="{'text-align': 'center'}" empty-text="无志愿表">
        <el-table-column prop="name" label="志愿表名称"/>
        <el-table-column prop="result" label="使用成绩"/>
        <el-table-column prop="createTime" label="生成时间"/>
        <el-table-column label="志愿表状态">
          <div class="buttonGroup">
            <span v-if="1">使用</span>
            <span v-else>未使用</span>
          </div>
        </el-table-column>
        <el-table-column label="操作">
          <template #default="scope">
            <div class="buttonGroup" style="text-align: center">
              <el-button type="primary" text @click="useVolunteer(scope.row)"
                         :disabled="scope.row.id===volunteerFormId">使用
              </el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </el-drawer>
  </el-container>
</template>

<style lang="scss" scoped>
.headerTop {
  width: 100%;
  height: 80px;
  background: #fff;
  border-radius: 10px;
  display: flex;

  .item1 {
    display: flex;

    .el-link {
      margin-left: 20px;
    }
  }
}

:deep(.el-link__inner) {
  margin-left: 10px;
}

:deep(.el-dialog) {
  position: absolute;
  font-size: 20px;
  left: 30%;
  top: 20%;

}

:deep(.el-dialog__body) {
  margin-top: 10px;
}

.dialog-footer {
  margin-top: 10px;
}

.inputDeep {
  :deep(.el-input__wrapper) {
    box-shadow: 0 0 0 0 var(--el-input-border-color, var(--el-border-color)) inset;
    cursor: default;

    .el-input__inner {
      cursor: default !important;
    }
  }
}

:deep(.layui-this) {
  border-radius: 5px !important;
  background-color: #1d39c4 !important;
  color:#fff
}
</style>
