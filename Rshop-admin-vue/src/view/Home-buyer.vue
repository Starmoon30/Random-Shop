<template>
  <div class="product-page">
    <buyer-header></buyer-header>
    <div style="display: flex">
      <ShopLogo></ShopLogo>
      <Goodssearch></Goodssearch>
    </div>
    <div class="filters">
      <div class="category-filter">
        <div
          class="filter-tag"
          v-for="category in topCategories"
          :key="category.cid"
          @click="selectCategory(category.cid)"
          tabindex="1"
        >
        {{ category.cname }}
      </div>
    </div>
    </div>
    <div class="subfilters">
      <div class="category-filter" v-if="selectedSubCategory.length">
        <div
          class="filter-subtag"
          v-for="(subcategory,index) in selectedSubCategory"
          :key="subcategory.cid"
          @click="selectSubCategory(subcategory.cid)"
          tabindex="2"
        >
          {{ index === 0 ? '全部' : subcategory.cname }}
        </div>
      </div>
    </div>
    <div class="product-list">
      <el-card v-for="product in products"
               :key="product.gid"
               class="product-card"
               @click="goToProductDetail(product.gid)">
        <el-carousel :interval="5000" arrow="always" height="200px">
          <el-carousel-item v-for="(pic, index) in product.pictures" :key="index">
            <img :src="pic" class="product-image" />
          </el-carousel-item>
        </el-carousel>
        <div class="product-info">
          <h3>{{ product.gname }}</h3>
          <p>￥:{{ product.gvalue }} 库存:{{ product.gstock }}</p>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
import ShopLogo from "@/components/Shop-Logo.vue";
import BuyerHeader from "@/components/block-buyer/buyer-header.vue";
import Goodssearch from "@/components/block-search/buyer-search.vue";
import axios from "axios";

const token = localStorage.getItem('token');
console.log("token", token);

import { defineComponent, onMounted, ref, computed } from "vue";
import {useRouter} from "vue-router";

