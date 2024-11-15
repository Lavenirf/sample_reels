// pages/login/login.js
Page({
  data: {
    disabled: true,
    btnstate: 'default',
    account: '',
    user: {
      userId: '',
      password: ''
    }
  },
  accountInput: function (event) {
    var content = event.detail.value.trim();
    var username = event.detail.value
    if (content !== '') {
      this.setData({
        disabled: false,
        btnstate: 'primary',
        account: content,
        'user.userId': username
      });
    } else {
      this.setData({
        disabled: true,
        btnstate: 'default',
      });
    }
  },
  //获取输入的密码
  pwdBlur: function (e) {
    var password = e.detail.value;
    if (password != '') {
      this.setData({
        'user.password': password
      });
    }
  },
  //登录
  handleLogin: function () {
    var that = this;
    var userId = that.data.user.userId;
    var password = that.data.user.password;
    let url = 'http://47.96.122.197:80/teacher/login/' + userId + '/' + password;
    wx.request({
      url: url,
      method: "GET",
      data: {
        user: that.data.user
      },
      success: function (res) {
        console.log(res.data);
        //登录成功
        if (res.statusCode === 200 && Object.keys(res.data).length !== 0) {
          let teacherId=res.data.teacherId
          let url='http://47.96.122.197:80/teacherclass/teacherid/'+teacherId;
          wx.request({
            url: url,
            success: function (res) {
              console.log(res.data);
              if (res.statusCode === 200) {
                getApp().globalData.eventBus.emit('loginSuccess', res.data);
              }
            }
          });
          wx.showLoading({
            title: '正在登录...',
            mask: true
          });
          wx.switchTab({
            url: '../teacher/shouye_1/shouye_1',
          });
        }else {
          // 老师登录失败判断学生里面是否有信息
          let url='http://47.96.122.197:80/student/login/' + userId + '/' + password;
          wx.request({
            url: url,
            method: "GET",
            data: {
              user: that.data.user
            },
            success: function (res) {
              console.log(res.data);
              //登录成功
              if (res.statusCode === 200 && Object.keys(res.data).length !== 0) {
                let studentId=res.data.studentId
                let url='http://47.96.122.197:80/studentclass/StudentClassId/'+studentId;
                wx.request({
                  url: url,
                  success: function (res) {
                    console.log(res.data);
                    if (res.statusCode === 200) {
                      getApp().globalData.eventBus.emit('loginSuccess', res.data);
                    }
                  }
                });
                wx.showLoading({
                  title: '正在登录...',
                  mask: true
                });
                wx.switchTab({
                  url: '../student/stushouye/stushouye',
                });
              }else {
                // 登录失败
                wx.showToast({
                  title: '登录失败，请重试',
                  icon: 'none'
                });
              }
            },
          });
        }
      },
      fail: function(error) {
        // 请求失败
        wx.showToast({
          title: '请求失败，请重试',
          icon: 'none'
        });
      }
    });
  }
})