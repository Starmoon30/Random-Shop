<script setup>

</script>

<template>
  <div>
    <h2>购买商品</h2>
    <label>商品名称</label><input type="text" v-model="productName">
    <label>购买数量</label><input type="number" v-model="quantity">
    <label>确认金额</label><input type="text" v-model="amount">
    <label>可交易时间</label><input type="datetime-local" v-model="tradeTime">
    <label>交易地点</label><input type="text" v-model="location">
    <button @click="purchase">购买商品</button>
  </div>
</template>

<script>
export default {
  data() {
    return { productName: '', quantity: 0, amount: '', tradeTime: '', location: '' };
  },
  methods: {
    purchase() {
      alert("购买成功！商品: " + this.productName);
    }
  }
};
</script>

<style scoped>

</style>
