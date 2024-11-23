import { createRouter, createWebHistory } from 'vue-router'
import AHome from "@/view/Home-admin.vue";
import BHome from "@/view/Home-buyer.vue";
import Login from "@/view/login-home.vue";
import Ishell from "@/components/block-admin/shelf-ing.vue";
import UserIn from "@/view/Home-buyerInfor.vue";
import UpdatePwd from "@/components/block-admin/admin-pwd.vue";
import AGoodData from "@/components/block-admin/admin-detail.vue";
import NewProduct from "@/components/block-admin/admin-NewPro.vue";

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/aHome',
    name: 'AHome',
    component: AHome,
    props: true // 启用 props
  },
  {
    path: '/aHome/updatePwd',
    name: 'UpdatePwd',
    component: UpdatePwd,
    props: true // 启用 props
  },
  {
    path: '/aHome/aGoodData',
    name: 'AGoodData',
    component: AGoodData,
    props: true // 启用 props
  },
  {
    path: '/aHome/newProduct',
    name: 'NewProduct',
    component: NewProduct,
    props: true // 启用 props
  },
  {
    path: '/bHome',
    name: 'BHome',
    component: BHome,
    props: true // 启用 props
  },
  {
    path: '/ingshell',
    name: 'Ishell',
    component: Ishell
  },
  {
    path: '/user-info',
    name: 'UserIn',
    component: UserIn
  },
];
const router = createRouter({
  history: createWebHistory(),
  routes
})
export default router;
