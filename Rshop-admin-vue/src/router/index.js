import { createRouter, createWebHistory } from 'vue-router'
import AHome from "@/view/Home-admin.vue";
import BHome from "@/view/Home-buyer.vue";
import Login from "@/view/login-home.vue";
import Ishell from "@/components/block-admin/shelf-ing.vue";
import UserIn from "@/view/Home-buyerInfor.vue";
import UpdatePwd from "@/components/block-admin/admin-pwd.vue";
import UpdateGood from "@/components/block-admin/admin-update.vue";
import ProductDet from "@/components/block-admin/admin-detail.vue";
import NewProduct from "@/components/block-admin/admin-NewPro.vue";
import UserInfo from '../components/block-buyer/buyer-imformation/UserInfo.vue';
import OrderManagement from '../components/block-buyer/buyer-imformation/OrderManagement.vue';
import ShippingManagement from '../components/block-buyer/buyer-imformation/ShippingManagement.vue';
import AccountManagement from '../components/block-buyer/buyer-imformation/AccountManagement.vue';
import WelcomePage from "@/components/block-buyer/buyer-imformation/welcome.vue";


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
    path: '/aHome/updateGood',
    name: 'UpdateGood',
    component: UpdateGood,
    props: true // 启用 props
  },
  {
    path: '/aHome/newProduct',
    name: 'NewProduct',
    component: NewProduct,
    props: true // 启用 props
  },
  {
    path: '/aHome/productDet/:pid',
    name: 'ProductDet',
    component: ProductDet
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
  {
    path: '/updatePwd',
    name: 'UpdatePwd',
    component: UpdatePwd
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
router.beforeEach((to, from, next) => {
//to到哪儿  from从哪儿离开  next跳转 为空就是放行
  if (to.path === '/') {
    //如果跳转为登录，就放行
    next();
  } else {
    //取出localStorage判断
    const token = localStorage.getItem('token');
    if (token === null || token === '') {
      alert('请先登录')
      next({path: '/'});
    } else {
      next();
    }
  }});
