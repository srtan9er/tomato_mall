<script setup>
import { ref, computed } from 'vue'

const cartItems = ref([
  { id: 1, title: '三体', price: 99, quantity: 1 },
  { id: 2, title: '活着', price: 39, quantity: 1 },
])

const removeItem = (id) => {
  cartItems.value = cartItems.value.filter(item => item.id !== id)
}

const updateQuantity = (item, change) => {
  item.quantity += change
  if (item.quantity <= 0) {
    removeItem(item.id)
  }
}

const total = computed(() => {
  return cartItems.value.reduce((sum, item) => sum + item.price * item.quantity, 0)
})
</script>

<template>
  <div class="cart-container">
    <h2>购物车</h2>
    <div class="cart-items">
      <div v-for="item in cartItems" :key="item.id" class="cart-item">
        <span>{{ item.title }}</span>
        <span>¥{{ item.price }}</span>
        <div class="quantity">
          <button @click="updateQuantity(item, -1)" :disabled="item.quantity <= 0">-</button>
          <span>{{ item.quantity }}</span>
          <button @click="updateQuantity(item, 1)">+</button>
          <button class="delete-btn" @click="removeItem(item.id)">删除</button>
        </div>
      </div>
    </div>
    <div class="cart-total">
      <span>总计：¥{{ total }}</span>
      <router-link to="/order/checkout" class="checkout-btn">结算</router-link>
    </div>
  </div>
</template>

<style scoped>
.cart-container {
  max-width: 800px;
  margin: 40px auto;
  padding: 20px;
}

.cart-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
  border-bottom: 1px solid #eee;
}

.quantity {
  display: flex;
  align-items: center;
  gap: 10px;
}

.cart-total {
  margin-top: 20px;
  text-align: right;
}

.checkout-btn {
  display: inline-block;
  padding: 10px 20px;
  background: #42b983;
  color: white;
  text-decoration: none;
  border-radius: 4px;
  margin-left: 20px;
}

.delete-btn {
  margin-left: 10px;
  padding: 5px 10px;
  background: #ff4949;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.delete-btn:hover {
  background: #f56c6c;
}
</style>