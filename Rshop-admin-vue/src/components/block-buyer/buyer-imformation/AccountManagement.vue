<template>
  <el-card class="password-change-card">
    <template v-slot:header>
      <div>{{ `修改密码` }}</div>
      <div class="account-info">当前账号为：{{ account }}</div>
    </template>
    <el-form ref="passwordForm" :model="passwordForm" label-width="150px" @submit.native.prevent="submitPasswordChange">
      <el-form-item label="旧密码" :rules="[{ required: true, message: '请输入旧密码', trigger: 'blur' }]">
        <el-input type="password" v-model="passwordForm.old_pwd" autocomplete="off" required></el-input>
      </el-form-item>
      <el-form-item label="新密码" :rules="[{ required: true, message: '请输入新密码', trigger: 'blur' }]">
        <el-input type="password" v-model="passwordForm.new_pwd" autocomplete="new-password" required></el-input>
      </el-form-item>
      <el-form-item label="确认新密码" :rules="[{ required: true, message: '请确认新密码', trigger: 'blur' }]">
        <el-input type="password" v-model="passwordForm.confirmPassword" autocomplete="new-password" required></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitPasswordChange">提交</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<style scoped>
.password-change-card {
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
import { defineComponent } from 'vue';
import { jwtDecode } from "jwt-decode";

export default defineComponent({
  created() {
    const token = localStorage.getItem('token');
    if (token) {
      const claims = jwtDecode(token);
      this.account = claims.account;
      this.passwordForm.account = claims.account;
      console.log("pwd页面：", this.account);
    } else {
      this.$router.push({ path: '/'});
    }
  },
  data() {
    return {
      account: '',
      passwordForm: {
        account: '',
        old_pwd: '',
        new_pwd: '',
        confirmPassword: ''
      }
    };
  },
  methods: {
    async submitPasswordChange() {
      if (this.passwordForm.new_pwd !== this.passwordForm.confirmPassword) {
        ElMessage.error('新密码和确认密码不匹配');
        return;
      }

      const formData = {
        account: this.passwordForm.account,
        old_pwd: this.passwordForm.old_pwd,
        new_pwd: this.passwordForm.new_pwd
      };

      // Send the request to change the password
      try {
        console.log("新密码：",formData.new_pwd);
        console.log("账号为：", formData.account);
        console.log("旧密码为：", formData.old_pwd);
        const response = await axios.post('http://localhost:8090/user/update_pwd', formData, {
          headers: {
            'Authorization': `${token}`,
          }
        });
        console.log(response.data);
        if (response.data) { // Assuming your API returns a success flag
          ElMessage.success('密码修改成功');
          this.$router.push('/');
        } else {
          ElMessage.error('密码修改失败，请检查旧密码');
        }
      } catch (error) {
        console.error('密码修改请求出错:', error);
        ElMessage.error('密码修改过程中出现错误');
      }
    }
  }
});
</script>
