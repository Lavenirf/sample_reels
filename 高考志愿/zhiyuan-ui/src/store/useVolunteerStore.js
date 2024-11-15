import {defineStore} from 'pinia';

export const useVolunteerStore = defineStore("volunteerData", {
    state: () => ({
        volunteerForm: [],
        useTemp:1,
        volunteerDetails1: [],
        volunteerDetails2: [],
        paragraph:1
    }),
    actions: {
        setVolunteerForm(data) {
            this.volunteerForm=data
        },
        setVolunteerDetails1(data) {
            this.volunteerDetails1=data
        },
        setVolunteerDetails2(data) {
            this.volunteerDetails2=data
        },
        setParagraph(data) {
            this.paragraph=data
        },
    },
    getters: {
        getVolunteerForm(state) {
            return state.volunteerForm ? state.volunteerForm : []
        },
        getVolunteerDetails1(state) {
            return state.volunteerDetails1 ? state.volunteerDetails1 : []
        },
        getVolunteerDetails2(state) {
            return state.volunteerDetails2 ? state.volunteerDetails2 : []
        },
        getParagraph(state) {
            return state.paragraph ? state.paragraph : 1
        },
    },
});
