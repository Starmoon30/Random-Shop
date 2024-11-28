<template>
  <div>
    <el-table :data="orderData" class="custom-table-row" style="width: 100%">
      <el-table-column prop="oid" label="订单ID" />
      <el-table-column prop="gid" label="商品ID" />
      <el-table-column prop="uaccount" label="用户账号" />
      <el-table-column prop="ophone" label="电话" />
      <el-table-column prop="oaddress" label="收货地址" />
      <el-table-column prop="oremark" label="备注" />
      <el-table-column prop="ostate" label="订单状态" />
      <el-table-column label="操作">
        <template #default="scope">
          <el-button type="primary" size="small" @click="acceptOrder(scope.row)">接受订单</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

const orderData = ref([]);

const fetchOrderData = async () => {
  const response = await axios.post('/order/list');
  orderData.value = response.data;
};

const acceptOrder = async (order) => {
  const response = await axios.post('/order/update_Ostate', { id: order.oid, state: 2 });  // Change state to accepted
  if (response.status === 200) {
    fetchOrderData();  // Refresh the order list after accepting
  }
};

onMounted(() => {
  fetchOrderData();
});
</script>

<style scoped>
/* 订单管理页面样式 */
</style>
