<script setup>
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const orderInfo = ref({
  items: [
    { id: 1, title: '三体', price: 99, quantity: 1 },
    { id: 2, title: '活着', price: 39, quantity: 1 }
  ],
  address: '',
  phone: '',
  note: ''
})

const total = computed(() => {
  return orderInfo.value.items.reduce((sum, item) => sum + item.price * item.quantity, 0)
})

const submitOrder = () => {
  // 模拟提交订单
  setTimeout(() => {
    router.push('/order/list')
  }, 1000)
}
</script>

<template>
  <div class="checkout-container">
    <h2>确认订单</h2>
    
    <div class="order-items">
      <h3>订单商品</h3>
      <div v-for="item in orderInfo.items" :key="item.id" class="item">
        <span>{{ item.title }}</span>
        <span>数量：{{ item.quantity }}</span>
        <span>¥{{ item.price * item.quantity }}</span>
      </div>
    </div>

    <div class="delivery-info">
      <h3>收货信息</h3>
      <div class="form-group">
        <label>收货地址</label>
        <input v-model="orderInfo.address" type="text" placeholder="请输入收货地址">
      </div>
      <div class="form-group">
        <label>联系电话</label>
        <input v-model="orderInfo.phone" type="text" placeholder="请输入联系电话">
      </div>
      <div class="form-group">
        <label>备注</label>
        <textarea v-model="orderInfo.note" placeholder="请输入备注信息"></textarea>
      </div>
    </div>

    <div class="order-summary">
      <div class="total">总计：¥{{ total }}</div>
      <button @click="submitOrder" class="submit-btn">提交订单</button>
    </div>
  </div>
</template>

<style scoped>
.checkout-container {
  max-width: 800px;
  margin: 40px auto;
  padding: 20px;
}

.order-items {
  margin-bottom: 30px;
  border: 1px solid #eee;
  border-radius: 8px;
  padding: 15px;
}

.item {
  display: flex;
  justify-content: space-between;
  padding: 10px 0;
  border-bottom: 1px solid #eee;
}

.delivery-info {
  margin-bottom: 30px;
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 5px;
}

input, textarea {
  width: 100%;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
}

textarea {
  height: 100px;
  resize: vertical;
}

.order-summary {
  text-align: right;
}

.total {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 15px;
}

.submit-btn {
  padding: 10px 30px;
  background: #42b983;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
}

.submit-btn:hover {
  background: #3aa876;
}
</style>