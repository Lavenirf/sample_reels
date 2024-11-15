<script setup>
/*
* ai
* */
import {ref} from "vue"
import {ElLoading} from "element-plus";

const answer = ref("")
const eventSource = ref()
const userMessage = ref("")
const answer1=ref("山东大学实力排名?")
const answer2=ref("软件工程专业就业怎么样?")
const answer3=ref("山东物化生520可以上那些大学?")
const answer4=ref("平行志愿录取规则及填报技巧?")
const currentMessages = ref("");
const sendMessage = (query) => {
  const loadingInstance = ElLoading.service({
    lock: true,
    text: 'Loading',
    background: 'rgba(0, 0, 0, 0.7)',
  })
  let message
  if (query.isTrusted){
    message=userMessage.value
  }else {
   message=query
  }
  if (eventSource.value) {
    eventSource.value.close();
  }

  eventSource.value = new EventSource(`http://222.206.160.32:88/ai/dialogue?userMessage=${encodeURIComponent(message)}`);
  eventSource.value.onmessage = (event) => {
    const data = JSON.parse(event.data);
    loadingInstance.close()
    if (data.number !== 'done') {
      let regex = /"content":"([^"]*)"/;
      let match = data.match(regex);

      if (match && match.length > 1) {
        let contentValue = match[1];
        currentMessages.value = currentMessages.value + contentValue
      }
    } else {
      eventSource.value.close()
    }
  }

  eventSource.value.onerror = (error) => {
    if (eventSource.value.readyState === EventSource.CLOSED) {
      console.log('Connection to server closed');
    } else {
      console.error('Error occurred:', error);
    }
  };
  answer.value=message
  // 清空输入框
  userMessage.value = '';
};
</script>

<template>
  <div class="box">
    <div class="dialog">
      <div class="picture">
        <svg t="1721991298111" class="icon" viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg"
             p-id="26183" width="40" height="40">
          <path
              d="M687.65 650.87l-95.24 243.39h50.58l21.16-58.79h99.92l22.37 58.79h50.54l-95.24-243.39h-54.09zM675.9 800.2c20.37-57.99 30.97-87.77 31.74-89.36 0.77-2.36 1.94-6.66 3.53-12.94 1.55-4.72 2.36-7.83 2.36-9.41h1.17c1.87 7.21 4.24 14.29 7.07 21.18 0.77 1.59 11.73 31.74 32.89 90.53H675.9zM864.03 650.87h47.02v243.39h-47.02z"
              fill="#347DFF" p-id="26184"></path>
          <path
              d="M512.5 96.37c-228.03 0-413.54 187.06-413.54 416.95 0 229.91 185.49 416.97 413.52 416.97 17.51 0 31.7-14.2 31.7-31.71 0-17.5-14.19-31.69-31.7-31.7-193.04 0-350.1-158.61-350.1-353.57 0-194.92 157.06-353.53 350.1-353.53 193.06 0 350.12 158.59 350.12 353.53 0.62 17.52 15.32 31.22 32.84 30.6 16.65-0.59 30.01-13.95 30.6-30.6 0-229.88-185.51-416.94-413.54-416.94z"
              fill="#347DFF" p-id="26185"></path>
          <path
              d="M743.22 509.82c0-21.43-17.37-38.8-38.8-38.8H318.8c-21.43 0-38.8 17.37-38.8 38.8 0 21.43 17.37 38.8 38.8 38.8h385.62c21.43 0 38.8-17.37 38.8-38.8z"
              fill="#347DFF" p-id="26186"></path>
        </svg>
      </div>
      <div class="content" v-if="!currentMessages">
        <div class="title">
          <span>下午好，我是高考小智，您的智能问答助手</span>
        </div>
        <div class="introduce">
          <span>作为您的高考小伙伴，我可以帮助您解决一些高考相关问题，请提出您的问题吧～</span>
        </div>
        <div class="introduce">
          <span>您可以试着问我：</span>
        </div>
        <ul>
          <li @click="sendMessage(answer1)">
            <div>
              <img src="/Icon/ai/dx.png" alt="">
              <span>查大学</span>
            </div>
            <div>
              <span>{{answer1}}</span>
            </div>
          </li>
          <li @click="sendMessage(answer2)">
            <div>
              <img src="/Icon/ai/zy.png" alt="">
              <span>查专业</span>
            </div>
            <div>
              <span>{{ answer2 }}</span>
            </div>
          </li>
          <li @click="sendMessage(answer3)">
            <div>
              <img src="/Icon/ai/azy.png" alt="">
              <span>报志愿</span>
            </div>
            <div>
              <span>{{ answer3 }}</span>
            </div>
          </li>
          <li @click="sendMessage(answer4)">
            <div>
              <img src="/Icon/ai/jq.png" alt="">
              <span>填报小知识</span>
            </div>
            <div>
              <span>{{answer4}}</span>
            </div>
          </li>
        </ul>
      </div>
      <div v-else class="an">
        <div style="font-weight: bold">
          <span style="padding-left: 10px;">{{ answer }}</span><span style="padding-left: 5px">：</span>
        </div>
        <div style="margin-top:15px;background: #fdfdff;box-shadow: 0 -4px 37px -4px rgba(0, 0, 0, 0.1);padding: 20px 0;text-indent: 2em;line-height: 25px;border-radius: 5px">
          <p style="font-size: 15px;padding: 0 15px">{{currentMessages}}</p>
        </div>
      </div>
    </div>
    <div class="inputBox">
      <el-input @keyup.enter="sendMessage" v-model="userMessage" class="custom-input" style="height: 70px;"
                placeholder="请输入您想咨询的问题，例如：山东大学历年院校分、500分可以上什么大学..."/>
      <div class="sendBox" @click="sendMessage" >
        <span>发送</span>
        <img src="/Icon/ai/fs.png" alt="">
      </div>
    </div>
  </div>
