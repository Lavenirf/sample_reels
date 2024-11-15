<script setup>
/*Created by wzh on 2024/6/13 下午10:10.*/
import {ref, reactive} from 'vue';
import {selectDistrictName} from "@/api/baiDuApi.js";
import {region_NotAll, cities, grade} from "@/static/index.js";
import {onMounted} from "vue";
import {useUserStore} from '@/store/useUserStore.js'
import {EditPen} from "@element-plus/icons-vue";
import {getUserInfo, updateUserInfo} from "@/api/userDetails.js";
import {ElMessage} from "element-plus";

const userStore = useUserStore()
const info = ref({})
const city = ref([]); // 定义响应式的 city 数组
const City_array = ref([]); // 定义响应式的 City_array 数组 -->市
const district = ref([]);
const District_array = ref([]);
//头像
const avatarFileInput = ref(null);
const studentInfo = reactive({
  data: {
    identity: '',
    origin: {
      Province: '',
      City_name: '',
      District_name: '',
    },
    School: '',
    Grade: '',
    Subject: [],
    score: '',
    rank: '',
    Stu_type: ''
  },
});
// 处理文件改变事件
const handleFileChange = (event) => {
  const file = event.target.files[0];
  if (file) {
    const reader = new FileReader();
    reader.onload = (e) => {
      const imgElement = document.querySelector('.head_img img');
      if (imgElement) {  // 检查 img 元素是否存在
        console.log(imgElement)
        imgElement.src = e.target.result.toString();
        info.value.avatar = imgElement.src
      } else {
        alert("请选择正确的头像路径")
      }
    };
    reader.onerror = () => {
      alert("读取文件出错,请稍后再试")
    };
    reader.readAsDataURL(file);
  }
};


// 触发文件输入元素的点击事件
const triggerFileInput = () => {
  avatarFileInput.value.click();
};
//个人信息
const isEditing = ref(false);
const toggleEdit = () => {
  isEditing.value = true;
}

const saveChanges = () => {
  if (studentInfo.data.Subject.length !== 3) {
    alert('请选择三科科目');
  } else {
    info.value.subject1 = studentInfo.data.Subject[0]
    info.value.subject2 = studentInfo.data.Subject[1]
    info.value.subject3 = studentInfo.data.Subject[2]
    isEditing.value = false;
  }
};

const handleChangeProvince_Click = async () => {
  const res = await selectDistrictName(studentInfo.data.origin.Province);
  city.value = res.data.districts;
  if (city.value.length > 0) {
    City_array.value = city.value[0].districts.map(item => item.name);
  }
}
const handleChangeDistrict_Click = async () => {
  const res1 = await selectDistrictName(studentInfo.data.origin.City_name);
  district.value = res1.data.districts;
  if (district.value.length > 0) {
    District_array.value = district.value[0].districts.map(item => item.name);
  }
}
const handleChangeProvince = async () => {
  const res = await selectDistrictName(studentInfo.data.origin.Province);
  city.value = res.data.districts;
  if (city.value.length > 0) {
    City_array.value = city.value[0].districts.map(item => item.name);
    studentInfo.data.origin.City_name = City_array.value[0]; // 设置第一个城市为默认选中
  }
}
const handleChangeDistrict = async () => {
  const res1 = await selectDistrictName(studentInfo.data.origin.City_name);
  district.value = res1.data.districts;
  if (district.value.length > 0) {
    District_array.value = district.value[0].districts.map(item => item.name);
    studentInfo.data.origin.District_name = District_array.value[0]; // 设置第一个城市为默认选中
    console.log(District_array.value);
  }
}
onMounted(() => {
  info.value = userStore.getData
  studentInfo.data.Subject[0] = info.value.subject1
  studentInfo.data.Subject[1] = info.value.subject2
  studentInfo.data.Subject[2] = info.value.subject3
  if (info.value.avatar===''){
    info.value.avatar ='/Avatar/defaultAvatar.png'
  }
})
const editMode = reactive({
  userName: false,
  phonenumber: false,
  password: false,
});

const form = reactive({
  userName: '',
  phonenumber: '',
  password: ''
});

const editField = (field) => {
  editMode[field] = true;
  form[field] = info.value[field];
};

