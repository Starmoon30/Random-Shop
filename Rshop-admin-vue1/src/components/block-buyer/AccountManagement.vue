<template>
  <div>
    <el-form :model="accountInfo" label-width="120px">
      <el-form-item label="用户名">
        <el-input v-model="accountInfo.uaccount" disabled></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="accountInfo.upassword" type="password" disabled></el-input>
      </el-form-item>
      <el-form-item label="电话">
        <el-input v-model="accountInfo.uphone" disabled></el-input>
      </el-form-item>
      <el-form-item label="地址">
        <el-input v-model="accountInfo.uaddress" disabled></el-input>
      </el-form-item>
      <el-button type="primary" @click="editAccountInfo">编辑</el-button>
      <el-button type="danger" @click="resetPassword">重置密码</el-button>
    </el-form>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';

const accountInfo = ref({
  uaccount: 'A1',
  upassword: '1234',
  uphone: '123456789',
  uaddress: '浙江工商大学',
});

const editAccountInfo = () => {
  // Logic for editing account info (could show a modal or open form for editing)
  console.log('Editing account info...');
};

const resetPassword = async () => {
  const response = await axios.post('/user/reset_admin');  // Example endpoint to reset admin password
  if (response.status === 200) {
    alert('密码已重置');
  }
};
</script>

<style scoped>
/* 账号管理页面样式 */
</style>
