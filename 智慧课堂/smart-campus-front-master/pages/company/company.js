// pages/company/company.js
Page({
  data: {
    disabled: true,
    btnstate: 'default',
    teacher: {},
    student: {},
    userType: 'student'
  },
  //学生老师的选择
  selectUserType: function (event) {
    const type = event.currentTarget.dataset.type;
    // 根据用户选择的身份类型进行相应处理
    if (type === 'student') {
      this.setData({
        userType: type
      })
      console.log(this.data.userType)
    } else if (type === 'teacher') {
      this.setData({
        userType: type
      })
      console.log(this.data.userType)
    }
  },
  accountblur: function (e) {
    var content = e.detail.value.trim();
    if (content != '') {
      this.setData({
        disabled: false,
        btnstate: 'primary'
      });
    } else {
      this.setData({
        disabled: true,
        btnstate: 'default'
      });
    }
  },
  //添加用户信息
  formSubmit: function (e) {
    var that = this;
    var value = e.detail.value;
    //为老师对象添加数据
    if (that.data.userType == "teacher") {
      this.data.teacher.username = value.userName;
      this.data.teacher.password = value.password;
      this.data.teacher.name = value.name;
      this.data.teacher.teacherId = value.id * 1;
    }
    //为学生对象添加数据
    else if(that.data.userType=="student"){
      this.data.student.username = value.userName;
      this.data.student.password = value.password;
      this.data.student.name = value.name;
      this.data.student.studentId = value.id * 1;
    }
    // 添加老师
    if (that.data.userType
      == "teacher") {
      wx.request({
        url: 'http://47.96.122.197:80/teacher',
        method: "POST",
        data: that.data.teacher,
        success: function (res) {
          console.log("添加老师成功", res);
        },
        fail: function (err) {
          // 请求失败回调
          console.error('Failed to add data:', err);
        }
      })
    }
    //添加学生
    else if (that.data.userType == "student") {
      console.log(that.data.student);
      wx.request({
        url: 'http://47.96.122.197:80/student',
        method: "POST",
        data: that.data.student,
        success: function (res) {
          console.log("添加学生成功", res);
        },
        fail: function (err) {
          // 请求失败回调
          console.error('Failed to add data:', err);
        }
      })
    }
    //注册成功返回登录页面
    wx.showToast({
      title: '注册成功',
      icon: 'success',
      duration: 1000,
      success: function () {
        wx.navigateTo({
          url: '../login/login'
        })
      }
    });
  }
})