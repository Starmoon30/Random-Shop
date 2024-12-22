<template>
  <div class="add-product-container">
      <el-form-item label="商品名称：" >
        <el-input v-model="productForm.gname" placeholder="商品名"></el-input>
      </el-form-item>
      <el-form-item label="商品价格：">
        <el-input type="number" v-model="productForm.gvalue" placeholder="商品价格"></el-input>
      </el-form-item>
      <el-form-item label="商品库存：">
        <el-input type="number" v-model="productForm.gstock" placeholder="商品库存"></el-input>
      </el-form-item>
      <el-form-item label="一级分类">
        <el-select v-model="productForm.categoryId1" @change="handleTopCategoryChange">
          <el-option
            v-for="category in topCategories"
            :key="category.cid"
            :label="category.cname"
            :value="category.cid">
          </el-option>
        </el-select>
        <!-- 显示选中的分类信息 -->
        <div v-if="selectedTopCategory" class="selected-category-info">
        </div>
      </el-form-item>
      <el-form-item label="二级分类">
        <el-select v-model="productForm.categoryId2" @change="handleSubCategoryChange">
          <el-option
            v-for="category in subCategories"
            :key="category.cid"
            :label="category.cname"
            :value="category.cid">
          </el-option>
        </el-select>
        <div v-if="selectedSubCategory" class="selected-category-info">
        </div>
      </el-form-item>

      <RichText :onContentChange="updateDescription"></RichText>

      <el-form-item>
        <el-button type="primary" @click="submitProduct">新增商品</el-button>
        <el-button type="primary" @click="goBack">取消</el-button>
      </el-form-item>
  </div>
</template>

<script lang="ts" setup>
  import { computed, onMounted, reactive, ref } from 'vue';
  import { useRouter } from 'vue-router';
  import { ElMessage } from 'element-plus';
  import RichText from "@/components/block-rich/RichText.vue";
  import axios from 'axios';

  const token = localStorage.getItem('token');
  const categories = ref([]); // 一级分类
  const subCategories = ref([]); // 二级分类
  const selectedTopCategory = ref(null); // 选中的一级分类
  const selectedSubCategory = ref(null); // 选中的二级分类
  const router = useRouter();
  const productForm = reactive({
    gname: '',
    gvalue: '',
    gstock: '',
    categoryId1: '',
    categoryId2: '',
    cid: '',
    desc: ''
  });
  const goBack = () => {
    router.back();
  };

  const updateDescription = (content) => {
    productForm.desc = content; // 更新productForm.desc
    console.log("desc:",productForm.desc);
  };

  const fetchCategories = async () => {
    try {
      const response = await axios.post('http://localhost:8090/cat/get_cid_by_parent', { id: [0] }, {
        headers: {
          'Authorization': `${token}`,
        }
      });
      categories.value = await Promise.all(response.data.map(async (category) => {
        try {
          const infoResponse = await axios.get(`http://localhost:8090/cat/get_info_by_cid/${category}`, {
            headers: {
              'Authorization': `${token}`,
            }
          });
          return { ...category, ...infoResponse.data };
        } catch (error) {
          console.error(`Error fetching info for category ${category.cid}:`, error);
          return category;
        }
      }));
    } catch (error) {
      console.error('Error fetching categories:', error);
    }
  };

  const topCategories = computed(() => categories.value);

  const fetchCategoryInfoById = async (subCat) => {
    try {
      const response = await axios.get(`http://localhost:8090/cat/get_info_by_cid/${subCat}`, {
        headers: {
          'Authorization': `${token}`,
        }
      });
      return response.data;
    } catch (error) {
      console.error('Error fetching category info:', error);
      return null;
    }
  };

  const fetchSubCategories = async (categoryId1) => {
    try {
      const response = await axios.post('http://localhost:8090/cat/get_all_child', { id: [categoryId1] }, {
        headers: {
          'Authorization': `${token}`,
        }
      });
      subCategories.value = await Promise.all(response.data.map(async (subCat) => {
        return await fetchCategoryInfoById(subCat);
      }));
    } catch (error) {
      console.error('Error fetching subcategories:', error);
    }
  };

  const handleTopCategoryChange = async (categoryId1) => {
    selectedTopCategory.value = topCategories.value.find(cat => cat.cid === categoryId1);
    await fetchSubCategories(categoryId1); // 获取二级分类
  };

  const handleSubCategoryChange = (categoryId2) => {
    selectedSubCategory.value = subCategories.value.find(cat => cat.cid === categoryId2);
  };

  const submitProduct = async () => {
    try {
      const data = {
        gname: productForm.gname,
        gvalue: productForm.gvalue,
        gstock: productForm.gstock,
        cid: productForm.categoryId2,
        gdesc: productForm.desc
      }
      console.log("新增商品的描述",data.gdesc);
      const response = await axios.post('http://localhost:8090/goods/add_goods', data, {
        headers: {
          'Authorization': `${token}`,
          'Content-Type': 'application/json'
        }
      });
      if (response.data) { // 假设后端返回true表示成功
        ElMessage.success('商品添加成功！');
      } else {
        ElMessage.error('添加商品失败！');
      }
    } catch (error) {
      console.error('Error submitting product:', error);
      ElMessage.error('添加商品失败！');
    }
  };

  onMounted(() => {
    fetchCategories();
  });
</script>

<style scoped>
  .add-product-container {
    max-width: 600px;
    margin: 20px auto;
    padding: 20px;
  }
  .el-button {
    margin-right: 10px;
  }
  .selected-category-info {
    margin-top: 10px;
    font-size: 14px;
    color: #333;
  }
</style>
