
<script setup>
import cityMap from "@/js/china-main-city-map.js";
import * as echarts from "echarts";
import {selectAddress, selectCounty} from "@/api/baiDuApi.js";
import {onMounted, ref} from 'vue'
import Baidu from "@/Components/SearchUniversity/CollegeInquiry/BaiduMap.vue";
import {ElLoading, ElMessage} from 'element-plus'
//中国地图（第一级地图）的ID、Name、Json数据
var chinaId = 100000
var chinaName = "china"
var chinaJson = null
//记录父级ID、Name
var mapStack = []
var parentId = null
var parentName = null
//Echarts地图全局变量，主要是在返回上级地图的方法中会用到
defineProps(['msg'])
const myChart = ref(null)
const mapShow = ref(false)
const cityName =ref('')
let dituid = ref(null)
let dituname = ref(null)
const lat =ref(0)
const lng =ref(0)
/**
 * Echarts地图
 */
const changeShowMap =()=>{
  mapShow.value=!mapShow.value
}
const mapChart = async (divid) => {
  const mapJson = await selectAddress(chinaId)
  chinaJson = mapJson;
  myChart.value = echarts.init(document.getElementById(divid));
  registerAndsetOption(myChart.value, chinaId, chinaName, mapJson, false);
  parentId = chinaId;
  parentName = "china";
  myChart.value.on("click", async (param) => {
    let cityId = cityMap[param.name];
    if (cityId) {
      //代表有下级地图
      registerAndsetOption(myChart.value, cityId, param.name, await selectAddress(cityId), true);
    } else {
      const res = await selectCounty(mapStack[mapStack.length-1].mapName+param.name)
      if (res ===undefined){
        ElMessage.error("接口限额")
      }else {
        lat.value =res.data.result.location.lat
        lng.value =res.data.result.location.lng
        cityName.value=param.name
        changeShowMap()
      }
    }
  })
}

/**
 * 返回上一级地图
 */
const back = async () => {
  var map = mapStack.pop();
  registerAndsetOption(myChart.value, map.mapId, map.mapName, await selectAddress(map.mapId), false);
  //返回上一级后，父级的ID、Name随之改变
  parentId = map.mapId;
  parentName = map.mapName;
}

/**
 *
 * @param {*} myChart
 * @param {*} id        省市县Id
 * @param {*} name      省市县名称
 * @param {*} mapJson   地图Json数据
 * @param {*} flag      是否往mapStack里添加parentId，parentName
 */
const registerAndsetOption = (myChart, id, name, mapJson, flag) => {
  echarts.registerMap(name, mapJson);
  myChart.setOption({
    series: [
      {
        type: "map",
        map: name,
        itemStyle: {
          areaColor: "rgba(23, 27, 57,0)",
          borderColor: "#1dc199",
          borderWidth: 1,
        },
        data: initMapData(mapJson),
        label: {
          normal: {
            textStyle: {
              fontSize: 15,
              fontWeight: 'bold',
              color: '#fff'
            }
          },
          emphasis:{
            label:{
              show:true,
              color: "#fff"
            }
          }
        },
      }
    ],

  });

  if (flag) {
    mapStack.push({
      mapId: parentId,
      mapName: parentName
    });
    parentId = id;
    parentName = name;
  }
}

const initMapData = (mapJson) => {
  var mapData = [];
  for (var i = 0; i < mapJson.features.length; i++) {
    mapData.push({
      name: mapJson.features[i].properties.name
      //id:mapJson.features[i].id
    });
  }
  return mapData;
}

onMounted(() => {
  const loadingInstance = ElLoading.service({
    lock: true,
    text: 'Loading',
    background: '#fff',
  })
  mapChart("mapChart")
  loadingInstance.close()
})
</script>
<template>
  <div class="box">
    <button v-show="!mapShow" class="backBtn" @click="back()">返回</button>
    <div v-show="!mapShow" id="mapChart" class="chart"></div>
    <Baidu @changeShowMap="changeShowMap" v-if="mapShow" :cityName="cityName" :dituid="dituid"   :lat="lat" :lng="lng" class="chart2"/>
  </div>
</template>


<style scoped>
.box {
  width: 95%;
  height: 730px;
  background-image: url("../../../assets/beijingtupian.png");
}

.chart {
  position: relative;
  height: 90%;
  top: 10%;
}

.chart2 {
  top: 0;
  left: 0;
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
  cursor: pointer;
}


</style>
