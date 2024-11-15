import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import {LayuiVueResolver} from 'unplugin-vue-components/resolvers'

export default defineConfig({
    plugins: [
        vue(),
        AutoImport({
            resolvers: [LayuiVueResolver()],
        }),
        Components({
            resolvers: [LayuiVueResolver()],
        }),
    ],
    resolve: {
        alias: {
            '@': '/src',
        },
    },
    server: {
        port:7000,
    },
});

export interface LayuiVueResolverOptions {
    /**
     * 将样式与组件一起导入
     *
     * @default 'css'
     */
    importStyle?: boolean | 'css'

    /**
     * 是否解析图标
     *
     * @default false
     */
    resolveIcons?: boolean

    /**
     * 排除不需要自动导入的组件
     *
     * eg: exclude: ['LayDocTable', /^LayDoc[A-Z]/,]
     */
    exclude?: Array<string | RegExp>;
}
