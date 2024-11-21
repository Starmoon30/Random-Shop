<template>
  <div class="product-list">
    <el-card v-for="product in launchedProducts" :key="product.id" class="product-card">
      <img :src="product.image" alt="" class="product-image">
      <div class="product-info">
        <h3>{{ product.name }}</h3>
        <p>￥{{ product.price }}</p>
        <p>库存: {{ product.stock }}</p>
      </div>
      <el-button v-if="!product.frozen" type="warning" @click="freezeProduct(product.id)">冻结商品</el-button>
      <el-button v-else type="success" @click="unfreezeProduct(product.id)">取消冻结</el-button>
    </el-card>
  </div>
</template>

<script lang="ts" setup>
import { ref } from 'vue';
import { ElMessage } from 'element-plus';

const launchedProducts = ref([
  // 假设的商品数据
  { id: 1, name: '商品1', price: 100, stock: 100, image: 'D:/Rshop/Rshop-admin-vue//public/favicon.ico', frozen: false },
  // 更多商品...
]);

const freezeProduct = (productId) => {
  // 这里添加实际的冻结逻辑，例如使用 axios 发送请求
  console.log('冻结商品:', productId);
  // 假设商品冻结成功
  ElMessage.success('商品冻结成功');
};

const unfreezeProduct = (productId) => {
  // 这里添加实际的取消冻结逻辑，例如使用 axios 发送请求
  console.log('取消冻结商品:', productId);
  // 假设取消冻结成功
  ElMessage.success('取消冻结成功');
};
</script>

<style scoped>
/* 样式与 PendingProducts 相同 */
</style>
