<template>
  <el-card class="trade-info-change-card">
    <template v-slot:header>
      <div>{{ `收货管理` }}</div>
      <div class="account-info">当前账号为：{{ account }}</div>
    </template>
    <el-form ref="Form" :model="Form" label-width="150px">
      <el-form-item label="密码" :rules="[{ required: true, message: '请输入密码', trigger: 'blur' }]">
        <el-input type="password" v-model="Form.pwd" autocomplete="off" required></el-input>
      </el-form-item>
      <el-form-item label="默认电话" :rules="[{ required: true, message: '请输入电话', trigger: 'blur' }]">
        <el-input type="text" v-model="Form.phone" autocomplete="phone" required></el-input>
      </el-form-item>
      <el-form-item label="默认地址" :rules="[{ required: true, message: '请输入地址', trigger: 'blur' }]">
        <el-input type="text" v-model="Form.address" autocomplete="address" required></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitPasswordCheck">提交</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
import { ElMessage } from 'element-plus';
import axios from 'axios';
import { defineComponent } from 'vue';
import { jwtDecode } from "jwt-decode";

const token = localStorage.getItem('token');
export default defineComponent({
  created() {
    if (token) {
      const claims = jwtDecode(token);
      this.account = claims.account;
      this.password = claims.password;
      this.Form.address = claims.address;
      this.Form.phone = claims.phone;
    } else {
      this.$router.push({ path: '/'});
    }
  },
  data() {
    return {
      account: '',
      password:'',
      Form: {
        pwd:'',
        address:'',
        phone:''
      }
    };
  },
  methods: {
    async submitPasswordCheck() {
      if (this.Form.pwd !== this.password) {
        ElMessage.error('密码错误');
        return;
      }
      const formData = {
        account: this.account,
        address: this.Form.address,
        phone: this.Form.phone
      };

      // Send the request to change the password
      try {
        const response = await axios.post('http://localhost:8090/user/update_uinfo', formData, {
          headers: {
            'Authorization': `${token}`,
          }
        });
        console.log(response.data);
        if (response.data) { // Assuming your API returns a success flag
          ElMessage.success('修改成功');
          this.$router.push('/');
        } else {
          ElMessage.error('修改失败');
        }
      } catch (error) {
        console.error('修改请求出错:', error);
        ElMessage.error('修改过程中出现错误');
      }
    }
  }
});
</script>
<style scoped>
.trade-info-change-card {
  max-width: 400px;
  margin: 20px auto;
}
.el-form-item {
  margin-bottom: 20px;
}
</style>
