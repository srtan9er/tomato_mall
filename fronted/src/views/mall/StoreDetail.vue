<template>
  <div class="store-detail">
    
    <div v-if="showEditForm" class="edit-dialog">
      <div class="dialog-content">
        <h3>编辑店铺信息</h3>
        <div class="form-group">
          <label>店铺名称</label>
          <input v-model="editForm.name" type="text">
        </div>
        <div class="form-group">
          <label>店铺简介</label>
          <textarea v-model="editForm.description"></textarea>
        </div>
        <div class="dialog-actions">
          <button class="cancel-btn" @click="showEditForm = false">取消</button>
          <button class="save-btn" @click="saveStoreInfo">保存</button>
        </div>
      </div>
    </div>

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
        <button class="edit-btn" @click="showEditForm = true">编辑店铺信息</button>
      </div>
    </div>

    <!-- 店铺管理面板（仅店主可见） -->
    <div v-if="isStoreOwner" class="store-management">
      <!-- 商品管理部分 -->
      <div class="books-management">
        <div class="management-header">
          <h2>商品管理</h2>
          <button class="add-btn" @click="showAddBookForm = true">添加商品</button>
        </div>
        
        <div class="book-grid">
          <BookCard
            v-for="book in store.books"
            :key="book.id"
            :book="book"
            :from-mall="true"
            :edit-mode="true"
          >
          </BookCard>
        </div>
      </div>

      <!-- 店铺图书部分 -->
      <div v-if="!isStoreOwner" class="store-books">
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
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import BookCard from '../../components/BookCard.vue'

const route = useRoute()
const router = useRouter()
const store = ref({})
const isStoreOwner = ref(false)
const showEditForm = ref(false)
const showAddBookForm = ref(false)
const editForm = ref({
  name: '',
  description: ''
})

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
        cover: 'https://srtanger-bucket-0.oss-cn-shanghai.aliyuncs.com/%E5%A4%B4%E5%83%8F0.jpg'
      },
      {
        id: 2,
        title: '算法导论',
        price: 78.00,
        cover: 'https://srtanger-bucket-0.oss-cn-shanghai.aliyuncs.com/%E5%A4%B4%E5%83%8F0.jpg'
      },
      // 更多图书...
    ]
  }
  // 检查是否为店主
  // const token = localStorage.getItem('token')
  // if (token) {
  //   try {
      // TODO: 调用后端 API 检查用户身份和店铺所有权
      // 模拟数据
      isStoreOwner.value = true
      editForm.value = {
        name: store.value.name,
        description: store.value.description
      }
    // } catch (error) {
    //   console.error('验证店铺所有权失败:', error)
    // }
  // }
})


// 保存店铺信息
const saveStoreInfo = async () => {
  try {
    // TODO: 调用后端 API 保存店铺信息
    store.value.name = editForm.value.name
    store.value.description = editForm.value.description
    showEditForm.value = false
  } catch (error) {
    console.error('保存店铺信息失败:', error)
  }
}

// 删除商品
const deleteBook = async (bookId) => {
  if (confirm('确定要删除这本书吗？')) {
    try {
      // TODO: 调用后端 API 删除商品
      store.value.books = store.value.books.filter(book => book.id !== bookId)
    } catch (error) {
      console.error('删除商品失败:', error)
    }
  }
}

</script>

<style scoped>

.store-management {
  margin-top: 30px;
}

.book-item {
  position: relative;
}

.delete-btn {
  position: absolute;
  top: 10px;
  right: 10px;
  background: rgba(139, 0, 0, 0.8);
  color: white;
  border: none;
  border-radius: 4px;
  padding: 4px 8px;
  cursor: pointer;
  z-index: 1;
}

.delete-btn:hover {
  background: rgba(139, 0, 0, 1);
}
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


.store-management {
  background: rgba(255, 255, 255, 0.4);
  backdrop-filter: blur(10px);  
  border-radius: 8px;
  padding: 20px;
  margin-bottom: 30px;
}

.management-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.edit-btn, .add-btn {
  padding: 8px 16px;
  background: #8B0000;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.edit-dialog {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.dialog-content {
  background: white;
  padding: 20px;
  border-radius: 8px;
  width: 400px;
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
}

.form-group input, .form-group textarea {
  width: 100%;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

.dialog-actions {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
  margin-top: 20px;
}

.book-list {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: 20px;
}

.book-item {
  background: rgba(255, 255, 255, 0.6);
  padding: 10px;
  border-radius: 8px;
  position: relative;
}

.book-item img {
  width: 100%;
  height: 200px;
  object-fit: cover;
  border-radius: 4px;
}

.delete-btn {
  position: absolute;
  top: 10px;
  right: 10px;
  background: rgba(255, 0, 0, 0.8);
  color: white;
  border: none;
  border-radius: 4px;
  padding: 4px 8px;
  cursor: pointer;
}

.cancel-btn, .save-btn {
  padding: 8px 16px;
  border-radius: 4px;
  cursor: pointer;
  border: none;
}

.cancel-btn {
  background: #ddd;
}

.save-btn {
  background: #8B0000;
  color: white;
}

</style>