<template>
  <buyer-header></buyer-header>
  <div style="display: flex">
    <ShopLogo></ShopLogo>
    <div class="info-header">
      <el-button type="default" @click="goBack"><el-icon size="32"><HomeFilled /></el-icon></el-button>
    </div>
  </div>
  <div class="product-list">
    <el-card v-for="product in products"
             :key="product.gid"
             class="product-card"
             @click="goToProductDetail(product.gid)">
      <el-carousel :interval="5000" arrow="always" height="200px">
        <el-carousel-item v-for="(pic, index) in product.pictures" :key="index">
          <img :src="pic" class="product-image" />
        </el-carousel-item>
      </el-carousel>
      <div class="product-info">
        <h3>{{ product.gname }}</h3>
        <p>￥:{{ product.gvalue }} 库存:{{ product.gstock }}</p>
      </div>
    </el-card>
  </div>
</template>

<script>
import axios from "axios";
import {onMounted, ref} from "vue";
import {useRoute} from "vue-router";
import BuyerHeader from "@/components/block-buyer/buyer-header.vue";
import ShopLogo from "@/components/Shop-Logo.vue";
import router from "@/router/index.js";

export default {
  components: {ShopLogo, BuyerHeader},
  setup() {
    const route = useRoute();
    const query = route.params.query; // 修改这里
    console.log('Search query:', query);
    const token = localStorage.getItem('token');
    const products = ref([]); // 用于存储商品数据

    const fetchProductPictures = async (gid) => {
      try {
        const picMap = { gid: gid };
        const response = await axios.post('http://localhost:8090/pic/get_pic', picMap, {
          headers: {
            'Authorization': `${token}`,
          }
        });
        // 遍历返回的Map对象并获取Base64编码图片数据
        const picArray = Object.entries(response.data).map(([picId, base64Data]) => {
          return `data:image/jpeg;base64,${base64Data}`;
        });

        return picArray;
      } catch (error) {
        console.error('获取商品图片失败:', error);
        return [];
      }
    };

    const searchProducts = async () => {
      try {
        const response = await axios.post('http://localhost:8090/goods/get_info_by_name', {
          name: query // 传入搜索框中的内容
        }, {
          headers: {
            'Authorization': `${token}`,
          }
        });
        const productsWithPictures = await Promise.all(response.data.map(async product => {
          const pictures = await fetchProductPictures(product.gid);
          return {...product, pictures};
        }));
        products.value = productsWithPictures; // 确保这里赋值给 products.value
        console.log("获得商品：", response.data);
      } catch (error) {
        console.error('搜索商品信息失败:', error);
      }
    };
    const goToProductDetail = (gid) => {
      const route = {
        name: 'BProductDet', // 详情页面的路由名称
        params: { pid: gid }
      };
      router.push(route);
    };
    onMounted(() => {
      searchProducts();
    });
    const goBack = () => {
      router.go(-1);
    };
    return {
      products,
      goToProductDetail,
      goBack
    };
  }
};
</script>

<style scoped>
.product-page {
  display: flex;
  flex-direction: column;
}

.product-list {
  margin: 40px;
  display: flex;
  flex-wrap: wrap;
  gap: 40px;
  justify-content: center;
}

.product-card {
  width: calc(25% - 20px);
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
  border-radius: 8px;
  overflow: hidden;
  display: flex;
  flex-direction: column;
}

.el-carousel {
  height: 200px; /* 保持与 template 中的高度一致 */
}

.el-carousel__item {
  display: flex;
  justify-content: center;
  align-items: center;
}

.product-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
  display: block;
}

.product-info {
  padding: 10px;
  background-color: #fff;
  border-top: 1px solid #eee;
}
.info-header {
  padding: 10px; /* 调整按钮的 padding */
  font-size: 16px; /* 调整按钮的字体大小 */
  margin-left: auto; /* 添加这个样式 */
}
</style>
