import request from '../utils/request.js'

// 检索学校
export function getReCommend(query) {
    return request({
        url: `/gk/schools/recommend`,
        method: 'post',
        data:query
    })
}
// 推荐专业
export function getSpecialized(query) {
    return request({
        url: `/gk/schools/specialized`,
        method: 'post',
        data:query
    })
}
