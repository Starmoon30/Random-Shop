<script setup>
import {Edit, Menu, Promotion, Setting, User} from "@element-plus/icons-vue";
</script>

<template>
  <!-- 菜单组件，设置默认展开的菜单项 -->
  <el-menu
    :default-openeds="['1', '5']" style="background-color: var(--el-color-primary-light-9)"
    :collapse="isCollapse">
    <!-- 子菜单1，索引为1 -->
    <el-menu-item-group>
      <el-menu-item index="1"> <el-icon><User /></el-icon>用户管理</el-menu-item>
    </el-menu-item-group>
    <!-- 子菜单2，索引为2 -->
    <el-sub-menu index="2">
      <!-- 子菜单标题，包含一个菜单图标 -->
      <template #title>
        <el-icon><Menu /></el-icon>货架管理
      </template>
      <el-menu-item-group>
        <!-- 菜单项2-1 -->
        <el-menu-item index="2-1">预备上架</el-menu-item>
        <!-- 菜单项2-2 -->
        <el-menu-item index="2-2">已上架</el-menu-item>
        <el-menu-item index="2-3">历史上架</el-menu-item>
      </el-menu-item-group>
    </el-sub-menu>
    <!-- 子菜单3，索引为3 -->
    <el-menu-item-group>
      <el-menu-item index="3"> <el-icon><setting /></el-icon>商品管理</el-menu-item>
    </el-menu-item-group>
    <el-menu-item-group>
      <el-menu-item index="4"> <el-icon><Promotion /></el-icon>订单管理</el-menu-item>
    </el-menu-item-group>
    <el-menu-item-group>
      <el-menu-item index="5"><el-icon><Edit /></el-icon>账号管理</el-menu-item>
    </el-menu-item-group>
  </el-menu>
</template>

<style scoped>

</style>
