<script setup>
import { onMounted } from 'vue'
import gsap from 'gsap'

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
</style>