<script setup>
import {ref, watch, watchEffect, unref} from "vue";
import {Location, Search} from "@element-plus/icons-vue";
import {useUserStore} from '@/store/useUserStore.js';
import ToHeaderMenu from "@/Components/Header/Menu/ToHeaderMenu.vue";
import {ClickOutside as vClickOutside} from 'element-plus'
import {getPopularSchool, searchList} from "@/api/school.js";
import router from "@/router/index.js";
import {searchSpecial} from "@/api/schoolSpecial.js";

const queryName = ref('')
const select = ref('学校')
const showLogin = ref(false)
const userStore = useUserStore()
const info = ref({})
const popularList = ref([])
const buttonRef = ref()
const popoverRef = ref()
const searchDataList = ref([])
const onClickOutside = () => {
  unref(popoverRef).popperRef?.delayHide?.()
}
const handleSearch = async () => {
  if (queryName.value === '') {
    searchDataList.value = []
  } else {
    let query = {
      name: queryName.value
    }
    if (select.value === '学校') {
      const res = await searchList(query)
      searchDataList.value = res.data
    } else {
      const res = await searchSpecial(query)
      searchDataList.value = res.data
    }
  }
}
const getData = async (query) => {
  const list = await getPopularSchool(query)
  popularList.value = list.data
  popularList.value.pop()
}
const handlePush =(schoolId,specialId)=>{
 if (schoolId===undefined){
   router.push({ path: 'professionalDetails', query: { id: specialId } })
 }else {
   router.push({ path: '/universityDetails', query: { id: schoolId }})
 }
}
watch(queryName, (newValue, oldValue) => {
  handleSearch()
})
// 监听是否登录
watchEffect(async () => {
  showLogin.value = userStore.showLogin
  if (userStore.showLogin) {
    const res = userStore.getData
    if (res) {
      info.value = res
      const list = await getPopularSchool(info.value.examinationArea ? info.value.examinationArea : '')
      popularList.value = list.data
      popularList.value.pop()
    } else {
      await getData('')
    }
  } else {
    await getData('')
  }
})
</script>

