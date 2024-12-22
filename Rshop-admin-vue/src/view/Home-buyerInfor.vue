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
        <buyer-menu @menu-click="handleMenuClick" style="height: 100%"></buyer-menu>
      </el-aside>
      <!-- 主要内容区域 -->
      <el-container style="flex: 1;background-color: #e8eaeb">
        <el-main style="height: 100%">
          <div style="height: 400px;display: contents">
            <component :is="currentComponent"></component>
          </div>
        </el-main>
      </el-container>
    </div>
  </el-container>
</template>

<script lang="ts" setup>
import BuyerOrderFinish from "@/components/block-buyer/buyer-order-finish.vue";
import BuyerOrderIng from "@/components/block-buyer/buyer-order-ing.vue";
import BuyerOrderUn from "@/components/block-buyer/buyer-order-un.vue";
import BuyerInfo from "@/components/block-buyer/buyer-info.vue";
import BuyerAccountInfo from "@/components/block-buyer/buyer-account-info.vue";
import BuyerTradeInfo from "@/components/block-buyer/buyer-trade-info.vue";
import {onMounted, ref} from "vue";
import {useRoute} from "vue-router";
import {jwtDecode} from "jwt-decode";
import BuyerMenu from "@/components/block-buyer/buyer-menu.vue";
import BuyerSearch from "@/components/block-search/buyer-search.vue";
import ShopLogo from "@/components/Shop-Logo.vue";
import BuyerHeader from "@/components/block-buyer/buyer-header.vue";

// 定义一个响应式变量来存储当前显示的组件
const currentComponent = ref(BuyerInfo);

// 定义响应式变量来存储token
const route = useRoute();
const token = ref(route.query.token);

// 如果需要解析token
const decodedToken = ref({});
onMounted(async () => {
  if (token.value) {
    try {
      decodedToken.value = jwtDecode(token.value);
    } catch (error) {
      console.error("Failed to decode token:", error);
    }
  }
});
const handleMenuClick = (index) => {
  switch (index) {
    case '1':
      currentComponent.value = BuyerInfo;
      break;
    case '2':
      currentComponent.value = BuyerOrderUn;
      break;
    case '3':
      currentComponent.value = BuyerOrderIng;
      break;
    case '4':
      currentComponent.value = BuyerOrderFinish;
      break;
    case '5':
      currentComponent.value = BuyerTradeInfo;
      break;
    case '6':
      currentComponent.value = BuyerAccountInfo;
      break;
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
