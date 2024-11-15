import {createRouter, createWebHistory} from "vue-router";
import Login from "../views/Login.vue";
import MainBox from "../views/MainBox.vue";
import RouteConfig from './config.js'
import {useRouterStore} from "../store/useRouterStore.js";
import NotFound from "../views/NotFound.vue";
import {useUserStore} from "../store/useUserStore.js";
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
const routes = [
    {
        path: "/login",
        name: "login",
        component: Login
    },
    {
        path: "/mainBox",
        name: "mainBox",
        component: MainBox
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes: routes
})
//路由拦截
router.beforeEach((to, from, next)=>{
    NProgress.start()
    const {isGetterRouter} =useRouterStore()
    const {user} =useUserStore()
    if (to.name==='login'){
        next()
    }else {
        //未登录
        if(!user.id){
            next({
                path:"/login"
            })
        }else {
            if (!isGetterRouter){
                //删除mainBox
                router.removeRoute("mainBox")
                ConfigRouter()
                next({
                    path:to.fullPath
                })
            }else {
                next()
            }
        }
    }

})
router.afterEach((to,from,next)=>{
    NProgress.done()
})
const ConfigRouter =()=>{
    //创建mainBox
    router.addRoute({
        path:"/mainBox",
        name:"mainBox",
        component:MainBox
    })
    let {changeRouter} =useRouterStore()
    RouteConfig.forEach(item=>{
       checkPermission(item.path) && router.addRoute("mainBox",item)
    })
    //重定向
    router.addRoute("mainBox",{
        path: '/',
        redirect: "/index"
    })
    router.addRoute("mainBox",{
        path:"/:pathMatch(.*)*",
        component:NotFound
    })
    // console.log(router.getRoutes())
    changeRouter(true)
}
const checkPermission =(path)=>{
    const {user} =useUserStore()
    return user.role.rights.includes(path)
}
export default router