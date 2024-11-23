<template>
  <!-- 搜索框 -->
  <el-input
    placeholder="搜索商品"
    v-model="searchQuery"
    style="flex-grow: 1; margin-right: 10px;">
    <template #append>
      <el-button icon="el-icon-search" @click="searchProducts">搜索</el-button>
    </template>
  </el-input>
</template>

<script>
import { defineComponent, ref } from 'vue';
import axios from 'axios';

export default defineComponent({
  setup(props, { emit }) {
    const searchQuery = ref(''); // 用于存储搜索查询
    const products = ref([]); // 用于存储商品数据
    // 获取商品图片的函数
    const fetchProductPictures = async (gid) => {
      try {
        const picMap = { gid: gid };
        const response = await axios.post('http://localhost:8090/pic/get_pic', picMap);
        return response.data.map(pic => `data:image/jpeg;base64,${pic}`);
      } catch (error) {
        console.error('获取商品图片失败:', error);
        return [];
      }
    };
    // 获取商品信息的函数
    const searchProducts = async () => {
      try {
        const response = await axios.post('http://localhost:8090/goods/get_info_by_name', {
          name: searchQuery.value // 传入搜索框中的内容
        });
        const productsWithPictures = await Promise.all(response.data.map(async product => {
          const pictures = await fetchProductPictures(product.gid);
          return { ...product, pictures };
        }));
        products.value = productsWithPictures; // 确保这里赋值给 products.value
        console.log("获得商品：", response.data);
        emit('update:products', products.value);
      } catch (error) {
        console.error('搜索商品信息失败:', error);
      }
    };

    return {
      products,
      searchQuery,
      searchProducts
    };
  }
});
</script>

<style scoped>
</style>
