const mitt = require('mitt');
App({
  globalData: {
    classData: [],
    eventBus: mitt(),
    userType:1
  },
  onLaunch: function () {
    const that = this;
    // 监听登录成功事件
    that.globalData.eventBus.on('loginSuccess', function (data) {
      const hasTeacherId = data.some(user => user.hasOwnProperty('teacherId'));
      //判断是老师还是学生，更改对应条件
      if (hasTeacherId) {
      } else {
        that.globalData.userType=2;
      }
    });
  }
})
