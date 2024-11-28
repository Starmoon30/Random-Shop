<script setup>
import { ref, onMounted } from 'vue';

// 定义表格的一行数据
//const tableData = ref([]);

// 定义获取数据的函数
/*const fetchData = async () => {
  try {
    const response = await axios.get('http://localhost:8090/list');
    tableData.value = response.data; // 将获取的数据赋值给tableData
  } catch (error) {
    console.error(error);
  }
};

// 在组件挂载时调用fetchData函数
onMounted(fetchData);*/
const item = {
  Good: 'cloth',
  WarehousingStatus: '1000',
  Number: '-2'

}
const tableData = ref(Array.from({ length: 100 }).fill(item))
const pageSize = 10;
const pageNum = 10;
const handleSizeChange = (val) => {
  console.log(`${val} items per page`)
}
const handleCurrentChange = (val) => {
  console.log(`current page: ${val}`)
}
</script>

<template>
  <!-- 滚动条组件 -->
  <div>
    <el-scrollbar>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="Good" label="Good"/>
        <el-table-column prop="WarehousingStatus" label="Warehousing Status"/>
        <el-table-column prop="Number" label="Number" />
      </el-table>
    </el-scrollbar>
    <el-pagination
      v-model:current-page="pageNum"
      v-model:page-size="pageSize"
      :page-sizes="[100, 200, 300, 400]"
      :size="size"
      :disabled="disabled"
      :background="background"
      layout="total, sizes, prev, pager, next, jumper"
      :total="400"
      :hide-on-single-page="true"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
  </div>

</template>

<style scoped>
</style>
