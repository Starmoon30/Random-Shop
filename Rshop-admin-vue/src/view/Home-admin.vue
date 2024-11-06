<template>
  <!-- 根容器 -->
  <el-container class="layout-container-demo" style="height: 100%">
    <!-- 侧边栏 -->
    <el-aside width="300px">
      <ShopLogo></ShopLogo>
      <admin-menu></admin-menu>
    </el-aside>
    <!-- 主区域 -->
    <el-container>
      <!-- 头部，设置文本对齐为右对齐，字体大小为12px -->
      <el-header style="text-align: right; font-size: 12px; display: flex; align-items: center; height: 80px; background-color: var(--el-color-primary-light-9);">
        <admin-head></admin-head>
      </el-header>

      <!-- 主要内容区域 -->
      <el-main>
        <div>
          <admin-main-user></admin-main-user>
        </div>

      </el-main>
    </el-container>
  </el-container>
</template>

<script lang="ts" setup>
import AdminMenu from "@/components/block-admin/adminMenu.vue";
import AdminHead from "@/components/block-admin/adminHead.vue";
import AdminMainUser from "@/components/block-admin/adminMain-user.vue";
import ShopLogo from "@/components/Shop-Logo.vue";
</script>

<style scoped>
/* 样式定义 */
.layout-container-demo {
  padding: 0;
  color: var(--el-text-color-primary);
}

.layout-container-demo .el-aside {
  padding: 0;
  color: var(--el-text-color-primary);
  background: var(--el-color-primary-light-8);
}
.layout-container-demo .el-menu {
  padding: 0;
  border-right: none;
}
.layout-container-demo .el-main {
  padding: 0;
}
.layout-container-demo .toolbar {
  display: flex;
  align-items: center;
  justify-content: flex-end;
  height: 100%;
}
html, body, #app {
  height: 100%;
  margin: 0;
}

.logo img {
  max-width: 100%; /* Ensure the logo scales with the container */
  height: 100%; /* Ensures logo height matches container */
}
</style>
