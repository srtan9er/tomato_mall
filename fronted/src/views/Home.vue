<script setup>
import { onMounted } from 'vue'
import gsap from 'gsap'
import { useRouter } from 'vue-router'

const router = useRouter()

const goToMall = () => {
  router.push('/mall')
}

onMounted(() => {
  gsap.from('.welcome-text', {
    y: -50,
    opacity: 0,
    duration: 1,
    ease: 'power2.out'
  })
})
</script>

<template>
  <div class="home">
    <h1 class="welcome-text">欢迎来到番茄商城</h1>
    <div class="features">
      <div class="feature-card">
        <h3>海量图书</h3>
        <p>丰富的图书资源，满足您的阅读需求</p>
      </div>
      <div class="feature-card">
        <h3>优惠活动</h3>
        <p>定期优惠活动，超值购物体验</p>
      </div>
    </div>
    <button class="mall-button" @click="goToMall">
      浏览商城
    </button>
  </div>
</template>

<style scoped>
.home {
  padding: 20px;
  text-align: center;
}

.welcome-text {
  color: #2c3e50;
  margin-bottom: 40px;
}

.features {
  display: flex;
  justify-content: center;
  gap: 20px;
}

.feature-card {
  background: #f8f9fa;
  padding: 20px;
  border-radius: 8px;
  width: 250px;
}

.mall-button {
  margin-top: 30px;
  padding: 12px 24px;
  font-size: 16px;
  background-color: #42b983;
  color: black;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.mall-button:hover {
  background-color: #3aa876;
}
</style>