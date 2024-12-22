<template>
  <div class="content-wrapper">
    <!-- 滚动条组件包裹内容区域 -->
    <el-scrollbar class="scrollbar-container">
      <el-table :data="tableData" class="custom-table-row" style="width: 100%">
        <el-table-column prop="oid" label="订单号"/>
        <el-table-column prop="gid" label="商品号"/>
        <el-table-column prop="uaccount" label="下单用户"/>
        <el-table-column prop="ophone" label="联系电话"/>
        <el-table-column prop="oaddress" label="地址"/>
        <el-table-column prop="oremark" label="备注"/>
        <el-table-column prop="ostate" label="订单阶段"/>
        <!-- 新增的按钮列 -->
        <el-table-column label="操作">
          <template #default="scope">
            <el-button type="primary" size="small" @click="acceptOrder(scope.row)">接受订单</el-button>
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
import {ref, onMounted} from 'vue';
import axios from 'axios';

// 定义全部数据的响应式变量
const allData = ref([]);
// 定义表格数据的响应式变量
const tableData = ref([]);
// 定义分页参数
const pageSize = ref(10);
const pageNum = ref(1);
const total = ref(0);
const token = localStorage.getItem('token');

// 获取所有订单数据的函数
const fetchAllOrders = async () => {
  try {
    const response = await axios.get('http://localhost:8090/order/list', {
      headers: {
        'Authorization': `${token}`,
      }
    });
    allData.value = response.data.filter(item => item.ostate === 0); // 过滤出ostate为0的数据
    total.value = allData.value.length; // 总数据量
    paginate(); // 进行分页
  } catch (error) {
    console.error('Error fetching orders:', error);
  }
};

// 分页函数
const paginate = () => {
  const startIndex = (pageNum.value - 1) * pageSize.value;
  const endIndex = startIndex + pageSize.value;
  tableData.value = allData.value.slice(startIndex, endIndex);
};

// 分页事件处理函数
const handleSizeChange = (val) => {
  pageSize.value = val;
  paginate();
};

const handleCurrentChange = (val) => {
  pageNum.value = val;
  paginate();
};

// 接受订单的方法
const acceptOrder = async (order) => {
  try {
    const response = await axios.post('http://localhost:8090/order/update_Ostate', {id: order.oid, state: 1}, {
      headers: {
        'Authorization': `${token}`,
      }
    });
    if (response.data) {
      console.log('接受成功');
      // 重新获取商品列表
      fetchAllOrders();
    }
    else {
      console.error('Error accepting order:', response.data.message);
    }
  } catch (error) {
    console.error('Error accepting order:', error);
  }
};

// 组件挂载时获取所有订单数据
onMounted(() => {
  fetchAllOrders();
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
