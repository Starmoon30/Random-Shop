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

export default {
  components: {BuyerSearch, BuyerHeader, ShopLogo },
  data() {
    return {
      categories: [
        { value: 'all', label: '全部' },
        { value: 'upper', label: '上身' },
        { value: 'lower', label: '下身' },
      ],
      subCategories: [
        { value: 'all', label: '全部' },
        { value: 'hat', label: '帽子' },
        { value: 'clothes', label: '上衣' },
        { value: 'pants', label: '裤子' },
        { value: 'socks', label: '袜子' },
        { value: 'shoes', label: '鞋子' },
      ],
      selectedCategory: 'all',
      selectedSubCategory: 'all',
      searchQuery: '',
      products: [
        { id: 1, name: '商品1', price: '100元', category: 'upper', subCategory: 'clothes' },
        { id: 2, name: '商品2', price: '200元', category: 'lower', subCategory: 'pants' },
        { id: 3, name: '商品3', price: '150元', category: 'upper', subCategory: 'hat' },
        { id: 4, name: '商品4', price: '250元', category: 'lower', subCategory: 'shoes' },
        { id: 5, name: '商品5', price: '180元', category: 'upper', subCategory: 'clothes' },
      ],
      filteredProducts: [],
    };
  },
  methods: {
    selectCategory(category) {
      this.selectedCategory = category;
      this.filterProducts();
    },
    selectSubCategory(subCategory) {
      this.selectedSubCategory = subCategory;
      this.filterProducts();
    },
    filterProducts() {
      this.filteredProducts = this.products.filter(product => {
        const categoryMatch = this.selectedCategory === 'all' || product.category === this.selectedCategory;
        const subCategoryMatch = this.selectedSubCategory === 'all' || product.subCategory === this.selectedSubCategory;
        return categoryMatch && subCategoryMatch;
      });
    },
    logout() {
      // Implement logout functionality here
    }
  },
  created() {
    this.filterProducts();
  },
};
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
