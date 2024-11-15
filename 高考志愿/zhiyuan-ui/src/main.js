import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import { createPinia } from 'pinia';
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
import router from './router/index.js'
import ElementPlus from 'element-plus'
import Antd from 'ant-design-vue';
import 'element-plus/dist/index.css'
import 'ant-design-vue/dist/reset.css';
import layui from '@layui/layui-vue';
import '@layui/layui-vue/lib/index.css';
const pinia = createPinia();
pinia.use(piniaPluginPersistedstate)
const app =createApp(App)
app .use(pinia)
    .use(router)
    .use(Antd)
    .use(ElementPlus)
    .use(layui)
    .mount('#app')

