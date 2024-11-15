<script setup>
import {ImageLayer, PointLayer, Scene} from '@antv/l7';
import {Map} from '@antv/l7-maps';
import {onMounted, reactive, ref} from "vue";
import axios from "axios";
import {ElMessage} from "element-plus";
import {ClassType} from "../../util/type.js";
import {useUserStore} from "../../store/useUserStore.js";

const {user} =useUserStore()

const fromRef = ref()
const dialogVisible = ref(false)
const currentItem =ref({})
let scene
const form = reactive({
  bookTime:"",
  bookClass:"",
  bookReason:""
})

const rules = reactive({
  bookTime: [
    {required: true, message: '请输入日期', trigger: 'blur'},
  ],
  bookClass: [
    {required: true, message: '请输入课节', trigger: 'blur'},
  ],
  bookReason: [
    {required: true, message: '请输入原因', trigger: 'blur'},
  ]
})
//发起请求
const handleConfirm = (item) => {
  fromRef.value.validate( async (valid) => {
    if (valid) {
      const res =await axios.post(`/api/books`,{
        ...form,
        bookUser:user.id,
        labId:currentItem.value.id
      })
      const {code,msg} =res.data
      if (code===200) {
        ElMessage.success("预约成功")
      }
      else{
        ElMessage.error(msg)
      }
      reset()
    }
  })
}
//地图渲染
const addTestLayer = (data) => {
  const textlayer = new PointLayer({zIndex: 2})
      .source(data, {
        parser: {
          type: 'json',
          x: 'x',
          y: 'y',
        },
      })
      .shape('t', 'text')
      .size(14)
      .active({
        color: '#00f',
        mix: 0.9,
      })
      .color('rgb(86,156,214)')
      .style({
        textAnchor: 'center', // 文本相对锚点的位置 center|left|right|top|bottom|top-left
        spacing: 2, // 字符间距
        fontWeight: '800',
        padding: [1, 1], // 文本包围盒 padding [水平，垂直]，影响碰撞检测结果，避免相邻文本靠的太近
        stroke: '#ffffff', // 描边颜色
        strokeWidth: 2, // 描边宽度
        textAllowOverlap: true,
      });
  textlayer.on('click',(e)=>{
    // console.log(e)
    currentItem.value = e.feature
    dialogVisible.value=true
  })
  scene.addLayer(textlayer);
}
const reset = () => {
  form.bookTime=""
  form.bookReason=""
  form.bookClass=""
  dialogVisible.value=false
}
onMounted(() => {
  scene = new Scene({
    id: 'map',
    map: new Map({
      center: [500, 500],
      zoom: 3,
      version: 'SIMPLE',
      mapSize: 1000,
      maxZoom: 5,
      minZoom: 2,
      pitchEnabled: false,
      rotateEnabled: false,
    }),
  });
  scene.setBgColor('rgb(94, 182, 140)');

  const imagelayer = new ImageLayer({}).source(
      '/bg.jpg',
      {
        parser: {
          type: 'image',
          extent: [360, 400, 640, 600],
        },
      },
  );
  scene.on('loaded', () => {
    getList()
    scene.addLayer(imagelayer);
  });
})
//获取实验室列表
const getList = async ()=>{
  const res =await axios.get(`/api/labs/list`)
  let list =res.data.data.map(item=>({
    id:item.id,
    x:item.x,
    y:item.y,
    t:"预约"+item.title
  }))
  addTestLayer(list)
}
//禁用日期选择
const disabledDate= (time)=>{
  return time.getTime()<Date.now()||time.getTime()>(Date.now()+3600*1000*24*7)
}
const disableSelect=ref(['0'])
//改变日期的回调
const handleSelectChange = async ()=>{
  form.bookClass=""
  const res =await axios.post(`/api/books/select`,{
    bookTime:form.bookTime,
    labId:currentItem.value.labId
  })
  disableSelect.value =res.data.data.map(item=>item.bookClass)
  console.log(disableSelect.value)
}
</script>

<template>
  <div style="min-height: 500px; justify-content: center;position: relative" id="map"/>
  <el-dialog v-model="dialogVisible" :title="currentItem.t" width="500" @close="reset">
    <template #footer>
      <div class="dialog-footer">
        <el-form ref="fromRef" :model="form" :rules="rules" label-width="100px" class="ruleForm" status-icon>
          <el-form-item label="预约日期" prop="bookTime">
            <el-date-picker v-model="form.bookTime" type="date" placeholder="请选择日期" style="width: 100%" :disabled-date="disabledDate" @change="handleSelectChange"/>
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