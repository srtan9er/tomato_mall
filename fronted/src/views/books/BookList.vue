<template>
  <div class="book-list">
    <h1>图书列表</h1>
    <div class="book-grid">
      <BookCard
        v-for="book in books"
        :key="book.id"
        :book="book"
        :from-mall="false"
      />
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import BookCard from '../../components/BookCard.vue'

const books = ref([])

onMounted(async () => {
  // TODO: 从后端获取图书列表
  // 模拟数据
  books.value = [
    {
      id: 1,
      title: '深入理解计算机系统',
      price: 89.00,
      cover: '/images/book1.jpg',
      store: {
        name: '优质图书店'
      }
    },
    {
      id: 2,
      title: '算法导论',
      price: 78.00,
      cover: '/images/book2.jpg',
      store: {
        name: '计算机书店'
      }
    }
  ]
})
</script>

<style scoped>
.book-list {
  padding: 20px;
  max-width: 1200px;
  margin: 0 auto;
}

h1 {
  color: #2c3e50;
  margin-bottom: 30px;
}

.book-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: 20px;
}
</style>