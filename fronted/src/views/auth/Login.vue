<script setup>
import {computed, ref} from 'vue'
import { useRouter } from 'vue-router'
import {userInfo, userLogin} from "../../api/user.js";
import {ElMessage} from "element-plus";

const router = useRouter()
const tel = ref('')
const password = ref('')
const errorMessage = ref('')

// 电话号码是否为空
const hasTelInput = computed(() => tel.value !== '')
// 密码是否为空
const hasPasswordInput = computed(() => password.value !== '')
// 电话号码的规则
const chinaMobileRegex = /^1(3[0-9]|4[579]|5[0-35-9]|6[2567]|7[0-8]|8[0-9]|9[1289])\d{8}$/
const telLegal = computed(() => chinaMobileRegex.test(tel.value))
// 密码不设置特殊规则
// 登录按钮可用性
const loginDisabled = computed(() => {
  return !(hasTelInput.value && telLegal.value && hasPasswordInput.value)
})

function handleLogin() {
  userLogin({
    phone: tel.value,
    password: password.value
  }).then(res => {
    console.log(res)
    if (res.data.code === '000') {
      ElMessage({
        message: "登录成功！",
        type: 'success',
        center: true,
      })
      const token = res.data.result
      sessionStorage.setItem('token', token)

      userInfo().then(res => {
        sessionStorage.setItem('name', res.data.result.name)
        sessionStorage.setItem('role', res.data.result.role)
        router.push({path: "/Register"})
      })
    } else if (res.data.code === '400') {
      ElMessage({
        message: res.data.msg,
        type: 'error',
        center: true,
      })
      password.value = ''
    }
  })
}
</script>

<template>
  <div class="login-container">
    <h2>用户登录</h2>
    <el-form>
      <div class="form-group">
        <input v-model="tel" type="text" placeholder="手机号">
      </div>
      <div class="form-group">
        <input v-model="password" type="password" placeholder="密码">
      </div>
      <div v-if="errorMessage" class="error-message">
        {{ errorMessage }}
      </div>
      <el-button @click.prevent="handleLogin" :disabled="loginDisabled" type="primary">
        登录
      </el-button>
      <div class="register-link">
        还没有账号？<router-link to="/auth/register">立即注册</router-link>
      </div>
    </el-form>
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