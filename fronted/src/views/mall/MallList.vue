<template>
  <div class="mall-list">
    <h1>图书商城</h1>
    <div class="store-list">
      <div v-for="store in stores" :key="store.id" class="store-card">
        <div class="store-header">
          <div class="store-info">
            <h2>{{ store.name }}</h2>
            <p>{{ store.description }}</p>
          </div>
          <button class="store-detail-btn" @click="router.push(`/mall/store/${store.id}`)">
            查看店铺详情
          </button>
        </div>
        <div class="book-list">
          <BookCard
            v-for="book in store.books.slice(0, 4)"
            :key="book.id"
            :book="book"
            :from-mall="true"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import BookCard from '../../components/BookCard.vue'

const router = useRouter()
const stores = ref([])

onMounted(async () => {
  // TODO: 从后端获取商城数据
  stores.value = [
    {
      id: 1,
      name: '优质图书店',
      description: '专注优质图书销售',
      books: [
        { id: 1, title: '深入理解计算机系统', price: 89, cover: '/images/book1.jpg' },
        { id: 2, title: '算法导论', price: 78, cover: '/images/book2.jpg' },
      ]
    }
  ]
})
</script>

<style scoped>
.mall-list {
  padding: 20px;
  max-width: 1200px;
  margin: 0 auto;
}

.store-card {
  border: 1px solid #eee;
  margin-bottom: 30px;
  padding: 20px;
  border-radius: 8px;
}

.store-header {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  margin-bottom: 20px;
}

.store-info h2 {
  margin: 0 0 10px 0;
  color: #2c3e50;
}

.store-info p {
  color: #666;
  margin: 0;
}

.store-detail-btn {
  padding: 8px 16px;
  background: #42b983;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: opacity 0.2s;
}

.store-detail-btn:hover {
  opacity: 0.8;
}

.book-list {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: 20px;
}
</style>