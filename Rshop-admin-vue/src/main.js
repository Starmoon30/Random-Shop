import './assets/main.css'
import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router'
import './assets/global.css'
import axios from 'axios'
const app = createApp(App)
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}
app.use(router)
app.config.globalProperties.$axios = axios;
app.use(ElementPlus)

import 'virtual:windi.css'

app.mount('#app')
