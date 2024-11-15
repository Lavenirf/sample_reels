<script setup>
/*
* 开设专业
* */
import {ref} from "vue"
import {Search} from "@element-plus/icons-vue";
const type= ref("全部类别")
const current= ref(1)
const planPage = ref(1)
const scopeYear = ref('2024')
const scopeCity = ref("山东")

/*onMounted(async ()=>{
  let query = {
    pageNum: planPage.value,
    schoolId: schoolId.value,
    year: specialYear.value,
    localProvinceName: specialCity.value,
    localTypeName: type.value,
  }
  await selectMscores()
})*/
</script>

<template>
  <div class="box">
    <div class="title">
      <span>专业分数线</span>
      <div class="selectBox">
        <el-input
            style="max-width: 250px;margin-left: 20px;height: 30px"
            placeholder="请输入专业名称"
            class="input-with-select"
        >
          <template #prepend>
            <el-button style="width: 50px" :icon="Search" />
          </template>
          <template #append>
            <el-button style="width: 60px">搜索</el-button>
          </template>
        </el-input>
      </div>
    </div>
    <ul class="ul2">
      <li>
        <span>专业名称</span>
        <span>层次</span>
        <span>专业门类</span>
        <span>专业类别</span>
        <span>学制</span>
      </li>
      <li v-for="index of 10">
        <span>法学</span>
        <span>本科</span>
        <span>理学</span>
        <span>海洋科学类（本）</span>
        <span>四年</span>
      </li>
    </ul>
    <a-pagination class="page" v-model:current="current" :total="50" show-less-items/>
  </div>
</template>

<style scoped lang="scss">
.box {
  width: 80%;
  height: auto;
  padding: 0 20px 20px;
  background: -webkit-linear-gradient(#FAFAFA 60%,#F6F6F6 100%);


  border-radius: 10px;

  .title {
    width: 100%;
    margin-top: 20px;
    font-size: 18px;
    font-weight: bold;
    padding-top: 15px;
    display: flex;
    justify-content: space-between;

    .selectBox {
      display: flex;

      .select {
        margin-left: 10px;
        width: 100px;
        font-weight: normal;
        font-size: 13px;
        height: 40px;
        .option {
          max-height: 160px;
        }
      }
    }
  }

  .ul2 {
    li:first-child{
      display: flex;
      margin-top: 20px;
      color: #ababab;
    }
    li{
      display: flex;
      margin-top: 25px;
      font-size: 15px;
      span:last-child{
        text-align: right;
      }
      span{
        width: 20%;
      }
    }
  }
  .ul1 {
    li:first-child{
      display: flex;
      margin-top: 20px;
      color: #ababab;
    }
    li{
      display: flex;
      margin-top: 25px;
      font-size: 15px;
      justify-content: space-around;
      span:last-child{
        text-align: right;
      }
      span{
        width: 10%;
      }
    }
  }
  .page{
    margin-top: 40px;
    text-align: center;
  }
}
:deep(.layui-input[size=md]) {
  height: 30px;
}
:deep(input[type='text'], input[type='password'], input[type='number'], textarea){
  font-size: 12px;
  font-weight: normal;
}
</style>
