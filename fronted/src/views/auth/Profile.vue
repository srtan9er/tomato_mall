<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const userInfo = ref({
  username: '',
  email: '',
  avatar: 'https://via.placeholder.com/150'
})

onMounted(() => {
  // 检查登录状态
  const token = localStorage.getItem('token')
  const username = localStorage.getItem('username')
  if (!token) {
    router.push('/auth/login')
    return
  }
  
  // 获取用户信息
  userInfo.value = {
    username: username || '测试用户',
    email: 'stranger@example.com',
    avatar: 'https://via.placeholder.com/150'
  }
})

const handleLogout = () => {
  localStorage.removeItem('token')
  router.push('/auth/login')
}
</script>

<template>
  <div class="profile-container">
    <div class="profile-header">
      <img :src="userInfo.avatar" alt="用户头像" class="avatar">
      <h2>{{ userInfo.username }}</h2>
    </div>
    <div class="profile-info">
      <div class="info-item">
        <label>邮箱：</label>
        <span>{{ userInfo.email }}</span>
      </div>
      <div class="actions">
        <button class="edit-btn">编辑资料</button>
        <button class="logout-btn" @click="handleLogout">退出登录</button>
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
</style>