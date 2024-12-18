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
      <el-form-item label="父分类">
        <el-select v-model="form.cparentid">
          <el-option
            v-for="category in topCategories"
            :key="category.cid"
            :label="category.cname"
            :value="category.cid">
          </el-option>
        </el-select>
        <!-- 显示选中的分类信息 -->
        <div v-if="selectedTopCategory" class="selected-category-info">
        </div>
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
import {computed, onMounted, ref} from "vue";
import ShopLogo from "@/components/Shop-Logo.vue";
import AdminHead from "@/components/block-admin/adminHead.vue";
const token = localStorage.getItem('token');
const claims = jwtDecode(token);
const categories = ref([]); // 一级分类
console.log("claim:", claims);
const form = ref({
  cname: '',
  cparentid: '',
});
const goBack = () => {
  window.history.back();
};
const fetchCategories = async () => {
  try {
    const response = await axios.post('http://localhost:8090/cat/get_cid_by_parent', { id: [-1,0] }, {
      headers: {
        'Authorization': `${token}`,
      }
    });
    categories.value = await Promise.all(response.data.map(async (category) => {
      try {
        const infoResponse = await axios.get(`http://localhost:8090/cat/get_info_by_cid/${category}`, {
          headers: {
            'Authorization': `${token}`,
          }
        });
        return { ...category, ...infoResponse.data };
      } catch (error) {
        console.error(`Error fetching info for category ${category.cid}:`, error);
        return category;
      }
    }));
  } catch (error) {
    console.error('Error fetching categories:', error);
  }
};

const topCategories = computed(() => categories.value);

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
onMounted(() => {
  fetchCategories();
});
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
