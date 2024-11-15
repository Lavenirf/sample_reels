var app=getApp();
Component({
  data: {
    selected: 0,
    color: "#000000",
    roleId: '',
    selectedColor: "#1396DB",
    allList: [{

      list1: [{
        "pagePath": "/pages/teacher/shouye_1/shouye_1",
        "text": "首页",
        "iconPath": "../img/d13.png",
        "selectedIconPath": "../img/d13.png"
      },{
        "pagePath": "/pages/teacher/gonggao_2/gonggao_2",
        "text": "通知",
        "iconPath": "../img/d1 (2).png",
        "selectedIconPath": "../img/d1 (2).png"
      }, {
        "pagePath": "/pages/mine/mine",
        "text": "我的",
        "iconPath": "../img/d10.png",
        "selectedIconPath": "../img/d10.png"
      }],
      list2: [{
        "pagePath":"/pages/student/stushouye/stushouye",
        "text":"首页",
        "iconPath":"../img/d13.png",
        "selectedIconPath":"../img/d13.png"
      }, {
        "pagePath":"/pages/student/stugonggao/stugonggao",
        "text":"通知",
        "iconPath":"../img/d1 (2).png",
        "selectedIconPath":"../img/d1 (2).png"
      }, {
        "pagePath": "/pages/mine/mine",
        "text": "我的",
        "iconPath": "../img/d10.png",
        "selectedIconPath": "../img/d10.png"
      }],
    }],
    list: []
  },
  attached() {
    let roleId =app.globalData.userType;
    if (roleId == 1) {
      this.setData({
        list: this.data.allList[0].list1
      })
    }else if(roleId==2){
      this.setData({
        list: this.data.allList[0].list2
      })
    }
  },
  methods: {
    
    switchTab(e) {
      const data = e.currentTarget.dataset;
      const url = data.path;
      wx.switchTab({ 
        url:url
      });
      this.setData({
        selected: data.index
      })
    }
  },
})