<script setup>
import { ref } from 'vue'

const cartItems = ref([
  {
    id: 1,
    bookId: 1,
    title: '三体',
    price: 99.00,
    quantity: 1
  },
  {
    id: 2,
    bookId: 2,
    title: '活着',
    price: 39.00,
    quantity: 1
  }
])
</script>

<template>
  <div class="cart-container">
    <h2>购物车</h2>
    <div class="cart-items">
      <div v-for="item in cartItems" :key="item.id" class="cart-item">
        <div class="item-info">
          <!-- 修改书名为可点击的链接 -->
          <router-link :to="`/bookstore/book/${item.bookId}`" class="book-title">
            {{ item.title }}
          </router-link>
          <span class="item-price">¥{{ item.price }}</span>
        </div>
        <!-- ... 其他购物车项目内容 ... -->
      </div>
    </div>
  </div>
</template>

<style scoped>
.book-title {
  color: #42b983;
  text-decoration: none;
  font-weight: 500;
}

.book-title:hover {
  text-decoration: underline;
}
</style>