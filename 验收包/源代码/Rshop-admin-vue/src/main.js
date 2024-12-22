import './assets/main.css'
import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router'
//import store from './store'
import './assets/global.css'
import axios from 'axios'
const app = createApp(App)

import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import VueQuillEditor from 'vue-quill-editor';
import 'quill/dist/quill.core.css';
import 'quill/dist/quill.snow.css';
import 'quill/dist/quill.bubble.css';
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}
app.use(VueQuillEditor);
app.use(router)
app.config.globalProperties.$axios = axios;
app.use(ElementPlus)
//app.use(store)
// axios.interceptors.request.use(
//   config =>{
//     let token = localStorage.getItem('token')
//     if(token){
//       //这里面获取的请求头的键(tokenHeader)根据每个后端的习惯封装的名称各不相同
//       config.headers.common['Authorization'] = token
//     }
//     return config
//   }, error =>{
//     return Promise.reject(error)
//   }
// )
import 'virtual:windi.css'

app.mount('#app')
