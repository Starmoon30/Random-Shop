<template>
  <div style="height: 100%; display: flex;">
    <ShopLogo style="background-color: #ffffff"></ShopLogo>
    <el-header style="width: 82%; text-align: right; font-size: 12px; display: flex; align-items: center; height: 80px; background-color: var(--el-color-primary-light-9);">
      <admin-head></admin-head>
    </el-header>
  </div>
  <el-main>
    <div v-if="pro" class="edit-product-info">
      <el-form :model="pro">
        <el-form-item label="商品名称">
          <el-input v-model="pro.gname"></el-input>
        </el-form-item>
        <el-form-item label="价格">
          <el-input type="number" v-model="pro.gvalue"></el-input>
        </el-form-item>
        <el-form-item label="库存">
          <el-input type="number" v-model="pro.gstock"></el-input>
        </el-form-item>

        <upRichText :content="pro.gdesc" :onContentChange="updateDescription"></upRichText>

        <el-form-item>
          <el-button type="primary" @click="submitProductInfo">提交</el-button>
          <el-button type="text" @click="goBack">返回</el-button>
        </el-form-item>
      </el-form>

    </div>
  </el-main>
</template>

<script lang="ts" setup>
defineProps({
  content: String
});
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import { ElMessage } from 'element-plus';
import axios from 'axios';
import ShopLogo from "@/components/Shop-Logo.vue";
import AdminHead from "@/components/block-admin/adminHead.vue";
import upRichText from "@/components/block-rich/UpdateRichText.vue";

const token = localStorage.getItem('token');
const route = useRoute();
const gid = Number(route.params.pid);
const pro = ref()
const newDesc = ref('');
const updateDescription = (content) => {
  newDesc.value = content; // 更新 newDesc 的值
  pro.value.desc = content; // 同时更新 pro 的 desc 属性
};
// 从后端获取商品信息
const fetchProductData = async (gid) => {
  try {
    const response = await axios.post('http://localhost:8090/goods/get_info', { gid }, {
      headers: {
        'Authorization': `${token}`,
      }
    });
    if (response.data.length > 0) {
      const fetchedProduct = response.data[0];
      const pictures = await fetchProductPictures(fetchedProduct.gid);
      pro.value = { ...fetchedProduct, pictures };
    }
  } catch (error) {
    console.error("获取商品详情失败:", error);
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
    console.log("传输：",newDesc)
    const product ={
      gid: gid,
      name: pro.value.gname,
      value: parseInt(pro.value.gvalue),
      stock: parseInt(pro.value.gstock),
      cid: pro.value.cid,
      desc: newDesc.value
    }
    const response = await axios.post(`http://localhost:8090/goods/update_Ginfo`, product, {
      headers: {
        'Authorization': `${token}`,
      }
    });
    if (response) {
      ElMessage.success('商品信息修改成功');
    }
  } catch (error) {
    console.error("提交商品信息失败:", error);
    ElMessage.error('提交商品信息失败');
  }
};

// 在组件挂载时获取商品信息
onMounted(() => {
  const gid = Number(route.params.pid);
  console.log("得到gid：",gid);
  fetchProductData(gid);
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
