<template>
  <div style="height: 100%; display: flex;">
    <ShopLogo style="background-color: #ffffff"></ShopLogo>
    <el-header style="width: 82%; text-align: right; font-size: 12px; display: flex; align-items: center; height: 80px; background-color: var(--el-color-primary-light-9);">
      <admin-head></admin-head>
    </el-header>
  </div>
  <div class="order-form">
    <el-form :model="form" class="form-container">
      <h2>新建分类标签</h2>
      <el-form-item label="分类名：">
        <el-input v-model="form.cname" placeholder="CName"></el-input>
      </el-form-item>
      <el-form-item label="父id：">
        <el-input v-model="form.cparentid" placeholder="CParentId"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="goBack">取消</el-button>
        <el-button type="primary" @click="submit">提交</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
import { ElButton, ElFormItem, ElInput, ElForm, ElMessage } from "element-plus";
import axios from "axios";
import { jwtDecode } from "jwt-decode";
import { ref } from "vue";
import ShopLogo from "@/components/Shop-Logo.vue";
import AdminHead from "@/components/block-admin/adminHead.vue";
const token = localStorage.getItem('token');
const claims = jwtDecode(token);
console.log("claim:", claims);
const form = ref({
  cname: '',
  cparentid: '',
});
const goBack = () => {
  window.history.back();
};
const submit = async () => {
  try {
    console.log("form:", form.value);
    const response = await axios.post('http://localhost:8090/cat/creat', form.value, {
      headers: {
        'Authorization': `${token}`,
      }
    });
    if (response.data) {
      ElMessage.success('分类标签创建成功！');
    } else {
      ElMessage.error('创建错误');
    }
  } catch (error) {
    console.error('Error:', error);
    ElMessage.error('错误');
  }
};
</script>

<style scoped>
.order-form {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh; /* Use 100vh to make it full height of the viewport */
}

.form-container {
  width: 100%;
  max-width: 400px;
  padding: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}
</style>
