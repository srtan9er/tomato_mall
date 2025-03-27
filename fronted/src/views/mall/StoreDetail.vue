<template>
  <div class="store-detail">
    <div class="store-header">
      <div class="store-info">
        <h1>{{ store.name }}</h1>
        <div class="store-stats">
          <span>商品数量: {{ store.bookCount }}</span>
          <span>店铺评分: {{ store.rating }}</span>
        </div>
        <p class="store-description">{{ store.description }}</p>
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
  background: #f8f9fa;
  padding: 30px;
  border-radius: 8px;
  margin-bottom: 30px;
}

.store-info h1 {
  margin: 0 0 15px 0;
  color: #2c3e50;
}

.store-stats {
  display: flex;
  gap: 20px;
  margin-bottom: 15px;
}

.store-stats span {
  color: #666;
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