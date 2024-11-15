<template>
  <el-main>
    <div class="product-list">
      <el-card v-for="product in products" :key="product.id" class="product-card">
        <img :src="product.image" alt="" class="product-image">
        <div class="product-info">
          <h3>{{ product.name }}</h3>
          <p>￥{{ product.price }}</p>
        </div>
        <el-button type="text" @click="viewDetails(product)">查看详情</el-button>
      </el-card>
    </div>
  </el-main>
</template>

<script lang="ts" setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const products = ref([
  // 假设的商品数据
  { id: 1, name: '商品1', price: 100, image: 'path/to/image' },
  // 更多商品...
]);

const viewDetails = (product) => {
  router.push({ name: 'ProductDetails', params: { productId: product.id } });
};
</script>

<style scoped>
.product-list {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
}
.product-card {
  width: calc(25% - 20px);
}
.product-image {
  width: 100%;
  height: 200px;
  object-fit: cover;
}
</style>
