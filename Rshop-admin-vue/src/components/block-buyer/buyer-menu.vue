<template>
  <el-menu :default-openeds="['1', '5']" style="background-color: var(--el-color-primary-light-9)" :collapse="isCollapse">
    <!-- 子菜单1，索引为1 -->
    <el-menu-item index="1" @click="navigateTo('/userinfo')">
      <el-icon><User /></el-icon>个人信息
    </el-menu-item>

    <!-- 子菜单2，索引为2 -->
    <el-menu-item index="2" @click="navigateTo('/order-management')">
      <el-icon><DocumentCopy /></el-icon>订单管理
    </el-menu-item>

    <!-- 子菜单3，索引为3 -->
    <el-menu-item index="3" @click="navigateTo('/shipping-management')">
      <el-icon><Setting /></el-icon>收货管理
    </el-menu-item>

    <!-- 子菜单4，索引为5 -->
    <el-menu-item index="5" @click="navigateTo('/account-management')">
      <el-icon><Edit /></el-icon>账号管理
    </el-menu-item>
  </el-menu>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { ref } from 'vue';
import {DocumentCopy, Edit, Setting, User} from "@element-plus/icons-vue";


const router = useRouter();
const isCollapse = ref(false);

// 跳转到指定路由
const navigateTo = (route) => {
  router.push(route);
};
</script>

<style scoped>
/* Menu 样式 */
</style>
