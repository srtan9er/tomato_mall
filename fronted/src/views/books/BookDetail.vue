<template>
  <div class="book-detail">
    <div class="book-container" v-if="book">
      <div class="book-image">
        <img :src="book.cover" :alt="book.title" />
      </div>
      <div class="book-info">
        <h1>{{ book.title }}</h1>
        <p class="price">¥{{ book.price }}</p>
        <p class="description">{{ book.description }}</p>
        <div class="store-info">
          <h3>商家信息</h3>
          <p>{{ book.store?.name }}</p>
        </div>
        <div class="actions">
          <button class="add-to-cart" @click="addToCart">加入购物车</button>
          <button class="buy-now" @click="buyNow">立即购买</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'

const route = useRoute()
const router = useRouter()
const book = ref(null)

onMounted(async () => {
  // TODO: 从后端获取图书详情
  // 模拟数据
  book.value = {
    id: route.params.id,
    title: '深入理解计算机系统',
    price: 89.00,
    description: '本书从程序员的视角详细阐述计算机系统的本质概念，并展示这些概念如何实实在在地影响应用程序的正确性、性能和实用性。',
    cover: '/images/book1.jpg',
    store: {
      id: 1,
      name: '优质图书店'
    }
  }
})

const addToCart = () => {
  // TODO: 实现加入购物车功能
}

const buyNow = () => {
  router.push('/order/checkout')
}
</script>

<style scoped>
.book-detail {
  padding: 20px;
  max-width: 1200px;
  margin: 0 auto;
}

.book-container {
  display: flex;
  gap: 40px;
}

.book-image {
  flex: 0 0 400px;
}

.book-image img {
  width: 100%;
  height: auto;
  border-radius: 8px;
}

.book-info {
  flex: 1;
}

.price {
  font-size: 24px;
  color: #f56c6c;
  margin: 20px 0;
}

.description {
  line-height: 1.6;
  color: #666;
  margin-bottom: 20px;
}

.store-info {
  margin: 20px 0;
  padding: 15px;
  background: #f8f9fa;
  border-radius: 8px;
}

.actions {
  margin-top: 30px;
  display: flex;
  gap: 20px;
}

button {
  padding: 12px 24px;
  border-radius: 4px;
  border: none;
  cursor: pointer;
  font-size: 16px;
}

.add-to-cart {
  background: #fff;
  border: 1px solid #409eff;
  color: #409eff;
}

.buy-now {
  background: #409eff;
  color: white;
}

button:hover {
  opacity: 0.8;
}
</style>