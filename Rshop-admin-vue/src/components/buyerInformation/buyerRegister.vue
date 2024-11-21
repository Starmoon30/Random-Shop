<script setup>

</script>

<template>
  <div>
    <h2>注册</h2>
    <label>用户名</label><input type="text" v-model="username">
    <label>密码</label><input type="password" v-model="password">
    <label>电话</label><input type="text" v-model="phone">
    <label>默认交易地点</label><input type="text" v-model="location">
    <button @click="register">注册</button>
  </div>
</template>

<script>
export default {
  data() {
    return { username: '', password: '', phone: '', location: '' };
  },
  methods: {
    register() {
      alert("注册成功！用户名: " + this.username);
    }
  }
};
</script>

<style scoped>

</style>
