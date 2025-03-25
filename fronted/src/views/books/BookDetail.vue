<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const book = ref({
  id: route.params.id,
  title: '三体',
  author: '刘慈欣',
  price: 99,
  description: '这是一本科幻小说...',
  cover: 'https://via.placeholder.com/300'
})
</script>

<template>
  <div class="book-detail">
    <div class="book-content">
      <div class="book-image">
        <img :src="book.cover" :alt="book.title">
      </div>
      <div class="book-info">
        <h1>{{ book.title }}</h1>
        <p class="author">作者：{{ book.author }}</p>
        <p class="price">¥{{ book.price }}</p>
        <p class="description">{{ book.description }}</p>
        <button class="add-to-cart">加入购物车</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.book-detail {
  padding: 20px;
  max-width: 1200px;
  margin: 0 auto;
}

.book-content {
  display: flex;
  gap: 40px;
}

.book-image {
  flex: 0 0 300px;
}

.book-image img {
  width: 100%;
  border-radius: 8px;
}

.book-info {
  flex: 1;
}

.price {
  color: #e74c3c;
  font-size: 24px;
  font-weight: bold;
}

.add-to-cart {
  padding: 10px 20px;
  background: #42b983;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin-top: 20px;
}
</style>