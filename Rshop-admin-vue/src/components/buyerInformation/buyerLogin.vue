<script setup>

</script>

<template>
  <div>
    <h2>登录</h2>
    <label>用户名</label><input type="text" v-model="username">
    <label>密码</label><input type="password" v-model="password">
    <button @click="login">登录</button>
  </div>
</template>

<script>
export default {
  data() {
    return { username: '', password: '' };
  },
  methods: {
    login() {
      alert("登录成功！用户名: " + this.username);
    }
  }
};
</script>

<style scoped>

</style>
