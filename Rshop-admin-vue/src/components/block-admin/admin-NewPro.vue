<template>
  <el-card class="add-product-card">
    <template v-slot:header>
<div  class="clearfix">
      <el-button style="float: left;" @click="goBack">返回</el-button>
      <span>新增商品</span>
    </div>
</template>
    <el-form ref="productForm" :model="productForm" label-width="120px" style="height: 350px">
      <el-form-item label="商品名称">
        <el-input v-model="productForm.name"></el-input>
      </el-form-item>
      <el-form-item label="价格">
        <el-input type="number" v-model="productForm.price"></el-input>
      </el-form-item>
      <el-form-item label="库存">
        <el-input type="number" v-model="productForm.stock"></el-input>
      </el-form-item>
      <el-form-item label="商品介绍">
        <el-input type="textarea" v-model="productForm.description"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitProduct">新增商品</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue';
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';

const router = useRouter();
const productForm = reactive({
  name: '',
  price: '',
  stock: '',
  description: ''
});

const productFormRef = ref(null);

const goBack = () => {
  router.back();
};

const submitProduct = () => {
  // 这里添加实际的表单提交逻辑，例如使用 axios 发送请求
  console.log('提交的商品表单数据:', productForm);
  // 假设商品添加成功
  ElMessage.success('商品添加成功');
};
</script>

<style scoped>
.add-product-card {
  max-width: 600px;
  margin: 20px auto;
}

.el-button {
  margin-right: 10px;
}
</style>
