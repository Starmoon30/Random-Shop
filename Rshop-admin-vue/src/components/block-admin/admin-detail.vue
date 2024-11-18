<template>
  <el-main>
    <div v-if="product" class="product-details">
      <img :src="product.image" alt="" class="product-image-large">
      <div class="product-info">
        <h3>{{ product.name }}</h3>
        <p>￥{{ product.price }}</p>
        <el-button type="primary" @click="editProductInfo">修改信息</el-button>
      </div>

      <el-button type="text" @click="goBack">返回</el-button>
    </div>
    <div class="product-description">
      <!-- 富媒体介绍 -->
      <p v-html="product.description"></p>
    </div>
  </el-main>
</template>

<script lang="ts" setup>
import { ref, computed } from 'vue';
import { useRoute } from 'vue-router';
import { ElMessage } from 'element-plus';

const route = useRoute();
const product = ref({
  id: route.params.productId,
  name: '',
  price: '',
  image: '',
  description: ''
});

// 假设从后端获取商品详情的逻辑
const fetchProductDetails = async () => {
  // 模拟异步请求
  setTimeout(() => {
    product.value = {
      id: 1,
      name: '商品1',
      price: 100,
      image: 'path/to/image',
      description: '<p>这是商品的富媒体介绍</p>'
    };
  }, 1000);
};

fetchProductDetails();

const goBack = () => {
  history.back();
};

const editProductInfo = () => {
  // 跳转到修改商品信息组件
  window.location.href = `#/edit-product-info/${product.value.id}`;
};
</script>

<style scoped>
.product-details {
  display: flex;
  gap: 20px;
}
.product-image-large {
  width: 300px;
  height: 300px;
  object-fit: cover;
}
.product-info {
  flex: 1;
}
.product-description {
  flex: 1;
}
</style>
