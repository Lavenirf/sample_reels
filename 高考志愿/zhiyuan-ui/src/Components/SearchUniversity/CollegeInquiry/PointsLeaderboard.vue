<script setup>
/*
* 分数榜
* */
import {Location, Search} from "@element-plus/icons-vue";
import {LaySelect, LaySelectOption} from "@layui/layui-vue";
import {ref, onMounted, computed} from "vue";
import {
  InstitutionAffiliation,
  InstitutionType,
  SchoolCharacteristics
} from "@/static/index.js";
import {getSchoolList} from "@/api/school.js";
import {useUserStore} from "@/store/useUserStore.js";
import router from "@/router/index.js";

const userStore = useUserStore()
const dataList = ref([])
const name = ref('')
const year = ref(['2024','2023', '2022', '2021', '2020', '2019'])
const selectYear = ref('2024')
const total = ref(10)
const pageNum = ref(1)
const pageSize = ref(10)
const selectedInstitutionAffiliation = ref(InstitutionAffiliation[0]);
const selectedInstitutionType = ref(InstitutionType[0]);
const selectedSchoolCharacteristics = ref(SchoolCharacteristics[0]);
const handleInstitutionAffiliationClick = (item) => {
  selectedInstitutionAffiliation.value = item
  getDataList()
}

const handleInstitutionTypeClick = (item) => {
  selectedInstitutionType.value = item
  getDataList()
}

const handleSchoolCharacteristicsClick = (item) => {
  selectedSchoolCharacteristics.value = item
  getDataList()

}
const getDataList = async () => {
  const year = selectYear.value
  let userLocal = userStore.getData.examinationArea
  let provinceName = selectedInstitutionAffiliation.value
  let type = selectedInstitutionType.value
  let characteristics = selectedSchoolCharacteristics.value

  if (provinceName === "全部") {
    provinceName = ""
  }
  if (type === "全部") {
    type = ""
  }
  if (characteristics === "全部") {
    characteristics = ""
  }
  const query = {
    year: year,
    localName: userLocal,
    typeName: type,
    levelName: characteristics,
    name: name.value,
    provinceName: provinceName,
    pageSize: pageSize.value,
    pageNum: pageNum.value
  }
  const res = await getSchoolList(query)
  dataList.value = res.data.rows
  console.log(dataList.value)
  pageNum.value = res.data.pageNum
  total.value = res.data.total
}
const handlePage = async () => {
  await getDataList()
}
onMounted(async () => {
  await getDataList()
})
const pageLength = computed(() => Math.ceil(total.value / pageSize.value));
const spliceLevelName=computed(()=>(name)=>{
  if (name==='普通本科'){
    return "本科"
  }
  if (name==='专科（高职）'){
    return "高职"
  }
  else {
    return ''
  }
})
</script>

