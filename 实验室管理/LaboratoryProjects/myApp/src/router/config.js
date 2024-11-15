import Home from "../views/home/Home.vue";
import UserList from "../views/user-mange/UserList.vue";
import RoleList from "../views/right-manage/RoleList.vue";
import RightList from "../views/right-manage/RightList.vue";
import LabList from "../views/lab-manage/LabList.vue";
import AddLab from "../views/lab-manage/AddLab.vue";
import AuditList from "../views/book-manage/AuditList.vue";
import BookList from "../views/book-manage/BookList.vue";
import AddBook from "../views/book-manage/AddBook.vue";
import UserMange from "../views/user-mange/UserMange.vue";

const routes =[
    {
        path:"/index",
        component:Home
    },
    {
        path:"/user-manage/list",
        component: UserList
    },
    {
        path:"/user-manage/userMange",
        component: UserMange
    },
    {
        path:'/right-manage/roleList',
        component: RoleList
    },
    {
        path:'/right-manage/rightList',
        component: RightList
    },
    {
        path:'/lab-manage/labList',
        component: LabList
    },
    {
        path:'/lab-manage/addLab',
        component: AddLab
    },
    {
        path:'/book-manage/auditList',
        component: AuditList
    },
    {
        path:'/book-manage/bookList',
        component: BookList
    },
    {
        path:'/book-manage/addBook',
        component: AddBook
    }
]
export default routes