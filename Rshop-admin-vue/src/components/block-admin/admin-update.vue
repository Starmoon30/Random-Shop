<template>
  <div style="height: 100%; display: flex;">
    <ShopLogo style="background-color: #ffffff"></ShopLogo>
    <el-header style="width: 82%; text-align: right; font-size: 12px; display: flex; align-items: center; height: 80px; background-color: var(--el-color-primary-light-9);">
      <admin-head></admin-head>
    </el-header>
  </div>
  <el-main>
    <div v-if="pro" class="edit-product-info">
      <el-form :model="pro">
        <el-form-item label="商品名称">
          <el-input v-model="pro.gname"></el-input>
        </el-form-item>
        <!-- 商品图片展示和上传 -->
        <el-form-item label="商品图片">
          <div v-if="pro.pictures" class="image-list">
            <div v-for="(picObj, index) in pro.pictures" :key="index" class="image-item">
              <img :src="picObj.url" class="product-image" />
              <el-button type="text" icon="el-icon-close" @click="handleRemovePic(picObj.id, index)">
                <el-icon size="20"><Close /></el-icon>
              </el-button>
            </div>
          </div>
          <el-upload
            class="image-uploader"
            action="http://localhost:8090/pic/add"
            list-type="picture-card"
            :on-success="handleSuccess"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :before-upload="beforeUpload"
            :headers="headers"
          >
            <i class="el-icon-plus"></i>
            <el-icon size="48"><Plus /></el-icon>
          </el-upload>
        </el-form-item>
        <el-form-item label="价格">
          <el-input type="number" v-model="pro.gvalue"></el-input>
        </el-form-item>
        <el-form-item label="库存">
          <el-input type="number" v-model="pro.gstock"></el-input>
        </el-form-item>

        <upRichText :content="pro.gdesc" :onContentChange="updateDescription"></upRichText>

        <el-form-item>
          <el-button type="primary" @click="submitProductInfo">提交</el-button>
          <el-button type="primary" @click="goBack">返回</el-button>
        </el-form-item>
      </el-form>
    </div>
  </el-main>
</template>

<script lang="ts" setup>
defineProps({
  content: String
});
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import {ElMessage, ElMessageBox} from 'element-plus';
import axios from 'axios';
import ShopLogo from "@/components/Shop-Logo.vue";
import AdminHead from "@/components/block-admin/adminHead.vue";
import upRichText from "@/components/block-rich/UpdateRichText.vue";

const token = localStorage.getItem('token');
const route = useRoute();
const gid = Number(route.params.pid);
const pro = ref();
const newDesc = ref('');
const updateDescription = (content) => {
  newDesc.value = content; // 更新 newDesc 的值
  pro.value.desc = content; // 同时更新 pro 的 desc 属性
};

// 图片上传相关
const headers = {
  'Authorization': token
};

const handleSuccess = (response, file, fileList) => {
  console.log('上传成功:', response);
  pro.value.pictures = [...pro.value.pictures, `data:image/jpeg;base64,${response}`];
};

const handlePreview = (file) => {
  console.log('预览文件:', file);
};

const handleRemove = (file, fileList) => {
  console.log('移除文件:', file);
};

const beforeUpload = (file) => {
  const reader = new FileReader();
  reader.readAsDataURL(file);
  reader.onload = () => {
    const picMap = {
      gid: gid,
      data: reader.result.split(',')[1]
    };
    axios.post('http://localhost:8090/pic/add', picMap, {
      headers: {
        'Authorization': `${token}`,
      }
    }).then(response => {
      if (response.data) {
        ElMessage.success('图片上传成功');
        handleSuccess(response.data, file, pro.value.pictures);
      }
    }).catch(error => {
      console.error('图片上传失败:', error);
      ElMessage.error('图片上传失败');
    });
    return false; // 阻止自动上传
  };
};

// 删除图片
const handleRemovePic = async (picId, index) => {
  console.log("picId:", picId, "index:", index);
  await ElMessageBox.confirm('确认删除此图片吗？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  });
  try {
    const response = await axios.delete(`http://localhost:8090/pic/delete/${picId}`, {
      headers: {
        'Authorization': `${token}`,
      }
    });
    if (response.data) {
      pro.value.pictures.splice(index, 1);
      ElMessage.success('图片删除成功');
    }
  } catch (error) {
    console.error('删除图片失败:', error);
    ElMessage.error('删除图片失败');
  }
};

// 从后端获取商品信息
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
      pro.value = { ...fetchedProduct, pictures };
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
    console.log("返回：",response.data);
    // 遍历返回的Map对象并获取Base64编码图片数据
    const picArray = Object.entries(response.data).map(([picId, base64Data]) => {
      return { id: picId, url: `data:image/jpeg;base64,${base64Data}` };
    });

    return picArray;
  } catch (error) {
    console.error('获取商品图片失败:', error);
    return [];
  }
};

// 提交商品信息修改
const submitProductInfo = async () => {
  try {
    console.log("传输：",newDesc)
    const product ={
      gid: gid,
      name: pro.value.gname,
      value: parseInt(pro.value.gvalue),
      stock: parseInt(pro.value.gstock),
      cid: pro.value.cid,
      desc: newDesc.value
    }
    const response = await axios.post(`http://localhost:8090/goods/update_Ginfo`, product, {
      headers: {
        'Authorization': `${token}`,
      }
    });
    if (response) {
      ElMessage.success('商品信息修改成功');
    }
  } catch (error) {
    console.error("提交商品信息失败:", error);
    ElMessage.error('提交商品信息失败');
  }
};

// 在组件挂载时获取商品信息
onMounted(() => {
  const gid = Number(route.params.pid);
  console.log("得到gid：",gid);
  fetchProductData(gid);
});

const goBack = () => {
  history.back();
};
</script>

<style scoped>

.edit-product-info {
  max-width: 600px;
  margin: 20px auto;
}

.image-list {
  display: flex;
  flex-wrap: wrap;
  margin-bottom: 10px;
}

.image-item {
  display: inline-block;
  margin-right: 10px;
  position: relative;
}

.product-image {
  width: 50px; /* 根据需要调整 */
  height: 50px; /* 根据需要调整 */
  object-fit: cover;
  border: 1px solid #ccc;
}

.image-uploader {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.image-uploader .el-icon-plus {
  font-size: 28px;
  color: #8c939d;
  line-height: 50px;
  text-align: center;
}

.el-button.el-button--text {
  border: none;
  background: none;
  padding: 0;
  position: absolute;
  top: -5px;
  right: -5px;
  border-radius: 50%;
  width: 20px;
  height: 20px;
  line-height: 20px;
  color: #fff;
  background-color: #f56c6c;
  cursor: pointer;
}
</style>
