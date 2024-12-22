<script setup>
import { Discount, Edit, Menu, Promotion, Setting, User } from "@element-plus/icons-vue";
import { defineEmits, ref } from 'vue';

// 定义 emits 对象
const emits = defineEmits(['menu-click', 'shelf-click', 'order-click']);

// 用于控制子菜单展开状态
const isShelfMenuOpen = ref(false);
const isOrderMenuOpen = ref(false);

// 处理菜单选择事件
const handleSelect = (index) => {
  emits('menu-click', index);
};

// 处理货架管理点击事件
const handleShelfClick = () => {
  // 切换货架管理子菜单的展开状态
  isShelfMenuOpen.value = !isShelfMenuOpen.value;
  emits('shelf-click');
};

// 处理订单管理点击事件
const handleOrderClick = () => {
  // 切换订单管理子菜单的展开状态
  isOrderMenuOpen.value = !isOrderMenuOpen.value;
  emits('order-click');
};
</script>

<template>
  <el-menu
    :default-openeds="['1', '11']"
    style="background-color: var(--el-color-primary-light-9)"
    :collapse="isCollapse"
    @select="handleSelect"
  >
    <el-menu-item-group>
      <el-menu-item index="1">
        <el-icon><User /></el-icon>用户管理
      </el-menu-item>
    </el-menu-item-group>

    <el-sub-menu v-model:opened="isShelfMenuOpen">
      <template #title>
        <el-icon @click.prevent="handleShelfClick"><Menu /></el-icon>货架管理
      </template>
      <el-menu-item-group>
        <el-menu-item index="2">预备上架</el-menu-item>
        <el-menu-item index="3">已上架</el-menu-item>
        <el-menu-item index="4">历史上架</el-menu-item>
      </el-menu-item-group>
    </el-sub-menu>

    <el-menu-item-group>
      <el-menu-item index="5">
        <el-icon><Setting /></el-icon>商品管理
      </el-menu-item>
    </el-menu-item-group>

    <el-sub-menu v-model:opened="isOrderMenuOpen">
      <template #title>
        <el-icon @click.prevent="handleOrderClick"><Promotion /></el-icon>订单管理
      </template>
      <el-menu-item-group>
        <el-menu-item index="6">未接受订单</el-menu-item>
        <el-menu-item index="7">已接受订单</el-menu-item>
        <el-menu-item index="8">已完成订单</el-menu-item>
      </el-menu-item-group>
    </el-sub-menu>

    <el-menu-item-group>
      <el-menu-item index="9">
        <el-icon><Discount /></el-icon>分类管理
      </el-menu-item>
    </el-menu-item-group>

    <el-menu-item-group>
      <el-menu-item index="10">
        <el-icon><Histogram /></el-icon>库存历史管理
      </el-menu-item>
    </el-menu-item-group>

    <el-menu-item-group>
      <el-menu-item index="11">
        <el-icon><Edit /></el-icon>账号管理
      </el-menu-item>
    </el-menu-item-group>
  </el-menu>
</template>

<style scoped>
</style>
