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
    console.log("lingpai:", token);
    if (token == null || token === '') {
      alert('请先登录')
      next({name: 'Login'});
    } else {
      next();
    }
  }});
