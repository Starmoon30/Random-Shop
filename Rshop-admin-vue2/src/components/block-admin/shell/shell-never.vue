<template>
  <div class="product-list">
    <el-card v-for="product in pendingProducts" :key="product.id" class="product-card">
      <img :src="product.image" alt="" class="product-image">
      <div class="product-info">
        <h3>{{ product.name }}</h3>
        <p>￥{{ product.price }}</p>
      </div>
      <el-button type="primary" @click="launchProduct(product.id)">上架</el-button>
    </el-card>
  </div>
</template>

<script lang="ts" setup>
import { ref } from 'vue';
import { ElMessage } from 'element-plus';

const pendingProducts = ref([
  // 假设的商品数据
  { id: 1, name: '商品1', price: 100, image: 'D:/Rshop/Rshop-admin-vue//public/favicon.ico' },
  // 更多商品...
]);

const launchProduct = (productId) => {
  // 这里添加实际的上架逻辑，例如使用 axios 发送请求
  console.log('上架商品:', productId);
  // 假设商品上架成功
  ElMessage.success('商品上架成功');
};
</script>

<style scoped>
.product-list {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
}
.product-card {
  width: calc(33.333% - 20px);
}
.product-image {
  width: 100%;
  height: 200px;
  object-fit: cover;
}
</style>
