<script setup>
import { ref, onMounted } from 'vue';
import gsap from 'gsap';

const tomato = ref(null);
const container = ref(null);

onMounted(() => {
  const tl = gsap.timeline({
    delay: 0.2,
  });

  tl.to(tomato.value, {
    scale: 2.5,
    opacity: 0,
    duration: 1.0,
    ease: "power2.inOut"
  })
  .to(container.value, {
    autoAlpha: 0,
    duration: 0.5
  });
});
</script>

<template>
  <div ref="container" class="tomato-container">
    <div ref="tomato" class="tomato"></div>
  </div>
</template>

<style scoped>
.tomato-container {
  position: relative;
  width: 100%;
  height: 100vh;
  overflow: hidden;
}

.tomato {
  position: absolute;
  top: 40%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 300px;
  height: 300px;
  background-image: url('../assets/tomoto.png');
  background-size: contain;
  background-position: center;
  background-repeat: no-repeat;
}
</style>