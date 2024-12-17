<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

// 定义响应式变量
const userInfo = ref({
  uaccount: '',
  uphone: '',
  uaddress: '',
});

// 定义编辑状态
const isEditing = ref(false); // 是否在编辑模式下

// 获取当前登录用户的账号（假设存储在 localStorage 中）
const getCurrentUserAccount = () => {
  return localStorage.getItem('userAccount') || ''; // 或者使用 Vuex 获取当前登录用户的账号
};

// 获取用户信息
const fetchUserInfo = async () => {
  const account = getCurrentUserAccount();  // 获取当前登录用户的账号
  if (!account) {
    console.error('No user is logged in.');
    return;
  }

  try {
    // 向后端发送请求，获取当前用户的个人信息
    const response = await axios.post('/user/uinfo', { account });
    userInfo.value = response.data;  // 假设返回的数据是用户的个人信息
  } catch (error) {
    console.error('Error fetching user info:', error);
  }
};

// 编辑用户信息
const editUserInfo = () => {
  isEditing.value = true; // 切换到编辑模式
};

// 保存用户修改
const saveUserInfo = async () => {
  try {
    const response = await axios.post('/user/update', userInfo.value); // 假设后端有更新用户信息的接口
    if (response.data.success) {
      isEditing.value = false; // 提交成功后退出编辑模式
      alert('信息更新成功！');
    } else {
      alert('更新失败，请稍后再试');
    }
  } catch (error) {
    console.error('Error saving user info:', error);
    alert('保存失败，请稍后再试');
  }
};

// 组件挂载时获取用户信息
onMounted(() => {
  fetchUserInfo();
});
</script>

<template>
  <div>
    <el-form :model="userInfo" label-width="120px">
      <!-- 用户名字段：显示不可编辑 -->
      <el-form-item label="用户名">
        <el-input v-model="userInfo.uaccount" :disabled="true"></el-input>
      </el-form-item>

      <!-- 电话字段：可以编辑 -->
      <el-form-item label="电话">
        <el-input v-model="userInfo.uphone" :disabled="!isEditing"></el-input>
      </el-form-item>

      <!-- 地址字段：可以编辑 -->
      <el-form-item label="地址">
        <el-input v-model="userInfo.uaddress" :disabled="!isEditing"></el-input>
      </el-form-item>

      <!-- 编辑和保存按钮 -->
      <el-button v-if="!isEditing" type="primary" @click="editUserInfo">编辑</el-button>
      <el-button v-if="isEditing" type="success" @click="saveUserInfo">保存</el-button>
      <el-button v-if="isEditing" @click="isEditing = false">取消</el-button>
    </el-form>
  </div>
</template>

<style scoped>
/* 自定义个人信息页面样式 */
</style>
