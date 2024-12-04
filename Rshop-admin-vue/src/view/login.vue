<template>
  <div class="login-container">
    <ShopLogo style="width: 300px"></ShopLogo>

    <!-- Card -->
    <div class="card" style="height: 400px;width: 500px;">
      <select v-model="role" style="width: 80px;height: 40px;fron-size:24px">
        <option value="user">User</option>
        <option value="admin">Admin</option>
      </select>

      <form v-if="role === 'user'">
        <input type="text" v-model="user.username" placeholder="Username">
        <input type="password" v-model="user.password" placeholder="Password">
        <button @click="login('user')" style="background-color: #83b3f3">用户登录</button>
        <!-- Registration link -->
        <p v-if="role === 'user'" style="margin-top: 20px;color: #174e90"><a href="#" @click="showRegistration = true">注册账号</a></p>
      </form>

      <form v-if="role === 'admin'">
        <input type="text" v-model="admin.username" placeholder="Username">
        <input type="password" v-model="admin.password" placeholder="Password">
        <button @click="login('admin')" style="background-color: #83b3f3">管理员登录</button>
      </form>

      <!-- Registration Modal -->
      <div v-if="showRegistration" class="registration-modal">
        <h3>Registration</h3>
        <!-- Registration Form -->
        <input type="text" v-model="newUser.username" placeholder="Username">
        <input type="email" v-model="newUser.email" placeholder="Email">
        <input type="password" v-model="newUser.password" placeholder="Password">
        <button @click="registerUser">Register</button>
        <button @click="showRegistration = false">Close</button>
      </div>
    </div>
  </div>
</template>

<script>
import ShopLogo from "@/components/Shop-Logo.vue";

export default {
  components: {ShopLogo},
  data() {
    return {
      role: 'user',
      user: {
        username: '',
        password: ''
      },
      admin: {
        username: '',
        password: ''
      },
      newUser: {
        username: '',
        email: '',
        password: ''
      },
      showRegistration: false
    };
  },
  methods: {
    login(role) {
      const formData = role === 'user' ? this.user : this.admin;
      console.log('Logging in as:', role);
      console.log('Form Data:', formData);
      // Perform the actual login logic here, e.g., using fetch or XMLHttpRequest
    },
    registerUser() {
      console.log('Registering new user:', this.newUser);
      // Perform the actual registration logic here, e.g., using fetch or XMLHttpRequest
    }
  }
};
</script>

<style scoped>
.login-container {
  background-image: url('https://tse1-mm.cn.bing.net/th/id/OIP-C.KknZ82d9g6mi2ISfFEK7IgHaEK?w=309&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7');
  background-size: cover; /* 覆盖整个页面 */
  background-position: center; /* 居中显示 */
  background-repeat: no-repeat; /* 不重复 */
  font-family: Arial, sans-serif;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  margin: 0;
  background-color: #f4f4f4;
}
.logo {
  position: absolute;
  top: 20px;
  left: 20px;
  width: 100px;
}
.card {
  background: white;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  text-align: center;
  width: 300px;
}
input[type="text"], input[type="password"], input[type="email"] {
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
  background-color: #5cb85c;
  color: white;
  cursor: pointer;
}
button:hover {
  background-color: #4cae4c;
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
