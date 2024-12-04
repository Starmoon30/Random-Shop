<template>
  <el-main class="product-list-container">
    <!-- 搜索框和新增商品按钮的容器 -->
    <div style="display: flex; justify-content: center; margin-bottom: 20px;">
      <!-- 搜索框 -->
      <goods-search @update:products="handleProductsUpdate"></goods-search>
      <!-- 新增商品按钮 -->
      <el-button type="primary" @click="goToAddProduct">新增商品</el-button>
    </div>

    <!-- 滚动条组件包裹产品列表 -->
    <el-scrollbar class="product-list-scrollbar">
      <div class="product-list">
        <el-card v-for="product in products" :key="product.gid" class="product-card">
          <!-- 轮播图组件 -->
          <el-carousel :interval="5000" arrow="always" height="200px">
            <el-carousel-item v-for="(pic, index) in product.pictures" :key="index">
              <img :src="pic" class="product-image" />
            </el-carousel-item>
          </el-carousel>
          <div class="product-info">
            <h3>{{ product.gname }}</h3>
            <p>￥:{{ product.gvalue }} 库存:{{ product.gstock }}</p>
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
import axios from 'axios';
import GoodsSearch from "@/components/block-search/Goods-Search.vue";

export default defineComponent({
  components: { GoodsSearch },
  setup() {
    const router = useRouter();
    const products = ref([]); // 用于存储商品数据
    const token = localStorage.getItem('token');

    // 获取商品图片的函数
    const fetchProductPictures = async (gid) => {
      try {
        const picMap = { gid: gid };
        const response = await axios.post('http://localhost:8090/pic/get_pic', picMap, {
          headers: {
            'Authorization': `${token}`,
          }
        });
        return response.data.map(pic => `data:image/jpeg;base64,${pic}`);
      } catch (error) {
        console.error('获取商品图片失败:', error);
        return [];
      }
    };

    // 获取商品信息的函数
    const fetchProducts = async () => {
      try {
        const data = { cid: [0], query: '' }; // 示例数据，查询条件为一个空字符串
        const response = await axios.post('http://localhost:8090/goods/list_By_Category', data, {
          headers: {
            'Authorization': `${token}`,
          }
        });
        const productsWithPictures = await Promise.all(response.data.map(async product => {
          const pictures = await fetchProductPictures(product.gid);
          return { ...product, pictures };
        }));
        products.value = productsWithPictures;
      } catch (error) {
        console.error('获取商品信息失败:', error);
      }
    };

    // 组件挂载时获取商品信息
    onMounted(fetchProducts);

    // 处理子组件传递的 products 更新
    const handleProductsUpdate = (newProducts) => {
      products.value = newProducts;
    };

    // 跳转到商品详情页面
    const viewDetails = (product) => {
      const id = product.gid;
      const route = {
        name: 'ProductDet', // 详情页面的路由名称
        params: { pid: id }
      };
      router.push(route);
    };

    // 跳转到新增商品页面的方法
    const goToAddProduct = () => {
      router.push({ path: '/aHome/newProduct', name: 'NewProduct' });
    };

    return {
      products,
      viewDetails,
      goToAddProduct,
      handleProductsUpdate
    };
  }
});
</script>

<style scoped>
.product-list-container {
  height: calc(100vh - 60px);
}

.product-list-scrollbar {
  height: 100%;
  overflow-y: auto;
}

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
