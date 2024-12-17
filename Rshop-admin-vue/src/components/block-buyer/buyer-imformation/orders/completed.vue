<template>
  <div>
    <el-scrollbar>
      <el-table :data="completedTableData" class="custom-table-row" style="width: 100%">
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
      v-model:current-page="completedPageNum"
      v-model:page-size="pageSize"
      :page-sizes="[5, 10, 20]"
      :total="completedTotal"
      layout="total, sizes, prev, pager, next, jumper"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import axios from 'axios';

// 定义响应式变量
const allData = ref([]);
const completedTableData = ref([]);
const completedTotal = ref(0);
const completedPageNum = ref(1); // 默认第一页
const pageSize = ref(10); // 默认每页10条

// 计算属性：基于当前分页参数获取数据切片
const paginatedData = computed(() => {
  const start = (completedPageNum.value - 1) * pageSize.value;
  const end = start + pageSize.value;
  return allData.value.slice(start, end);
});

// 获取所有数据的方法
const fetchData = async () => {
  try {
    const response = await axios.get('/api/orders/completed'); // 假设这是你的API路径
    allData.value = response.data;
    completedTotal.value = allData.value.length;
    // 更新表格数据
    completedTableData.value = paginatedData.value;
  } catch (error) {
    console.error('Failed to fetch data:', error);
  }
};

// 分页大小改变时触发
const handleSizeChange = (newSize) => {
  pageSize.value = newSize;
  // 更新表格数据
  completedTableData.value = paginatedData.value;
};

// 当前页码改变时触发
const handleCurrentChange = (newPage) => {
  completedPageNum.value = newPage;
  // 更新表格数据
  completedTableData.value = paginatedData.value;
};

// 组件挂载时获取数据
onMounted(() => {
  fetchData();
});
</script>
