<script setup>
import { ref } from 'vue'

const books = ref([
  { id: 1, title: '三体', author: '刘慈欣', price: 99, cover: 'https://via.placeholder.com/150' },
  { id: 2, title: '活着', author: '余华', price: 39, cover: 'https://via.placeholder.com/150' },
])
</script>

<template>
  <div class="books-container">
    <h2>图书列表</h2>
    <div class="book-grid">
      <div v-for="book in books" :key="book.id" class="book-card">
        <img :src="book.cover" :alt="book.title">
        <h3>{{ book.title }}</h3>
        <p>作者：{{ book.author }}</p>
        <p class="price">¥{{ book.price }}</p>
        <router-link :to="`/books/${book.id}`" class="view-btn">查看详情</router-link>
      </div>
    </div>
  </div>
</template>

<style scoped>
.books-container {
  padding: 20px;
}

.book-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: 20px;
  padding: 20px;
}

.book-card {
  border: 1px solid #eee;
  padding: 10px;
  text-align: center;
  border-radius: 8px;
}

.book-card img {
  width: 100%;
  height: auto;
  border-radius: 4px;
}

.price {
  color: #e74c3c;
  font-weight: bold;
}

.view-btn {
  display: inline-block;
  padding: 5px 15px;
  background: #42b983;
  color: white;
  text-decoration: none;
  border-radius: 4px;
}
</style>