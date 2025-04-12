<template>
  <div class="book-detail">
    <div class="detail-card">
      <div class="book-container" v-if="book">
        <div class="book-image">
          <img :src="book.cover" :alt="book.title" />
        </div>
        <div class="book-info">
          <div class="title-section">
            <h1>{{ book.title }}</h1>
            <button v-if="isBookOwner" class="edit-btn" @click="showEditForm = true">
              编辑图书
            </button>
          </div>
          <p class="price">¥{{ book.price }}</p>
          <p class="description">{{ book.description }}</p>
          <div class="store-info" @click="goToStore">
            <h3>商家信息</h3>
            <p>{{ book.store?.name }}</p>
            <span class="store-link">查看店铺 ></span>
          </div>
          <div class="actions">
            <button class="add-to-cart" @click="addToCart">加入购物车</button>
            <button class="buy-now" @click="buyNow">立即购买</button>
          </div>
        </div>
      </div>
    </div>

    <!-- 编辑图书弹窗 -->
    <div v-if="showEditForm" class="edit-dialog">
      <div class="dialog-content">
        <h3>编辑图书信息</h3>
        <div class="form-group">
          <label>书名</label>
          <input v-model="editForm.title" type="text">
        </div>
        <div class="form-group">
          <label>价格</label>
          <input v-model="editForm.price" type="number" step="0.01">
        </div>
        <div class="form-group">
          <label>描述</label>
          <textarea v-model="editForm.description"></textarea>
        </div>
        <div class="form-group">
          <label>封面图片URL</label>
          <input v-model="editForm.cover" type="text">
        </div>
        <div class="dialog-actions">
          <button class="cancel-btn" @click="showEditForm = false">取消</button>
          <button class="save-btn" @click="saveBookInfo">保存</button>
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

const isBookOwner = ref(false)
const showEditForm = ref(false)
const editForm = ref({
  title: '',
  price: 0,
  description: '',
  cover: ''
})

onMounted(async () => {
  // TODO: 从后端获取图书详情
  // 模拟数据
  
  book.value = {
    id: route.params.id,
    title: '深入理解计算机系统',
    price: 89.00,
    description: '本书从程序员的视角详细阐述计算机系统的本质概念，并展示这些概念如何actually影响应用程序的正确性、性能和实用性。',
    cover: 'https://srtanger-bucket-0.oss-cn-shanghai.aliyuncs.com/%E5%A4%B4%E5%83%8F0.jpg',
    store: {
      id: 1,
      name: '优质图书店'
    }
  }
  
// 检查是否为图书所有者
  // const token = localStorage.getItem('token')
  // if (token) {
  //   try {
      // TODO: 调用后端API检查用户身份和店铺所有权
      // 模拟数据
      const userStoreId = 1 // 假设当前用户的店铺ID
      isBookOwner.value = userStoreId === book.value?.store?.id

      if (isBookOwner.value) {
        editForm.value = {
          title: book.value.title,
          price: book.value.price,
          description: book.value.description,
          cover: book.value.cover
        }
      }
  //   } catch (error) {
  //     console.error('验证图书所有权失败:', error)
  //   }
  // }
})

const addToCart = () => {
  // TODO: 实现加入购物车功能
}

const buyNow = () => {
  router.push('/order/checkout')
}

const goToStore = () => {
  if (book.value?.store?.id) {
    router.push(`/mall/store/${book.value.store.id}`)
  }
}


// 保存图书信息
const saveBookInfo = async () => {
  try {
    // TODO: 调用后端API保存图书信息
    book.value = {
      ...book.value,
      ...editForm.value
    }
    showEditForm.value = false
  } catch (error) {
    console.error('保存图书信息失败:', error)
  }
}
</script>

<style scoped>
.book-detail {
  padding: 20px;
  max-width: 1200px;
  margin: 0 auto;
}

.detail-card {
  background: rgba(255, 255, 255, 0.4);
  backdrop-filter: blur(10px);
  border-radius: 12px;
  padding: 30px;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.08);
}

.store-info {
  margin: 20px 0;
  padding: 15px;
  background: rgba(248, 249, 250, 0.2);
  border-radius: 8px;
  backdrop-filter: blur(5px);
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
}

.store-info:hover {
  background: rgba(248, 249, 250, 0.3);
  transform: translateY(-2px);
}

.store-link {
  position: absolute;
  right: 15px;
  bottom: 15px;
  color: #8B0000;
  font-size: 14px;
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
  height: 500px;
  object-fit: cover;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  border: 6px solid rgba(255, 255, 255, 0.8);
  transition: transform 0.3s, box-shadow 0.3s;
}

.book-image img:hover {
  transform: scale(1.02);
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
}

.book-info {
  flex: 1;
}

.price {
  font-size: 24px;
  color: #5c1440;
  margin: 20px 0;
}

.description {
  line-height: 1.6;
  color: #666;
  margin-bottom: 20px;
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
  background: rgba(255, 255, 255, 0.9);
  border: 1px solid #8B0000;
  color: #8B0000;
}

.buy-now {
  background: #8B0000;
  color: white;
}

button:hover {
  opacity: 0.8;
}

.title-section {
  display: flex;
  align-items: center;
  gap: 20px;
  margin-bottom: 20px;
}

.edit-btn {
  padding: 8px 16px;
  background: #8B0000;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
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