export default defineComponent({
  components: { Goodssearch, BuyerHeader, ShopLogo },
  setup() {
    const categories = ref([]);
    const subCategories = ref([]);
    const selectedCategory = ref('all');
    const selectedSubCategory = ref([]);
    const products = ref([]); // 用于存储商品数据
    const filteredProducts = ref([]);
    const searchQuery = ref(''); // 用于存储搜索查询
    const selectedCategoryStyle = ref({});
    const selectedSubCategoryStyle = ref({});

    const topCategories = computed(() => categories.value);
    const router = useRouter(); // 创建 router 实例

    const goToProductDetail = (gid) => {
      const route = {
        name: 'BProductDet', // 详情页面的路由名称
        params: { pid: gid }
      };
      router.push(route);
    };
    const fetchCategories = async () => {
      try {
        const data = {
          id: [-1,0]
        }
        const response = await axios.post(`http://localhost:8090/cat/get_cid_by_parent`,data, {
          headers: {
            'Authorization': `${token}`,
          }
        });
        console.log("返回一级:", response.data);
        categories.value = response.data.map(async (category) => {
          try {
            // 对于每个category，发起一个新的请求来获取详细信息
            const infoResponse = await axios.get(`http://localhost:8090/cat/get_info_by_cid/${category}`, {
              headers: {
                'Authorization': `${token}`,
              }
            });
            // 将获取到的信息更新到category对象中
            return { ...category, ...infoResponse.data };
          } catch (error) {
            console.error(`Error fetching info for category ${category.cid}:`, error);
            // 可以选择返回原始category或者根据需要处理错误
            return category;
          }
        });

        // 等待所有请求完成
        const detailedCategories = await Promise.all(categories.value);

        // 更新categories.value为包含详细信息的数组
        categories.value = detailedCategories;

        // 默认选中第一个标签
        selectCategory(detailedCategories[0].cid);

      } catch (error) {
        console.error('Error fetching categories:', error);
      }
    };

    const fetchCategoryInfoById = async (subCat) => {
      try {
        console.log("sub:",subCat)
        const response = await axios.get(`http://localhost:8090/cat/get_info_by_cid/${subCat}`, {
          headers: {
            'Authorization': `${token}`,
          }
        });
        console.log("Category Info:", response.data);
        // 返回获取到的类别信息
        return response.data;
      } catch (error) {
        console.error('Error fetching category info:', error);
        return null;
      }
    };

    const fetchSubCategories = async (categoryId) => {
      try {
        const response = await axios.post('http://localhost:8090/cat/get_all_child', { id: [categoryId] }, {
          headers: {
            'Authorization': `${token}`,
            'Content-Type': 'application/json',
          }
        });
        console.log("erji:", response.data);
        // 清空selectedSubCategory
        selectedSubCategory.value = [];
        // 遍历子类别数组，为每个子类别获取详细信息
        const subCategoriesDetails = await Promise.all(response.data.map(async (subCat) => {
          // 调用fetchCategoryInfoById获取详细信息
          return await fetchCategoryInfoById(subCat);
        }));
        // 更新selectedSubCategory
        selectedSubCategory.value = subCategoriesDetails.filter(cat => cat !== null);
        // 默认选中第一个二级分类
        if (selectedSubCategory.value.length > 0) {
          selectSubCategory(selectedSubCategory.value[0].cid);
        }
      } catch (error) {
        console.error('Error fetching subcategories:', error);
      }
    };

    // 获取商品图片的函数
    const fetchProductPictures = async (gid) => {
      try {
        const picMap = { gid: gid };
        const response = await axios.post('http://localhost:8090/pic/get_pic', picMap, {
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

    const fetchProducts = async (cid) => {
      try {
        const data = {
          cid: [cid],
          query: searchQuery.value
        };
        const response = await axios.post('http://localhost:8090/goods/list_By_Category', data, {
          headers: {
            'Authorization': `${token}`,
          }
        });
        const productsWithPictures = await Promise.all(response.data.map(async product => {
          if (product.gshelf === 1) {
            const pictures = await fetchProductPictures(product.gid);
            return { ...product, pictures };
          }
          return null;
        }));
        const filteredProducts = productsWithPictures.filter(item => item !== null); // 过滤掉null项
        products.value = filteredProducts; // 确保这里赋值给 products.value

        console.log("过滤后的商品：", filteredProducts);
      } catch (error) {
        console.error('获取商品信息失败:', error);
      }
    };

    onMounted(() => {
      fetchCategories();
      fetchProducts(0);
    });

    const selectCategory = (cid) => {
      selectedCategory.value = cid;
      selectedCategoryStyle.value = { backgroundColor: '#666666', color: 'white' };
      fetchSubCategories(cid);
    };

    const selectSubCategory = (sid) => {
      // 这里可以添加根据选中的子分类进行操作的逻辑
      selectedSubCategoryStyle.value = { backgroundColor: '#666666', color: 'white' };
      fetchProducts(sid);
    };

    const selectedSubCategoryActive = (sid) => {
      // 这里可以添加逻辑来判断子分类是否被选中
    };

    return {
      products,
      topCategories,
      selectedCategory,
      selectCategory,
      selectedSubCategory,
      selectSubCategory,
      selectedSubCategoryActive,
      selectedCategoryStyle,
      selectedSubCategoryStyle,
      goToProductDetail
    };
  },
});
</script>

<style>
.product-page {
  display: flex;
  flex-direction: column;
}

.filters,.subfilters {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 10px;
  background-color: #f5f5f5; /* 设置背景为浅灰色 */
  padding: 10px; /* 添加内边距 */
  border: 1px solid #ddd; /* 添加边框 */
  border-radius: 4px; /* 添加圆角边框 */
}

.category-filter {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}

.filter-tag {
  padding: 5px 10px;
  border: 1px solid #ccc; /* 设置边框颜色 */
  border-radius: 4px;
  cursor: pointer;
  background-color: #fff; /* 设置标签背景颜色 */
  color: #333; /* 设置标签文字颜色 */
  transition: background-color 0.3s, color 0.3s; /* 添加过渡效果 */
}

.filter-tag:hover {
  background-color: #e6e6e6; /* 鼠标悬停时的背景颜色 */
}

/* 更新被选中时标签的样式 */
.filter-tag:focus {
  background-color: #666666; /* 被选中时的背景颜色 */
  color: white; /* 被选中时的文字颜色 */
}
.filter-subtag {
  padding: 5px 10px;
  border: 1px solid #ccc; /* 设置边框颜色 */
  border-radius: 4px;
  cursor: pointer;
  background-color: #fff; /* 设置标签背景颜色 */
  color: #333; /* 设置标签文字颜色 */
  transition: background-color 0.3s, color 0.3s; /* 添加过渡效果 */
}

.filter-subtag:hover {
  background-color: #e6e6e6; /* 鼠标悬停时的背景颜色 */
}

/* 更新被选中时标签的样式 */
.filter-subtag:focus {
  background-color: #666666; /* 被选中时的背景颜色 */
  color: white; /* 被选中时的文字颜色 */
}
.product-list {
  margin: 40px;
  display: flex;
  flex-wrap: wrap;
  gap: 40px; /* 卡片之间的间隙 */
  justify-content: center; /* 居中对齐 */
}

.product-card {
  width: calc(25% - 20px); /* 每张卡片占据25%的宽度，减去间距 */
  box-shadow: 0 2px 5px rgba(0,0,0,0.2); /* 添加阴影效果 */
  border-radius: 8px; /* 圆角边框 */
  overflow: hidden; /* 隐藏溢出的内容 */
  display: flex;
  flex-direction: column;
}

.el-carousel {
  height: 300px; /* 增加轮播图的高度 */
}

.el-carousel__item {
  display: flex;
  justify-content: center;
  align-items: center;
}

.product-image {
  width: 100%; /* 图片宽度100% */
  height: 100%; /* 高度100%，填满容器 */
  object-fit: cover; /* 保持图片的比例 */
  display: block; /* 防止图片下方出现空隙 */
}

.product-info {
  padding: 10px;
  background-color: #fff; /* 信息区域背景色 */
  border-top: 1px solid #eee; /* 分隔线 */
}
</style>
