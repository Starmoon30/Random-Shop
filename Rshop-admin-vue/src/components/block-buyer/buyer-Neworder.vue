<template>
  <buyer-header></buyer-header>
  <div style="display: flex">
    <ShopLogo></ShopLogo>
  </div>
  <div class="order-form">
    <el-form-item>
      <el-input v-model="form.uaccount" placeholder="UAccount"></el-input>
    </el-form-item>
    <el-form-item>
      <el-input v-model="form.ophone" placeholder="OPhone"></el-input>
    </el-form-item>
    <el-form-item>
      <el-input v-model="form.oaddress" placeholder="OAddress"></el-input>
    </el-form-item>
    <el-form-item>
      <el-input
        type="textarea"
        v-model="form.oremark"
        placeholder="请输入订单备注"
        :rows="2"
      ></el-input>
    </el-form-item>
    <el-form-item>
      <el-button @click="goBack">取消</el-button>
      <el-button type="primary" @click="submitForm">提交订单</el-button>
    </el-form-item>
  </div>
</template>

<script setup>
import {ref, onMounted} from 'vue';
import {ElFormItem, ElInput, ElButton, ElMessage} from 'element-plus';
import ShopLogo from "@/components/Shop-Logo.vue";
import BuyerHeader from "@/components/block-buyer/buyer-header.vue";
import {jwtDecode} from "jwt-decode";
import axios from 'axios';
import {useRoute} from "vue-router";
import router from "@/router/index.js";

const route = useRoute();
const gid = Number(route.params.id);
console.log("商品id:", gid);
const token = localStorage.getItem('token');
const claims = jwtDecode(token);
console.log("claim:", claims);
const form = ref({
  gid: Number(route.params.id),
  uaccount: '',
  ophone: '',
  oaddress: '',
  oremark: '',
  ostate: 0
});

const submitForm = async () => {
  try {
    console.log("form:", form.value)
    const response = await axios.post('http://localhost:8090/order/creat', form.value, {
      headers: {
        'Authorization': `${token}`,
      }
    });
    if (response.data) {
      ElMessage.success('订单创建成功！');
    } else {
      ElMessage.error('订单错误');
    }
  } catch (error) {
    console.error('Error:', error);
    ElMessage.error('订单错误');
  }
};

const goBack = () => {
  window.history.back();
};

onMounted(() => {
  // 假设 claims 中的属性名是 account, phone, address
  // 请根据实际的属性名进行调整
  form.value.uaccount = claims.account || '';
  form.value.ophone = claims.phone || '';
  form.value.oaddress = claims.address || '';
});
</script>

<style scoped>
/* 你可以在这里添加一些样式来美化你的表单 */
.order-form {
  max-width: 400px;
  margin: auto;
  padding: 20px;
  border: 1px solid #ebeef5;
  border-radius: 4px;
}

.el-form-item {
  margin-bottom: 20px;
}

.el-textarea {
  width: 100%;
}
</style>
