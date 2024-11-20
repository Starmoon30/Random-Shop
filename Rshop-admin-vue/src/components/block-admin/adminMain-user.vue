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

// 获取所有用户数据的函数
const fetchAllUsers = async () => {
  try {
    const response = await axios.get('http://localhost:8090/user/list');
    allData.value = response.data; // 假设后端返回所有用户数据
    total.value = allData.value.length; // 总数据量
    paginate(allData.value); // 进行分页
  } catch (error) {
    console.error('Error fetching users:', error);
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

// 组件挂载时获取所有用户数据
onMounted(() => {
  fetchAllUsers();
});
</script>

<template>
  <!-- 滚动条组件 -->
  <div>
    <el-scrollbar>
      <el-table :data="tableData" class="custom-table-row" style="width: 100%">
        <el-table-column prop="uaccount" label="UAccount"/>
        <el-table-column prop="upassword" label="UPassword"/>
        <el-table-column prop="uphone" label="UPhone"/>
        <el-table-column prop="uaddress" label="UAddress"/>
        <el-table-column prop="ucategory" label="UCategory"/>
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

<style scoped>
/* 自定义表格行高 */
.custom-table-row .el-table .el-table__body {
  --el-table-row-height: 100px; /* 设置行高为100px，可以根据需要调整 */
}
</style>
