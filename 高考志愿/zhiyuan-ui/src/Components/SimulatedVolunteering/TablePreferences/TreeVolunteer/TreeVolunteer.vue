<script setup>
import {useVolunteerStore} from "@/store/useVolunteerStore.js";
import {computed, ref, onMounted,watchEffect,toRef} from "vue"
import {getUserVolunteerFormDetails, updateVolunteer} from "@/api/volunteerForm.js";
import {ElMessage} from "element-plus";

const props = defineProps(["volunteerFormId","item"])
const volunteerStore = useVolunteerStore()
const volunteerDetails = ref([])
const flag = ref(1)
const oldOrder = ref(0)
const newOrder = ref(0)
const open = ref(false)
const title = ref("")
const emit = defineEmits(['changeDataList'])
const ranges = [
  {label: '1~10', items: Array.from({length: 10}, (_, i) => i + 1)},
  {label: '10~20', items: Array.from({length: 10}, (_, i) => i + 10)},
  {label: '20~30', items: Array.from({length: 10}, (_, i) => i + 20)},
  {label: '30~40', items: Array.from({length: 10}, (_, i) => i + 30)},
  {label: '40~50', items: Array.from({length: 10}, (_, i) => i + 40)},
  {label: '50~60', items: Array.from({length: 10}, (_, i) => i + 50)},
  {label: '60~70', items: Array.from({length: 10}, (_, i) => i + 60)},
  {label: '70~80', items: Array.from({length: 10}, (_, i) => i + 70)},
  {label: '80~90', items: Array.from({length: 10}, (_, i) => i + 80)},
  {label: '90~96', items: Array.from({length: 7}, (_, i) => i + 90)},
];
const volunteerFormId=ref()
const item =toRef(props.item)
const findItem = computed(() => {
  return (index) => volunteerDetails.value.find(v => v.order === index)
})
const handleChange = (index) => {
  if (oldOrder.value === 0) {
    oldOrder.value = volunteerDetails.value.filter(v => v.order === index)[0].order
  } else {
    open.value = true
    newOrder.value = index
    title.value = "将志愿" + oldOrder.value + "与志愿" + newOrder.value + "交换"
    flag.value = 0
  }

}
const handleAlter = (index) => {
  if (oldOrder.value !== 0) {
    open.value = true
    newOrder.value = index
    title.value = "将志愿" + oldOrder.value + "调整到志愿" + newOrder.value
    flag.value = 1
  }
}
const handleUpdate = async () => {
  let temp = volunteerDetails.value.filter(v => v.order === oldOrder.value)
  const data = {
    id: temp[0].id,
    specialId: temp[0].specialId,
    schoolId: temp[0].schoolId,
    volunteerFromId: temp[0].volunteerFromId,
    order: newOrder.value,
    paragraph: item.value
  }
  let res
  if (flag.value === 0) {
    res = await updateVolunteer(data, oldOrder.value)
  } else {
    res = await updateVolunteer(data, 0)
  }
  if (res.code === 200) {
    ElMessage.success("调整成功")
    await getVolunteerDataList()
    emit('changeDataList')
  } else {
    ElMessage.error(res.msg)
    await getVolunteerDataList()
    emit('changeDataList')
  }
  reset()
  open.value = false
}
const getVolunteerDataList = async () => {
  if (volunteerFormId.value !== undefined) {
    const res = await getUserVolunteerFormDetails(volunteerFormId.value,item.value)
    if (res.code === 200) {
      volunteerDetails.value = res.data
      if (item.value===1){
        volunteerStore.setVolunteerDetails1(res.data)
      }else {
        volunteerStore.setVolunteerDetails2(res.data)
      }
    }
  }
}
const reset = () => {
  newOrder.value = 0
  oldOrder.value = 0
}
onMounted(() => {
  getDatList()
})
const getDatList=()=>{
  if (item.value===1){
    volunteerDetails.value = volunteerStore.getVolunteerDetails1
  }else {
    volunteerDetails.value =volunteerStore.getVolunteerDetails2
  }
}
//监听变化
watchEffect(()=>{
  volunteerFormId.value =  props.volunteerFormId
  item.value=props.item
  getDatList()
})
</script>

<template>
  <div>
    <el-tabs tab-position="left">
      <el-tab-pane v-for="range in ranges" :key="range.label" :label="range.label">
        <template v-for="item in range.items" :key="item">
          <div v-if="findItem(item)" class="exist" @click="handleChange(item)" tabindex="1">
            志愿{{ item }}
          </div>
          <div v-else class="absent" @click="handleAlter(item)">
            志愿{{ item }}
          </div>
        </template>
      </el-tab-pane>
    </el-tabs>
    <a-modal v-model:open="open" centered width="240px" okText="确定" cancelText="取消" @cancel="reset"
             @ok="handleUpdate">
      <p>{{ title }}</p>
    </a-modal>
  </div>
</template>
<style scoped>
:deep(.el-tabs__item) {
  font-size: 12px;
  height: 30px;
  border: 1px solid #eee;
}

:deep(.el-tabs__item:hover) {
  background: #ffefe5;
}

:deep(.el-tabs__header.is-left) {
  margin-right: -8px !important;
}

:deep(.el-tabs__active-bar ) {
  height: 30px;
}

:deep(.el-tabs__content) {
  width: auto;
  left: 5px;
}

:deep(.el-tabs) {
  height: 100%;
  width: 100%;
  margin-top: 0;
}

.absent, .exist {
  font-size: 12px;
  width: 75px;
  height: 30px;
  margin-left: 4px;
  text-align: center;
  line-height: 30px;
  cursor: pointer;
  border: 1px solid #eee;
}

.absent {
  color: #000;
}
.exist:hover{
  background: #ffefe5;
}
.exist {
  color: #ff8324;
}

.exist:focus {
  background: #ffefe5;
}

:deep(.el-tab-pane) {
  width: 75px;
}
</style>
