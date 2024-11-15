import {defineStore} from 'pinia';

export const useSchoolStore = defineStore("schoolData", {
    state:()=>({
        popularSchoolData:[],
        badgeList:[]
    }),
    actions:{
        setPopularSchoolData(data){
            this.popularSchoolData=data
        },
        setBadgeList(data){
            this.popularSchoolData=data
        },
    },
    getters:{
        getPopularSchoolData(state){
            return state.popularSchoolData?state.popularSchoolData:[]
        },
        getBadgeList(state){
            return state.badgeList?state.badgeList:[]
        }
    }
})