</template>
<style lang="scss" scoped>
.box {
  width: 90%;
  height: auto;
  background: #fbfbfa;
  margin: 0 auto;
  border-radius: 10px;
  padding-bottom: 30px;
  .dialog {
    height: 500px;
    display: flex;
    .picture {
      width: 45px;
      height: 45px;
      margin-top: 40px;
      margin-left: 40px;
    }
  }
  .an{
    width: 80%;
    height: auto;
    margin-top: 25px;
    margin-left: 15px;
    border-radius: 10px;
  }

  .content {
    width: 70%;
    height: 350px;
    margin: 60px 20px;
    background: #fdfdff;
    border-radius: 10px;
    box-shadow: 0 -4px 37px -4px rgba(0, 0, 0, 0.1);

    .title {
      margin-top: 20px;
      margin-left: 20px;
      font-size: 24px;
      font-weight: bold;
    }

    .introduce {
      margin-top: 20px;
      margin-left: 20px;
      font-size: 15px;
      color: #8b8b8b;
    }

    ul {
      display: flex;
      flex-wrap: wrap;

      li {
        height: 80px;
        width: 40%;
        background: #fafafa;
        margin-left: 20px;
        margin-top: 15px;
        cursor: pointer;
        div:first-child {
          margin-top: 10px;

          img {
            margin-left: 10px;
            width: 25px;
          }

          span {
            margin-left: 10px;
            font-size: 15px;
            font-weight: bold;
          }
        }

        div:last-child {
          margin-left: 10px;
          margin-top: 10px;
          color: #808080;
        }
      }
    }
  }

  .inputBox {
    width: 80%;
    height: 150px;
    padding: 20px;
    margin-left: 100px;
    margin-top: 20px;
    background: -webkit-linear-gradient(#FFFFFF 48.1%,#FDFDFF 99.79%);
    box-shadow: 0 -4px 37px -4px rgba(0, 0, 0, 0.1);
    border-radius: 20px;

    .custom-input {
      width: 100%;
    }

    .sendBox {
      width: 80px;
      height: 35px;
      background: #ff7500;
      border-radius: 20px;
      display: flex;
      margin-left: 80%;
      cursor: pointer;

      span {
        margin-left: 10px;
        margin-top: 7px;
        font-size: 15px;
        color: #fff;
      }

      img {
        width: 25px;
        height: 25px;
        margin-left: 5px;
        margin-top: 5px
      }
    }
  }
}

:deep(.el-input__wrapper) {
  box-shadow: none;
}
:deep(.el-input__wrapper){
  background: transparent;
}
</style>
