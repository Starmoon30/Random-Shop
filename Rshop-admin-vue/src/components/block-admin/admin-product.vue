<template>
  <el-main>
    <div class="product-list">
      <el-card v-for="product in products" :key="product.gid" class="product-card">
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
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios'; // 确保已经安装了axios

const router = useRouter();
const products = ref([]); // 用于存储商品数据

// 获取商品信息的函数
const fetchProducts = async () => {
  try {
    // 假设你的接口需要一个GET请求，并且不需要请求体
    const response = await axios.get('http://localhost:8090/goods/list', {
      params: { cid: [0] }
    });
    products.value = response.data;
    console.log("获得商品：",products.value);
  } catch (error) {
    console.error('获取商品信息失败:', error);
  }
};

// 组件挂载时获取商品信息
onMounted(fetchProducts);

const viewDetails = (product) => {
  router.push({ name: 'ProductDetails', params: { productId: product.gid } });
};
</script>

<style scoped>
.product-list {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
}
.product-card {
  width: calc(33.333% - 20px); /* 一行三个 */
}
.product-image {
  width: 100%;
  height: 200px;
  object-fit: cover;
}
</style>
