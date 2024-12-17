<template>
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
import { ref, computed } from 'vue';
import { useRoute } from 'vue-router';
import { ElMessage } from 'element-plus';

const route = useRoute();
const product = ref({
  id: route.params.productId,
  name: '',
  price: '',
  // 更多商品信息...
});

// 假设从后端获取商品信息的逻辑
const fetchProductInfo = async () => {
  // 模拟异步请求
  setTimeout(() => {
    product.value = {
      id: 1,
      name: '商品1',
      price: 100,
      // 更多商品信息...
    };
  }, 1000);
};

fetchProductInfo();

const goBack = () => {
  history.back();
};

const submitProductInfo = () => {
  // 提交商品信息修改的逻辑
  console.log('提交的商品信息:', product.value);
  ElMessage.success('商品信息修改成功');
};
</script>

<style scoped>
.edit-product-info {
  max-width: 600px;
  margin: 20px auto;
}
</style>
