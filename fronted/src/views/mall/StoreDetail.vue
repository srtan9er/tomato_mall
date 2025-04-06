<template>
  <div class="store-detail">
    <div class="store-header">
      <div class="store-info">
        <img :src="store.logo" :alt="store.name" class="store-logo">
        <div class="store-text">
          <h1>{{ store.name }}</h1>
          <div class="store-stats">
            <span>商品数量: {{ store.bookCount }}</span>
            <span>店铺评分: {{ store.rating }}</span>
          </div>
          <p class="store-description">{{ store.description }}</p>
        </div>
      </div>
    </div>

    <div class="store-books">
      <h2>店铺图书</h2>
      <div class="book-grid">
        <BookCard
          v-for="book in store.books"
          :key="book.id"
          :book="book"
          :from-mall="true"
        />
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'  // 添加 useRouter
import BookCard from '../../components/BookCard.vue'

const route = useRoute()
const router = useRouter()  // 正确初始化 router
const store = ref({})

onMounted(async () => {
  // TODO: 从后端获取商家详情
  // 模拟数据
  store.value = {
    id: route.params.id,
    name: '优质图书店',
    description: '专注优质图书销售，提供最好的阅读体验',
    bookCount: 100,
    rating: 4.8,
    logo: 'https://srtanger-bucket-0.oss-cn-shanghai.aliyuncs.com/%E5%A4%B4%E5%83%8F0.jpg',
    books: [
      {
        id: 1,
        title: '深入理解计算机系统',
        price: 89.00,
        cover: '/images/book1.jpg'
      },
      {
        id: 2,
        title: '算法导论',
        price: 78.00,
        cover: '/images/book2.jpg'
      },
      // 更多图书...
    ]
  }
})
</script>

<style scoped>
.store-detail {
  padding: 20px;
  max-width: 1200px;
  margin: 0 auto;
}

.store-header {
  background: rgba(248, 249, 250, 0.5);
  backdrop-filter: blur(10px);
  padding: 30px;
  border-radius: 8px;
  margin-bottom: 30px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.08);
}

.store-info {
  display: flex;
  gap: 30px;
  align-items: flex-start;
}

.store-logo {
  width: 120px;
  height: 120px;
  border-radius: 50%;
  object-fit: cover;
  border: 3px solid rgba(255, 255, 255, 0.8);
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.store-text {
  flex: 1;
}

.store-stats {
  display: flex;
  gap: 20px;
  margin-bottom: 15px;
}

.store-stats span {
  color: #666;
  background: rgba(255, 255, 255, 0.6);
  padding: 4px 12px;
  border-radius: 15px;
}

.store-description {
  color: #666;
  line-height: 1.6;
}

.store-books h2 {
  margin-bottom: 20px;
  color: #2c3e50;
}

.book-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: 20px;
}
</style>