import {createRouter, createWebHistory} from 'vue-router'
import {useDialogVisibleStore} from "@/store/useDialogVisibleStore.js";
import Cookies from 'js-cookie'

const getToken=()=> {
    return Cookies.get("Admin-Token")
}
const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: "/",
            name: "mainBox",
            component: () => import("../views/MainBox/MainBox.vue"),
            redirect: '/home',
            children: [
                {
                    path: '/home',
                    name: '首页',
                    component: () => import('@/views/MainBox/Home/Home.vue')
                },
                {
                    path: "/searchUniversity",
                    name: "查询大学",
                    component: () => import("@/views/MainBox/SearchUniversity/SearchUniversity/SearchUniversity.vue")
                },
                {
                    path: "/universityDetails",
                    name: "大学详情",
                    component: () => import("@/views/MainBox/SearchUniversity/UniversityDetails/UniversityDetails.vue")
                },
                {
                    path: "/inquireMajor",
                    name: "查询专业",
                    component: () => import("@/views/MainBox/InquireMajor/InquireMajor/InquireMajor.vue"),
                },
                {
                    path: '/professionalDetails',
                    name: '专业详情',
                    component: () => import('@/views/MainBox/InquireMajor/ProfessionalDetails/ProfessionalDetails.vue')
                },
                {
                    path: '/ai',
                    name: 'ai聊天',
                    component: () => import('@/views/MainBox/ai/Ai.vue')
                },
                {
                    path: "/simulatedVolunteering",
                    name: "模拟志愿填报",
                    component: () => import("@/views/MainBox/SimulatedVolunteering/SimulatedVolunteering.vue"),
                    redirect: '/intelligentRecommendation',
                    children: [
                        {
                            path: "/intelligentRecommendation",
                            name: "智能推荐",
                            component: () => import("@/views/MainBox/SimulatedVolunteering/IntelligentRecommendation/IntelligentRecommendation.vue"),
                            redirect: '/chooseInstitution',
                            children: [
                                {
                                    path: "/chooseInstitution",
                                    name: "选院校",
                                    component: () => import("@/Components/SimulatedVolunteering/SearchBar/ChooseInstitution/ChooseInstitution.vue")
                                },
                                {
                                    path: "/chooseMajor",
                                    name: "选专业",
                                    component: () => import("@/Components/SimulatedVolunteering/SearchBar/ChooseMajor/ChooseMajor.vue")
                                },
                            ]
                        },
                        {
                            path: "/volunteerList",
                            name: "志愿列表",
                            component: () => import("@/views/MainBox/SimulatedVolunteering/VolunteerList/VolunteerList.vue")
                        },
                        {
                            path: "/tablePreferences",
                            name: "志愿表",
                            component: () => import("@/views/MainBox/SimulatedVolunteering/TablePreferences/TablePreferences.vue")
                        },
                    ]
                },
                {
                    path: "/personalInformation",
                    name: "个人信息",
                    component: () => import("@/views/MainBox/PersonalInformation/PersonalInformation.vue"),
                    redirect: "/personalData",
                    children: [
                        {
                            path: "/personalData",
                            name: "个人资料",
                            component: () => import("@/Components/personalInformation/PersonalData/PersonalData.vue"),

                        },
                        {
                            path: "/mersonalData",
                            name: "会员中心",
                            component: () => import("@/Components/personalInformation/Membership/Membership.vue")
                        },
                        {
                            path: "/MyFavorites",
                            name: "我的收藏",
                            component: () => import("@/Components/personalInformation/MyFavorites/MyFavorites.vue")
                        }

                    ]
                },
                {
                    path: "/holland",
                    name: "职业测试",
                    component: () => import("@/views/MainBox/Holland/Holland.vue")
                },
                {
                    path: "/information",
                    name: "资讯",
                    component: () => import("@/views/MainBox/Information/Information.vue")
                },

            ]
        },
    ]
})
router.beforeEach((to, from, next) => {
    const dialogVisibleStore = useDialogVisibleStore()
    const protectedRoutes = ["/mersonalData","/personalData","/MyFavorites","/chooseMajor","/chooseCareer","/simulatedVolunteering", "/personalInformation",
        "/holland","/intelligentRecommendation","/volunteerList","/tablePreferences","/chooseInstitution"];
    if (getToken()) {
        next();
    }else {
        if (protectedRoutes.includes(to.path) || protectedRoutes.some(route => to.path.startsWith(route))) {
            dialogVisibleStore.setDialogVisibleLogin(true)
            next();
        }else {
            next();
        }
    }
})
export default router



