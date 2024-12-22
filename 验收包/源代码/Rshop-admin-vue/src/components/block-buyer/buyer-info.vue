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
        <el-input v-model="this.account" :disabled="true" style="width: 300px;"></el-input>
      </el-form-item>
      <!-- 电话字段：不可编辑 -->
      <el-form-item label="电话">
        <el-input v-model="this.phone" :disabled="true" style="width: 300px;"></el-input>
      </el-form-item>
      <!-- 地址字段：不可编辑 -->
      <el-form-item label="地址">
        <el-input v-model="this.address" :disabled="true" style="width: 300px;"></el-input>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import {jwtDecode} from "jwt-decode";
export default {
  account: '',
  address: '',
  phone: '',
  created(){
    const token = localStorage.getItem('token');
    if (token) {
      const claims = jwtDecode(token);
      this.account = claims.account;
      this.address = claims.address;
      this.phone = claims.phone;
   } else {
      this.$router.push({path: '/'});
    }
  }
}
</script>

<style scoped>
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
