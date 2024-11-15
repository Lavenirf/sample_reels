import request from '../utils/request.js'

// 查询资讯列表
export function selectSchoolNews(query) {
    return request({
        url: '/gk/news/list',
        method: 'get',
        params:query
    })
}
