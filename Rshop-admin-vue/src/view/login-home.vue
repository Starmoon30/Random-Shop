<template>
  <ShopLogo style="width: 300px"></ShopLogo>
  <div class="login-container">
    <!-- Card -->
    <div class="card" style="height: 400px;width: 500px;">
      <select v-model="role" style="width: 80px;height: 40px;fron-size:24px">
        <option value="user">User</option>
        <option value="admin">Admin</option>
      </select>

      <form v-if="role === 'user'">
        <input type="text" v-model="user.UAccount" placeholder="Username">
        <input type="password" v-model="user.UPassword" placeholder="Password">
        <button @click.prevent="login('user')" style="background-color: #83b3f3">用户登录</button>
        <p v-if="role === 'user'" style="margin-top: 20px;color: #174e90">
          <a href="#" @click="showRegistration = true">注册账号</a>
        </p>
      </form>

      <form v-if="role === 'admin'">
        <input type="text" v-model="admin.UAccount" placeholder="Username">
        <input type="password" v-model="admin.UPassword" placeholder="Password">
        <button @click.prevent="login('admin')" style="background-color: #83b3f3">管理员登录</button>
      </form>

      <!-- Registration Modal -->
      <div v-if="showRegistration" class="registration-modal">
        <h3>Registration</h3>
        <input type="text" v-model="newUser.username" placeholder="Username">
        <input type="password" v-model="newUser.password" placeholder="Password">
        <input type="email" v-model="newUser.email" placeholder="Email">
        <input type="text" v-model="newUser.address" placeholder="Address">
        <button @click="registerUser" style="margin: 5px">提交</button>
        <button @click="showRegistration = false" style="margin: 5px">返回</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'; // 引入 Axios
import ShopLogo from "@/components/Shop-Logo.vue";

export default {
  components: { ShopLogo },
  data() {
    return {
      role: 'user',
      user: {
        UAccount: '',
        UPassword: ''
      },
      admin: {
        UAccount: '',
        UPassword: ''
      },
      showRegistration: false,
      newUser: {
        username: '',
        password: '',
        email: '',
        address: ''
      }
    };
  },
  methods: {
    async login(role) {
      const formData = {
        uaccount: role === 'user' ? this.user.UAccount : this.admin.UAccount,
        upassword: role === 'user' ? this.user.UPassword : this.admin.UPassword
      };

      try {
        const response = await axios.post('http://localhost:8090/user/login', formData);

        if (response.data) {
          // 根据角色跳转到不同的页面
          this.$router.push(role === 'user' ? '/BHome' : '/AHome');
        } else {
          alert('登录失败，请检查用户名和密码是否正确');
        }
      } catch (error) {
        console.error('登录请求失败:', error);
        alert('登录请求失败，错误信息: ' + error.message);
      }
    },
    registerUser() {
      console.log('Registering new user:', this.newUser);
      // 实现注册逻辑，比如使用 axios 发送 POST 请求
    }
  }
};
</script>

<style scoped>
/* 样式部分保持不变 */
.login-container {
  background-image: url('https://tse1-mm.cn.bing.net/th/id/OIP-C.KknZ82d9g6mi2ISfFEK7IgHaEK?w=309&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  font-family: Arial, sans-serif;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  margin: 0;
  background-color: #f4f4f4;
}
.card {
  background: white;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  text-align: center;
  width: 300px;
}
input[type="text"], input[type="password"], input[type="email"], input[type="address"] {
  width: 100%;
  padding: 10px;
  margin: 10px 0;
  border: 1px solid #ddd;
  border-radius: 5px;
}
button {
  width: 100%;
  padding: 10px;
  border: none;
  border-radius: 5px;
  background-color: #60bde1;
  color: white;
  cursor: pointer;
}
button:hover {
  background-color: #2dacda;
}
.registration-modal {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background: white;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  z-index: 1000;
}
</style>
