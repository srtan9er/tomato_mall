<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';

const props = defineProps({
  storeId: {
    type: [String, Number],
    required: true
  }
});

const router = useRouter();
const books = ref([
  {
    id: 1,
    title: '三体',
    author: '刘慈欣',
    price: 99.00,
    cover: '/src/assets/book-covers/three-body.jpg',
    description: '科幻小说代表作，讲述人类文明与三体文明的恢宏故事。'
  },
  {
    id: 2,
    title: '活着',
    author: '余华',
    price: 39.00,
    cover: '/src/assets/book-covers/to-live.jpg',
    description: '讲述了农村人福贵悲惨的人生遭遇。'
  },
  // 更多书籍...
]);

const addToCart = (book) => {
  // 添加到购物车的逻辑
  console.log('添加到购物车:', book);
};

const viewBookDetail = (bookId) => {
  router.push(`/bookstore/book/${bookId}`);
};
</script>

<template>
  <div class="book-list">
    <div class="book-grid">
      <div v-for="book in books" :key="book.id" class="book-card">
        <div class="book-cover" @click="viewBookDetail(book.id)">
          <img :src="book.cover" :alt="book.title">
        </div>
        <div class="book-info">
          <h3>{{ book.title }}</h3>
          <p class="author">{{ book.author }}</p>
          <p class="price">¥{{ book.price.toFixed(2) }}</p>
          <p class="description">{{ book.description }}</p>
          <button class="add-to-cart" @click="addToCart(book)">
            加入购物车
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.book-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: 20px;
  padding: 20px 0;
}

.book-card {
  border: 1px solid #eee;
  border-radius: 8px;
  overflow: hidden;
  transition: transform 0.3s;
}

.book-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 5px 15px rgba(0,0,0,0.1);
}

.book-cover {
  height: 250px;
  overflow: hidden;
  cursor: pointer;
}

.book-cover img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.book-info {
  padding: 15px;
}

.book-info h3 {
  margin: 0 0 10px;
  font-size: 16px;
}

.author {
  color: #666;
  font-size: 14px;
  margin: 5px 0;
}

.price {
  color: #e74c3c;
  font-weight: bold;
  margin: 5px 0;
}

.description {
  font-size: 13px;
  color: #666;
  margin: 10px 0;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}

.add-to-cart {
  width: 100%;
  padding: 8px;
  background: #42b983;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background 0.3s;
}

.add-to-cart:hover {
  background: #3aa876;
}
</style>