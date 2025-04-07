<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import {parseTime} from "element-plus/es/components/time-select/src/utils";
import {userInfo} from "../../api/user.js";

const router = useRouter()
// const userInfo = ref({
//   username: '',
//   email: '',
//   avatar: 'https://srtanger-bucket-0.oss-cn-shanghai.aliyuncs.com/%E5%A4%B4%E5%83%8F0.jpg'
// })
const showEditDialog = ref(false)
const editForm = ref({
  username: '',
  email: ''
})

const name = ref('')
const address = ref('')
const tel = ref('')
const regTime = ref()
const avatar = ref('')
const email = ref('')
const role = ref('')
const storeName = ref()
const userName = ref('')

function getUserInfo() {
  userInfo().then(res => {
    console.log(res);
    name.value = res.data.result.name
    tel.value = res.data.result.phone
    storeName.value = res.data.result.storeId
    address.value = res.data.result.address
    regTime.value = parseTime(res.data.result.createTime)
    avatar.value = res.data.result.avatar || 'https://nju-seek2025.oss-cn-shanghai.aliyuncs.com/InitAvatar.jpg'
    email.value = res.data.result.email
    role.value = res.data.result.role
    userName.value = res.data.result.username
  })
}
getUserInfo()

onMounted( () => {
  // 检查登录状态
  const token = localStorage.getItem('token')
  const username = localStorage.getItem('username')
  if (!token) {
    router.push('/auth/login')
  }
})

const handleLogout = () => {
  localStorage.removeItem('token')
  router.push('/auth/login')
}

const openEditDialog = () => {
  editForm.value = {
    username: userInfo.value.username,
    email: userInfo.value.email
  }
  showEditDialog.value = true
}

const handleSave = () => {
  // 这里添加保存逻辑，实际项目中应该调用API
  userInfo.value = {
    ...userInfo.value,
    ...editForm.value
  }
  showEditDialog.value = false
}
</script>

<template>
  <div class="profile-container">
    <div class="profile-header">
      <img :src="avatar" alt="用户头像" class="avatar">
      <h2>{{ name }}</h2>
    </div>
    <div class="profile-info">
      <div class="info-item">
        <label>电话号码：</label>
        <span>{{ tel }}</span>
      </div>
      <div class="info-item">
        <label>身份：</label>
        <span>{{ role }}</span>
      </div>
      <div class="info-item">
        <label>收货地址：</label>
        <span>{{ address }}</span>
      </div>
      <div class="info-item">
        <label>邮箱：</label>
        <span>{{ email }}</span>
      </div>
      <div class="info-item">
        <label>注册时间：</label>
        <span>{{ regTime }}</span>
      </div>
      <div class="actions">
        <button class="edit-btn" @click="openEditDialog">编辑资料</button>
        <button class="logout-btn" @click="handleLogout">退出登录</button>
      </div>

      <!-- 编辑资料弹窗 -->
      <div v-if="showEditDialog" class="edit-dialog">
        <div class="dialog-content">
          <h3>编辑个人资料</h3>
          <div class="form-item">
            <label>用户名：</label>
            <input v-model="editForm.username" type="text">
          </div>
          <div class="form-item">
            <label>邮箱：</label>
            <input v-model="editForm.email" type="email">
          </div>
          <div class="dialog-actions">
            <button class="cancel-btn" @click="showEditDialog = false">取消</button>
            <button class="save-btn" @click="handleSave">保存</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.profile-container {
  max-width: 600px;
  margin: 40px auto;
  padding: 20px;
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 12px rgba(0,0,0,0.1);
}

.profile-header {
  text-align: center;
  margin-bottom: 30px;
}

.avatar {
  width: 150px;
  height: 150px;
  border-radius: 50%;
  margin-bottom: 15px;
  border: 4px solid #f0f0f0;
}

.info-item {
  padding: 15px;
  border-bottom: 1px solid #eee;
}

.info-item label {
  font-weight: bold;
  margin-right: 10px;
  color: #666;
}

.actions {
  margin-top: 20px;
  display: flex;
  gap: 10px;
  justify-content: center;
}

.edit-btn, .logout-btn {
  padding: 8px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
}

.edit-btn {
  background: #42b983;
  color: white;
}

.logout-btn {
  background: #e74c3c;
  color: white;
}

.edit-btn:hover, .logout-btn:hover {
  opacity: 0.9;
}

.edit-dialog {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.dialog-content {
  background: white;
  padding: 20px;
  border-radius: 8px;
  width: 400px;
}

.form-item {
  margin: 15px 0;
}

.form-item label {
  display: block;
  margin-bottom: 5px;
  color: #666;
}

.form-item input {
  width: 100%;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

.dialog-actions {
  margin-top: 20px;
  display: flex;
  justify-content: flex-end;
  gap: 10px;
}

.cancel-btn, .save-btn {
  padding: 8px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.cancel-btn {
  background: #ddd;
}

.save-btn {
  background: #42b983;
  color: white;
}

.cancel-btn:hover, .save-btn:hover {
  opacity: 0.9;
}
</style>