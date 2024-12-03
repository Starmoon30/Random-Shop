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
    const searchQuery = ref(''); // 用于存储搜索查询
    const pictures = ref([]);
    const token=localStorage.getItem('token');
    // 获取商品图片的函数
    const fetchProductPictures = async (gid) => {
      try {
        const picMap = { gid: gid };
        const response = await axios.post('http://localhost:8090/pic/get_pic', picMap, {
          headers: {
            'Authorization': `${token}`,
          }
        });
        console.log(response.data);
        return response.data.map(pic => `data:image/jpeg;base64,${pic}`);
      } catch (error) {
        console.error('获取商品图片失败:', error);
        return [];
      }
    };
    // 获取商品信息的函数
    const fetchProducts = async () => {
      try {
        const data = {
          cid: [0],
          query: searchQuery.value
        };
        const response = await axios.post('http://localhost:8090/goods/list_By_Category', data, {
          headers: {
            'Authorization': `${token}`,
          }
        });
        const productsWithPictures = await Promise.all(response.data.map(async product => {
          const pictures = await fetchProductPictures(product.gid);
          return { ...product, pictures };
        }));
        products.value = productsWithPictures; // 确保这里赋值给 products.value
        console.log("获得商品：", response.data);
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

    const viewDetails = (product) => {
      router.push({ path: '/aHome/aGoodData', params: { productId: product.gid } });
    };

    // 跳转到新增商品页面的方法
    const goToAddProduct = () => {
      router.push({ path: '/aHome/newProduct', name: 'NewProduct' });
    };

    return {
      products,
      pictures,
      viewDetails,
      goToAddProduct,
      handleProductsUpdate
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
