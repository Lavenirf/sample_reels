import {defineStore} from 'pinia';

export const useCollectStore = defineStore("collectData", {
    state: () => ({
        schoolsCollectList: [],
        specialCollectList:[]
    }),
    actions: {
        setSchoolsCollectList(userData) {
            this.schoolsCollectList=userData
        },
        setSpecialCollectList(userData) {
            this.specialCollectList=userData
        },
    },
    getters: {
        getSchoolsCollectList(state) {
            return state.schoolsCollectList ? state.schoolsCollectList : []
        },
        getSpecialCollectList(state) {
            return state.specialCollectList ? state.specialCollectList : []
        }
    }
});
