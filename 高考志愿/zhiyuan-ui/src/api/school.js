import request from '../utils/request.js'

// 查询学校列表
export function getSchoolList(query) {
    return request({
        url: `/gk/schools/list`,
        method: 'get',
        params:query
    })
}
// 查询学校详情
export function getSchoolById(query) {
    return request({
        url: `/gk/schoolDetails/`+query,
        method: 'get',
    })
}
// 查询学校简介
export function getSchoolProfileById(query) {
    return request({
        url: `/gk/profile/`+query,
        method: 'get',
    })
}
// 查询学校列表
export function searchList(query) {
    return request({
        url: `/gk/schools/search`,
        method: 'get',
        params:query
    })
}
// 查询学校logo
export function getSchoolLogo(query) {
    return request({
        url: `/gk/schools/batch`,
        method: 'post',
        data:query
    })
}
// 查询热门学校
export function getPopularSchool(query) {
    return request({
        url: `/gk/schools/popular?local=`+query,
        method: 'get',
    })
}
// 批量查询学校
export function getSchoolByIds(query) {
    return request({
        url: `/gk/schools/ids?ids=`+query,
        method: 'get',
    })
}
