import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      name: 'Home',
      component: () => import('../views/Home.vue')
    },
    // 用户模块
    {
      path: '/auth',
      name: 'Auth',
      component: () => import('../views/auth/AuthLayout.vue'),
      children: [
        {
          path: 'login',
          name: 'Login',
          component: () => import('../views/auth/Login.vue')
        },
        {
          path: 'register',
          name: 'Register',
          component: () => import('../views/auth/Register.vue')
        },
        {
          path: 'profile',
          name: 'Profile',
          component: () => import('../views/auth/Profile.vue'),
          meta: { requiresAuth: true }
        }
      ]
    },
    // 商品模块
    {
      path: '/books',
      name: 'Books',
      children: [
        {
          path: '',
          name: 'BookList',
          component: () => import('../views/books/BookList.vue')
        },
        {
          path: ':id',
          name: 'BookDetail',
          component: () => import('../views/books/BookDetail.vue')
        }
      ]
    },
    // 购物车模块（独立）
    {
      path: '/cart',
      name: 'Cart',
      component: () => import('../views/cart/Cart.vue'),
      meta: { requiresAuth: true }
    },
    // 订单模块
    {
      path: '/order',
      name: 'Order',
      meta: { requiresAuth: true },
      children: [
        {
          path: 'checkout',
          name: 'Checkout',
          component: () => import('../views/order/Checkout.vue')
        },
        {
          path: 'list',
          name: 'OrderList',
          component: () => import('../views/order/OrderList.vue')
        }
      ]
    },
    // 商城模块
    {
      path: '/mall',
      name: 'MallList',
      component: () => import('../views/mall/MallList.vue')
    },
    {
      path: '/mall/store/:id',
      name: 'StoreDetail',
      component: () => import('../views/mall/StoreDetail.vue')
    }
  ]
})

// 路由守卫
router.beforeEach((to, from, next) => {
  if (to.meta.requiresAuth) {
    // 检查用户是否已登录
    const isAuthenticated = localStorage.getItem('token')
    if (!isAuthenticated) {
      next({ name: 'Login', query: { redirect: to.fullPath } })
    } else {
      next()
    }
  } else {
    next()
  }
})

export default router