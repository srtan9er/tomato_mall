<script setup>
import { onMounted, ref } from 'vue'
import { fadeIn, slideFromBottom } from '../utils/animation'

const elementRef = ref(null)

onMounted(() => {
  // 使用淡入动画
//   fadeIn(elementRef.value)
  
  // 或使用滑入动画
  slideFromBottom(elementRef.value)
})
</script>

<template>
  <div ref="elementRef" class="gsap-reveal">
    这是一个动画元素
  </div>
</template>

<style scoped>
.gsap-reveal {
  padding: 20px;
  margin: 20px;
  background-color: #f0f0f0;
  border-radius: 8px;
}
</style>