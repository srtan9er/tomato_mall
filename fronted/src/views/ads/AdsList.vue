<script setup>
import { ref } from 'vue'

const ads = ref([
  {
    id: 1,
    title: '新书特惠',
    image: 'https://via.placeholder.com/800x200',
    description: '新书上架，全场8折！'
  },
  {
    id: 2,
    title: '畅销榜单',
    image: 'https://via.placeholder.com/800x200',
    description: '本周最受欢迎的图书'
  }
])
</script>

<template>
  <div class="ads-container">
    <h2>活动广告</h2>
    <div class="ads-list">
      <div v-for="ad in ads" :key="ad.id" class="ad-card">
        <img :src="ad.image" :alt="ad.title">
        <div class="ad-content">
          <h3>{{ ad.title }}</h3>
          <p>{{ ad.description }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.ads-container {
  padding: 20px;
}

.ads-list {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.ad-card {
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 8px rgba(0,0,0,0.1);
}

.ad-card img {
  width: 100%;
  height: auto;
}

.ad-content {
  padding: 15px;
}
</style>