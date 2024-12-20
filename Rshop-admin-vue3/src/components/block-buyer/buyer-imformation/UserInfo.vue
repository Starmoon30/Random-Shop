<template>
  <div>
    <!-- 显示加载中的提示 -->
    <div v-if="loading" class="loading">加载中...</div>

    <!-- 错误提示信息 -->
    <div v-else-if="error" class="error">{{ error }}</div>

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
import { jwtDecode } from 'jwt-decode'; // 注意：jwt-decode 可能不直接导出 JwtDecodeError，这取决于您使用的库版本

// 假设 JwtDecodeError 不是直接导出的，我们可以自己定义一个错误类型来捕获解码错误
class CustomJwtDecodeError extends Error {
  constructor(message) {
    super(message);
    this.name = 'CustomJwtDecodeError';
  }
}

// 定义响应式变量
const userInfo = ref({
  uaccount: '',
  uphone: '',
  uaddress: ''
});
const loading = ref(true);  // 加载状态
const error = ref('');      // 错误信息

// 解析 Token 获取基本用户信息
const getUserInfoFromToken = (token) => {
  console.log("token", token);
  if (!token || typeof token !== 'string') {
    throw new CustomJwtDecodeError('Token 不存在或格式不正确！');
  }

  try {
    const decoded = jwtDecode(token);
    console.log("decoded", decoded);
    console.log("decoded.account", decoded.account);
    if (!decoded || !decoded.account) {
      throw new CustomJwtDecodeError('Token 无效或缺少账号信息！');
    }
    return {
      uaccount: decoded.account,

    };

  } catch (err) {
    if (err instanceof DOMException && err.name === 'SyntaxError') {
      // 对于某些环境，jwt-decode 可能会抛出 DOMException（例如，在浏览器环境中）
      throw new CustomJwtDecodeError('Token 格式错误，无法解析！');
    } else {
      // 对于其他类型的错误，我们可能想要重新抛出它们或记录它们
      console.error('Token 解析时发生未知错误:', err);
      throw new CustomJwtDecodeError('Token 解析失败！');
    }
  }
};
// 获取用户详细信息
const fetchUserInfo = async () => {
  loading.value = true; // 开始加载
  error.value = '';     // 清空错误消息

  try {
    const token = localStorage.getItem('token'); // 直接获取 Token
    if (!token) {
      throw new Error('Token 不存在，请重新登录！');
    }

    const currentUser = getUserInfoFromToken(token); // 解析 Token 获取用户账号
    const { uaccount } = currentUser;
    console.log('currentUser:', currentUser); // 输出整个 currentUser 对象以进行调试
    console.log('Extracted UAccount:', uaccount); // 输出提取到的 UAccount 值

    if (!uaccount) {
      // 注意：由于我们在 getUserInfoFromToken 中已经检查了 UAccount 的存在性，
      // 这里实际上不应该再检查一次。但为了清晰起见，我还是保留了这个检查。
      // 在实际应用中，您可能想要移除这个冗余的检查。
      throw new Error('从 Token 中提取的用户账号信息无效！');
    }

    // 发送请求获取用户信息
    const response = await axios.post(
      'http://localhost:8090/user/uinfo',
      { account: uaccount },
      {
        headers: {
          'Authorization': `${token}` // 携带 Token
        }
      }
    );

    // 处理响应数据
    const data = response.data;
    console.log('data', data);

    if (data && data.uaccount) {
      userInfo.value = {
        uaccount: data.uaccount,
        uphone: data.uphone || '未提供',
        uaddress: data.uaddress || '未提供'
      };
    } else {
      throw new Error('后端未返回有效用户信息或用户信息不完整！');
    }
  } catch (err) {
    if (err instanceof CustomJwtDecodeError) {
      // 特殊处理 jwt-decode 相关的错误
      error.value = err.message;
    } else {
      // 对于其他类型的错误，我们可能想要记录它们或以不同的方式处理
      console.error('获取用户信息失败:', err.message);
      error.value = '获取用户信息时发生错误，请稍后再试。';
    }
  } finally {
    loading.value = false; // 结束加载
  }
};

// 组件挂载时获取用户信息
onMounted(() => {
  console.log('组件挂载，获取用户信息...');
  fetchUserInfo();
});
</script>

<style scoped>
/* 自定义样式 */
.el-form {
  margin-top: 20px;
}

.loading, .error {
  text-align: center;
  padding: 20px;
  font-size: 1.2em;
}

.error {
  color: red;
}
</style>
