<script setup>

</script>

<template>
  <el-col :span="16">
    <div class="title">
      <span>系统使用说明</span>
    </div>
    <ul class="state">
      <li>
        <span>1、本查询系统的信息仅供参考，具体数据请以学校官网或考试院公布为准。</span>
      </li>
      <li>
        <span>2、本站数据未经授权严禁转载，违者将依法追究责任。</span>
      </li>
      <li>
        <span>3、如有任何疑问，可发送邮件至：18654500326@163.com，或点击【意见反馈】反馈于我们.</span>
      </li>
      <li>
        <span>4、志愿填报服务客服专线：18645400326，高校合作或其他商务客服电话：18654500326。</span>
      </li>
      <li>
        <span>友情链接：</span>
        <a href="https://www.eol.cn/" style="margin-left: 0">中国教育在线</a>
        <a href="https://www.sdzk.cn/">山东省招生考试院</a>
        <a href="https://gaokao.chsi.com.cn/">阳光高考</a>
        <a href="http://www.moe.gov.cn/">教育部</a>
        <a href="https://www.neea.edu.cn/">中国教育考试网</a>
      </li>
    </ul>
  </el-col>
  <el-col :span="8">
    <ul class="code">
      <li></li>
      <li class="box1">
        <img src="/gzh.jpg" alt="" width="100">
        <span>高考智愿公众号</span>
      </li>
      <li class="box2">
        <img src="/gzh.jpg" alt="" width="100">
        <span style="margin-left: 5px">高考智愿APP</span>
      </li>
    </ul>
  </el-col>
</template>

<style scoped lang="scss">
.el-col{
  border-radius: 5px
}
.title{
  margin-left: 35px;
  font-size: 20px;
  margin-top: 20px;
}
.state{
  margin-left: 35px;
  width: 100%;
  margin-top: 20px;
  font-size: 15px;
  color: #959795;
  li:last-child{
    a{
      text-decoration: none;
      font-size: 15px;
      color: #959795;
      margin-left: 15px;
    }
    a:hover{
      color: #fa541c;
    }
  }
  li{
    margin-top: 15px
  }
}
.code{
  display: flex;
  font-size: 15px;
  li:first-child{
    height: 150px;
    width: 1px;
    border-left: 1px solid #d9d9d9;
    margin-top: 10%;
  }
  .box1{
    display: flex;
    flex-direction: column;
    margin-left: 20%;
    margin-top: 13%;
  }
  .box2{
    display: flex;
    flex-direction: column;
    margin-left: 10%;
    margin-top: 13%;
  }
}
</style>
