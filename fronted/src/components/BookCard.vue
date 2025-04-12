<template>
  <div class="book-card">
    <img :src="book.cover" :alt="book.title" class="book-cover">
    <div class="book-info">
      <h3 class="book-title">{{ book.title }}</h3>
      <p class="book-price">￥{{ book.price }}</p>
      <p class="book-store" v-if="book.store">{{ book.store.name }}</p>
      <div class="button-group">
        <button class="detail-button" @click="goToDetail">查看详情</button>
        <button v-if="editMode" class="delete-button" @click="$emit('delete', book.id)">删除</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'

const props = defineProps({
  book: {
    type: Object,
    required: true
  },
  fromMall: {
    type: Boolean,
    default: false
  },
  editMode: {
    type: Boolean,
    default: false
  }
})

defineEmits(['delete'])

const router = useRouter()

const goToDetail = () => {
  router.push(`/books/${props.book.id}`)
}
</script>

<style scoped>
.book-card {
  border-radius: 8px;
  overflow: hidden;
  transition: transform 0.2s, background-color 0.2s;
  background-color: rgba(255, 255, 255, 0.4);
  backdrop-filter: blur(5px);
}

.book-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
  background-color: rgba(255, 255, 255, 0.45);
}

.book-cover {
  width: 100%;
  height: 150px;
  object-fit: cover;
}

.book-info {
  padding: 15px;
}

.book-title {
  margin: 0 0 10px 0;
  font-size: 16px;
  color: #2c3e50;
}

.book-price {
  color: #f56c6c;
  font-size: 18px;
  margin: 10px 0;
}

.book-store {
  color: #666;
  font-size: 14px;
  margin: 8px 0;
}

.button-group {
  display: flex;
  gap: 10px;
}

.detail-button {
  flex: 2;
  padding: 8px;
  background: #409eff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: opacity 0.2s;
}

.delete-button {
  flex: 1;
  padding: 8px;
  background: #8B0000;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: opacity 0.2s;
}

.delete-button:hover {
  opacity: 0.8;
}
</style>