import { createRouter, createWebHistory } from 'vue-router'
import AHome from "@/view/Home-admin.vue";
import BHome from "@/view/Home-buyer.vue";
import Login from "@/view/login-home.vue";
import Ishell from "@/components/block-admin/shell-ing.vue";
import UserIn from "@/view/Home-buyerInfor.vue";
import UpdatePwd from "@/components/block-admin/admin-pwd.vue";
import AGoodData from "@/components/block-admin/admin-detail.vue";
import NewProduct from "@/components/block-admin/admin-NewPro.vue";
import UserInfo from '../components/block-buyer/UserInfo.vue';
import OrderManagement from '../components/block-buyer/OrderManagement.vue';
import ShippingManagement from '../components/block-buyer/ShippingManagement.vue';
import AccountManagement from '../components/block-buyer/AccountManagement.vue';
import WelcomePage from "@/components/block-buyer/welcome.vue";


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
    path: '/user-home',
    component:UserIn,
    children: [
      {
        path: '', // 默认子路由，渲染欢迎界面
        name: 'Welcome',
        component: WelcomePage
      },
      {
        path: '/userinfo', // 嵌套路由
        name: 'UserInfo',
        component: UserInfo
      },
      {
        path: '/order-management',
        name: 'OrderManagement',
        component: OrderManagement,
      },
      {
        path: '/shipping-management',
        name: 'ShippingManagement',
        component: ShippingManagement,
      },
      {
        path: '/account-management',
        name: 'AccountManagement',
        component: AccountManagement,
      },
    ]
  },

];
const router = createRouter({
  history: createWebHistory(),
  routes
})
export default router;
