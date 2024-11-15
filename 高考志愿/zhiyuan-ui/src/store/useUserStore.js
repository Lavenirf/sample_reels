import {defineStore} from 'pinia';

export const useUserStore = defineStore("userData", {
    state: () => ({
            data: {},
            //登录判断
            showLogin: false
    })
    ,
    actions: {
        setHollandResultId(id){
            this.data.hollandResultId=id
        },
        setData(userData) {
            this.data=userData
        },
        toggleShowLogin(data) {
            this.showLogin = data;
        },
    },
    getters: {
        getData(state) {
            if (state.data){
                return state.data
            }else{
                return localStorage.getItem("userData")?localStorage.getItem("userData"):{}
            }
        }
    },
    persist: true
});
