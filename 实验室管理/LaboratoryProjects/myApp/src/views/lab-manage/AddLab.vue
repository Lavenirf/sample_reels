<script setup>
import {ImageLayer, PointLayer, Scene, Popup} from '@antv/l7';
import {labType,collegeType} from '../../util/type.js'
import {Map} from '@antv/l7-maps';
import {onMounted, reactive, ref} from "vue";
import axios from "axios";
import {ElMessage} from "element-plus";
import LabMap from "../../components/lab-manage/LabMap.vue";

const fromRef = ref()
const dialogVisible = ref(false)

const form = reactive({
  title: "",
  capacity: "",
  labType: "",
  collegeType: "",
  x: 0,
  y: 0
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
let scene, popup
const handleConfirm = () => {
  fromRef.value.validate( async (valid) => {
    if (valid) {
      const res =await axios.post(`/api/labs`,form)
      const {code,msg} =res.data
      if (code===200) {
        ElMessage.success("新增成功")
        addTestLayer([
          {
            "x":form.x,
            "y":form.y,
            "t":form.title
          }
        ])
      }
      else{
        ElMessage.error(msg)
      }
      reset()
    }
  })
}
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
  scene.addLayer(textlayer);
}
const reset = () => {
  dialogVisible.value=false
  form.title=""
  form.labType=""
  form.capacity=""
  form.collegeType=""
  form.x=0
  form.y=0
  popup.close()
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

  imagelayer.on('click',  (e) => {
    let {lng, lat} = e.lngLat

    popup = new Popup({
      offsets: [0, 0],
      closeButton: false,
    })
        .setLnglat(e.lngLat)
        .setHTML(
            `<button class='el-button el-button--primary' onclick="add_popup(${lng},${lat})">选择位置</button>`);
    scene.addPopup(popup);
  });
  scene.on('loaded', () => {
    getList()
    scene.addLayer(imagelayer);
  });
})
window.add_popup = (x, y) => {
  dialogVisible.value = true
  // 将popup隐藏
  popup.close()
  form.x = 500 + 2.8 * x
  form.y = 500 + 2.8 * y
}
const getList = async ()=>{
  const res =await axios.get(`/api/labs/list`)
  let list =res.data.data.map(item=>({
    x:item.x,
    y:item.y,
    t:item.title
  }))
  addTestLayer(list)
}
</script>

<template>
  <div style="min-height: 500px; justify-content: center;position: relative" id="map"/>
  <el-dialog v-model="dialogVisible" title="添加实验室" width="500" @close="reset">
    <template #footer>
      <div class="dialog-footer">
        <el-form ref="fromRef" :model="form" :rules="rules" label-width="100px" class="ruleForm" status-icon>
          <el-form-item label="实验室名称" prop="title">
            <el-input v-model="form.title"/>
          </el-form-item>
          <el-form-item label="容纳人数" prop="capacity">
            <el-input v-model="form.capacity" type="number"/>
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
<style scoped>

</style>