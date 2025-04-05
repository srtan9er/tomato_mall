<script setup>
import { onMounted, ref } from 'vue'
import gsap from 'gsap'
import { useRouter } from 'vue-router'
import TomatoSplitAnimation from '../components/TomatoSplitAnimation.vue'

const router = useRouter()
const contentRef = ref(null)

const goToMall = () => {
  router.push('/mall')
}

onMounted(() => {
  // 初始隐藏内容
  gsap.set(contentRef.value, {
    opacity: 0,
    y: 50
  })

  // 2秒后开始显示内容（与番茄动画同步）
  gsap.to(contentRef.value, {
    opacity: 1,
    y: 0,
    duration: 1.5,
    delay: 1.0,
    ease: 'power2.out'
  })
})
</script>

<template>
  <div class="home">
    <TomatoSplitAnimation />
    <div ref="contentRef" class="content">
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
  </div>
</template>

<style scoped>
.home {
  padding: 20px;
  text-align: center;
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.content {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 100%;
  z-index: 2;
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