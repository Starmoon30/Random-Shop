<template>
  <div class="product-list">
    <el-card v-for="product in historicProducts" :key="product.id" class="product-card">
      <img :src="product.image" alt="" class="product-image">
      <div class="product-info">
        <h3>{{ product.name }}</h3>
        <p>￥{{ product.price }}</p>
      </div>
      <el-button type="primary" @click="relaunchProduct(product.id)">重新上架</el-button>
    </el-card>
  </div>
</template>

<script lang="ts" setup>
import { ref } from 'vue';
import { ElMessage } from 'element-plus';

const historicProducts = ref([
  // 假设的商品数据
  { id: 1, name: '商品1', price: 100, image: 'D:/Rshop/Rshop-admin-vue//public/favicon.ico' },
  // 更多商品...
]);

const relaunchProduct = (productId) => {
  // 这里添加实际的重新上架逻辑，例如使用 axios 发送请求
  console.log('重新上架商品:', productId);
  // 假设重新上架成功
  ElMessage.success('商品重新上架成功');
};
</script>

<style scoped>
/* 样式与 PendingProducts 相同 */
</style>
