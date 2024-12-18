<template>
  <div style="height: 100%; display: flex;">
    <ShopLogo style="background-color: #ffffff"></ShopLogo>
    <el-header style="width: 82%; text-align: right; font-size: 12px; display: flex; align-items: center; height: 80px; background-color: var(--el-color-primary-light-9);">
      <admin-head></admin-head>
    </el-header>
  </div>
  <el-main class="product-detail-container">
    <el-main class="product-detail-container">
      <el-card v-if="product" class="product-card">
        <div class="product-layout">
          <div class="product-image-container">
            <img :src="currentPicture"/>
          </div>
          <div class="product-info">
            <div class="info-header">
              <el-button type="default" @click="goBack">返回</el-button>
            </div>
            <div class="info-content">
              <h3>{{ product.gname }}</h3>
              <p>价格: ￥{{ product.gvalue }}</p>
              <p>库存: {{ product.gstock }}</p>
              <el-button type="primary" @click="editProduct(product.gid)">编辑商品</el-button>
            </div>
          </div>
        </div>
        <div class="preview-container">
          <img v-for="(pic, index) in product.pictures" :key="index" :src="pic" class="preview-image" @click="selectPicture(index)" />
        </div>
        <div class="product-description">
          <!-- 使用 v-html 指令来渲染 HTML 字符串 -->
          <div v-html="product.gdesc"></div>
        </div>
      </el-card>
    </el-main>
  </el-main>
</template>

<script>
import { defineComponent, ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import axios from 'axios';
import ShopLogo from "@/components/Shop-Logo.vue";
import AdminHead from "@/components/block-admin/adminHead.vue";

export default defineComponent({
  components: { AdminHead, ShopLogo },
  setup() {
    const product = ref(null);
    const currentPicture = ref('');
    const route = useRoute();
    const router = useRouter();
    const token = localStorage.getItem('token');
    const gid = Number(route.params.pid);

    const fetchProductData = async (gid) => {
      try {
        const response = await axios.post('http://localhost:8090/goods/get_info', { gid }, {
          headers: {
            'Authorization': `${token}`,
          }
        });
        if (response.data.length > 0) {
          const fetchedProduct = response.data[0];
          const pictures = await fetchProductPictures(fetchedProduct.gid);
          product.value = { ...fetchedProduct, pictures };
          currentPicture.value = pictures[0]; // 设置默认显示的第一张图片
        }
      } catch (error) {
        console.error("获取商品详情失败:", error);
      }
    };

    const fetchProductPictures = async (gid) => {
      try {
        const response = await axios.post('http://localhost:8090/pic/get_pic', { gid }, {
          headers: {
            'Authorization': `${token}`,
          }
        });
        // 遍历返回的Map对象并获取Base64编码图片数据
        const picArray = Object.entries(response.data).map(([picId, base64Data]) => {
          return `data:image/jpeg;base64,${base64Data}`;
        });

        return picArray;
      } catch (error) {
        console.error('获取商品图片失败:', error);
        return [];
      }
    };

    const goBack = () => {
      router.go(-1);
    };

    const editProduct = (gid) => {
      console.log("gid:",gid)
      if (gid) {
        router.push({ name: 'UpdateGood', params: { pid: gid } });
      } else {
        console.error('商品ID未找到');
      }
    };

    const selectPicture = (index) => {
      currentPicture.value = product.value.pictures[index];
    };

    onMounted(() => {
      if (!isNaN(gid)) {
        fetchProductData(gid);
      } else {
        console.error('商品ID无效');
      }
    });

    return {
      product,
      currentPicture,
      goBack,
      editProduct,
      selectPicture,
    };
  }
});
</script>

<style scoped>
body {
  font-family: 'Helvetica', 'Arial', sans-serif;
}

.product-image-container {
  position: relative;
  width: 100%; /* 铺满整行 */
  height: 500px; /* 固定大图容器高度 */
  overflow: hidden; /* 隐藏超出容器的图片部分 */
}

.product-image {
  width: 100%; /* 宽度自适应 */
  height: 100%; /* 高度自适应 */
  object-fit: cover; /* 保持图片比例 */
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0,0,0,0.1); /* 添加阴影效果 */
}

.product-card {
  display: flex;
  flex-direction: column;
  gap: 20px;
  width: 100%; /* 铺满整行 */
}

.product-layout {
  display: flex;
  align-items: flex-start; /* 修改为顶部对齐 */
  justify-content: space-between; /* 保持两端对齐 */
  width: 100%; /* 铺满整行 */
  gap: 20px; /* 添加间距 */
}

.product-image-container {
  flex: 1; /* 使图片容器占据可用空间 */
}

.product-info {
  flex: 1; /* 使信息容器占据可用空间 */
  padding: 20px; /* 保持原有内边距 */
}

.info-header {
  margin-bottom: 10px; /* 增加按钮与商品信息的间距 */
  display: flex;
  justify-content: flex-end; /* 返回按钮靠右 */
}

.product-info h3 {
  font-size: 24px;
  color: #333;
  margin: 0; /* Remove margin for better alignment */
}

.product-info p {
  font-size: 16px;
  color: #666;
  margin: 5px 0;
}

.product-description {
  margin-top: 20px;
  padding: 20px;
  border: 1px solid #ebeef5;
  border-radius: 8px;
  background-color: #fff;
  font-size: 16px;
  color: #444;
  line-height: 1.6;
}

.back-button-container {
  margin-left: auto; /* Align back button to the right */
}

.preview-container {
  display: flex;
  overflow-x: auto; /* 允许横向滚动 */
  gap: 10px; /* 预览图之间的间隙 */
  margin-top: 10px; /* 大图与预览图之间的间隙 */
  padding: 0 10px; /* 预览图滚动时的内边距 */
}

.preview-image {
  width: 100px; /* 预览图宽度 */
  height: 100px; /* 预览图高度 */
  object-fit: cover;
  border-radius: 4px;
  cursor: pointer;
  border: 2px solid transparent; /* 添加透明边框 */
  transition: border 0.3s; /* 添加边框变化动画 */
}

.preview-image:hover, .preview-image.active {
  border: 2px solid #409EFF; /* 鼠标悬停或选中时显示蓝色边框 */
}

.el-button {
  padding: 10px 20px;
  font-size: 16px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  transition: background-color 0.3s, box-shadow 0.3s;
}

.el-button:hover {
  background-color: #0056b3;
  box-shadow: 0 4px 8px rgba(0,0,0,0.2);
}

.el-card {
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

/* 新增样式 */
.product-info .info-content {
  display: flex;
  flex-direction: column;
  align-items: center; /* 居中对齐 */
  gap: 10px; /* 增加间隙 */
}

.product-info .info-content p {
  text-align: center; /* 文本居中 */
}
</style>
