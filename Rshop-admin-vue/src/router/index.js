import { createRouter, createWebHistory } from 'vue-router'
import AHome from "@/view/Home-admin.vue";
import BHome from "@/view/Home-buyer.vue";
import Login from "@/view/login-home.vue";
import Ishell from "@/components/block-admin/shell/shell-ing.vue";
const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/aHome',
    name: 'AHome',
    component: AHome
  },
  {
    path: '/bHome',
    name: 'BHome',
    component: BHome
  },
  {
    path: '/ingshell',
    name: 'Ishell',
    component: Ishell
  },

];
const router = createRouter({
  history: createWebHistory(),
  routes
})
export default router;
