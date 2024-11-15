<script setup>
import {ImageLayer, PointLayer, Scene, Popup} from '@antv/l7';
import {Map} from '@antv/l7-maps';
import {onMounted, reactive, ref} from "vue";
import axios from "axios";
import moment from "moment";
import {APPROVE, AUDIT, BookState, ClassType} from "../../util/type.js";

let scene, popup
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
  imagelayer.on("click", () => {
    popup && popup.close()
  })
  scene.on('loaded', () => {
    getList()
    scene.addLayer(imagelayer);
  });
})
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
  bingTextEvent(textlayer)
  scene.addLayer(textlayer);
}
const bingTextEvent = (textLayer) => {
  popup && popup.close()
  textLayer.on("click", async (e) => {
    const res =await Promise.all([axios.get(`/api/books?labId=${e.feature.id}&bookState=${AUDIT}`),
      axios.get(`/api/books?labId=${e.feature.id}&bookState=${APPROVE}`)])
    let list =[...res[0].data.data,...res[1].data.data]
    //使用map映射
    if (list.length){
      list =list.map(item=>`
        <li class=" home-item el-card is-always-shadow box-card">${moment(item.bookTime).format("YYYY-MM-DD")}
            ${ClassType.find(data=>data.value===item.bookClass).label}
            <span class="${item.bookState===0?'yellow':'green'}">${BookState[item.bookState]}</span>
        </li>`).join("")
    }else {
      list=`<li class="home-item el-card is-always-shadow box-card">暂无预约信息</li>`
    }
    popup = new Popup({
      offsets: [0, 0],
      closeButton: false,
    })
        .setLnglat(e.lngLat)
        .setHTML(list);
    scene.addPopup(popup);
  })
}
const getList = async () => {
  const res = await axios.get(`/api/labs/list`)
  let list = res.data.data.map(item => ({
    x: item.x,
    y: item.y,
    t: item.title,
    id: item.id
  }))
  addTestLayer(list)
}
</script>

<template>
  <div style="min-height: 500px; justify-content: center;position: relative" id="map"/>
</template>
<style lang="scss" scoped>
:deep(.home-item) {
  list-style: none;
  height: 20px;
  line-height: 30px;
  padding:10px;
  span.yellow{
    color: #e6a23c;
  }
  span.green{
    color: #14c145;
  }
}
</style>