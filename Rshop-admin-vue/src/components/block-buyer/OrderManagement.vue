<template>
  <!-- 滚动条组件 -->
  <div>
    <el-scrollbar>
      <el-table :data="tableData" class="custom-table-row" style="width: 100%">
        <el-table-column prop="oid" label="OID"/>
        <el-table-column prop="gid" label="GID"/>
        <el-table-column prop="uaccount" label="UAccount"/>
        <el-table-column prop="ophone" label="OPhone"/>
        <el-table-column prop="oaddress" label="OAddress"/>
        <el-table-column prop="oremark" label="ORemark"/>
        <el-table-column prop="ostate" label="OState"/>
        <!-- 新增的按钮列 -->
        <el-table-column label="操作">
          <template #default="scope">
            <el-button type="primary" size="small" @click="acceptOrder(scope.row)">接受订单</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-scrollbar>
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

// 定义全部数据的响应式变量
const allData = ref([]);
// 定义表格数据的响应式变量
const tableData = ref([]);
// 定义分页参数
const pageSize = ref(10);
const pageNum = ref(1);
const total = ref(0);

// 获取存储在 localStorage 中的用户信息
const userInfo = JSON.parse(localStorage.getItem('userInfo'));

// 获取所有用户数据的函数
const fetchAllUsers = async () => {
  try {
    const response = await axios.get('http://localhost:8090/order/list');
    allData.value = response.data; // 假设后端返回所有订单数据

    // 根据 localStorage 中的用户信息过滤订单数据
    if (userInfo) {
      // 假设根据用户的 `role` 或 `account` 来筛选订单数据
      // 例如：只显示与当前用户相关的订单
      allData.value = allData.value.filter(order => {
        // 你可以根据需要调整筛选逻辑
        return order.uaccount === userInfo.account; // 这里只展示当前用户的订单
      });
    }

    total.value = allData.value.length; // 更新总数据量
    paginate(allData.value); // 进行分页
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

// 接受订单的方法
const acceptOrder = async (order) => {
  try {
    const response = await axios.post('http://localhost:8090/order/accept', {oid: order.oid});
    if (response.data.success) {
      // 假设后端返回success字段表示操作成功
      fetchAllUsers(); // 重新获取订单数据
    } else {
      // 处理失败情况
      console.error('Error accepting order:', response.data.message);
    }
  } catch (error) {
    console.error('Error accepting order:', error);
  }
};

// 组件挂载时获取所有订单数据
onMounted(() => {
  fetchAllUsers();
});
</script>

<style scoped>
/* 自定义表格行高 */
.custom-table-row .el-table .el-table__body {
  --el-table-row-height: 100px; /* 设置行高为100px，可以根据需要调整 */
}
</style>
