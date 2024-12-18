<template>
  <el-main class="product-list-container">
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
            <p>￥:{{ product.gvalue }} 设置库存:{{ product.gstock }}</p>
          </div>
          <!-- 上架按钮 -->
          <el-button type="success" @click="shelveProduct(product)">上架</el-button>
        </el-card>
      </div>
    </el-scrollbar>
  </el-main>
</template>

<script>
import { defineComponent, ref, onMounted } from 'vue';
import axios from 'axios';
const token = localStorage.getItem('token');
export default defineComponent({
  setup() {
    const products = ref([]); // 用于存储商品数据
    const searchQuery = ref(''); // 用于存储搜索查询

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
        console.log("后端返回的商品数据：", response.data); // 输出查看返回的数据
        const productsWithPictures = await Promise.all(response.data.map(async product => {
          // 只处理gshelf为0的商品
          if (product.gshelf === 0) {
            const pictures = await fetchProductPictures(product.gid);
            return { ...product, pictures };
          }
          return null;
        }));
        const filteredProducts = productsWithPictures.filter(item => item !== null); // 过滤掉null项
        products.value = filteredProducts; // 确保这里赋值给 products.value
        console.log("过滤后的商品：", filteredProducts);
      } catch (error) {
        console.error('获取商品信息失败:', error);
      }
    };

    // 上架商品的函数
    const shelveProduct = async (product) => {
      try {
        const updateMap = {
          gid: product.gid,
          shelf: 1
        };
        const response = await axios.post('http://localhost:8090/goods/update_Gshelf', updateMap, {
          headers: {
            'Authorization': `${token}`,
          }
        });
        if (response.data) {
          console.log('商品上架成功');
          // 重新获取商品列表
          fetchProducts();
        } else {
          console.error('商品上架失败');
        }
      } catch (error) {
        console.error('商品上架失败:', error);
      }
    };

    // 组件挂载时获取商品信息
    onMounted(fetchProducts);

    // 处理子组件传递的 products 更新
    const handleProductsUpdate = (newProducts) => {
      products.value = newProducts;
    };

    return {
      products,
      shelveProduct,
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
