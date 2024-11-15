<script setup>
import {ImageLayer, PointLayer, Scene, Popup} from '@antv/l7';
import {Map} from '@antv/l7-maps';
import {nextTick, onMounted} from "vue";
const props =defineProps(["x","y","t"])
let scene
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
      .size(20)
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
onMounted(() => {
  nextTick(()=>{
    scene = new Scene({
      id: 'map',
      map: new Map({
        center: [props.x, props.y],
        zoom: 5,
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
      addTestLayer([{
        x: props.x,
        y: props.y,
        t: props.t
      }])
      scene.addLayer(imagelayer);
    });
  })
})
</script>

<template>
  <div style="min-height: 500px; justify-content: center;position: relative" id="map" />
</template>

<style scoped>

</style>