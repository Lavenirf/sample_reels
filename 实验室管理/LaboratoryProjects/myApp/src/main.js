import { createApp } from 'vue'
import App from './App.vue'
import router from "./router/index";
import {createPinia} from "pinia";
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
import ElementPlus from 'element-plus'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
//引入粒子库
import 'element-plus/dist/index.css'
import Particles from "particles.vue3";
import './http/config/config.js'
const pinia =createPinia()
const app=createApp(App)
pinia.use(piniaPluginPersistedstate)
app.use(router)
app.use(pinia)
app.use(ElementPlus,{
    locale:zhCn
})
app.use(Particles,)
app.mount('#app')