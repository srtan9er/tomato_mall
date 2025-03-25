<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const formData = ref({
  username: '',
  password: '',
  confirmPassword: '',
  email: ''
})

const handleRegister = () => {
  router.push('/auth/login')
}
</script>

<template>
  <div class="register-container">
    <h2>用户注册</h2>
    <form @submit.prevent="handleRegister">
      <div class="form-group">
        <input v-model="formData.username" type="text" placeholder="用户名">
      </div>
      <div class="form-group">
        <input v-model="formData.password" type="password" placeholder="密码">
      </div>
      <div class="form-group">
        <input v-model="formData.confirmPassword" type="password" placeholder="确认密码">
      </div>
      <div class="form-group">
        <input v-model="formData.email" type="email" placeholder="电子邮箱">
      </div>
      <button type="submit">注册</button>
    </form>
  </div>
</template>

<style scoped>
.register-container {
  max-width: 400px;
  margin: 40px auto;
  padding: 20px;
}

.form-group {
  margin-bottom: 15px;
}

input {
  width: 100%;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

button {
  width: 100%;
  padding: 10px;
  background: #42b983;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
</style>