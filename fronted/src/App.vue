<script setup>
</script>

<template>
  <nav>
    <!-- 导航栏 -->
    <router-link to="/">首页</router-link> |
    <router-link to="/books">书籍</router-link> |
    <router-link to="/mall">商城</router-link> | 
    <router-link to="/cart">购物车</router-link> |
    <router-link to="/auth/profile">个人中心</router-link>
  </nav>

  <!-- 路由出口 -->
  <router-view></router-view>
</template>

<style>
nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
  text-decoration: none;
  margin: 0 10px;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>
