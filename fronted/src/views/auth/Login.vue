<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const username = ref('')
const password = ref('')
const errorMessage = ref('')

const handleLogin = () => {
  // 临时验证逻辑
  if (username.value === 'stranger' && password.value === '123456') {
    localStorage.setItem('token', 'demo-token')
    localStorage.setItem('username', username.value)
    router.push('/auth/profile')
  } else {
    errorMessage.value = '用户名或密码错误'
  }
}
</script>

<template>
  <div class="login-container">
    <h2>用户登录</h2>
    <form @submit.prevent="handleLogin">
      <div class="form-group">
        <input v-model="username" type="text" placeholder="用户名">
      </div>
      <div class="form-group">
        <input v-model="password" type="password" placeholder="密码">
      </div>
      <div v-if="errorMessage" class="error-message">
        {{ errorMessage }}
      </div>
      <button type="submit">登录</button>
      <div class="register-link">
        还没有账号？<router-link to="/auth/register">立即注册</router-link>
      </div>
    </form>
  </div>
</template>

<style scoped>
.login-container {
  max-width: 400px;
  margin: 40px auto;
  padding: 20px;
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 12px rgba(0,0,0,0.1);
}

.form-group {
  margin-bottom: 15px;
}

input {
  width: 100%;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

button {
  width: 100%;
  padding: 12px;
  background: #42b983;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
}

button:hover {
  background: #3aa876;
}

.error-message {
  color: #e74c3c;
  margin-bottom: 15px;
  text-align: center;
}

.register-link {
  text-align: center;
  margin-top: 15px;
}

.register-link a {
  color: #42b983;
  text-decoration: none;
}
</style>