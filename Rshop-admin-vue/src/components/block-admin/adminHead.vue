<script setup>
import router from "@/router/index.js";
const goBack = () => {
  localStorage.removeItem('token');
  router.push('/');
};
</script>

<template>
  <!-- 工具栏 -->
  <div class="toolbar" style="flex-grow: 1;display: contents;height: 80px;width: 100%">
    <div style="font-size: 20px;font-family: SimSun, sans-serif;">欢迎来到后台管理!</div>
    <!-- 下拉菜单 -->
    <el-dropdown style="position: absolute; right: 20px;">
      <!-- 下拉菜单图标 -->
      <el-icon style="margin-right: 20px;width: 80px">
        <!-- 用户名 -->
        <span style="font-size: 15px"><el-icon><ArrowDown /></el-icon>管理员</span>
      </el-icon>
      <!-- 下拉菜单内容 -->
      <template #dropdown>
        <el-dropdown-menu>
          <!-- 下拉菜单项 -->
          <el-dropdown-item @click="goBack">退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>

  </div>
</template>

<style scoped>

</style>
