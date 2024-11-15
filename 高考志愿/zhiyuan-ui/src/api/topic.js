import request from '../utils/request.js'

// 查询霍兰德职业兴趣测试题目列表
export function listTopic(query) {
    return request({
        url: '/gk/topic/list',
        method: 'get',
        params: query
    })
}
// 提交结果
export function judgment(data) {
    return request({
        url: '/gk/topic/judgment',
        method: 'post',
        data: data
    })
}
//查询个人结果
export function getResult(query) {
    return request({
        url: '/gk/usercomparisontable/'+query,
        method: 'get',
    })
}