const saveField = (field) => {
  info.value[field] = form[field];
  editMode[field] = false;
};
const getUserData = async () => {
  const res = await getUserInfo()
  userStore.setData(res.data)
  info.value=res.data
}
const handleAvatarSuccess=async (res, file)=> {
  let query={
    avatar: res.url,
    userId:info.value.userId
  }
  const r=await updateUserInfo(query)
  if (r.code===200){
    ElMessage.success("修改成功")
  }
  await getUserData()
}
const beforeAvatarUpload=(file)=> {
  const isJPG = file.type === 'image/jpeg';
  const isPng = file.type === 'image/png';
  const isLt2M = file.size / 1024 / 1024 < 5;
  console.log(file.type)
  if (!isJPG && !isPng) {
    ElMessage.error('上传头像图片只能是 JPG 或 PNG 格式!');
    return false
  }
  if (!isLt2M) {
    ElMessage.error('上传头像图片大小不能超过 5MB!');
    return false
  }
  return true;
}
</script>

<template>
  <div class="common-layout">
    <el-container class="container">
      <el-header class="header1">
        <span style="font-size: 30px;line-height:80px">账号信息</span>
      </el-header>
      <el-main>
        <!--账号信息-->
        <el-row :gutter="15">
          <el-col :span="4">
            <div class="grid-content ep-bg-purple"/>
            <ul class="leftUl">
              <li style="line-height: 100px">头像</li>
              <li>昵称</li>
              <li>账号</li>
              <li>设置/修改密码</li>
            </ul>
          </el-col>

          <el-col :span="8">
            <div class="grid-content ep-bg-purple"/>
            <ul class="rightUl">
              <li style="line-height: 120px">
                <div class="head_img">
                  <el-upload action="http://222.206.160.32:88/common/upload" :show-file-list="false" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
                    <img :src="!info.avatar?'/Avatar/defaultAvatar.png':info.avatar" alt="" style="border-radius: 35px;" width="64"/>
                  </el-upload>
                </div>
              </li>
              <li>
                <template v-if="editMode.userName">
                  <el-input class="input" v-model="form.userName" @blur="saveField('userName')"/>
                </template>
                <template v-else>
                  {{ info.userName }}
                  <span class="hover" @click="editField('userName')">修改</span>
                </template>
              </li>
              <li>
                <template v-if="editMode.phonenumber">
                  <el-input v-model="form.phonenumber" @blur="saveField('phonenumber')" class="input"/>
                </template>
                <template v-else>
                  {{ info.phonenumber }}
                  <span class="hover" @click="editField('phonenumber')">修改</span>
                </template>
              </li>
              <li>
                <div v-if="editMode.password">
                  <el-input v-model="form.password" @blur="saveField('password')" type="password" class="input"/>
                </div>
                <div v-else style="display: flex">
                  <span style="margin-top: 5px">**********</span>
                  <span class="hover" @click="editField('password')" style="margin-left: 10px">修改</span>
                </div>
              </li>
              <input type="file" ref="avatarFileInput" style="display: none" @change="handleFileChange"/>
            </ul>
          </el-col>
        </el-row>
      </el-main>
    </el-container>
    <el-container>
      <!--        学生信息-->
      <el-header class="header2">
        <span class="title">学生信息</span>
        <div v-if="!isEditing" class="hover edit" @click="toggleEdit">
          <el-icon>
            <EditPen/>
          </el-icon>
          <span style="padding-left: 5px">编辑</span>
        </div>
        <div v-else class="hover edit" @click="isEditing=!isEditing">
          <span>取消</span>
        </div>
      </el-header>
      <div class="info-container" v-if="!isEditing">
        <div class="info-item">
          <div class="info-label">身份信息</div>
          <div class="info-value">{{ info.identityInformation }}</div>
        </div>
        <div class="info-item">
          <div class="info-label">生源地</div>
          <div class="info-value">{{ studentInfo.data.origin.Province }} {{ studentInfo.data.origin.City_name }}
            {{ studentInfo.data.origin.District_name }}
          </div>
        </div>
        <div class="info-item">
          <div class="info-label">学校</div>
          <div class="info-value">{{ info.schoolName }}</div>
        </div>
        <div class="info-item">
          <div class="info-label">年级</div>
          <div class="info-value">{{ info.grade }}</div>
        </div>
        <div class="info-item">
          <div class="info-label">选课信息</div>
          <div class="info-value">
            {{ info.subject1 }}
            {{ info.subject2 }}
            {{ info.subject3 }}
          </div>
        </div>
        <div class="info-item">
          <div class="info-label">分数</div>
          <div class="info-value"> {{ info.fraction }}</div>
        </div>
        <div class="info-item">
          <div class="info-label">位次</div>
          <div class="info-value"> {{ info.ranking }}</div>
        </div>
        <div class="info-item">
          <div class="info-label">考生类型</div>
          <div class="info-value">普通类</div>
        </div>
      </div>
      <div v-else class="info-container">
        <div class="info-item">
          <div class="info-label">身份信息</div>
          <el-radio-group v-model=" info.identityInformation">
            <el-radio label="学生">学生</el-radio>
            <el-radio label="家长">家长</el-radio>
            <el-radio label="其他">其他</el-radio>
          </el-radio-group>
        </div>

        <div class="info-item">
          <div class="info-label">生源地</div>
          <el-select v-model="studentInfo.data.origin.Province" placeholder="省" @change="handleChangeProvince"
                     style="margin-top: 10px;width: 12% ;margin-right: 20px">
            <el-option v-for="province in region_NotAll" :key="province" :label="province"
                       :value="province"></el-option>
          </el-select>

          <el-select v-model="studentInfo.data.origin.City_name" placeholder="市" @change="handleChangeDistrict"
                     @click="handleChangeProvince_Click" style="margin-top: 10px;width: 15% ;margin-right: 20px">
            <el-option
                v-for="City_name in City_array"
                :key="City_name"
                :label="City_name"
                :value="City_name"
            ></el-option>
          </el-select>
          <el-select v-model="studentInfo.data.origin.District_name" placeholder="区"
                     style="width: 15%;margin-top: 10px;" @click="handleChangeDistrict_Click">
            <el-option
                v-for="District_name in District_array"
                :key="District_name"
                :label="District_name"
                :value="District_name"
            ></el-option>
          </el-select>
        </div>


        <div class="info-item">
          <div class="info-label">学校</div>
          <el-input v-model="info.schoolName" style="width: 240px;height: 30px;margin-top: 13px"
                    placeholder="输入你的学校"/>
        </div>

        <div class="info-item">
          <div class="info-label">年级</div>

          <el-radio-group v-model="info.grade">

            <el-radio v-for="item in grade"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
              {{ item.label }}
            </el-radio>
          </el-radio-group>
        </div>
        <div class="info-item">
          <div class="info-label">选课信息</div>
          <el-checkbox-group style="margin-top: 12px" v-model="studentInfo.data.Subject" :max="3">
            <el-checkbox v-for="city in cities" :key="city" :label="city" :value="city">
              {{ city }}
            </el-checkbox>
          </el-checkbox-group>
        </div>

        <div class="info-item">
          <div class="info-label">成绩</div>
          <el-input v-model="info.fraction" style="width: 240px;height: 30px;margin-top: 13px"
                    placeholder="输入你的分数"/>
        </div>

        <div class="info-item">
          <div class="info-label">位次</div>
          <el-input v-model="info.ranking" style="width: 240px;height: 30px;margin-top: 13px"
                    placeholder="输入你的排名"/>
        </div>

        <div class="info-container">
          <div class="info-item">
            <div class="info-label">身份信息</div>
            普通类
          </div>
        </div>

        <el-button @click="saveChanges"> 保存</el-button>
      </div>
    </el-container>
  </div>
