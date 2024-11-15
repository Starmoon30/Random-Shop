<template>
  <el-card class="password-change-card">
    <template v-slot:header>
<div >修改密码</div>
</template>
    <el-form ref="passwordForm" :model="passwordForm" label-width="150px">
      <el-form-item label="旧密码">
        <el-input type="password" v-model="passwordForm.oldPassword" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="新密码">
        <el-input type="password" v-model="passwordForm.newPassword" autocomplete="new-password"></el-input>
      </el-form-item>
      <el-form-item label="确认新密码">
        <el-input type="password" v-model="passwordForm.confirmPassword" autocomplete="new-password"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitPasswordChange">提交</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue';
import { ElMessage } from 'element-plus';

const passwordForm = reactive({
  oldPassword: '',
  newPassword: '',
  confirmPassword: ''
});

const passwordFormRef = ref(null);

const submitPasswordChange = () => {
  if (passwordForm.newPassword !== passwordForm.confirmPassword) {
    ElMessage.error('新密码和确认密码不匹配');
    return;
  }
  // 这里添加实际的密码更新逻辑，例如使用 axios 发送请求

  console.log('提交的密码表单数据:', passwordForm);
  // 假设密码更新成功
  ElMessage.success('密码更新成功');
};
</script>

<style scoped>
.password-change-card {
  max-width: 400px;
  max-height: 100%;
  margin: 20px auto;
}

.el-form-item {
  margin-bottom: 20px;
}
</style>
