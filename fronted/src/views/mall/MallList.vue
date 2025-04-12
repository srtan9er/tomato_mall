<template>
  <div class="mall-list">
    <h1>图书商城</h1>
    
    <!-- 商家的店铺展示 -->
    <div v-if="userRole !== 'customer'" class="my-store-section">
      <div class="store-card my-store">
        <div class="store-header">
          <div class="store-info">
            <img :src="myStore.logo" :alt="myStore.name" class="store-logo">
            <div class="store-text">
              <h2>我的商店：{{ myStore.name }}</h2>
              <p>{{ myStore.description }}</p>
            </div>
          </div>
          <button class="store-detail-btn" @click="router.push(`/mall/store/${myStore.id}`)">
            管理店铺
          </button>
        </div>
      </div>
    </div>

    <!-- 现有的商店列表 -->
    <div class="store-list">
      <div v-for="store in stores" :key="store.id" class="store-card">
        <div class="store-header">
          <div class="store-info">
            <img :src="store.logo" :alt="store.name" class="store-logo">
            <div class="store-text">
              <h2>{{ store.name }}</h2>
              <p>{{ store.description }}</p>
            </div>
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
const userRole = ref('customer') // 用户角色
const myStore = ref(null)

onMounted(async () => {
  // 获取用户角色
  // const token = localStorage.getItem('token')
  // if (token) {
  //   try {
  //     // TODO: 从token中解析用户角色或调用后端API获取用户信息
      userRole.value = 'seller' // 模拟数据，实际应该从后端获取

      // 如果是商家，获取其店铺信息
      if (userRole.value !== 'customer') {
        // TODO: 调用后端API获取商家的店铺信息
        myStore.value = {
          id: 999,
          name: '我的书店',
          description: '我的专属书店',
          logo: 'https://srtanger-bucket-0.oss-cn-shanghai.aliyuncs.com/%E5%A4%B4%E5%83%8F0.jpg',
        }
      }
    // } catch (error) {
    //   console.error('获取用户信息失败:', error)
    // }
  // }

  // 获取所有商店列表
  stores.value = [
    {
      id: 1,
      name: '优质图书店',
      description: '专注优质图书销售',
      logo: 'https://srtanger-bucket-0.oss-cn-shanghai.aliyuncs.com/%E5%A4%B4%E5%83%8F0.jpg',  // 添加店铺logo
      books: [
        { id: 1, title: '深入理解计算机系统', price: 89, cover: 'https://srtanger-bucket-0.oss-cn-shanghai.aliyuncs.com/%E5%A4%B4%E5%83%8F0.jpg' },
        { id: 2, title: '算法导论', price: 78, cover: 'https://srtanger-bucket-0.oss-cn-shanghai.aliyuncs.com/%E5%A4%B4%E5%83%8F0.jpg' },
      ]
    },
    {
      id: 2,
      name: '优质图书店',
      description: '专注优质图书销售',
      logo: 'https://srtanger-bucket-0.oss-cn-shanghai.aliyuncs.com/%E5%A4%B4%E5%83%8F0.jpg',  // 添加店铺logo
      books: [
        { id: 1, title: '深入理解计算机系统', price: 89, cover: 'https://srtanger-bucket-0.oss-cn-shanghai.aliyuncs.com/%E5%A4%B4%E5%83%8F0.jpg' },
        { id: 2, title: '算法导论', price: 78, cover: 'https://srtanger-bucket-0.oss-cn-shanghai.aliyuncs.com/%E5%A4%B4%E5%83%8F0.jpg' },
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

.mall-list h1 {
  margin-bottom: 40px;
  margin-top: 30px;
}

.store-list {
  margin-top: 30px;  /* 增加商店列表上方间距 */
}

.store-card {
  background-color: rgba(255, 255, 255, 0.4);
  backdrop-filter: blur(10px);
  margin-bottom: 30px;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.08);
  transition: transform 0.3s, box-shadow 0.3s;
}

.store-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.15);
  background-color: rgba(255, 255, 255, 0.45);
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

.store-info {
  display: flex;
  align-items: center;
  gap: 20px;
}

.store-logo {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  object-fit: cover;
  border: 2px solid rgba(255, 255, 255, 0.8);
}

.store-text {
  display: flex;
  flex-direction: column;
}

.my-store-section {
  margin-bottom: 40px;
}

.my-store {
  border: 2px solid rgba(139, 0, 0, 0.3);
  background-color: rgba(255, 255, 255, 0.5);
}

.my-store .store-detail-btn {
  background: #8B0000;
}

.my-store:hover {
  border-color: rgba(139, 0, 0, 0.5);
}
</style>