</template>

<style scoped lang="scss">
.common-layout {
  width: 100%;
  background-color: #ffffff;

  .container {
    border-radius: 15px;

    .header1 {
      height: 75px;
      background-color: rgba(244, 238, 238, 0.4);
    }

    .leftUl {
      text-align: right;
      font-size: 15px;
      line-height: 70px;
    }

    .rightUl {
      text-align: left;
      font-size: 15px;
      line-height: 70px;
      color: #817d7d;

      .head_img {
        position: relative;
        width: 64px;
        height: 64px;
        border-radius: 50%;
        display: flex;
        align-items: center;
        justify-content: center;
        margin-top: 35px;

        img {
          width: 100%;
          height: 100%;
          object-fit: cover;
          border-radius: 50%;

        }
      }
    }
  }

  .header2 {
    height: 75px;
    background-color: rgba(244, 238, 238, 0.4);
    display: flex;
    justify-content: space-between;

    .title {
      font-size: 30px;
      line-height: 80px;
    }

    .edit {
      margin-top: 30px;
      margin-right: 20px;
      cursor: pointer;
    }
  }
}

.info-container {
  font-size: 15px;
  line-height: 60px;
}

.info-item {
  display: flex;
  margin-left: 10px;
}

.info-label {
  width: 150px; /* 根据需要调整宽度 */
  text-align: right;
  padding-right: 10px; /* 增加间距 */
}

.info-value {
  text-align: left;
  color: #817d7d
}

#setting {
  display: inline-block;
  width: 100%;
  height: 20px;
  line-height: 20px;
  background: rgba(0, 0, 0, .3);
  position: absolute;
  bottom: 0;
  left: 0;
  color: #fff;
  font-size: 12px;
  text-align: center;
  cursor: pointer;
}

.input {
  width: 200px;
}

.hover {
  cursor: pointer;
}

.hover:hover {
  color: #fa541c;
}

</style>
