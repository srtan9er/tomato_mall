// https://vite.dev/config/
// export default defineConfig({
//   plugins: [vue()],
// })
import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080',  // 后端的端口
        changeOrigin: true,  // 更改请求头中的 origin
        // rewrite: (path) => path.replace(/^\/api/, ''),  // 去掉 `/api` 前缀
      }
    }
  }
});
