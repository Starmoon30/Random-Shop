<template>
  <div class="content-wrapper">
    <el-scrollbar>
      <el-table :data="tableData" class="custom-table-row" style="width: 100%">
        <el-table-column prop="uaccount" label="用户名"/>
        <el-table-column prop="upassword" label="密码"/>
        <el-table-column prop="uphone" label="联系电话"/>
        <el-table-column prop="uaddress" label="默认地址"/>
        <el-table-column label="角色">
          <template #default="{ row }">
            {{ roleByCategory(row.ucategory) }}
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template #default="scope">
            <el-button type="primary" size="small" @click="fetchAllOrders(scope.row)">查看订单</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-scrollbar>
    <el-pagination
      class="pagination"
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
import { ref, onMounted, computed } from 'vue';
import axios from 'axios';
import router from "@/router/index.js";

const allData = ref([]);
const tableData = ref([]);
const orderData = ref([]);
const pageSize = ref(10);
const pageNum = ref(1);
const total = ref(0);
const token = localStorage.getItem('token');

const roleByCategory = computed(() => (category) => {
  return category === 0 ? '管理员' : category === 1 ? '买家用户' : '未知角色';
});

const fetchAllUsers = async () => {
  try {
    const response = await axios.get('http://localhost:8090/user/list', {
      headers: {
        'Authorization': `${token}`,
      }
    });
    allData.value = response.data;
    total.value = allData.value.length;
    paginate(allData.value);
  } catch (error) {
    console.error('Error fetching users:', error);
  }
};

const fetchAllOrders = async (user) => {
  try {
    const response = await axios.get('http://localhost:8090/order/list', {
      headers: {
        'Authorization': `${token}`,
      }
    });
    const orders = response.data.filter(item => item.uaccount === user.uaccount);
    orderData.value = await Promise.all(orders.map(async (order) => {
      const gname = await fetchGnameByGid(order.gid);
      return {...order, gname};
    }));
    console.log("订单：", orderData.value);
    // 跳转到新组建，并传递订单数据
    await router.push({name: 'OrderDet', params: {uid: user.uaccount}});
  } catch (error) {
    console.error('Error fetching orders:', error);
  }
};

const fetchGnameByGid = async (gid) => {
  try {
    const response = await axios.post(`http://localhost:8090/goods/get_info`, { gid: gid }, {
      headers: {
        'Authorization': `${token}`,
      }
    });
    return response.data[0].gname;
  } catch (error) {
    console.error('Error fetching gname:', error);
    return '未知商品';
  }
};

const paginate = (data) => {
  const startIndex = (pageNum.value - 1) * pageSize.value;
  const endIndex = startIndex + pageSize.value;
  tableData.value = data.slice(startIndex, endIndex);
};

const handleSizeChange = (val) => {
  pageSize.value = val;
  paginate(allData.value);
};

const handleCurrentChange = (val) => {
  pageNum.value = val;
  paginate(allData.value);
};

onMounted(() => {
  fetchAllUsers();
});
</script>

<style scoped>
.custom-table-row .el-table .el-table__body {
  --el-table-row-height: 100px;
}

.content-wrapper {
  position: relative;
  overflow: auto;
  height: calc(100vh - 50px);
}

.pagination {
  position: fixed;
  bottom: 0;
  width: 100%;
  background-color: white;
  box-shadow: 0 -2px 5px rgba(0, 0, 0, 0.1);
  z-index: 1000;
}
</style>