<template>
  <el-container>
    <el-main style="width: 100%">
      <el-scrollbar>
        <div>
          <el-row justify="end">
            <el-col :span="24">
              <div style="width: 95%;margin-left: 2%">
                <el-input style="width: 240px" size="large" placeholder="请输入院校名称" :prefix-icon="Search"
                          v-model="name"/>
                <el-button size="large" style="margin: 0" color="#3F51B5" @click="getDataList">搜索</el-button>
              </div>
            </el-col>
          </el-row>
          <el-row justify="space-between"
                  style="width: 95%; height: 70px; margin: 15px 2%;background: #fff9f2;border-radius: 10px">
            <el-col :span="6">
              <div class="flex-fsx">
                <div class="selectTitle">年份：</div>
                <lay-select v-model="selectYear" placeholder="请选择" @change="getDataList">
                  <lay-select-option v-for="item in year" :key="item" :value="item" :label="item"/>
                </lay-select>
              </div>
            </el-col>
          </el-row>
          <el-row class="collegeLibrary">
            <div style="margin: 25px 5% 0;">
              <div style="width: 100%; display: flex;">
                <div class="leftTitle">院校所属</div>
                <div class="reg">
                  <el-link
                      v-for="item in InstitutionAffiliation"
                      :key="item"
                      :underline="false"
                      @click="handleInstitutionAffiliationClick(item)"
                      :class="{ selected: item === selectedInstitutionAffiliation }"
                  >
                    {{ item }}
                  </el-link>
                </div>
              </div>
              <div style="width: 100%; display: flex; margin-top:10px">
                <div class="leftTitle">院校类型</div>
                <div class="reg">
                  <el-link
                      v-for="item in InstitutionType"
                      :key="item"
                      :underline="false"
                      @click="handleInstitutionTypeClick(item)"
                      :class="{ selected: item === selectedInstitutionType }"
                  >
                    {{ item }}
                  </el-link>
                </div>
              </div>
              <div style="width: 100%; display: flex;">
                <div class="leftTitle">办学类型</div>
                <div class="reg">
                  <el-link
                      v-for="item in SchoolCharacteristics"
                      :key="item"
                      :underline="false"
                      @click="handleSchoolCharacteristicsClick(item)"
                      :class="{ selected: item === selectedSchoolCharacteristics }">
                    {{ item }}
                  </el-link>
                </div>
              </div>
            </div>
          </el-row>
        </div>
        <div>
          <div class="listItem" @click="router.push({ path: '/universityDetails', query: { id: item.schoolId } })"
               v-for="item in dataList" :key="item.index">
            <div class="schoolBadge">
              <img v-if="!item.name" src="/Icon/imgLoading.gif" alt="" width="70">
              <img v-else :src="`/schoolLogo/${item.schoolId}.jpg`" alt="" width="70">
            </div>
            <div>
              <div class="schoolTitle">
                <span class="name">{{ item.name }}</span>
                <div class="local">
                  <span>
                    <el-icon>
                    <Location/>
                  </el-icon>
                  </span>
                  <span class="localName">{{ item.cityName }}{{ item.countyName }}</span>
                </div>
              </div>
              <ul>
                <li v-if="item.typeName">{{ item.typeName }}</li>
                <li v-if="item.zsName">{{ item.zsName }}</li>
                <li v-if="item.typeName">{{ item.typeName }}</li>
                <li style="background: #e6f7ff;color: #000" v-if="spliceLevelName(item.levelName)">{{ spliceLevelName(item.levelName) }}</li>
                <li style="background: #E8EAF6;color: #000" v-if="item.f211">211</li>
                <li style="background: #b5f5ec;color: #000" v-if="item.f985">985</li>
                <li v-if="item.dualClassName" style="background: #fcffe6;color: #000">{{ item.dualClassName }}</li>
              </ul>
              <div class="scopeRank">
                <span class="describe">最低分/最低位次</span>
                <span class="specific">{{ item.min }}/{{ item.minSection }}</span>
              </div>
            </div>
          </div>
        </div>
        <div style="display: flex;justify-content: center">
          <el-pagination
              v-if="dataList.length > 0 && pageLength > 1"
              layout="prev, pager, next"
              v-model:currentPage="pageNum"
              v-model:page-size="pageSize"
              @current-change="handlePage"
              :total="total"
              class="pagination-container"
          />
        </div>
      </el-scrollbar>
    </el-main>
  </el-container>
</template>

<style lang="scss" scoped>
.pagination-container {
  margin: 15px auto;
}
:deep(.el-pager li.is-active){
  border: 1px solid #4096ff;
  border-radius: 5px;
}
:deep(.layui-input) {
  height: 30px;
  width: 120px;
}

.flex-fsx {
  display: flex;
  margin: 5.5% 0;

  .selectTitle {
    text-align: center;
    width: 100px;
    font-size: 15px;
    height: 20px;
    margin-top: 7px
  }

  .lay-input {
    height: 30px;
    width: 120px;
  }
}

.leftTitle {
  width: 65px;
  height: 50px;
  line-height: 32px;
}

.reg {
  display: flex;
  width: 100%;
  flex-wrap: wrap;

  .el-link {
    text-align: center;
    height: 20px;
    margin: 5px 5px 6px 4px;

    &.selected {
      color: #fa541c;
    }
  }
}

.listItem {
  display: flex;
  justify-content: start;
  width: 95%;
  height: 135px;
  margin-left: 2%;
  background: -webkit-linear-gradient(#fff 20%, #F8F8F8 100%);
  margin-top: 20px;
  cursor: pointer;
  border-radius: 10px;

  ul {
    display: flex;
    font-size: 12px;
    color: #949694;
    margin-top: 8px;

    li {
      background: #f5f6f7;
      margin: 0 10px 0 0;
      padding: 3px 8px;
      border-radius: 5px;
    }
  }

  .scopeRank {
    margin-top: 8px;

    .describe {
      color: #949694;
      font-size: 13px
    }

    .specific {
      padding-left: 10px;
      font-size: 14px
    }
  }

  .schoolBadge {
    width: 95px;
    margin-left: 15px;
    margin-top: 23px;

  }

  .schoolTitle {
    display: flex;
    margin-top: 20px;

    .name {
      font-size: 18px;
    }

    .name:hover {
      color: #fa541c;
    }

    .local {
      display: flex;
      margin-left: 10px;
      border: 1px solid #666;
      padding: 0 10px;
      border-radius: 20px;
      height: 20px;
      margin-top: 4px;
      color: #666;

      .localName {
        font-size: 12px;
        line-height: 18px;
        margin-left: 5px
      }
    }
  }

}

.collegeLibrary {
  border-radius: 5px;
  margin-left: 2%;
  width: 95%;
  background: #fff;
  box-shadow: 0 4px 4px #fff;
}
</style>