<template>
  <div class="box">
    <el-row justify="start">
      <el-col :span="4" class="appTitle">
        <span class="title">高考智愿</span>
      </el-col>
      <el-col class="localInput" :span="8">
        <div style="display: flex;">
          <div class="localBox" v-if="info.examinationArea">
            <div>
              <el-icon>
                <Location/>
              </el-icon>
            </div>
            <span class="localName">{{ info.examinationArea }}</span>
          </div>
          <div class="searchInput">
            <el-input v-model="queryName" placeholder="请输入" ref="buttonRef">
              <template #prepend>
                <el-select v-model="select" placeholder="Select" v-click-outside="onClickOutside">
                  <el-option value="学校"/>
                  <el-option value="专业"/>
                </el-select>
              </template>
              <template #append>
                <el-button @click="handleSearch" :icon="Search"/>
              </template>
            </el-input>
            <el-popover placement="bottom-start" width="400px" ref="popoverRef" :virtual-ref="buttonRef" trigger="click"
                        virtual-triggering>
              <span style="padding-top: 5px;color: #999">热门搜索</span>
              <ul class="prop" v-if="!queryName">
                <li v-for="(item,index) in popularList"
                    @click="router.push({ path: '/universityDetails', query: { id: item.schoolId }})">
                  <span class="num">{{ index + 1 }}</span>
                  <span class="name">{{ item.name }}</span>
                  <div class="lab">
                    <span class="local">
                      <el-icon style="padding-top: 5px"><Location/></el-icon>
                      <span class="localName">{{ item.provinceName }}</span>
                    </span>
                    <span class="nat">{{ item.natureName }}</span>
                    <span class="type">{{ item.typeName }}</span>
                  </div>
                </li>
              </ul>
              <ul class="prop2" v-else>
                <el-scrollbar style="height: 350px">
                  <template v-if="searchDataList.length!==0">
                    <li v-for="item in  searchDataList"
                        @click="handlePush(item.schoolId,item.specialId)">
                    <span class="num">
                      <svg t="1722237486577" class="icon" viewBox="0 0 1024 1024"
                           xmlns="http://www.w3.org/2000/svg" p-id="7780" width="20" height="20">
                      <path
                          d="M495.872 131.84c10.069333-5.12 22.186667-5.12 32.256 0l392.021333 197.888a37.674667 37.674667 0 0 1 6.4 4.138667c3.84 3.114667 6.826667 6.826667 8.789334 10.965333l0.64 1.493333a30.976 30.976 0 0 1 2.432 8.661334l0.256 4.138666v316.629334c0 18.346667-15.530667 33.28-34.645334 33.28a34.218667 34.218667 0 0 1-34.346666-28.757334l-0.341334-4.522666V414.165333l-54.485333 27.477334v282.88c0 43.946667-32.213333 82.176-76.8 93.44-59.52 14.976-120.234667 36.565333-175.445333 65.621333a109.312 109.312 0 0 1-101.205334 0c-55.210667-29.013333-115.925333-50.645333-175.445333-65.664-41.984-10.581333-72.96-45.013333-76.501333-85.717333l-0.341334-7.68v-282.88L103.850667 388.565333a32.554667 32.554667 0 0 1-4.352-56.277333l4.352-2.56z m249.642667 344.832l-217.386667 109.781333a35.968 35.968 0 0 1-32.256 0l-217.429333-109.781333v247.850667c0 13.098667 10.368 25.386667 25.173333 29.098666 64.554667 16.298667 130.389333 39.68 191.146667 71.68 10.666667 5.632 23.808 5.632 34.474666 0 60.757333-32 126.592-55.381333 191.146667-71.68 13.141333-3.285333 22.826667-13.397333 24.789333-24.746666l0.384-4.352-0.042666-247.850667zM511.957333 198.826667L194.432 359.125333l317.525333 160.256 317.610667-160.256-317.610667-160.298666z"
                          fill="#999999" p-id="7781"></path>
                      </svg>
                    </span>
                      <span class="name">{{ item.name }}</span>
                    <div class="lab" v-if="select==='学校'">
                      <span class="local">
                      <el-icon style="padding-top: 5px"><Location/></el-icon>
                      <span class="localName">{{ item.provinceName }}</span>
                      </span>
                        <span class="nat">{{ item.natureName }}</span>
                        <span class="type">{{ item.typeName }}</span>
                      </div>
                    </li>
                  </template>
                  <template v-else>
                    <div style="text-align: center;padding-top: 20px;color: #838383">
                      暂无数据
                    </div>
                  </template>
                </el-scrollbar>
              </ul>
            </el-popover>
          </div>
        </div>
      </el-col>
      <el-col class="navbar" :span="6">
        <div>
          <router-link to="home">
            <div>首页</div>
          </router-link>
          <router-link to="simulatedVolunteering">
            <div>志愿模拟</div>
          </router-link>
          <router-link to="searchUniversity">
            <div>查大学</div>
          </router-link>
          <router-link to="inquireMajor">
            <div>查专业</div>
          </router-link>
        </div>
      </el-col>
      <el-col class="message" :span="4">
        <ToHeaderMenu :showLogin="showLogin" :info="info"/>
      </el-col>
    </el-row>
  </div>
</template>

