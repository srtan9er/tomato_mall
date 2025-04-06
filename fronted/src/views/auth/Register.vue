<script setup>
import {computed, ref} from 'vue'
import { useRouter } from 'vue-router'
import {userRegister} from "../../api/user.js";

const router = useRouter()
const formData = ref({
  username: '',
  password: '',
  confirmPassword: '',
  email: ''
})

// 输入框值（需要在前端拦截不合法输入：是否为空+额外规则）
const name = ref('')
const identity = ref('')
const tel = ref('')
const address = ref('')
const password = ref('')
const confirmPassword = ref('')
const isPasswordIdentical = computed(() => password.value === confirmPassword.value)
//对于商家用户，还需要在注册时选择所属商店，从而传入storeId。但由于Lab2才会开发商店模块，所以这里暂且设置唯一一个Id为1的商店1，待Lab2完善
const storeId = ref()
const email = ref('')
const avatar = ref('')

const handleRegister = () => {
  userRegister({
    role: identity.value,
    name: name.value,
    phone: tel.value,
    password: password.value,
    storeId: storeId.value,
    address: address.value,
    createTime: Date.now(),
    email: email.value,
    avatar: avatar.value,
  })
  router.push('/auth/login')
}
</script>

<template>
  <div class="register-container">
    <h2>用户注册</h2>
    <form @submit.prevent="handleRegister">
      <div class="form-group">
        <input v-model="tel" type="text" placeholder="手机号">
      </div>
      <div class="form-group">
        <input v-model="name" type="text" placeholder="用户名">
      </div>
      <el-form-item>
        <label for="identity"></label>
        <el-select id="identity"
                 v-model="identity"
                 placeholder="请选择身份"
                 style="width: 100%;"
        >
          <el-option value="CUSTOMER" label="顾客"/>
          <el-option value="STAFF" label="商家"/>
        </el-select>
      </el-form-item>
      <div class="form-group">
        <input v-model="password" type="password" placeholder="密码">
      </div>
      <label v-if="!isPasswordIdentical" class="error-warn">密码不一致</label>
      <div class="form-group">
        <input v-model="confirmPassword" type="password" placeholder="确认密码">
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

.error-warn {
  color: #ff2f00;
}

</style>