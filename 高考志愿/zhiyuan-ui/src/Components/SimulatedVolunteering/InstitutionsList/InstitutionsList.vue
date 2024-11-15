<script setup>
import List from "@/Components/SimulatedVolunteering/InstitutionsList/List/List.vue";
import { ref, onMounted, computed, watch } from "vue";
import { getSpecialized } from "@/api/recommend.js";
import { useUserStore } from "@/store/useUserStore.js";
import { ElLoading, ElMessageBox } from "element-plus";
import { useRecommendedParameters } from "@/store/useRecommendedParameters.js";
import router from "@/router/index.js";
import { useCollectStore } from "@/store/useCollectStore.js";

const parametersStore = useRecommendedParameters();
const region = ref('');
const institution_type = ref('');
const institution_level = ref('');

// 使用 computed 来创建响应式的引用
const leve2Name = computed(() => parametersStore.getlevel2name);

const props = defineProps(['search']);
const collectStore = useCollectStore();
const totalData = ref({
  cho: [],
  wen: [],
  bao: []
});
const baoList = ref([]);
const choList = ref([]);
const wenList = ref([]);
const allList = ref([]);
const current = ref("1");
const userStore = useUserStore();
const info = userStore.getData;
const load = ref(false);

const handleSearch = async () => {
  if (props.search !== '') {
    choList.value = totalData.value.cho.filter(t => t.schoolName === props.search);
    wenList.value = totalData.value.wen.filter(t => t.schoolName === props.search);
    baoList.value = totalData.value.bao.filter(t => t.schoolName === props.search);
    allList.value = allList.value.filter(t => t.schoolName === props.search);
  }
};

// 获取推荐院校
const fetchData = async () => {
  const loadingInstance = ElLoading.service({
    lock: true,
    text: 'Loading',
    background: 'rgba(0, 0, 0, 0.7)',
  });
  let query = {
    position: info.ranking,
    localProvinceName: info.examinationArea,
    region: region.value === '不限' ? '' : region.value,
    institution_type: institution_type.value === '不限' ? '' : institution_type.value,
    institution_level: institution_level.value === '不限' ? '' : institution_level.value,
    //level2name: leve2Name.value === '不限' ? '' : leve2Name.value,
    userId: 1,
  };
  console.log(query);
  const res = await getSpecialized(query);
  totalData.value.cho = res.data.chong;
  totalData.value.wen = res.data.wen;
  totalData.value.bao = res.data.bao;
  baoList.value = res.data.bao;
  choList.value = res.data.chong;
  wenList.value = res.data.wen;
  allList.value = [...choList.value, ...wenList.value, ...baoList.value];
  loadingInstance.close();
};

// 监听 leve2Name 的变化
watch(leve2Name, async (newVal, oldVal) => {
  if (newVal !== oldVal) {

    const loadingInstance = ElLoading.service({
      lock: true,
      text: 'Loading',
      background: 'rgba(0, 0, 0, 0.7)',
    });
    let query = {
      position: info.ranking,
      localProvinceName: info.examinationArea,
      region: region.value === '不限' ? '' : region.value,
      institution_type: institution_type.value === '不限' ? '' : institution_type.value,
      institution_level: institution_level.value === '不限' ? '' : institution_level.value,
      level2name: leve2Name.value === '不限' ? '' : leve2Name.value,
      userId: info.userId,
    };
    console.log(query);
    const res = await getSpecialized(query);
    totalData.value.cho = res.data.chong;
    totalData.value.wen = res.data.wen;
    totalData.value.bao = res.data.bao;
    baoList.value = res.data.bao;
    choList.value = res.data.chong;
    wenList.value = res.data.wen;
    allList.value = [...choList.value, ...wenList.value, ...baoList.value];
    loadingInstance.close();

  }
});

const openHolland = () => {
  ElMessageBox.confirm(
      '当前未进行职业测试，是否先进行职业测试再进行填报',
      {
        customClass: 'message',
        confirmButtonText: '是',
        cancelButtonText: '否',
        center: true,
      }
  ).then(() => {
    router.push("/holland");
  }).catch(async () => {
    const loadingInstance = ElLoading.service({
      lock: true,
      text: 'Loading',
      background: 'rgba(0, 0, 0, 0.7)',
    });
    await fetchData();
    load.value = true;
    loadingInstance.close();
  });
};

const openSubject = () => {
  ElMessageBox.confirm(
      '当前科目未填写完整！！！',
      {
        customClass: 'message',
        confirmButtonText: '前往填写',
        center: true,
        showCancelButton: false,
        closeOnClickModal: false,
        "show-close": false,
        closeOnPressEscape: false
      }
  ).then(() => {
    router.push("/home");
  });
};

const handleQuery = async () => {
  region.value = parametersStore.getRegion;
  institution_type.value = parametersStore.getInstitutionType;
  institution_level.value = parametersStore.getInstitutionLevel;

  await fetchData();
};

const loading = async () => {
  if (info.subject1 !== '' && info.subject2 !== '' && info.subject3 !== '') {
    if (info.hollandResultId === null) {
      openHolland();
    } else {
      region.value = parametersStore.getRegion;
      institution_type.value = parametersStore.getInstitutionType;
      institution_level.value = parametersStore.getInstitutionLevel;
      await fetchData();
      load.value = true;
    }
  } else {
    openSubject();
  }
};

onMounted(async () => {
  if (userStore.showLogin) {
    await loading();
  }
});

defineExpose({
  handleSearch,
  handleQuery
});
</script>



<template>
  <a-tabs style="margin-top: 15px" v-model:activeKey="current" type="card" :animated="true">
    <a-tab-pane key="1" tab="可冲击">
      <List v-if="load" :data="choList"/>
    </a-tab-pane>
    <a-tab-pane key="2" tab="较稳妥">
      <List :data="wenList"/>
    </a-tab-pane>
    <a-tab-pane key="3" tab="可保底">
      <List :data="baoList"/>
    </a-tab-pane>
    <a-tab-pane key="4" tab="全部">
      <List :data="allList"/>
    </a-tab-pane>
  </a-tabs>
</template>

<style lang="scss" scoped>
:deep(.ant-tabs-tab) {
  width: 110px !important;
  background: none !important;
  border: none !important;
  margin-left: 30px !important;
}

:deep(.ant-tabs-tab-btn) {
  margin: 0 auto !important;
}

:deep(.ant-tabs-tab-active) {
  background-color: #1d39c4 !important;
  color: #fff !important;
}

:deep(.ant-tabs-tab-active .ant-tabs-tab-btn) {
  color: #fff !important;
}

</style>
<style lang="scss">
.message{
  width: 320px !important;
}
</style>
