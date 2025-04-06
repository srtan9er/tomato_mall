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
    <div class="cart-card">
      <h2>购物车</h2>
      <div class="cart-items">
        <div v-for="item in cartItems" :key="item.id" class="cart-item">
          <router-link :to="`/books/${item.id}`" class="item-title">{{ item.title }}</router-link>
          <span class="item-price">¥{{ item.price }}</span>
          <div class="quantity">
            <button class="quantity-btn" @click="updateQuantity(item, -1)" :disabled="item.quantity <= 0">-</button>
            <span class="quantity-num">{{ item.quantity }}</span>
            <button class="quantity-btn" @click="updateQuantity(item, 1)">+</button>
            <button class="delete-btn" @click="removeItem(item.id)">删除</button>
          </div>
        </div>
      </div>
      <div class="cart-total">
        <span class="total-text">总计：¥{{ total }}</span>
        <router-link to="/order/checkout" class="checkout-btn">结算</router-link>
      </div>
    </div>
  </div>
</template>

<style scoped>
.cart-container {
  max-width: 800px;
  margin: 40px auto;
  padding: 20px;
}

.cart-card {
  background: rgba(255, 255, 255, 0.4);
  backdrop-filter: blur(10px);
  border-radius: 12px;
  padding: 25px;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.08);
}

.cart-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px;
  border-bottom: 2px solid rgba(238, 238, 238, 0.8);
  transition: background-color 0.3s;
}

.cart-item:hover {
  background: rgba(255, 255, 255, 0.3);
}

.item-title {
  font-size: 16px;
  color: #2c3e50;
  flex: 1;
  text-decoration: none;
  transition: color 0.3s;
}

.item-title:hover {
  color: #8B0000;
  text-decoration: underline;
}

.item-price {
  font-size: 16px;
  color: #8B0000;
  margin: 0 20px;
}

.quantity {
  display: flex;
  align-items: center;
  gap: 12px;
}

.quantity-btn {
  width: 30px;
  height: 30px;
  border: none;
  border-radius: 4px;
  background: rgba(139, 0, 0, 0.1);
  color: #8B0000;
  cursor: pointer;
  transition: all 0.3s;
}

.quantity-btn:hover {
  background: rgba(139, 0, 0, 0.2);
}

.quantity-btn:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}

.quantity-num {
  min-width: 30px;
  text-align: center;
  font-weight: bold;
}

.delete-btn {
  padding: 6px 12px;
  background: rgba(255, 73, 73, 0.9);
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: all 0.3s;
}

.delete-btn:hover {
  background: rgba(255, 73, 73, 1);
  transform: translateY(-1px);
}

.cart-total {
  margin-top: 30px;
  padding-top: 20px;
  border-top: 2px solid rgba(238, 238, 238, 0.8);
  text-align: right;
  display: flex;
  justify-content: flex-end;
  align-items: center;
  gap: 20px;
}

.total-text {
  font-size: 18px;
  font-weight: bold;
  color: #2c3e50;
}

.checkout-btn {
  display: inline-block;
  padding: 12px 30px;
  background: #8B0000;
  color: white;
  text-decoration: none;
  border-radius: 6px;
  transition: all 0.3s;
}

.checkout-btn:hover {
  background: #a00000;
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(139, 0, 0, 0.2);
}
</style>