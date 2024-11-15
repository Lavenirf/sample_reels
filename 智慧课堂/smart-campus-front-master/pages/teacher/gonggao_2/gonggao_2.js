Page({
  data: {
    fruit: [{
      id: 1,
      name: '开会',
    }, {
      id: 2,
      name: '补课'
    }, {
      id: 3,
      name: '考试'
    }, {
      id: 4,
      name: '其它',
    }],
    current: '其它',
    position: 'left',

    checked: false,
    disabled: false,

  },

  handleFruitChange({ detail = {} }) {
    this.setData({
      current: detail.value
    });
  },
  handleClick() {
    this.setData({
      position: this.data.position.indexOf('left') !== -1 ? 'right' : 'left',
    });
  },
  handleDisabled() {
    this.setData({
      disabled: !this.data.disabled
    });
  },
  handleAnimalChange({ detail = {} }) {
    this.setData({
      checked: detail.current
    });
  },
  onLoad() {
    var that = this;
  }, onShow: function () {
    if (typeof this.getTabBar === 'function' &&
      this.getTabBar()) {
      this.getTabBar().setData({
        selected: 0
      })
    }
 
  },
});




