<script setup>
import { ref, onMounted } from 'vue';
import gsap from 'gsap';

const leftHalf = ref(null);
const rightHalf = ref(null);
const container = ref(null);

onMounted(() => {
  const tl = gsap.timeline({
    delay: 0.2, // 延迟0.5秒开始动画
  });

  tl.to(leftHalf.value, {
    x: '-100%',
    duration: 1.0,
    ease: "power2.inOut"
  })
  .to(rightHalf.value, {
    x: '100%',
    duration: 1.0,
    ease: "power2.inOut"
  }, "<")
  .to(container.value, {
    autoAlpha: 0,
    duration: 0.5
  });
});
</script>

<template>
  <div ref="container" class="tomato-container">
    <div ref="leftHalf" class="tomato-half left"></div>
    <div ref="rightHalf" class="tomato-half right"></div>
  </div>
</template>

<style scoped>
.tomato-container {
  position: relative;
  width: 100%;
  height: 100vh;
  overflow: hidden;
}

.tomato-half {
  position: absolute;
  top: 0;
  width: 50%;
  height: 100%;
  background-size: cover;
  background-repeat: no-repeat;
}

.left {
  left: 0;
  background-image: url('/src/assets/tomato-left.png');
  background-position: right center;
}

.right {
  right: 0;
  background-image: url('/src/assets/tomato-right.png');
  background-position: left center;
}
</style>