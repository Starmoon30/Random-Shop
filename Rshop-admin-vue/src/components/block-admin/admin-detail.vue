<template>
  <el-main>
    <div v-if="product" class="product-details">
      <!-- 轮播图组件 -->
      <el-carousel :interval="5000" arrow="always" height="200px">
        <el-carousel-item v-for="(pic, index) in product.pictures" :key="index">
          <img :src="pic" class="product-image" />
        </el-carousel-item>
      </el-carousel>
      <div class="product-info">
        <h3>{{ product.gname }}</h3>
        <p>￥{{ product.gvalue }}</p>
        <el-button type="primary" @click="editProductInfo">修改信息</el-button>
      </div>
      <el-button type="text" @click="goBack">返回</el-button>
    </div>
  </el-main>
</template>

<script lang="ts">
import { ref, defineComponent, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import axios from "axios";
const token = localStorage.getItem('token');
export default defineComponent({
  props: {
    productId: {
      type: Number,
      required: true
    }
  },
  setup(props) {
    const product = ref(null); // 用于存储商品数据
    const router = useRouter();

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
    const fetchProductDetails = async () => {
      try {
        const response = await axios.post('http://localhost:8090/goods/get_info', { gid: props.productId }, {
          headers: {
            'Authorization': `${token}`,
          }
        });
        const productsWithPictures = await Promise.all(response.data.map(async product => {
          const pictures = await fetchProductPictures(product.gid);
          return { ...product, pictures };
        }));
        product.value = productsWithPictures; // 确保这里赋值给 products.value
        console.log("获得商品：", response.data);
      } catch (error) {
        console.error('获取商品信息失败:', error);
      }
    };

    // 组件挂载时获取商品信息
    onMounted(fetchProductDetails);

    const goBack = () => {
      history.back();
    };

    const editProductInfo = () => {
      // 跳转到修改商品信息组件
      router.push({ name: 'AGoodUpdate', params: { productId: props.productId } });
    };

    return {
      product,
      goBack,
      editProductInfo
    };
  }
});
</script>

<style scoped>
.product-details {
  display: flex;
  gap: 20px;
}
.product-image {
  width: 100%;
  height: 200px;
  object-fit: cover;
}
.product-info {
  flex: 1;
}
.product-description {
  flex: 1;
}
</style>
