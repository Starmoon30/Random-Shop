<template>
  <div>
    <el-table :data="shippingData" class="custom-table-row" style="width: 100%">
      <el-table-column prop="oid" label="订单ID" />
      <el-table-column prop="uaccount" label="用户账号" />
      <el-table-column prop="ophone" label="电话" />
      <el-table-column prop="oaddress" label="收货地址" />
      <el-table-column prop="oremark" label="备注" />
      <el-table-column label="操作">
        <template #default="scope">
          <el-button type="primary" size="small" @click="shipOrder(scope.row)">发货</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

const shippingData = ref([]);

const fetchShippingData = async () => {
  const response = await axios.post('/order/show_Buy', { account: 'A1' });  // Replace with actual user account
  shippingData.value = response.data;
};

const shipOrder = async (order) => {
  const response = await axios.post('/order/update_Ostate', { id: order.oid, state: 1 });  // Change state to shipped
  if (response.status === 200) {
    fetchShippingData();  // Refresh the shipping list after shipping
  }
};

onMounted(() => {
  fetchShippingData();
});
</script>

<style scoped>
/* 收货管理页面样式 */
</style>
