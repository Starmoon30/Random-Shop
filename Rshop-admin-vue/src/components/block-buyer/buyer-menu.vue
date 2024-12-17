<template>
  <el-menu :default-openeds="['1', '2', '5']" style="background-color: var(--el-color-primary-light-9)" :collapse="isCollapse">
    <!-- 子菜单项 -->
    <el-menu-item index="1" @click="navigateTo('/userinfo')">
      <el-icon><User /></el-icon>个人信息
    </el-menu-item>

    <el-sub-menu index="2">
      <template #title>
        <el-icon><DocumentCopy /></el-icon>订单管理
      </template>
      <el-menu-item index="2-1" @click="navigateTo('/orders/unaccepted')">未接受订单</el-menu-item>
      <el-menu-item index="2-2" @click="navigateTo('/orders/accepted')">已接受订单</el-menu-item>
      <el-menu-item index="2-3" @click="navigateTo('/orders/completed')">已完成订单</el-menu-item>
    </el-sub-menu>

    <el-menu-item index="3" @click="navigateTo('/shipping-management')">
      <el-icon><Setting /></el-icon>收货管理
    </el-menu-item>

    <el-menu-item index="5" @click="navigateTo('/account-management')">
      <el-icon><Edit /></el-icon>账号管理
    </el-menu-item>
  </el-menu>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { ref } from 'vue';
import { DocumentCopy, Edit, Setting, User } from "@element-plus/icons-vue";

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
