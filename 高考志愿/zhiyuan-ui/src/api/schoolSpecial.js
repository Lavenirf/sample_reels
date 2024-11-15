import request from '../utils/request.js'

// 查询专业列表
export function selectSchoolSpecial(query) {
    return request({
        url: '/gk/special/list',
        method: 'get',
        params:query
    })
}
// 搜索专业
export function searchSpecial(query) {
    return request({
        url: '/gk/special/search',
        method: 'get',
        params:query
    })
}
//专业详情
export function selectSpecialDetails(query){
    return request({
        url:'/gk/specialDetails/'+query,
        method:"get",
    })
}
