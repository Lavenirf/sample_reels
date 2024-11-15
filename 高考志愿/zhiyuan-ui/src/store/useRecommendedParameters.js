import {defineStore} from 'pinia';

export const useRecommendedParameters = defineStore("parameters", {
    state: () => ({
        region: '',
        institutionType: '',
        institutionLevel: '',
        level2name:''
    }),
    actions: {
        setRegion(data) {
            this.region = data
        },
        setlevel2name(data) {
            this.level2name = data
        },
        setInstitutionType(data) {
            this.institutionType = data
        },
        setInstitutionLevel(data) {
            this.institutionLevel = data
        },
    },
    getters: {
        getRegion(state) {
            return state.region ? state.region : ''
        },
        getlevel2name(state) {
            return state.level2name ? state.level2name : ''
        },
        getInstitutionType(state) {
            return state.institutionType ? state.institutionType : ''
        },
        getInstitutionLevel(state) {
            return state.institutionLevel ? state.institutionLevel : ''
        },
    }
})
