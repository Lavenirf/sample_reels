import request from '../utils/request.js'
// 查询省份地级市
export function selectAddress(query) {
    return request({
        url: '/static/json/map/'+query+".json",
        method: 'get',
    })
}
//查询高校 高德
export function selectSchools(address){
    return request({
        url:'/baidu/selectSchools',
        method:'get',
        params:{
            address:address,
            key:'322236613599603a077077843cfd695b'
        }
    })
}
//查询县区
export function selectCounty(address){
    return request({
        url:'/baidu/selectCounty',
        method:'get',
        params:{
            address:address,
            ak:'LZF4UXqpH2r7bE96Fb97QTaYlBL4GQeY'
        }
    })
}
//查询省以下的名称
export function selectDistrictName(address){
    return request({
        url:'/baidu/selectDistrictName',
        method:'get',
        params:{
            address:address,
            ak:'LZF4UXqpH2r7bE96Fb97QTaYlBL4GQeY'
        }
    })
}

//区代码查询
export function selectAdCode(address){
    return request({
        url:'/baidu/selectAdCode',
        method:'get',
        params:{
            address:address,
            key:'322236613599603a077077843cfd695b'
        }
    })
}
