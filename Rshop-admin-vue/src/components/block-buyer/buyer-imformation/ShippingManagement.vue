<template>
  <el-card class="account-info-update-card">
    <template v-slot:header>
      <div>{{ `修改账号信息` }}</div>
      <div class="account-info">当前账号为：{{ account }}</div>
    </template>
    <el-form ref="accountForm" :model="accountForm" label-width="150px" @submit.native.prevent="submitAccountUpdate">
      <el-form-item label="地址" :rules="[{ required: true, message: '请输入地址', trigger: 'blur' }]">
        <el-input v-model="accountForm.address" autocomplete="off" required></el-input>
      </el-form-item>
      <el-form-item label="电话号码" :rules="[{ required: true, message: '请输入电话号码', trigger: 'blur' }, { pattern: /^1[3-9]\d{9}$/, message: '请输入有效的手机号码', trigger: 'blur' }]">
        <el-input v-model="accountForm.phone" autocomplete="off" required></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitAccountUpdate">提交</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<style scoped>
.account-info-update-card {
  max-width: 400px;
  margin: 20px auto;
}

.el-form-item {
  margin-bottom: 20px;
}
</style>

<script>
import { ElMessage } from 'element-plus';
import axios from 'axios';
import { defineComponent, ref } from 'vue';
import { jwtDecode } from "jwt-decode";

export default defineComponent({
  data() {
    return {
      account: '',
      accountForm: {
        account: '',
        address: '',
        phone: ''
      },
      token: '' // 存储token
    };
  },
  created() {
    this.token = localStorage.getItem('token');
    if (this.token) {
      const claims = jwtDecode(this.token);
      this.account = claims.account;
      this.accountForm.account = claims.account;
      // 可以在这里添加额外的逻辑来预填充地址和电话，如果它们存储在token中或可以从其他地方获取
    } else {
      this.$router.push({ path: '/' });
    }
  },
  methods: {
    async submitAccountUpdate() {
      const formData = {
        account: this.accountForm.account,
        address: this.accountForm.address,
        phone: this.accountForm.phone
      };

      try {
        const response = await axios.post('http://localhost:8090/user/update_uinfo', formData, {
          headers: {
            'Authorization': `${this.token}`
          }
        });
        console.log(response.data);
        if (response.data) { // 根据你的API响应结构来检查是否成功
          ElMessage.success('账号信息修改成功');
          //this.$router.push('/');
        } else {
          ElMessage.error('账号信息修改失败，请检查输入');
        }
      } catch (error) {
        console.error('账号信息修改请求出错:', error);
        ElMessage.error('账号信息修改过程中出现错误');
      }
    }
  }
});
</script>
