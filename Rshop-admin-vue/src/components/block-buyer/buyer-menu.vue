<script setup>
import { Edit, Menu, Setting, User } from "@element-plus/icons-vue";
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

// 处理订单管理点击事件
const handleOrderClick = () => {
  // 切换订单管理子菜单的展开状态
  isOrderMenuOpen.value = !isOrderMenuOpen.value;
  emits('order-click');
};
</script>

<template>
  <!-- 菜单组件，设置默认展开的菜单项 -->
  <el-menu
    :default-openeds="['1', '6']"
    style="background-color: var(--el-color-primary-light-9)"
    :collapse="isCollapse"
    @select="handleSelect"
  >
    <el-menu-item-group>
      <el-menu-item index="1"> <el-icon><User /></el-icon>个人信息</el-menu-item>
    </el-menu-item-group>
    <el-sub-menu v-model:opened="isShelfMenuOpen">
      <template #title>
        <el-icon @click.prevent="handleOrderClick"><Menu /></el-icon>订单管理
      </template>
      <el-menu-item-group>
        <el-menu-item index="2">待接受订单</el-menu-item>
        <el-menu-item index="3">已接受订单</el-menu-item>
        <el-menu-item index="4">历史订单</el-menu-item>
      </el-menu-item-group>
    </el-sub-menu>
    <el-menu-item-group>
      <el-menu-item index="5"> <el-icon><setting /></el-icon>收货管理</el-menu-item>
    </el-menu-item-group>
    <el-menu-item-group>
      <el-menu-item index="6"><el-icon><Edit /></el-icon>账号管理</el-menu-item>
    </el-menu-item-group>
  </el-menu>
</template>
<style scoped>
</style>
