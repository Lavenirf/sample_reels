<script setup>
import {onMounted, ref, defineProps} from "vue";
import {selectSchools} from "@/api/baiDuApi.js";

import {useRouter} from 'vue-router'
import {searchList} from "@/api/school.js";

const route = useRouter()
const props = defineProps(['lat', 'lng', 'cityName', 'ditudata', 'myChart'])
const map = ref(null)
const emit = defineEmits(['changeShowMap'])
var labels;
const handleChange = () => {
  emit('changeShowMap')
}

onMounted(async () => {
  map.value = new BMapGL.Map("container");
  let point = new BMapGL.Point(props.lng, props.lat);
  map.value.centerAndZoom(point, 12);
  map.value.enableScrollWheelZoom();
  map.value.setTilt(50);
  map.value.enableScrollWheelZoom(true);
//设置放大级别固定为7
  map.value.setMaxZoom(15);
  map.value.setMinZoom(7);
  map.value.enableScrollWheelZoom();
  //根据地区名称（省市县）在地图上绘制多边形区域
  let bdary = new BMapGL.Boundary();
  bdary.get(props.cityName, function (rs) {
    // 绘制行政区
    for (var i = 0; i < rs.boundaries.length; i++) {
      var xyArr = rs.boundaries[i].split(';');
      var ptArr = [];
      for (var j = 0; j < xyArr.length; j++) {
        var tmp = xyArr[j].split(',');
        var pt = new BMapGL.Point(tmp[0], tmp[1]);
        ptArr.push(pt);
      }
      let mapmask = new BMapGL.MapMask(ptArr, {
        isBuildingMask: true,
        isPoiMask: true,
        isMapMask: true,
        showRegion: 'inside',
        topFillColor: '#5679ea',
        topFillOpacity: 0.5,
        sideFillColor: '#5679ea',
        sideFillOpacity: 0.9
      });
      map.value.addOverlay(mapmask);
      let border = new BMapGL.Polyline(ptArr, {
        strokeColor: '#4ca7a2',
        strokeWeight: 3,
        strokeOpacity: 1
      });
      map.value.addOverlay(border);
    }
  });
  const res = await selectSchools(props.cityName)
  const keywords = ["淘宝", "办公室", "党校","校区"];
  const filteredPois = [];

  for (let i = 0; i < res.data.pois.length; i++) {
    const poi = res.data.pois[i];
    let containsKeyword = false;

    for (let j = 0; j < keywords.length; j++) {
      if (poi.name.includes(keywords[j])) {
        containsKeyword = true;
        break;
      }
    }

    if (!containsKeyword) {
      filteredPois.push(poi);
    }
  }
  res.data.pois = filteredPois
  for (let i = 0; i < res.data.pois.length; i++) {
    let location = res.data.pois[i].location.split(",");
    let opts = {
      position: new BMapGL.Point(location[0], location[1]), // 指定文本标注所在的地理位置
      offset: new BMapGL.Size(20, -20) // 设置文本偏移量
    };
    // 创建文本标注对象
    let label = new BMapGL.Label(res.data.pois[i].name, opts);
    map.value.addOverlay(label);
    labels = label;
    label.addEventListener("click", async () => {
      let query={
      name:res.data.pois[i].name
      }
      const r=await searchList(query)
      await route.push({path: 'universityDetails', query: {id: r.data[0].schoolId}});
    });
  }
});

</script>

<template>
  <div>
    <el-button class="backBtn" @click="handleChange">返回</el-button>
    <div id="container">
    </div>
  </div>

</template>

<style scoped>
#container {
  width: 100%;
  height: 730px;
}

.backBtn {
  position: absolute;
  top: 10px;
  left: 50px;
  padding: 0 10px;
  background-color: #00c298;
  border: 0;
  border-radius: 3px;
  color: #fff;
  height: 27px;
  font-size: 1em;
  z-index: 999;
}
</style>