<style lang="scss" scoped>
.prop {
  margin-top: 15px;

  li:first-child {
    .num {
      background: #ff4d00;
    }

    .name {
      color: #ff5b15;
    }

    .lab {
      .local {
        color: #fc3636;
        background: #ffeae4;
      }

      .nat {
        color: #fc3636;
        background: #ffeae4;
      }

      .type {
        color: #fc3636;
        background: #ffeae4;
      }
    }
  }

  li:nth-child(2) {
    .num {
      background: #ff761b;
    }

    .name {
      color: #fb9653;
    }

    .lab {
      .local {
        color: #ff6c0c;
        background: #ffeeeb;
      }

      .nat {
        color: #ff6c0c;
        background: #ffeeeb;
      }

      .type {
        color: #ff6c0c;
        background: #ffeeeb;
      }
    }
  }

  li:nth-child(3) {
    .num {
      background: #ff9c00;
    }

    .name {
      color: #ffa00b;
    }

    .lab {
      .local {
        color: #feb137;
        background: #fdf5e6;
      }

      .nat {
        color: #feb137;
        background: #fdf5e6;
      }

      .type {
        color: #feb137;
        background: #fdf5e6;
      }
    }
  }

  li {
    display: flex;
    margin-top: 10px;
    cursor: pointer;
    width: 100%;
    padding: 5px;
    border-radius: 5px;

    .num {
      width: 15.23px;
      padding: 1px 4px;
      background: #ababab;
      color: #fff;
      font-size: 13px;
      border-radius: 2px;
    }

    .name {
      width: 45%;
      color: #838383;
      margin-left: 10px;
      margin-right: 50px;
    }

    .lab {
      display: flex;
      font-size: 12px;

      .local {
        display: flex;
        padding: 1px 2px;
        background: #f8f8f8;
        color: #ababab;
        margin-right: 5px;
        border-radius: 2px;

        .localName {
          padding-left: 2px;
        }
      }

      .nat {
        padding: 1px 2px;
        background: #f8f8f8;
        color: #ababab;
        margin-right: 5px;
        border-radius: 2px;
      }

      .type {
        padding: 1px 2px;
        background: #f8f8f8;
        color: #ababab;
        margin-right: 5px;
        border-radius: 2px;
      }
    }
  }

  li:hover {
    background: #f0f0f0;
  }
}

.prop2 {
  margin-top: 5px;

  li {
    display: flex;
    margin-top: 10px;
    cursor: pointer;
    width: 100%;
    padding: 5px;
    border-radius: 5px;

    .num {
      height: 20px;
      padding: 1px 4px;
      color: #fff;
      font-size: 13px;
      border-radius: 2px;
    }

    .name {
      width: 40%;
      color: #838383;
      margin-left: 10px;
      margin-right: 50px;
    }

    .lab {
      display: flex;
      font-size: 12px;

      .local {
        display: flex;
        padding: 1px 2px;
        background: #f8f8f8;
        color: #ababab;
        margin-right: 5px;
        border-radius: 2px;

        .localName {
          padding-left: 2px;
        }
      }

      .nat {
        padding: 1px 2px;
        background: #f8f8f8;
        color: #ababab;
        margin-right: 5px;
        border-radius: 2px;
      }

      .type {
        padding: 1px 2px;
        background: #f8f8f8;
        color: #ababab;
        margin-right: 5px;
        border-radius: 2px;
      }
    }
  }

  li:hover {
    background: #f0f0f0;
  }
}

.box {
  min-width: 1200px;
  height: 80px;

  .appTitle {
    padding-top: 10px;
    text-align: center;

    .title {
      font-size: 25px;
      font-weight: bold;
      margin-left: 40px;
    }
  }

  .localInput {
    padding-top: 10px;
    margin-left: 40px;

    .localBox {
      display: flex;
      margin-top: 10px;

      .localName {
        line-height: 15px;
        margin-left: 5px;
        font-size: 13px;
      }
    }

    .searchInput {
      margin-left: 20px;

      .el-input {
        max-width: 550px;

        .el-select {
          width: 80px;
          background: #fff;
        }

        .el-button {
          height: 29px;
          width: 60px;
        }
      }
    }
  }

  .navbar {
    padding-top: 8px;
    margin-left: 50px;

    div:first-child {
      display: flex;
      font-size: 16px;
      justify-content: space-between;
      margin-top: 4px;
    }
  }

  .message {
    padding-left: 9%;
    padding-top: 5px;
  }
}

</style>
