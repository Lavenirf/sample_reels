import axios from "axios";
import { errorCode } from "./errorCode";
import { getToken } from '../utils/auth';
import { ElNotification, ElMessage } from 'element-plus';
import {useUserStore} from "@/store/useUserStore";
const userStore = useUserStore()
// 创建axios实例
const service = axios.create({
    baseURL:"http://222.206.160.32:88",
    // baseURL:"http://localhost:88",
    timeout: 50000,
    headers: {
        'X-Custom-Header': 'foobar'
    }
});

// 添加请求拦截器
service.interceptors.request.use(config => {
    if (getToken()) {
        config.headers['Authorization'] = 'Bearer ' + getToken(); // 让每个请求携带自定义token 请根据实际情况自行修改
    }
    return config;
}, error => {
    return Promise.reject(error);
});

// 添加响应拦截器
service.interceptors.response.use(function (res) {
    // 未设置状态码则默认成功状态
    const code = res.data.code || 200;
    // 获取错误信息
    const msg = errorCode[code] || res.data.msg || errorCode['default'];
    // 二进制数据则直接返回
    if (res.request.responseType === 'blob' || res.request.responseType === 'arraybuffer') {
        return res;
    }
    if (code === 401) {
            userStore.showLogin = false
            userStore.setData(null)
    } else if (code === 500) {
        ElMessage({ message: msg, type: 'error' });
        return Promise.reject(new Error(msg));
    } else if (code === 601) {
        ElMessage({ message: msg, type: 'warning' });
        return Promise.reject(new Error(msg));
    } else if (code !== 200) {
        ElNotification.error({ title: msg });
        return Promise.reject('error');
    } else {
        return Promise.resolve(res.data);
    }
}, function (error) {
    // 超出 2xx 范围的状态码都会触发该函数。
    // 对响应错误做点什么
    return Promise.reject(error);
});

export default service;
