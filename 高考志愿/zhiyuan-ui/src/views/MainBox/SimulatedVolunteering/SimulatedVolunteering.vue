<script setup>
/*
* 模拟志愿
* */
import {ref} from "vue"
import {useUserStore} from "@/store/useUserStore.js";
import {computed, onMounted} from "vue";
import {getUseVolunteerForm} from "@/api/volunteerForm.js";
import {useVolunteerStore} from "@/store/useVolunteerStore.js";

const userStore = useUserStore()
const volunteerStore = useVolunteerStore()
const info = ref(userStore.getData)

const subjectComputed = computed(() => {
  if (info.value.subject1 === undefined) {
    return ""
  } else {
    return info.value.subject1.slice(0, 1) + info.value.subject2.slice(0, 1) + info.value.subject3.slice(0, 1) || ''
  }
})

onMounted(async () => {
  if (info.value.volunteerFormId !== undefined&&info.value.volunteerFormId!==null&&info.value.volunteerFormId!=='') {
    const res = await getUseVolunteerForm(info.value.volunteerFormId)
    volunteerStore.setVolunteerForm(res.data)
  }
})
</script>

<template>
  <el-container v-if="userStore.showLogin">
    <el-aside style="height: 700px;width:200px;margin-top: 15px">
      <el-row>
        <el-col :span="24">
          <div style="width: 80%;height:300px">
            <el-card>
              <template #header>
                <div class="card-header">
                  <span>普通类</span>
                </div>
              </template>
              <div class="cardRow">
                <div class="cardCol">
                  <router-link to="/intelligentRecommendation">智能推荐</router-link>
                </div>
                <div class="cardCol">
                  <el-link>一键填报</el-link>
                </div>
                <div class="cardCol">
                  <router-link to="/tablePreferences">
                    我的志愿单
                  </router-link>
                </div>
                <div class="cardCol">
                  <el-link>我的订单</el-link>
                </div>
              </div>
            </el-card>
          </div>
        </el-col>
      </el-row>
      <el-row style="margin-top: 20px">
        <el-col :span="24">
          <div style="width: 80%;height:300px;background: yellow">
            <el-card>
              <template #header>
                <div class="card-header">
                  <span>普通类一段</span>
                </div>
              </template>
              <div class="cardRow">
                <div class="cardCol">地区:
                  <span>
                   {{ info.examinationArea }}
                  </span>
                </div>
                <div class="cardCol">科目:
                  <span>
                    {{ subjectComputed }}
                  </span>
                </div>
                <div class="cardCol">分数:
                  <span>
                     {{ info.fraction }}
                  </span>
                </div>
                <div class="cardCol">位次:
                  <span>
                    {{ info.ranking }}
                  </span>
                </div>
              </div>
            </el-card>
          </div>
        </el-col>
      </el-row>
    </el-aside>
    <el-main style="margin-left: -35px">
      <router-view/>
    </el-main>

  </el-container>
</template>

<style lang="scss" scoped>
.buttonGroup {
  display: flex;
}

:deep(.el-card__body) {
  height: 250px;
}
:deep(.el-card){
  background:#fafafa
}
.cardRow {
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  .cardCol{
    span{
      padding-left: 20px
    }
  }
}

</style>
