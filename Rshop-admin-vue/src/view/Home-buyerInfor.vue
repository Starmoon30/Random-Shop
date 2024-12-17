<template>
  <el-container class="layout-container-demo" style="height: 100%; display: contents; flex-direction: column">
    <buyer-header></buyer-header>
    <div style="display: flex;">
      <ShopLogo style="width: 300px"></ShopLogo>
      <buyer-search></buyer-search>
    </div>
    <div style="display: flex; height: 100%">
      <!-- 侧边栏 -->
      <el-aside width="300px" style="background: var(--el-color-primary-light-8);height: 100%">
        <buyer-menu></buyer-menu>
      </el-aside>
      <!-- 主要内容区域 -->
      <el-container style="flex: 1;background-color: #e8eaeb">
        <el-main>
          <div>
            <router-view></router-view>
          </div>
        </el-main>
      </el-container>
    </div>
  </el-container>
</template>

<script>
import ShopLogo from "@/components/Shop-Logo.vue";
import BuyerMenu from "@/components/block-buyer/buyer-menu.vue";
import BuyerSearch from "@/components/block-buyer/buyer-search.vue";
import BuyerHeader from "@/components/block-buyer/buyer-header.vue";

export default {
  components: {
    ShopLogo,
    BuyerMenu,
    BuyerSearch,
    BuyerHeader
  }
};
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
