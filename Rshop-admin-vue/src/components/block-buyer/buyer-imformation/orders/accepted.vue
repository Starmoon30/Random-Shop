<template>
  <div class="content-wrapper">
    <!-- 滚动条组件包裹内容区域 -->
    <el-scrollbar class="scrollbar-container">
      <el-table :data="tableData" class="custom-table-row" style="width: 100%">
        <el-table-column prop="oid" label="OID"/>
        <el-table-column prop="gid" label="GID"/>
<!--        <el-table-column prop="gname" label="GName"/>  &lt;!&ndash; 商品名称 &ndash;&gt;-->
        <el-table-column prop="ophone" label="OPhone"/>
        <el-table-column prop="oaddress" label="OAddress"/>
        <el-table-column prop="oremark" label="ORemark"/>
        <el-table-column label="操作">
          <template #default="scope">
            <div style="justify-content: center;align-items: center;">
              <el-button type="primary" size="small" style="background-color: #ea4444" @click="canselOrder(scope.row)">取消接受</el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </el-scrollbar>
  </div>
  <!-- 分页条固定在底部 -->
  <div class="pagination-container">
    <el-pagination
      v-model:current-page="pageNum"
      v-model:page-size="pageSize"
      :page-sizes="[5, 10, 20]"
      :total="total"
      layout="total, sizes, prev, pager, next, jumper"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { jwtDecode } from 'jwt-decode';
import { ElMessage } from 'element-plus';

// 定义数据的响应式变量
const allData = ref([]);
const tableData = ref([]);
const goodsList = ref([]);  // 商品列表

// 分页相关变量
const pageSize = ref(10);
const pageNum = ref(1);
const total = ref(0);

const token = localStorage.getItem('token');
const decoded = jwtDecode(token);

// 获取商品列表
const fetchGoodsList = async () => {
  try {
    const response = await axios.get('http://localhost:8090/goods/get_info', {
      headers: {
        'Authorization': `${token}`,
      }
    });
    goodsList.value = response.data;  // 假设返回的商品列表数据在 data 中
  } catch (error) {
    console.error('Error fetching goods list:', error);
  }
};

// 获取订单数据并根据 gid 加载商品名称
const fetchAllUsers = async () => {
  try {
    const response = await axios.get('http://localhost:8090/order/list', {
      headers: {
        'Authorization': `${token}`,
      }
    });

    // 获取当前用户的订单数据
    const orders = response.data.filter(item => item.uaccount === decoded.account && item.ostate === 1);

    // 为每个订单添加商品名称
    for (let order of orders) {
      // 查找对应的商品名称
      const product = goodsList.value.find(item => item.gid === order.gid);
      order.gname = product ? product.gname : '未知商品';  // 如果找不到商品，默认设置为 '未知商品'
    }

    allData.value = orders;
    total.value = orders.length;
    paginate(orders);
  } catch (error) {
    console.error('Error fetching orders:', error);
  }
};

// 分页函数
const paginate = (data) => {
  const startIndex = (pageNum.value - 1) * pageSize.value;
  const endIndex = startIndex + pageSize.value;
  tableData.value = data.slice(startIndex, endIndex);
};

// 分页事件处理函数
const handleSizeChange = (val) => {
  pageSize.value = val;
  paginate(allData.value);
};

const handleCurrentChange = (val) => {
  pageNum.value = val;
  paginate(allData.value);
};

// 取消订单
const canselOrder = async (order) => {
  try {
    const response = await axios.post('http://localhost:8090/order/update_Ostate', {
      id: order.oid,
      state: 0
    }, {
      headers: {
        'Authorization': `${token}`,
      }
    });

    if (response.data) {
      ElMessage({
        message: '取消成功',
        type: 'success'
      });
      fetchAllUsers();
    } else {
      ElMessage({
        message: response.data.message || '取消订单失败',
        type: 'error'
      });
    }
  } catch (error) {
    console.error('Error cancelling order:', error);
    ElMessage({
      message: '取消订单时发生错误',
      type: 'error'
    });
  }
};

// 组件挂载时获取商品列表和订单数据
onMounted(() => {
  fetchGoodsList();  // 获取商品列表
  fetchAllUsers();   // 获取订单数据
});
</script>

<style scoped>
/* 自定义表格行高 */
.custom-table-row .el-table .el-table__body {
  --el-table-row-height: 100px; /* 设置行高为100px，可以根据需要调整 */
}

/* 滚动条容器样式 */
.scrollbar-container {
  height: calc(100vh - 60px); /* 减去分页条的高度 */
  overflow-y: auto; /* 启用垂直滚动 */
}

/* 分页条固定在底部的样式 */
.pagination-container {
  position: fixed;
  bottom: 0;
  width: 100%;
  background-color: #fff; /* 根据需要调整背景色 */
  z-index: 1000; /* 确保分页条在最上层 */
}

/* 内容区域包裹器，用于计算滚动条高度 */
.content-wrapper {
  padding-bottom: 60px; /* 分页条的高度，确保内容不会被遮挡 */
}
</style>
