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

<script lang="ts">
import {ref, computed, defineComponent, onMounted} from 'vue';
import {useRoute, useRouter} from 'vue-router';
import { ElMessage } from 'element-plus';
import axios from "axios";

export default defineComponent({
  setup() {
    const router = useRouter();
    const products = ref([]); // 用于存储商品数据

    // 获取商品信息的函数
    const fetchProducts = async () => {
      try {
        const data = {
          gid: this.productId,
        };
        const response = await axios.post('http://localhost:8090/goods/get_info', data);
        products.value = response.data; // 确保这里赋值给 products.value
        console.log("获得商品：", response.data);
      } catch (error) {
        console.error('获取商品信息失败:', error);
      }
    };

    // 组件挂载时获取商品信息
    onMounted(fetchProducts);

    const viewDetails = (product) => {
      router.push({ name: 'AGoodData', params: { productId: product.gid } });
    };
    const editProductInfo = (product) => {
      // 跳转到修改商品信息组件s
      router.push({ name: 'AGoodUpdate', params: { productId: product.gid } });
    };
    return {
      products, // 只需要返回 products 这个响应式引用
      viewDetails,
      data:{
        cid: [],
      }
    };
  }
});

const goBack = () => {
  history.back();
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
