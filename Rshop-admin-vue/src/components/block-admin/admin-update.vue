<template>
  <div style="height: 100%; display: flex;">
    <ShopLogo style="background-color: #ffffff"></ShopLogo>
    <el-header style="width: 82%; text-align: right; font-size: 12px; display: flex; align-items: center; height: 80px; background-color: var(--el-color-primary-light-9);">
      <admin-head></admin-head>
    </el-header>
  </div>
  <el-main>
    <div v-if="product" class="edit-product-info">
      <el-form :model="product">
        <el-form-item label="商品名称">
          <el-input v-model="product.name"></el-input>
        </el-form-item>
        <el-form-item label="价格">
          <el-input type="number" v-model="product.price"></el-input>
        </el-form-item>
        <!-- 更多表单项 -->
        <el-form-item>
          <el-button type="primary" @click="submitProductInfo">提交</el-button>
        </el-form-item>
      </el-form>
      <el-button type="text" @click="goBack">返回</el-button>
    </div>
  </el-main>
</template>

<script lang="ts" setup>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import { ElMessage } from 'element-plus';
import axios from 'axios';
import ShopLogo from "@/components/Shop-Logo.vue";
import AdminHead from "@/components/block-admin/adminHead.vue";

const token = localStorage.getItem('token');
const route = useRoute();
const gid = Number(route.params.pid);
const product = ref({
  id: gid,
  name: '',
  price: '',
  // 更多商品信息...
});

// 从后端获取商品信息
const fetchProductInfo = async (gid) => {
  try {
    const response = await axios.post('http://localhost:8090/goods/get_info', { gid }, {
      headers: {
        'Authorization': `${token}`,
      }
    });
    if (response.data.length > 0) {
      const fetchedProduct = response.data[0];
      const pictures = await fetchProductPictures(fetchedProduct.gid);
      product.value = { ...fetchedProduct, pictures };
    }
  } catch (error) {
    console.error("获取商品详情失败:", error);
    // 这里可以添加更多的错误处理逻辑，例如显示错误消息
  }
};

const fetchProductPictures = async (gid) => {
  try {
    const response = await axios.post('http://localhost:8090/pic/get_pic', { gid }, {
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

// 提交商品信息修改
const submitProductInfo = async () => {
  try {
    const response = await axios.post(`http://localhost:8090/goods/update`, product.value, {
      headers: {
        'Authorization': `Bearer ${token}`,
      }
    });
    ElMessage.success('商品信息修改成功');
  } catch (error) {
    console.error("提交商品信息失败:", error);
    ElMessage.error('提交商品信息失败');
  }
};

// 在组件挂载时获取商品信息
onMounted(() => {
  if (!isNaN(gid)) {
    fetchProductInfo(gid);
  } else {
    console.error('商品ID无效');
    // 这里可以添加更多的错误处理逻辑，例如跳转到错误页面或显示错误消息
  }
});

const goBack = () => {
  history.back();
};
</script>

<style scoped>
.edit-product-info {
  max-width: 600px;
  margin: 20px auto;
}
</style>
