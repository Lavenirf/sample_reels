export const candidatesType = [
    {label: '普通类', value: '普通类'},
    {label: '艺术类', value: '艺术类'},
    {label: '春季高考', value: '春季高考'},
    {label: '高职单招', value: '高职单招'},
    {label: '综合评价', value: '综合评价'}]
export const grade = [
    {label: '高一', value: '高一'},
    {label: '高二', value: '高二'},
    {label: '高三(2022年入学)', value: '高三(2022年入学)'},
    {label: '高三(2021年入学)', value: '高三(2021年入学)'},
]
//院校转换规则
export const conversionRules = {
    '10': 'A',
    '11': 'B',
    '12': 'C',
    '13': 'D',
    '14': 'E',
    '15': 'T',
    '16': 'F',
    '18': 'G',
    '19': 'H',
    '50': 'K',
    '51': 'M',
    '81': 'N',
    '91': 'P',
};
export const cities = ['物理', '化学', '生物', '政治', '历史', '地理']
export const routers = ['首页', '个人信息', '所有志愿表', '职业测试']
//专业门类
export const professionalCategory =['不限','教育学','理学','工学','哲学','经济学','法学','文学','历史学','农学','医学','管理学','艺术学']
export const region = ['不限', '北京', '天津', '河北', '山西', '内蒙古', '辽宁', '吉林', '黑龙江', '上海', '江苏', '浙江', '安徽', '福建', '江西', '山东', '河南', '湖北', '湖南', '广东', '广西', '海南', '重庆', '四川', '贵州', '云南', '陕西',
    '甘肃', '青海', '宁夏', '新疆']
export const region_NotAll = [ '北京', '天津', '河北', '山西', '内蒙古', '辽宁', '吉林', '黑龙江', '上海', '江苏', '浙江', '安徽', '福建', '江西', '山东', '河南', '湖北', '湖南', '广东', '广西', '海南', '重庆', '四川', '贵州', '云南', '陕西',
    '甘肃', '青海', '宁夏', '新疆']
//院校类型
export const institutionType =['不限','军事','综合','师范','理工','其他','农林','医药','语言','财经','政法','体育','艺术','民族']
//院校层次
export const universityLevel =['不限','公办','民办','中外合作办学','内地与港澳台地区合作办学','985','211','双一流','教育部直属','中央部委','双高计划','强基计划']
//院校所属
export const InstitutionAffiliation =['全部', '北京', '天津', '河北', '山西', '内蒙古', '辽宁', '吉林', '黑龙江', '上海', '江苏', '浙江', '安徽', '福建', '江西', '山东', '河南', '湖北', '湖南', '广东', '广西', '海南', '重庆', '四川', '贵州', '云南', '陕西',
    '甘肃', '青海', '宁夏', '新疆']
