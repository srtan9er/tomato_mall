<script setup>
import { ref } from 'vue'

const orders = ref([
  {
    id: 1,
    date: '2024-01-20',
    total: 99,
    status: '已完成',
    items: [{ title: '三体', quantity: 1 }]
  },
])
</script>

<template>
  <div class="orders-container">
    <h2>我的订单</h2>
    <div class="order-list">
      <div v-for="order in orders" :key="order.id" class="order-item">
        <div class="order-header">
          <span>订单号：{{ order.id }}</span>
          <span>{{ order.date }}</span>
          <span class="status">{{ order.status }}</span>
        </div>
        <div class="order-content">
          <div v-for="item in order.items" :key="item.title" class="order-book">
            {{ item.title }} x {{ item.quantity }}
          </div>
        </div>
        <div class="order-footer">
          <span>总计：¥{{ order.total }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.orders-container {
  max-width: 800px;
  margin: 40px auto;
  padding: 20px;
}

.order-item {
  border: 1px solid #eee;
  border-radius: 8px;
  margin-bottom: 20px;
  padding: 15px;
}

.order-header {
  display: flex;
  justify-content: space-between;
  padding-bottom: 10px;
  border-bottom: 1px solid #eee;
}

.status {
  color: #42b983;
}

.order-footer {
  text-align: right;
  margin-top: 10px;
  font-weight: bold;
}
</style>