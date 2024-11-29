<template>
  <div class="product-page">
    <buyer-header></buyer-header>
    <div style="display: flex">
      <ShopLogo></ShopLogo>
      <buyer-search></buyer-search>
    </div>
    <div class="filters">
      <div class="category-filter">
        <span
          v-for="category in categories"
          :key="category.value"
          class="filter-tag"
          :class="{active: selectedCategory === category.value}"
          @click="selectCategory(category.value)">
          {{ category.label }}
        </span>
      </div>
      <div class="sub-category-filter">
        <span
          v-for="subCategory in subCategories"
          :key="subCategory.value"
          class="filter-tag"
          :class="{active: selectedSubCategory === subCategory.value}"
          @click="selectSubCategory(subCategory.value)">
          {{ subCategory.label }}
        </span>
      </div>
    </div>
    <div class="product-list">
      <div class="product-item" v-for="product in filteredProducts" :key="product.id">
        <p class="product-name">{{ product.name }}</p>
        <p class="product-price">{{ product.price }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import ShopLogo from "@/components/Shop-Logo.vue";
import BuyerHeader from "@/components/block-buyer/buyer-header.vue";
import BuyerSearch from "@/components/block-buyer/buyer-search.vue";
import axios from "axios";
import { defineComponent, onMounted, ref } from "vue";

export default defineComponent({
  components: { BuyerSearch, BuyerHeader, ShopLogo },
  setup() {
    const categories = ref([]);
    const subCategories = ref([]);
    const selectedCategory = ref('all');
    const selectedSubCategory = ref('all');
    const products = ref([]);
    const filteredProducts = ref([]);

    const fetchCategories = async () => {
      try {
        const response = await axios.get('http://localhost:8090/cat/list', {
          params: { CParentID: 0 }
        });
        categories.value = response.data.map(item => ({
          value: item.CID,
          label: item.CNAME,
        }));
        categories.value.unshift({ value: 'all', label: '全部' });
        console.log("一级标签：", categories.value);
      } catch (error) {
        console.error('There was an error fetching categories:', error);
      }
    };

    const fetchSubCategories = async (parentId) => {
      try {
        const response = await axios.post('http://localhost:8090/cat/get_all_child', { id: [parentId] });
        const childIds = response.data;
        const subCatArray = [];
        for (const id of childIds) {
          const subResponse = await axios.get(`http://localhost:8090/cat/list`, {
            params: { CParentID: id }
          });
          subCatArray.push(...subResponse.data.map(item => ({
            value: item.CID,
            label: item.CNAME,
          })));
        }
        subCategories.value = [{ value: 'all', label: '全部' }, ...subCatArray];
      } catch (error) {
        console.error('There was an error fetching subcategories:', error);
      }
    };

    const selectCategory = (category) => {
      selectedCategory.value = category;
      if (category !== 'all') {
        fetchSubCategories(category);
      } else {
        subCategories.value = [];
      }
    };

    const selectSubCategory = (subCategory) => {
      selectedSubCategory.value = subCategory;
    };

    const filterProducts = () => {
      filteredProducts.value = products.value.filter(product => {
        const categoryMatch = selectedCategory.value === 'all' || product.category === selectedCategory.value;
        const subCategoryMatch = selectedSubCategory.value === 'all' || product.subCategory === selectedSubCategory.value;
        return categoryMatch && subCategoryMatch;
      });
    };

    onMounted(() => {
      fetchCategories();
    });

    return {
      categories,
      subCategories,
      selectedCategory,
      selectedSubCategory,
      products,
      filteredProducts,
      selectCategory,
      selectSubCategory,
      filterProducts,
    };
  },
});
</script>

<style>
.product-page {
  font-family: Arial, sans-serif;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
  height: 60px;
  background-color: #f5f5f5;
}

.header-left {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
  font-family: "宋体", SimSun, sans-serif;
}

.welcome-message {
  margin: 0;
}

.header-right {
  display: flex;
  align-items: center;
}

.user-management {
  display: flex;
  gap: 10px;
  align-items: center;
}

.avatar {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  object-fit: cover;
}

.user-info-link {
  text-decoration: none;
  color: black;
  margin-right: 10px;
}

.logout-button {
  padding: 5px 10px;
  background-color: #f44336;
  color: white;
  border: none;
  cursor: pointer;
}

.logout-button:hover {
  background-color: #d32f2f;
}

.search-bar {
  height: 50px;
  display: flex;
  margin-top: 10px;
  justify-content: center;
  width: 100%;
}

.search-bar input {
  padding: 10px;
  flex: 1;
}

.search-bar button {
  padding: 10px;
  background-color: #007bff;
  color: white;
  border: none;
  cursor: pointer;
}

.search-bar button:hover {
  background-color: #0056b3;
}

.filters {
  margin-top: 20px;
  background-color: #eae9e9; /* 更改为淡蓝色 */
}

.category-filter {
  display: flex;
  flex-wrap: wrap; /* 允许换行 */
  gap: 10px; /* 标签之间的间距 */
  margin-top: 3px;
  margin-bottom: 10px; /* 让每一行标签之间有间距 */
  background-color: #cfd0d1;
}

.sub-category-filter {
  display: flex;
  flex-wrap: wrap; /* 允许换行 */
  gap: 10px; /* 标签之间的间距 */
  margin-top: 3px;
  margin-bottom: 10px; /* 让每一行标签之间有间距 */
}

.filter-tag {
  padding: 10px 15px;
  cursor: pointer;
  background-color: transparent; /* 默认标签背景颜色 */
  border-radius: 5px;
}

.filter-tag.active {
  background-color: #ffffff; /* 选中标签背景颜色 */
}

.product-list {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  margin-top: 20px;
}

.product-item {
  border: 1px solid #ccc;
  padding: 10px;
  width: calc(20% - 20px);
  text-align: center;
}

.product-name {
  font-weight: bold;
}

.product-price {
  color: red;
}
</style>
