import {defineStore} from 'pinia'


export const useDialogVisibleStore = defineStore("showDialogStore", {
        state: () => ({
            dialogVisibleLogin: false,
        }),
        actions: {
            setDialogVisibleLogin(data) {
                this.dialogVisibleLogin = data
            },
        },
        getters: {
            getDialogVisibleLogin(state) {
                return state.dialogVisibleLogin
            }
        }
    }
)

