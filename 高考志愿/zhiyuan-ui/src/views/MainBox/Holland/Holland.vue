<script setup>
import {ref, onMounted} from 'vue';
import {useUserStore} from "@/store/useUserStore.js";
import TopicTest from "@/Components/Holland/TopicTest/TopicTest.vue";
import Result from "@/Components/Holland/Result/Result.vue";
const userStore = useUserStore()
const hollandResultId = ref(null)
const handleReset =()=>{
  hollandResultId.value=null
}
onMounted(async () => {
  hollandResultId.value = userStore.getData.hollandResultId
})

</script>

<template>
  <div>
    <div v-if="hollandResultId!==null" style="display: flex">
      <Result @reset="handleReset"/>
    </div>
    <div v-else>
      <TopicTest/>
    </div>
  </div>

</template>

<style lang="scss" scoped>

</style>
