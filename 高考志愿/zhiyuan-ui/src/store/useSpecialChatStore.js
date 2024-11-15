import {defineStore} from 'pinia';

export const useSpecialStore = defineStore("specialData", {
    state: () => ({
        areas: [],
        positions: [],
        distribution: [],
        outFreshGraduates:[],
        thisFreshGraduates:[],
        specialDetail:[]

    })
    ,
    actions: {
        setAreas(data) {
            this.areas=data
        },
        setPositions(data) {
            this.positions = data
        },
        setDistribution(data) {
            this.distribution = data
        },
        setOutFreshGraduates(data) {
            this.outFreshGraduates = data
        },
        setThisFreshGraduates(data) {
            this.thisFreshGraduates = data
        },
        setSpecialDetail(data) {
            this.specialDetail = data
        }
    },
    getters: {
        getAreas(state) {
            return state.areas ? state.areas : []
        },
        getPositions(state) {
            return state.positions ? state.positions : []
        },
        getDistribution(state) {
            return state.distribution ? state.distribution : []
        },
        getOutFreshGraduates(state) {
            return state.outFreshGraduates ? state.outFreshGraduates : []
        },
        getThisFreshGraduates(state) {
            return state.thisFreshGraduates ? state.thisFreshGraduates : []
        },
        getSpecialDetail(state) {
            return state.specialDetail ? state.specialDetail : []
        }
    },
});