//院校类型
export const InstitutionType =['全部','军事','综合','师范','理工','其他','农林','医药','语言','财经','政法','体育','艺术','民族']
//办学特色
export const SchoolCharacteristics=['全部','普通本科','专科（高职）']
//院校特色
export const InstitutionalCharacteristics=['全部','985','211','双一流','保研资格','教育部直属','中央部委','双高计划','强基计划','国际本科']
//志愿填报时间
export const volunteerTime =[
    {batch:"普通类提前批、体育类提前批第1次志愿、艺术类本科提前批第一次志愿", time:"6月29日9点至18点"},
    {batch:"春季高考本科提前批（仅限技能拔尖人才填报）第1次志愿", time:"6月29日9点至18点"},
    {batch:"普通类特殊类型批", time:"7月5日9点至7日18点"},
    {batch:"普通类常规批和体育类常规批第1次志愿", time:"7月5日9点至7日18点"},
    {batch:"艺术类本科批第1次志愿", time:"7月5日9点至7日18点"},
    {batch:"春季高考本科批第1次志愿", time:"7月5日9点至7日18点"},
    {batch:"普通类提前批第2次志愿", time:"7月13日9点至18点"},
    {batch:"艺术类本科提前批第2次志愿", time:"7月13日9点至18点"},
    {batch:"体育类提前批第2次志愿", time:"7月13日9点至18点"},
    {batch:"春季高考本科提前批第2次志愿", time:"7月13日9点至18点"},
    {batch:"艺术类本科批第2次志愿", time:"7月20日9点至18点"},
    {batch:"春季高考本科批第2次志愿", time:"7月20日9点至18点"},
    {batch:"普通类、体育类常规批第2次志愿（含第1次志愿剩余本科计划、所有专科计划）", time:"7月24日9点至26日18点"},
    {batch:"艺术类专科批第1次志愿", time:"7月24日9点至26日18点"},
    {batch:"春季高考专科批（含技能拔尖人才）第1次志愿", time:"7月24日9点至26日18点"},
    {batch:"普通类、体育类常规批第3次志愿", time:"7月31日9点至18点"},
    {batch:"艺术类专科批第2次志愿", time:"7月31日9点至18点"},
    {batch:"春季高考专科批第2次志愿", time:"7月31日9点至18点"},
]
//录取时间
export const admissionTime = [
    {batch:"艺术类本科提前批第1次志愿", time:"7月11日00:00"},
    {batch:"普通类提前批第1次志愿", time:"7月12日00:00"},
    {batch:"春季高考本科提前批第1次志愿", time:"7月12日00:00"},
    {batch:"体育类提前批第1次志愿", time:"7月12日00:00"},
    {batch:"普通类提前批第2次志愿", time:"7月16日00:00"},
    {batch:"艺术类本科提前批第2次志愿", time:"7月16日00:00"},
    {batch:"体育类提前批第2次志愿", time:"7月16日00:00"},
    {batch:"春季高考本科提前批第2次志愿", time:"7月16日00:00"},
    {batch:"艺术类本科批第1次志愿", time:"7月19日00:00"},
    {batch:"体育类常规批第1次志愿", time:"7月19日00:00"},
    {batch:"春季高考本科第1次志愿", time:"7月19日00:00"},
    {batch:"普通类特殊类型批第1次志愿", time:"7月22日00:00"},
    {batch:"普通类常规批第1次志愿", time:"7月22日00:00"},
    {batch:"艺术类本科批第2次志愿", time:"7月23日00:00"},
    {batch:"春季高考本科第2次志愿", time:"7月23日00:00"},
    {batch:"普通类常规批第2次志愿", time:"7月30日00:00"},
    {batch:"体育类常规批第2次志愿", time:"7月30日00:00"},
    {batch:"艺术类专科批第1次志愿", time:"7月30日00:00"},
    {batch:"春季高考专科批（含技能拔尖人才）第1次志愿", time:"7月30日00:00"},
    {batch:"普通类常规批第3次志愿", time:"8月2日00:00"},
    {batch:"体育类常规批第3次志愿", time:"8月2日00:00"},
    {batch:"艺术类专科批第2次志愿", time:"8月2日00:00"},
    {batch:"春季高考专科批第2次志愿", time:"8月2日00:00"},
]
//专业数据
export const majorData = [
    {
        name: '本科',
        value: 47.36,
        itemStyle: {
            color: '#4B0082'
        },
        nick:"本科",
        children: [
            {
                name: '哲学',
                value: 0.7,
                itemStyle: {
                    color: '#FF6347'
                },
                nick:"本科",
                children: [
                    {
                        name: '哲学类',
                        value: 0.7,
                        itemStyle: {
                            color: '#FF8C00'
                        }
                    }
                ]
            },
            {
                name: '经济学',
                value: 2.2,
                itemStyle: {
                    color: '#1E90FF',
                },
                nick:"本科",
                children: [
                    {
                        name: '经济学类',
                        value: 0.55,
                        itemStyle: {
                            color: '#00BFFF',
                        }
                    },
                    {
                        name: '财政学类',
                        value: 0.55,
                        itemStyle: {
                            color: '#87CEEB',
                        }
                    },
                    {
                        name: '金融学类',
                        value: 0.55,
                        itemStyle: {
                            color: '#87CEFA'
                        }
                    },
                    {
                        name: '经济与贸易类',
                        value: 0.55,
                        itemStyle: {
                            color: '#4682B4'
                        }
                    },
                ]
            },
            {
                name: '法学',
                value: 3.3,
                itemStyle: {
                    color: '#D2691E'
                },
                nick:"本科",
                children: [
                    {
                        name: "法学类",
                        value: 0.55,
                        itemStyle: {
                            color: '#CD853F'
                        }
                    },
                    {
                        name: "政治学类",
                        value: 0.55,
                        itemStyle: {
                            color: '#B8860B'
                        }
                    },
                    {
                        name: "社会学类",
                        value: 0.55,
                        itemStyle: {
                            color: '#DAA520'
                        }
                    },
                    {
                        name: "民族学类",
                        value: 0.55,
                        itemStyle: {
                            color: '#F4A460'
                        }
                    },
                    {
                        name: "马克思主义理论类",
                        value: 0.55,
                        itemStyle: {
                            color: '#BC8F8F'
                        }
                    },
                    {
                        name: "公安学类",
                        value: 0.55,
                        itemStyle: {
                            color: '#D2B48C'
                        }
                    },
                ]
            },
            {
                name: '教育学',
                value: 1.1,
                itemStyle: {
                    color: '#FF00FF'
                },
                nick:"本科",
                children: [
                    {
                        name: "教育学类",
                        value: 0.55,
                        itemStyle: {
                            color: '#DA70D6'
                        }
                    },
                    {
                        name: "体育学类",
                        value: 0.55,
                        itemStyle: {
                            color: '#DDA0DD'
                        }
                    },
                ]
            },
            {
                name: '文学',
                value: 1.65,
                itemStyle: {
                    color: '#FFD700'
                },
                nick:"本科",
                children: [
                    {
                        name: "中国语言文学类",
                        value: 0.55,
                        itemStyle: {
                            color: '#EEE8AA'
                        }
                    },
                    {
                        name: "外国语言文学类",
                        value: 0.55,
                        itemStyle: {
                            color: '#FFDAB9'
                        }
                    },
                    {
                        name: "新闻传播类",
                        value: 0.55,
                        itemStyle: {
                            color: '#FFE4B5'
                        }
                    },
                ]
            },
            {
                name: '历史学',
                value: 0.7,
                itemStyle: {
                    color: '#8B4513'
                },
                nick:"本科",
                children: [
                    {
                        name: "历史学类",
                        value: 0.7,
                        itemStyle: {
                            color: '#A0522D'
                        }
                    },
                ]
            },
            {
                name: '理学',
                value: 5.5,
                itemStyle: {
                    color: '#6B8E23'
                },
                nick:"本科",
                children: [
                    {
                        name: "数学类",
                        value: 0.45,
                        itemStyle: {
                            color: '#006400'
                        }
                    },
                    {
                        name: "物理学类",
                        value: 0.5,
                        itemStyle: {
                            color: '#2E8B57'
                        }
                    },
                    {
                        name: "化学类",
                        value: 0.45,
                        itemStyle: {
                            color: '#3CB371'
                        }
                    },
                    {
                        name: "天文学类",
                        value: 0.45,
                        itemStyle: {
                            color: '#20B2AA'
                        }
                    },
                    {
                        name: "地理科学类",
                        value: 0.45,
                        itemStyle: {
                            color: '#7FFFD4'
                        }
                    },
                    {
                        name: "大气科学类",
                        value: 0.45,
                        itemStyle: {
                            color: '#66CDAA'
                        }
                    },
                    {
                        name: "海洋科学类",
                        value: 0.45,
                        itemStyle: {
                            color: '#00FF7F'
                        }
                    },
                    {
                        name: "地球物理学类",
                        value: 0.45,
                        itemStyle: {
                            color: '#00FF00'
                        }
                    },
                    {
                        name: "地质学类",
                        value: 0.45,
                        itemStyle: {
                            color: '#7CFC00'
                        }
                    },
                    {
                        name: "生物科学类",
                        value: 0.45,
                        itemStyle: {
                            color: '#9ACD32'
                        }
                    },
                    {
                        name: "心理学类",
                        value: 0.45,
                        itemStyle: {
                            color: '#98FB98'
                        }
                    },
                    {
                        name: "统计学类",
                        value: 0.45,
                        itemStyle: {
                            color: '#8FBC8F'
                        }
                    },
                ]

            },
            {
                name: '工学',
                value: 17.05,
                itemStyle: {
                    color: '#FFFF00'
                },
                nick:"本科",
                children: [
                    {
                        name: "力学类",
                        value: 0.5,
                        itemStyle: {
                            color: '#FFFACD'
                        }
                    },
                    {
                        name: "机械类",
                        value: 0.55,
                        itemStyle: {
                            color: '#a6954e'
                        }
                    },
                    {
                        name: "仪器类",
                        value: 0.5,
                        itemStyle: {
                            color: '#FFEFD5'
                        }
                    },
                    {
                        name: "材料类",
                        value: 0.55,
                        itemStyle: {
                            color: '#FFE4B5'
                        }
                    },
                    {
                        name: "能源动力类",
                        value: 0.5,
                        itemStyle: {
                            color: '#FFDAB9'
                        }
                    },
                    {
                        name: "电气类",
                        value: 0.5,
                        itemStyle: {
                            color: '#EEE8AA'
                        }
                    },
                    {
                        name: "电子信息类",
                        value: 0.55,
                        itemStyle: {
                            color: '#F0E68C'
                        }
                    },
                    {
                        name: "自动化类",
                        value: 0.5,
                        itemStyle: {
                            color: '#a6954e'
                        }
                    },
                    {
                        name: "计算机类",
                        value: 0.5,
                        itemStyle: {
                            color: '#BDB76B'
                        }
                    },
                    {
                        name: "土木类",
                        value: 0.5,
                        itemStyle: {
                            color: '#FFD700'
                        }
                    },
                    {
                        name: "水利类",
                        value: 0.5,
                        itemStyle: {
                            color: '#FFFACD'
                        }
                    },
                    {
                        name: "测绘类",
                        value: 0.55,
                        itemStyle: {
                            color: '#FAFAD2'
                        }
                    },
                    {
                        name: "化工与制药类",
                        value: 0.5,
                        itemStyle: {
                            color: '#FFEFD5'
                        }
                    },
                    {
                        name: "地质类",
                        value: 0.5,
                        itemStyle: {
                            color: '#FFE4B5'
                        }
                    },
                    {
                        name: "矿业类",
                        value: 0.55,
                        itemStyle: {
                            color: '#FFDAB9'
                        }
                    },
                    {
                        name: "纺织类",
                        value: 0.5,
                        itemStyle: {
                            color: '#EEE8AA'
                        }
                    },
                    {
                        name: "轻工类",
                        value: 0.5,
                        itemStyle: {
                            color: '#F0E68C'
                        }
                    },
                    {
                        name: "交通运输类",
                        value: 0.55,
                        itemStyle: {
                            color: '#BDB76B'
                        }
                    },
                    {
                        name: "海洋工程类",
                        value: 0.5,
                        itemStyle: {
                            color: '#FFD700'
                        }
                    },
                    {
                        name: "航空航天类",
                        value: 0.55,
                        itemStyle: {
                            color: '#00FFFF'
                        }
                    },
                    {
                        name: "兵器类",
                        value: 0.5,
                        itemStyle: {
                            color: '#00FFFF'
                        }
                    },
                    {
                        name: "核工程类",
                        value: 0.55,
                        itemStyle: {
                            color: '#00CED1'
                        }
                    },
                    {
                        name: "农业工程类",
                        value: 0.55,
                        itemStyle: {
                            color: '#48D1CC'
                        }
                    },
                    {
                        name: "交叉工程类",
                        value: 0.5,
                        itemStyle: {
                            color: '#ADD8E6'
                        }
                    },
                    {
                        name: "林业工程类",
                        value: 0.5,
                        itemStyle: {
                            color: '#3CB371'
                        }
                    },
                    {
                        name: "医学技术类",
                        value: 0.5,
                        itemStyle: {
                            color: '#7CFC00'
                        }
                    },
                    {
                        name: "环境科学与工程类",
                        value: 0.5,
                        itemStyle: {
                            color: '#00FF7F'
                        }
                    },
                    {
                        name: "生物医学工程类",
                        value: 0.5,
                        itemStyle: {
                            color: '#00FF00'
                        }
                    },
                    {
                        name: "食品科学与工程类",
                        value: 0.5,
                        itemStyle: {
                            color: '#7FFF00'
                        }
                    },
                    {
                        name: "建筑类",
                        value: 0.5,
                        itemStyle: {
                            color: '#7CFC00'
                        }
                    },
                    {
                        name: "安全技术与工程",
                        value: 0.5,
                        itemStyle: {
                            color: '#008080'
                        }
                    },
                    {
                        name: "生物工程类",
                        value: 0.5,
                        itemStyle: {
                            color: '#98FB98'
                        }
                    },
                    {
                        name: "公安技术类",
                        value: 0.5,
                        itemStyle: {
                            color: '#ADFF2F'
                        }
                    },
                ]
            },
            {
                name: '农学',
                value: 3.58,
                itemStyle: {
                    color: '#155e44'
                },
                nick:"本科",
                children: [
                    {
                        name: "植物生产类",
                        value: 0.55,
                        itemStyle: {
                            color: '#006400'
                        }
                    },
                    {
                        name: "自然保护与环境生态类",
                        value: 0.55,
                        itemStyle: {
                            color: '#219269'
                        }
                    },
                    {
                        name: "动物生产类",
                        value: 0.55,
                        itemStyle: {
                            color: '#228B22'
                        }
                    },
                    {
                        name: "动物医学类",
                        value: 0.55,
                        itemStyle: {
                            color: '#ADFF2F'
                        }
                    },
                    {
                        name: "林学类",
                        value: 0.55,
                        itemStyle: {
                            color: '#00FF7F'
                        }
                    },
                    {
                        name: "水产类",
                        value: 0.55,
                        itemStyle: {
                            color: '#00FA9A'
                        }
                    },
                    {
                        name: "草学类",
                        value: 0.55,
                        itemStyle: {
                            color: '#808000'
                        }
                    },
                ]
            },
            {
                name: '医学',
                value: 4.95,
                itemStyle: {
                    color: '#8A2BE2'
                },
                nick:"本科",
                children: [
                    {
                        name: "基础医学类",
                        value: 0.45,
                        itemStyle: {
                            color: '#9966CC'
                        }
                    },
                    {
                        name: "临床医学类",
                        value: 0.45,
                        itemStyle: {
                            color: '#9400D3'
                        }
                    },
                    {
                        name: "口腔医学类",
                        value: 0.45,
                        itemStyle: {
                            color: '#DDA0DD'
                        }
                    },
                    {
                        name: "公共卫生与预防医学类",
                        value: 0.45,
                        itemStyle: {
                            color: '#8B008B'
                        }
                    },
                    {
                        name: "中医学类",
                        value: 0.45,
                        itemStyle: {
                            color: '#6A5ACD'
                        }
                    },
                    {
                        name: "中西医结合类",
                        value: 0.45,
                        itemStyle: {
                            color: '#DDA0DD'
                        }
                    },
                    {
                        name: "药学类",
                        value: 0.45,
                        itemStyle: {
                            color: '#8A2BE2'
                        }
                    },
                    {
                        name: "中药学类",
                        value: 0.45,
                        itemStyle: {
                            color: '#1E90FF'
                        }
                    },
                    {
                        name: "法医学类",
                        value: 0.45,
                        itemStyle: {
                            color: '#6495ED'
                        }
                    },
                    {
                        name: "医学技术类",
                        value: 0.45,
                        itemStyle: {
                            color: '#4169E1'
                        }
                    },
                    {
                        name: "护理学类",
                        value: 0.45,
                        itemStyle: {
                            color: '#00008B'
                        }
                    }
                ]
            },
            {
                name: '管理学',
                value: 3.85,
                itemStyle: {
                    color: '#FF1493'
                },
                nick:"本科",
                children: [
                    {
                        name: "管理科学与工程类",
                        value: 0.42,
                        itemStyle: {
                            color: '#f67cab'
                        }
                    },
                    {
                        name: "工商管理类",
                        value: 0.42,
                        itemStyle: {
                            color: '#FF69B4'
                        }
                    },
                    {
                        name: "农业经济管理类",
                        value: 0.42,
                        itemStyle: {
                            color: '#FFC0CB'
                        }
                    },
                    {
                        name: "公共管理类",
                        value: 0.42,
                        itemStyle: {
                            color: '#DB7093'
                        }
                    },
                    {
                        name: "图书情报与档案管理类",
                        value: 0.45,
                        itemStyle: {
                            color: '#f67cab'
                        }
                    },
                    {
                        name: "物流管理与工程类",
                        value: 0.42,
                        itemStyle: {
                            color: '#C71585'
                        }
                    },
                    {
                        name: "工业工程类",
                        value: 0.42,
                        itemStyle: {
                            color: '#FFC0CB'
                        }
                    },
                    {
                        name: "电子商务类",
                        value: 0.42,
                        itemStyle: {
                            color: '#FF7F50'
                        }
                    },
                    {
                        name: "旅游管理类",
                        value: 0.42,
                        itemStyle: {
                            color: '#FF6347'
                        }
                    }
                ]
            },
            {
                name: '艺术学',
                value: 2.75,
                itemStyle: {
                    color: '#DC143C'
                },
                nick:"本科",
                children: [
                    {
                        name: "艺术理论类",
                        value: 0.55,
                        itemStyle: {
                            color: '#F08080'
                        }
                    },
                    {
                        name: "音乐与舞蹈学类",
                        value: 0.55,
                        itemStyle: {
                            color: '#E9967A'
                        }
                    },
                    {
                        name: "戏剧与影视学类",
                        value: 0.55,
                        itemStyle: {
                            color: '#FFA07A'
                        }
                    },
                    {
                        name: "美术学类",
                        value: 0.55,
                        itemStyle: {
                            color: '#B22222'
                        }
                    },
                    {
                        name: "设计学类",
                        value: 0.55,
                        itemStyle: {
                            color: '#CD5C5C'
                        }
                    },
                ]
            },
        ]
    },
    {
        name: '专科（高职）',
        value:52.64,
        itemStyle: {
            color: '#0000FF'
        },
        nick:"专科（高职）",
        children: [
            {
                name: '农林牧渔大类',
                value: 2.2,
                itemStyle: {
                    color: '#155e44'
                },
                nick:"专科（高职）",
                children: [
                    {
                        name: "农业类",
                        value: 0.55,
                        itemStyle: {
                            color: '#98FB98'
                        }
                    },
                    {
                        name: "林业类",
                        value: 0.55,
                        itemStyle: {
                            color: '#32CD32'
                        }
                    },
                    {
                        name: "畜牧业类",
                        value: 0.55,
                        itemStyle: {
                            color: '#20B2AA'
                        }
                    },
                    {
                        name: "渔业类",
                        value: 0.55,
                        itemStyle: {
                            color: '#228B22'
                        }
                    },
                ]
            },
            {
                name: '资源环境与安全大类',
                value: 4.38,
                itemStyle: {
                    color: '#A52A2A'
                },
                nick:"专科（高职）",
                children: [
                    {
                        name: "资源勘察类",
                        value: 0.48,
                        itemStyle: {
                            color: '#FFEBCD'
                        }
                    },
                    {
                        name: "地质类",
                        value: 0.48,
                        itemStyle: {
                            color: '#B8860B'
                        }
                    },
                    {
                        name: "测绘地理信息类",
                        value: 0.48,
                        itemStyle: {
                            color: '#BC8F8F'
                        }
                    },
                    {
                        name: "石油与天然气类",
                        value: 0.49,
                        itemStyle: {
                            color: '#8B4513'
                        }
                    },
                    {
                        name: "煤炭类",
                        value: 0.48,
                        itemStyle: {
                            color: '#000000'
                        }
                    },
                    {
                        name: "金属与非金属矿类",
                        value: 0.48,
                        itemStyle: {
                            color: '#D2691E'
                        }
                    },
                    {
                        name: "气象类",
                        value: 0.48,
                        itemStyle: {
                            color: '#F5DEB3'
                        }
                    },
                    {
                        name: "环境保护类",
                        value: 0.49,
                        itemStyle: {
                            color: '#00FF00'
                        }
                    },
                    {
                        name: "安全类",
                        value: 0.50,
                        itemStyle: {
                            color: '#CD853F'
                        }
                    }
                ]
            },
            {
                name: '能源动力与材料大类',
                value: 3.38,
                itemStyle: {
                    color: '#ADD8E6'
                },
                nick:"专科（高职）",
                children: [
                    {
                        name: "电力技术类",
                        value: 0.46,
                        itemStyle: {
                            color: '#AFEEEE'
                        }
                    },
                    {
                        name: "热能与发电工程类",
                        value: 0.46,
                        itemStyle: {
                            color: '#48D1CC'
                        }
                    },
                    {
                        name: "新能源发电工程类",
                        value: 0.46,
                        itemStyle: {
                            color: '#00CED1'
                        }
                    },
                    {
                        name: "黑色金属材料类",
                        value: 0.46,
                        itemStyle: {
                            color: '#f67cab'
                        }
                    },
                    {
                        name: "有色金属类",
                        value: 0.46,
                        itemStyle: {
                            color: '#008B8B'
                        }
                    },
                    {
                        name: "非金属材料类",
                        value: 0.46,
                        itemStyle: {
                            color: '#4682B4'
                        }
                    },
                    {
                        name: "建筑材料类",
                        value: 0.46,
                        itemStyle: {
                            color: '#00008B'
                        }
                    }
                ]
            },
            {
                name: '土木建筑大类',
                value: 3.28,
                itemStyle: {
                    color: '#2F4F4F'
                },
                nick:"专科（高职）",
                children: [
                    {
                        name: "建筑设计类",
                        value: 0.46,
                        itemStyle: {
                            color: '#808080'
                        }
                    },
                    {
                        name: "城乡规划与管理类",
                        value: 0.46,
                        itemStyle: {
                            color: '#DCDCDC'
                        }
                    },
                    {
                        name: "土建施工类",
                        value: 0.46,
                        itemStyle: {
                            color: '#A9A9A9'
                        }
                    },
                    {
                        name: "建筑设备类",
                        value: 0.46,
                        itemStyle: {
                            color: '#696969'
                        }
                    },
                    {
                        name: "建筑工程管理类",
                        value: 0.46,
                        itemStyle: {
                            color: '#708090'
                        }
                    },
                    {
                        name: "市政工程类类",
                        value: 0.46,
                        itemStyle: {
                            color: '#778899'
                        }
                    },
                    {
                        name: "房地产类",
                        value: 0.46,
                        itemStyle: {
                            color: '#C0C0C0'
                        }
                    },
                ]
            },
            {
                name: '水利大类',
                value: 2.2,
                itemStyle: {
                    color: '#1E90FF'
                },
                nick:"专科（高职）",
                children: [
                    {
                        name: "水文水资源类",
                        value: 0.55,
                        itemStyle: {
                            color: '#6495ED'
                        }
                    },
                    {
                        name: "水利工程与管理",
                        value: 0.55,
                        itemStyle: {
                            color: '#87CEEB'
                        }
                    },
                    {
                        name: "水利水电设备类",
                        value: 0.55,
                        itemStyle: {
                            color: '#B0C4DE'
                        }
                    },
                    {
                        name: "水土保持与水环境类",
                        value: 0.55,
                        itemStyle: {
                            color: '#00FFFF'
                        }
                    },
                ]
            },
            {
                name: '装备制造大类',
                value: 3.28,
                itemStyle: {
                    color: '#483D8B'
                },
                nick:"专科（高职）",
                children: [
                    {
                        name: "机械设计制造类",
                        value: 0.46,
                        itemStyle: {
                            color: '#9400D3'
                        }
                    },
                    {
                        name: "机电设备类",
                        value: 0.46,
                        itemStyle: {
                            color: '#8A2BE2'
                        }
                    },
                    {
                        name: "自动化类",
                        value: 0.46,
                        itemStyle: {
                            color: '#FF00FF'
                        }
                    },
                    {
                        name: "轨道设备类",
                        value: 0.46,
                        itemStyle: {
                            color: '#9932CC'
                        }
                    },
                    {
                        name: "船舶与海洋工程装备类",
                        value: 0.46,
                        itemStyle: {
                            color: '#9966CC'
                        }
                    },
                    {
                        name: "航空装备类",
                        value: 0.46,
                        itemStyle: {
                            color: '#BA55D3'
                        }
                    },
                    {
                        name: "汽车制造类",
                        value: 0.46,
                        itemStyle: {
                            color: '#9370DB'
                        }
                    }
                ]
            },
            {
                name: '生物与化工大类',
                value: 1.1,
                itemStyle: {
                    color: '#8B0000'
                },
                nick:"专科（高职）",
                children: [
                    {
                        name: "生物技术类",
                        value: 0.55,
                        itemStyle: {
                            color: '#CD5C5C'
                        }
                    },
                    {
                        name: "化工技术类",
                        value: 0.55,
                        itemStyle: {
                            color: '#F08080'
                        }
                    }
                ]
            },
            {
                name: '轻工纺织大类',
                value: 2.2,
                itemStyle: {
                    color: '#FFD700'
                },
                nick:"专科（高职）",
                children: [
                    {
                        name: "轻化工类",
                        value: 0.55,
                        itemStyle: {
                            color: '#BDB76B'
                        }
                    },
                    {
                        name: "包装类",
                        value: 0.55,
                        itemStyle: {
                            color: '#F0E68C'
                        }
                    },
                    {
                        name: "印刷类",
                        value: 0.55,
                        itemStyle: {
                            color: '#FFEFD5'
                        }
                    },
                    {
                        name: "纺织服务类",
                        value: 0.55,
                        itemStyle: {
                            color: '#FFFACD'
                        }
                    },
                ]
            },
            {
                name: '食品药品与粮食大类',
                value: 2.75,
                itemStyle: {
                    color: '#C71585'
                },
                nick:"专科（高职）",
                children: [
                    {
                        name: "食品类",
                        value: 0.55,
                        itemStyle: {
                            color: '#DB7093'
                        }
                    },
                    {
                        name: "药品与医疗器械类",
                        value: 0.55,
                        itemStyle: {
                            color: '#FF1493'
                        }
                    },
                    {
                        name: "食品药品管理类",
                        value: 0.55,
                        itemStyle: {
                            color: '#FF69B4'
                        }
                    },
                    {
                        name: "粮食类",
                        value: 0.55,
                        itemStyle: {
                            color: '#FFB6C1'
                        }
                    },
                    {
                        name: "粮食储检类",
                        value: 0.55,
                        itemStyle: {
                            color: '#FFC0CB'
                        }
                    },
                ]
            },
            {
                name: '交通运输大类',
                value: 3.28,
                itemStyle: {
                    color: '#696969'
                },
                nick:"专科（高职）",
                children: [
                    {
                        name: "铁道运输类",
                        value: 0.46,
                        itemStyle: {
                            color: '#778899'
                        }
                    },
                    {
                        name: "道路运输类",
                        value: 0.46,
                        itemStyle: {
                            color: '#2F4F4F'
                        }
                    },
                    {
                        name: "水上运输类",
                        value: 0.46,
                        itemStyle: {
                            color: '#D3D3D3'
                        }
                    },
                    {
                        name: "航空运输类",
                        value: 0.46,
                        itemStyle: {
                            color: '#DCDCDC'
                        }
                    },
                    {
                        name: "管道运输类",
                        value: 0.46,
                        itemStyle: {
                            color: '#808080'
                        }
                    },
                    {
                        name: "城市轨道交通类",
                        value: 0.46,
                        itemStyle: {
                            color: '#708090'
                        }
                    },
                    {
                        name: "邮政类",
                        value: 0.46,
                        itemStyle: {
                            color: '#A9A9A9'
                        }
                    },
                ]
            },
            {
                name: '电子与信息大类',
                value: 2.2,
                itemStyle: {
                    color: '#556B2F'
                },
                nick:"专科（高职）",
                children: [
                    {
                        name: "电子信息类",
                        value: 0.55,
                        itemStyle: {
                            color: '#6B8E23'
                        }
                    },
                    {
                        name: "计算机类",
                        value: 0.55,
                        itemStyle: {
                            color: '#20B2AA'
                        }
                    },
                    {
                        name: "通信类",
                        value: 0.55,
                        itemStyle: {
                            color: '#00FF7F'
                        }
                    },
                    {
                        name: "集成电路类",
                        value: 0.55,
                        itemStyle: {
                            color: '#00FA9A'
                        }
                    },
                ]
            },
            {
                name: '医药卫生大类',
                value: 4.93,
                itemStyle: {
                    color: '#FFFF00'
                },
                nick:"专科（高职）",
                children: [
                    {
                        name: "临床医学类",
                        value: 0.493,
                        itemStyle: {
                            color: '#FFFFE0'
                        }
                    },
                    {
                        name: "护理类",
                        value: 0.493,
                        itemStyle: {
                            color: '#FFFACD'
                        }
                    },
                    {
                        name: "医药类",
                        value: 0.493,
                        itemStyle: {
                            color: '#FAFAD2'
                        }
                    },
                    {
                        name: "医学技术类",
                        value: 0.493,
                        itemStyle: {
                            color: '#FFEFD5'
                        }
                    },
                    {
                        name: "康复治疗类",
                        value: 0.493,
                        itemStyle: {
                            color: '#FFE4B5'
                        }
                    },
                    {
                        name: "公共卫生与卫生管理类",
                        value: 0.493,
                        itemStyle: {
                            color: '#FFDAB9'
                        }
                    },
                    {
                        name: "人口与计划生育类",
                        value: 0.493,
                        itemStyle: {
                            color: '#EEE8AA'
                        }
                    },
                    {
                        name: "健康管理与促进类",
                        value: 0.493,
                        itemStyle: {
                            color: '#F0E68C'
                        }
                    },
                    {
                        name: "中医药类",
                        value: 0.493,
                        itemStyle: {
                            color: '#BDB76B'
                        }
                    },
                    {
                        name: "眼视光类",
                        value: 0.493,
                        itemStyle: {
                            color: '#FFD700'
                        }
                    },
                ]

            },
            {
                name: '财经商贸大类',
                value: 4.38,
                itemStyle: {
                    color: '#FFE4E1'
                },
                nick:"专科（高职）",
                children: [
                    {
                        name: "财政税务类",
                        value: 0.48,
                        itemStyle: {
                            color: '#FFF0F5'
                        }
                    },
                    {
                        name: "金融类",
                        value: 0.48,
                        itemStyle: {
                            color: '#FAF0E6'
                        }
                    },
                    {
                        name: "财务会计类",
                        value: 0.48,
                        itemStyle: {
                            color: '#FAEBD7'
                        }
                    },
                    {
                        name: "统计学类",
                        value: 0.48,
                        itemStyle: {
                            color: '#FFFFF0'
                        }
                    },
                    {
                        name: "经济贸易类",
                        value: 0.48,
                        itemStyle: {
                            color: '#FFFAF0'
                        }
                    },
                    {
                        name: "工商管理类",
                        value: 0.48,
                        itemStyle: {
                            color: '#FDF5E6'
                        }
                    },
                    {
                        name: "市场营销类",
                        value: 0.48,
                        itemStyle: {
                            color: '#F5F5DC'
                        }
                    },
                    {
                        name: "电子商务类",
                        value: 0.48,
                        itemStyle: {
                            color: '#FFF5EE'
                        }
                    },
                    {
                        name: "物流类",
                        value: 0.48,
                        itemStyle: {
                            color: '#F5F5F5'
                        }
                    },
                ]
            },
            {
                name: '旅游大类',
                value: 1.65,
                itemStyle: {
                    color: '#FF4500'
                },
                nick:"专科（高职）",
                children: [
                    {
                        name: "旅游类",
                        value: 0.55,
                        itemStyle: {
                            color: '#FF6347'
                        }
                    },
                    {
                        name: "餐饮类",
                        value: 0.55,
                        itemStyle: {
                            color: '#FF7F50'
                        }
                    },
                    {
                        name: "会展类",
                        value: 0.55,
                        itemStyle: {
                            color: '#FF8C00'
                        }
                    }
                ]
            },
            {
                name: '文化艺术大类',
                value: 2.2,
                itemStyle: {
                    color: '#FF69B4'
                },
                nick:"专科（高职）",
                children: [
                    {
                        name: "艺术设计类",
                        value: 0.55,
                        itemStyle: {
                            color: '#FF1493'
                        }
                    },
                    {
                        name: "表演艺术类",
                        value: 0.55,
                        itemStyle: {
                            color: '#FFC0CB'
                        }
                    },
                    {
                        name: "民族文化艺术类",
                        value: 0.55,
                        itemStyle: {
                            color: '#DB7093'
                        }
                    },
                    {
                        name: "文化服务类",
                        value: 0.55,
                        itemStyle: {
                            color: '#C71585'
                        }
                    },
                ]
            },
            {
                name: '新闻传播大类',
                value: 1.1,
                itemStyle: {
                    color: '#00CED1'
                },
                nick:"专科（高职）",
                children: [
                    {
                        name: "新闻出版类",
                        value: 0.55,
                        itemStyle: {
                            color: '#E0FFFF'
                        }
                    },
                    {
                        name: "广播影视类",
                        value: 0.55,
                        itemStyle: {
                            color: '#008B8B'
                        }
                    },
                ]
            },
            {
                name: '教育与体育大类',
                value: 2.2,
                itemStyle: {
                    color: '#9370DB'
                },
                nick:"专科（高职）",
                children: [
                    {
                        name: "教育类",
                        value: 0.55,
                        itemStyle: {
                            color: '#9966CC'
                        }
                    },
                    {
                        name: "语言类",
                        value: 0.55,
                        itemStyle: {
                            color: '#9400D3'
                        }
                    },
                    {
                        name: "文秘类",
                        value: 0.55,
                        itemStyle: {
                            color: '#9932CC'
                        }
                    },
                    {
                        name: "体育类",
                        value: 0.55,
                        itemStyle: {
                            color: '#EE82EE'
                        }
                    }
                ]
            },
            {
                name: '公安与司法大类',
                value: 3.83,
                itemStyle: {
                    color: '#00008B'
                },
                nick:"专科（高职）",
                children: [
                    {
                        name: "公安管理类",
                        value:0.47,
                        itemStyle: {
                            color: '#6495ED'
                        }
                    },
                    {
                        name: "公安指挥类",
                        value: 0.47,
                        itemStyle: {
                            color: '#1E90FF'
                        }
                    },
                    {
                        name: "公安技术类",
                        value: 0.47,
                        itemStyle: {
                            color: '#4682B4'
                        }
                    },
                    {
                        name: "侦查类",
                        value: 0.47,
                        itemStyle: {
                            color: '#00FFFF'
                        }
                    },
                    {
                        name: "法律实务类",
                        value: 0.47,
                        itemStyle: {
                            color: '#000080'
                        }
                    },
                    {
                        name: "法律执行类",
                        value: 0.47,
                        itemStyle: {
                            color: '#ADD8E6'
                        }
                    },
                    {
                        name: "司法技术类",
                        value: 0.47,
                        itemStyle: {
                            color: '#48D1CC'
                        }
                    },
                    {
                        name: "安全防范类",
                        value: 0.47,
                        itemStyle: {
                            color: '#B0E0E6'
                        }
                    },
                ]
            },
            {
                name: '公共管理与服务大类',
                value: 2.2,
                itemStyle: {
                    color: '#20B2AA'
                },
                nick:"专科（高职）",
                children: [
                    {
                        name: "公共事务类",
                        value: 0.55,
                        itemStyle: {
                            color: '#8FBC8F'
                        }
                    },
                    {
                        name: "公共管理类",
                        value: 0.55,
                        itemStyle: {
                            color: '#808000'
                        }
                    },
                    {
                        name: "公共服务类",
                        value: 0.55,
                        itemStyle: {
                            color: '#00FF7F'
                        }
                    },
                    {
                        name: "文秘类",
                        value: 0.55,
                        itemStyle: {
                            color: '#ADFF2F'
                        }
                    },
                ]
            }
        ]
    },
]
