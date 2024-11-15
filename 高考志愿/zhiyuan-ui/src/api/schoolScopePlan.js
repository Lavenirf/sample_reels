import request from '../utils/request.js'

// 查询学校分数
export function selectSchoolScope(query) {
    return request({
        url: '/gk/scores/list',
        method: 'get',
        params:query
    })
}
// 查询学校招生计划
export function selectEnrollmentPlan(query) {
    return request({
        url: '/gk/enrollmentPlan/list',
        method: 'get',
        params:query
    })
}
// 查询专业分数线
export function selectMscores(query) {
    return request({
        url: '/gk/mscores/list',
        method: 'get',
        params:query
    })
}
// 查询位次差异
export function selectPlanGap(data) {
    return request({
        url: '/gk/enrollmentPlan/gapList',
        method: 'post',
        data:data
    })
}
