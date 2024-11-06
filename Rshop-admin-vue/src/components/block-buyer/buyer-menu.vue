<script setup>
import {DocumentCopy, Edit, Menu, Promotion, Setting, User} from "@element-plus/icons-vue";
</script>

<template>
  <!-- 菜单组件，设置默认展开的菜单项 -->
  <el-menu
    :default-openeds="['1', '5']" style="background-color: var(--el-color-primary-light-9)"
    :collapse="isCollapse">
    <!-- 子菜单1，索引为1 -->
    <el-menu-item-group>
      <el-menu-item index="1"> <el-icon><User /></el-icon>个人信息</el-menu-item>
    </el-menu-item-group>
    <!-- 子菜单2，索引为2 -->
    <el-menu-item-group>
      <el-menu-item index="2"> <el-icon><DocumentCopy /></el-icon>订单管理</el-menu-item>
    </el-menu-item-group>
    <!-- 子菜单3，索引为3 -->
    <el-menu-item-group>
      <el-menu-item index="3"> <el-icon><setting /></el-icon>收货管理</el-menu-item>
    </el-menu-item-group>
    <el-menu-item-group>
      <el-menu-item index="5"><el-icon><Edit /></el-icon>账号管理</el-menu-item>
    </el-menu-item-group>
  </el-menu>
</template>

<style scoped>

</style>
