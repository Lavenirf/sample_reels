var app=getApp();
Page({
  data: {
    classList: [],
    time: new Date().toISOString().substring(0, 10)
  },
  //跳转页面
  tclass: function () {
    wx.navigateTo({
      url: '../../PPT/PPT',
    })
  },
  twork: function () {
    wx.navigateTo({
      url: '../zuoyepigai_1/zuoyepigai_1',
    })
  },
  tword: function () {
    wx.navigateTo({
      url: '../zaixianzuoye_1/zaixianzuoye_1',
    })
  },
  onLoad: function () {
    var that = this;
    //获取班级信息
    app.globalData.eventBus.on('loginSuccess', function(data) {
      // 在这里处理登录成功后的数据
      that.setData({
        classList:data
      })
      // 存储到全局数据中
      app.globalData.classData = data;
    });
  },
  onShareAppMessage() {
    return {};
  },
  onShow: function () {
    if (typeof this.getTabBar === 'function' &&
      this.getTabBar()) {
      this.getTabBar().setData({
        selected: 0
      })
    }
 
  },
});