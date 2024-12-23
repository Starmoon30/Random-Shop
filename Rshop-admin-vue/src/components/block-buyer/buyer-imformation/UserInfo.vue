<template>
  <div>
    <!-- 显示加载中的提示 -->
    <div v-if="loading" class="loading">Loading...</div>

    <!-- 用户信息表单 -->
    <el-form :model="userInfo" label-width="120px" v-else>
      <!-- 用户名字段：显示不可编辑 -->
      <el-form-item label="用户名">
        <el-input v-model="userInfo.uaccount" :disabled="true"></el-input>
      </el-form-item>

      <!-- 电话字段：不可编辑 -->
      <el-form-item label="电话">
        <el-input v-model="userInfo.uphone" :disabled="true"></el-input>
      </el-form-item>

      <!-- 地址字段：不可编辑 -->
      <el-form-item label="地址">
        <el-input v-model="userInfo.uaddress" :disabled="true"></el-input>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { jwtDecode } from 'jwt-decode'; // 引入 jwt-decode

// 定义响应式变量
const userInfo = ref({
  uaccount: '',
  uphone: '',
  uaddress: ''
});
const loading = ref(true); // 加载状态

// 获取当前登录用户的Token（假设存储在 localStorage 中）
const getCurrentUserToken = () => {
  return localStorage.getItem('token') || null;
};

// 解析Token以获取用户的基本信息
const getUserInfoFromToken = (token) => {
  if (!token) return null;
  try {
    const decoded = jwtDecode(token);
    return {
      uaccount: decoded.UAccount || '', // 从Token中提取 uaccount
      uphone: decoded.UPhone || '',     // 从Token中提取 uphone
      uaddress: decoded.UAddress || ''   // 从Token中提取 uaddress
    };
  } catch (error) {
    console.error('Error decoding token:', error);
    return null;
  }
};

// 获取用户信息
const fetchUserInfo = async () => {
  try {
    const token = getCurrentUserToken();  // 获取当前登录用户的Token
    if (!token) {
      console.error('No user is logged in.');
      loading.value = false;
      return;
    }

    // 解析Token以获取用户的基本信息
    const currentUserInfo = getUserInfoFromToken(token);
    if (!currentUserInfo || !currentUserInfo.uaccount) {
      console.error('Invalid or incomplete token.');
      loading.value = false;
      return;
    }

    // 向后端发送 POST 请求，获取当前用户的详细个人信息
    const response = await axios.post('http://localhost:8090/user/uinfo',
      { account: currentUserInfo.uaccount }, // 将 account 包含在请求体中
      {
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}` // 添加Token到请求头
        }
      }
    );

    // 调试输出查看返回的数据
    console.log('Response data:', response.data);

    // 如果返回数据不为空，更新 userInfo
    if (response.data && response.data.UAccount) {
      userInfo.value = {
        uaccount: response.data.UAccount,
        uphone: response.data.UPhone || '',  // 确保即使电话为空也不会报错
        uaddress: response.data.UAddress || ''  // 确保即使地址为空也不会报错
      };
    } else {
      console.warn('No user info returned or UAccount not found.');
    }
  } catch (error) {
    console.error('Error fetching user info:', error);
    // 处理错误情况，比如显示错误消息给用户
  } finally {
    loading.value = false; // 不管成功与否，都结束加载状态
  }
};

// 组件挂载时获取用户信息
onMounted(() => {
  console.log('Component mounted, fetching user info...');
  fetchUserInfo();
});
</script>

<style scoped>
/* 自定义个人信息页面样式 */
.el-form {
  margin-top: 20px;
}

.loading {
  text-align: center;
  padding: 20px;
  font-size: 1.2em;
}
</style>
