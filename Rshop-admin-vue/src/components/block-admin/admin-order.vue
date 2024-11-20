<script setup>
import {ref, onMounted} from 'vue';
import axios from 'axios';

// 定义表格数据的响应式变量
const tableData = ref([]);

// 定义分页参数
const pageSize = ref(10);
const pageNum = ref(1);
const total = ref(0);

// 分页事件处理函数
const handleSizeChange = (val) => {
  console.log(`${val} items per page`);
  pageSize.value = val; // 更新每页显示的数量
  pageNum.value = 1; // 重置到第一页
  fetchUsers(); // 重新获取数据
};

const handleCurrentChange = (val) => {
  console.log(`current page: ${val}`);
  pageNum.value = val;
  fetchUsers(); // 重新获取数据
};

// 获取用户数据的函数
const fetchUsers = async () => {
  try {
    const response = await axios.get('http://localhost:8090/order/list', {
      params: {
        page: pageNum.value,
        size: pageSize.value
      }
    });
    // 假设后端返回的数据格式为 { list: [], total: 0 }
    console.log(response.data);
    tableData.value = response.data; // 假设后端返回的用户列表在 list 属性中
    total.value = tableData.value.length; // 假设后端返回的总数在 total 属性中
    console.log("获得数组：", tableData);
  } catch (error) {
    console.error('Error fetching users:', error);
  }
};

// 组件挂载时获取用户数据
onMounted(() => {
  fetchUsers();
});
</script>

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
      </el-table>
    </el-scrollbar>
    <el-pagination
      v-model:current-page="pageNum"
      v-model:page-size="pageSize"
      :page-sizes="[10, 20, 30, 40]"
      :total="total"
      layout="total, sizes, prev, pager, next, jumper"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </div>
</template>

<style scoped>
/* 自定义表格行高 */
.custom-table-row .el-table .el-table__body {
  --el-table-row-height: 100px; /* 设置行高为100px，可以根据需要调整 */
}
</style>
