import request from '../utils/request.js'

// 导出志愿
export function exportVolunteerForm(voId,paragraph) {
    return request({
        url: `/gk/volunteerFormDetails/export?voId=${voId}&paragraphId=${paragraph}`,
        method: 'post',
        responseType:'blob'
    })
}
// 查询用户志愿表
export function getUserVolunteerForm(query) {
    return request({
        url: '/gk/volunteerForm/'+query,
        method: 'get',
    })
}
// 查询用户志愿表详情
export function getUserVolunteerFormDetails(voId,paragraphId) {
    return request({
        url:`/gk/volunteerFormDetails/getDetails?voId=${voId}&paragraph=${paragraphId}`,
        method: 'get',
    })
}
// 查询使用中的志愿表
export function getUseVolunteerForm(query) {
    return request({
        url: '/gk/volunteerForm?id='+query,
        method: 'get',
    })
}
// 删除志愿
export function deleteVolunteer(query) {
    return request({
        url: '/gk/volunteerFormDetails/'+query,
        method: 'delete',
    })
}
// 调整志愿顺序
export function updateVolunteer(data,query) {
    return request({
        url: '/gk/volunteerFormDetails?oldOrder='+ query,
        method: 'put',
        data:data
    })
}
// 更新志愿名称
export function updateVolunteerForm(data) {
    return request({
        url: '/gk/volunteerForm/',
        method: 'put',
        data:data
    })
}
// 新增志愿表
export function insertVolunteerForm(data) {
    return request({
        url: '/gk/volunteerForm/',
        method: 'post',
        data:data
    })
}
// 新增志愿项
export function insertVolunteerDetails(data) {
    return request({
        url: '/gk/volunteerFormDetails/',
        method: 'post',
        data:data
    })
}
