import request from '../utils/request.js'

// 查询用户详细信息
export function getUserInfo() {
    return request({
        url: '/gk/user/getInfo',
        method: 'get',
    })
}
//更新用户信息
export function updateUserInfo(data) {
    return request({
        url: '/gk/user',
        method: 'put',
        data:data
    })
}