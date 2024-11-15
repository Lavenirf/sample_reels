import request from '../utils/request.js'

// 查询个人收藏列表
export function getCollectList(query) {
    return request({
        url: '/gk/favorites/'+query,
        method: 'get',
    })
}
// 添加个人收藏
export function addCollect(data) {
    return request({
        url: '/gk/favorites',
        method: 'post',
        data:data
    })
}
// 删除个人收藏
export function delCollect(query) {
    return request({
        url: '/gk/favorites/'+query,
        method: 'delete',
    })
}