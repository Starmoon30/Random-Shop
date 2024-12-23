import { createRouter, createWebHistory } from 'vue-router'
import AHome from "@/view/Home-admin.vue";
import BHome from "@/view/Home-buyer.vue";
import Login from "@/view/login-home.vue";
import Ishell from "@/components/block-admin/shelf-ing.vue";
import UserIn from "@/view/Home-buyerInfor.vue";
import UpdatePwd from "@/components/block-admin/admin-pwd.vue";
import UpdateGood from "@/components/block-admin/admin-update.vue";
import AProductDet from "@/components/block-admin/admin-detail.vue";
import BProductDet from "@/components/block-buyer/buyer-detail.vue";
import NewProduct from "@/components/block-admin/admin-NewPro.vue";
import NewOrder from "@/components/block-buyer/buyer-Neworder.vue";
import SearchRes from "@/components/block-buyer/buyer-searchGoods.vue";
import NewLabel from "@/components/block-admin/admin-newCat.vue";
import OrderDet from "@/components/block-admin/admin-order.vue";
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
    path: '/aHome/updateGood/:pid',
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
    path: '/aHome/newLabel',
    name: 'NewLabel',
    component: NewLabel,
    props: true // 启用 props
  },
  {
    path: '/aHome/productDet/:pid',
    name: 'AProductDet',
    component: AProductDet
  },
  {
    path: '/aHome/orderDet/:uid',
    name: 'OrderDet',
    component: OrderDet
  },
  {
    path: '/bHome',
    name: 'BHome',
    component: BHome,
    props: true // 启用 props
  },
  {
    path: '/bHome/productDet/:pid',
    name: 'BProductDet',
    component: BProductDet,
    props: true // 启用 props
  },
  {
    path: '/bHome/newOrder/:id',
    name: 'NewOrder',
    component: NewOrder,
    props: true // 启用 props
  },
  {
    path: '/bHome/searchRes/:query',
    name: 'SearchRes',
    component: SearchRes,
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
