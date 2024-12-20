<script setup>
const logout = () => {
  history.back();
};
</script>

<template>
  <header class="header">
    <div class="header-left">
      <h3 class="welcome-message">欢迎浏览随意商城！</h3>
    </div>
    <div class="header-right">
      <div class="user-management" style="padding-right: 20px">
        <img class="avatar" src="https://tse2-mm.cn.bing.net/th/id/OIP-C.qYeM02sjAeO7kgvR6TDA8AAAAA?w=211&h=211&c=7&r=0&o=5&dpr=1.5&pid=1.7" alt="Avatar">
        <a href="/user-info" class="user-info-link" style="color: #093b7f">用户信息</a>
        <button @click="logout" class="logout-button" style="background-color: #0c4388">退出登录</button>
      </div>
    </div>
  </header>
</template>

<style scoped>
.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
  height: 60px;
  background-color: #f5f5f5;
}

.header-left {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
  font-family: "宋体", SimSun, sans-serif;
}

.welcome-message {
  margin: 0;
}

.header-right {
  display: flex;
  align-items: center;
}

.user-management {
  display: flex;
  gap: 10px;
  align-items: center;
}

.avatar {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  object-fit: cover;
}

.user-info-link {
  text-decoration: none;
  color: black;
  margin-right: 10px;
}

.logout-button {
  padding: 5px 10px;
  background-color: #f44336;
  color: white;
  border: none;
  cursor: pointer;
}

.logout-button:hover {
  background-color: #d32f2f;
}
</style>
