// 添加请求拦截器
import axios from "axios";
import {useUserStore} from "../../store/useUserStore.js";
import { ElLoading } from 'element-plus'
import {ElMessage} from "element-plus";
let loadingInstance1 = null
axios.interceptors.request.use(function (config) {
    loadingInstance1 =ElLoading.service({fullscreen:true})
    config.headers.Authorization=localStorage.getItem("Authorization")
    return config;
}, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
});

// 添加响应拦截器
axios.interceptors.response.use(function (response) {
    const {code,msg} = response.data
    if (code===5000||code===-1) {
        setTimeout(()=>{
            window.location.href="/login"
        },2000)
        ElMessage.error(msg)
    }
   loadingInstance1 && loadingInstance1.close()
    return response;
}, function (error) {
    loadingInstance1 && loadingInstance1.close()
    return Promise.reject(error);
});

