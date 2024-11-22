<template>
  <el-main class="product-list-container">
    <!-- 搜索框和新增商品按钮的容器 -->
    <div style="display: flex; justify-content: center; margin-bottom: 20px;">
      <!-- 搜索框 -->
      <el-input
        placeholder="搜索商品"
        v-model="searchQuery"
        style="flex-grow: 1; margin-right: 10px;">
        <template #append>
          <el-button icon="el-icon-search" @click="fetchProducts">搜索</el-button>
        </template>
      </el-input>
      <!-- 新增商品按钮 -->
      <el-button type="primary" @click="goToAddProduct">新增商品</el-button>
    </div>

    <!-- 滚动条组件包裹产品列表 -->
    <el-scrollbar class="product-list-scrollbar">
      <div class="product-list">
        <el-card v-for="product in products" :key="product.gid" class="product-card">
          <img :src="product.image" alt="" class="product-image">
          <div class="product-info">
            <h3>{{ product.gname }}</h3>
            <p>￥:{{ product.gvalue }}</p>
          </div>
          <el-button type="text" @click="viewDetails(product)">查看详情</el-button>
        </el-card>
      </div>
    </el-scrollbar>
  </el-main>
</template>

<script>
import { defineComponent, ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios'; // 确保已经安装了axios

export default defineComponent({
  setup() {
    const router = useRouter();
    const products = ref([]); // 用于存储商品数据
    const searchQuery = ref(''); // 用于存储搜索查询
    const pictures = ref([]);
    // 获取商品信息的函数
    const fetchProducts = async () => {
      try {
        const data = {
          cid: [0],
          query: searchQuery.value
        };
        const response = await axios.post('http://localhost:8090/goods/get_info_by_name', data);
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

    // 跳转到新增商品页面的方法
    const goToAddProduct = () => {
      router.push({ path: '/aHome/newProduct', name: 'NewProduct' });
    };

    return {
      products, // 只需要返回 products 这个响应式引用
      pictures,
      viewDetails,
      goToAddProduct,
      searchQuery, // 返回搜索查询的响应式引用
    };
  }
});
</script>

<style scoped>
.product-list-container {
  height: calc(100vh - 60px); /* 设置固定高度，减去顶部导航栏的高度 */
}

.product-list-scrollbar {
  height: 100%; /* 设置滚动条容器高度为100% */
  overflow-y: auto; /* 启用垂直滚动 */
}

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
