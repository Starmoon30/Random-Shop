<script setup>
import {Discount, Edit, Menu, Promotion, Setting, User} from "@element-plus/icons-vue";
import { defineEmits } from 'vue';
// 定义 emits 对象
const emits = defineEmits(['menu-click']);

// 处理菜单选择事件
const handleSelect = (index, indexPath) => {
  // 触发 menu-click 事件，并传递索引
  emits('menu-click', index);
};
</script>

<template>
  <!-- 菜单组件，设置默认展开的菜单项 -->
  <el-menu
    :default-openeds="['1', '8']"
    style="background-color: var(--el-color-primary-light-9)"
    :collapse="isCollapse"
    @select="handleSelect"
  >
    <!-- 子菜单1，索引为1 -->
    <el-menu-item-group>
      <el-menu-item index="1"> <el-icon><User /></el-icon>用户管理</el-menu-item>
    </el-menu-item-group>
    <!-- 子菜单2，索引为2 -->
    <el-sub-menu index="2">
      <!-- 子菜单标题，包含一个菜单图标 -->
      <template #title>
        <el-icon onclick="openshell()"><Menu /></el-icon>货架管理
      </template>
      <el-menu-item-group>
        <!-- 菜单项2-1 -->
        <el-menu-item index="3">预备上架</el-menu-item>
        <!-- 菜单项2-2 -->
        <el-menu-item index="4">已上架</el-menu-item>
        <el-menu-item index="5">历史上架</el-menu-item>
      </el-menu-item-group>
    </el-sub-menu>
    <!-- 子菜单3，索引为3 -->
    <el-menu-item-group>
      <el-menu-item index="6"> <el-icon><setting /></el-icon>商品管理</el-menu-item>
    </el-menu-item-group>
    <el-menu-item-group>
      <el-menu-item index="7"> <el-icon><Promotion /></el-icon>订单管理</el-menu-item>
    </el-menu-item-group>
    <el-menu-item-group>
      <el-menu-item index="8"><el-icon><Discount /></el-icon>分类管理</el-menu-item>
    </el-menu-item-group>
    <el-menu-item-group>
      <el-menu-item index="9"><el-icon><svg xmlns="http://www.w3.org/2000/svg" width="128" height="128" viewBox="0 0 48 48"><g fill="none" stroke="#000000" stroke-linejoin="round" stroke-width="4"><path stroke-linecap="round" d="M7 42h36"/><path fill="#000000" d="M11 26.72V34h7.317L39 13.308L31.695 6z"/></g></svg></el-icon>账号管理</el-menu-item>
    </el-menu-item-group>
  </el-menu>
</template>

<style scoped>

</style>
