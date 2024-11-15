import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
    plugins: [vue()],
    //反向代理
    server: {
        port: 8080,
        proxy: {
            "/api": {
                target: "http://lab-system:5000",
                changeOrigin: true,
            }
        }
    }
})
