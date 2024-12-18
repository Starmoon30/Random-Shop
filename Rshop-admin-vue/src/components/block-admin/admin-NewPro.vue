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
      <el-form-item label="商品图片">
        <el-upload
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          :before-remove="beforeRemove"
          :on-success="handleSuccess"
          :on-error="handleError"
          multiple
          :limit="3"
          :on-exceed="handleExceed"
          :file-list="productForm.images"
          list-type="picture">
          <el-button size="small" type="primary">点击上传</el-button>
          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过3张</div>
        </el-upload>
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
  description: '',
  images: [] // 用于存储图片的数组
});

const goBack = () => {
  router.back();
};

const handlePreview = (file) => {
  // 处理图片预览逻辑
};

const handleRemove = (file, fileList) => {
  // 处理图片移除逻辑
};

const beforeRemove = (file, fileList) => {
  // 处理图片移除前的确认逻辑
  return true;
};

const handleExceed = (files, fileList) => {
  ElMessage.warning(`当前限制选择 3 张图片，本次选择了 ${files.length} 张，共选择了 ${files.length + fileList.length} 张`);
};

const handleSuccess = (response, file) => {
  // 文件上传成功的处理逻辑
  // 可以在这里更新 file 的 url 属性，以便显示预览图
};

const handleError = (err, file) => {
  // 文件上传失败的处理逻辑
  ElMessage.error('图片上传失败');
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
