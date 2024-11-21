<script setup>

</script>

<template>
  <div>
    <h2>历史下单记录</h2>
    <button @click="viewOrderHistory">查看历史订单</button>
    <ul>
      <li v-for="order in orderHistory" :key="order.id">{{ order.details }}</li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return { orderHistory: [] };
  },
  methods: {
    viewOrderHistory() {
      this.orderHistory = [
        { id: 1, details: '订单1 - 商品A - 数量: 1' },
        { id: 2, details: '订单2 - 商品B - 数量: 3' }
      ];
    }
  }
};
</script>

<style scoped>

</style>
