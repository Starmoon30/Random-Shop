<template>
  <div class="content-wrapper">
    <!-- 滚动条组件包裹内容区域 -->
    <el-scrollbar class="scrollbar-container">
      <el-table :data="tableData" class="custom-table-row" style="width: 100%">
        <el-table-column prop="oid" label="OID"/>
        <el-table-column prop="gid" label="GID"/>
<!--        <el-table-column prop="uaccount" label="UAccount"/>-->
        <el-table-column prop="ophone" label="OPhone"/>
        <el-table-column prop="oaddress" label="OAddress"/>
        <el-table-column prop="oremark" label="ORemark"/>
<!--        <el-table-column prop="ostate" label="OState"/>-->
        <!-- 新增的按钮列，修改按钮居中 -->
        <el-table-column label="操作">
          <template #default="scope">
            <div class="action-buttons">
              <el-button type="primary" size="small" @click="editOrder(scope.row)">修改</el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </el-scrollbar>
  </div>
  <!-- 分页条固定在底部 -->
  <div class="pagination-container">
    <el-pagination
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
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { jwtDecode } from 'jwt-decode';

// 定义全部数据的响应式变量
const allData = ref([]);
// 定义表格数据的响应式变量
const tableData = ref([]);
// 定义分页参数
const pageSize = ref(10);
const pageNum = ref(1);
const total = ref(0);
const token = localStorage.getItem('token');

// 获取当前账号信息
const getCurrentAccount = () => {
  try {
    const decoded = jwtDecode(token);  // 解码token
    return decoded.account;  // 获取当前账号
  } catch (error) {
    console.error('Error decoding token:', error);
    return null;
  }
};

// 获取当前账号的订单数据
const fetchOrders = async () => {
  const currentAccount = getCurrentAccount();
  if (!currentAccount) return;

  try {
    const response = await axios.get('http://localhost:8090/order/list', {
      headers: {
        'Authorization': `${token}`,
      }
    });
    // 过滤出状态为0且属于当前账号的订单
    allData.value = response.data.filter(item => item.ostate === 0 && item.uaccount === currentAccount);
    total.value = allData.value.length; // 总数据量
    paginate(allData.value); // 进行分页
  } catch (error) {
    console.error('Error fetching orders:', error);
  }
};

// 分页函数
const paginate = (data) => {
  const startIndex = (pageNum.value - 1) * pageSize.value;
  const endIndex = startIndex + pageSize.value;
  tableData.value = data.slice(startIndex, endIndex);
};

// 分页事件处理函数
const handleSizeChange = (val) => {
  pageSize.value = val;
  paginate(allData.value);
};

const handleCurrentChange = (val) => {
  pageNum.value = val;
  paginate(allData.value);
};

// 修改订单的方法
const editOrder = async (order) => {
  // 示例：可以在这里展示一个编辑表单，修改订单信息
  const updatedOrder = {
    id: order.oid,
    phone: prompt('请输入新的电话', order.ophone),
    address: prompt('请输入新的地址', order.oaddress),
    remark: prompt('请输入新的备注', order.oremark),
  };

  try {
    const response = await axios.post('http://localhost:8090/order/update_Oinfo', updatedOrder, {
      headers: {
        'Authorization': `${token}`,
      }
    });

    if (response.data) {
      console.log('订单修改成功');
      fetchOrders();  // 修改后重新获取订单数据
    } else {
      console.error('修改失败:', response.data.message);
    }
  } catch (error) {
    console.error('Error updating order:', error);
  }
};

// 组件挂载时获取所有订单数据
onMounted(() => {
  fetchOrders();
});
</script>

<style scoped>
/* 自定义表格行高 */
.custom-table-row .el-table .el-table__body {
  --el-table-row-height: 100px; /* 设置行高为100px，可以根据需要调整 */
}

/* 滚动条容器样式 */
.scrollbar-container {
  height: calc(100vh - 60px); /* 减去分页条的高度 */
  overflow-y: auto; /* 启用垂直滚动 */
}

/* 分页条固定在底部的样式 */
.pagination-container {
  position: fixed;
  bottom: 0;
  width: 100%;
  background-color: #fff; /* 根据需要调整背景色 */
  z-index: 1000; /* 确保分页条在最上层 */
}

/* 内容区域包裹器，用于计算滚动条高度 */
.content-wrapper {
  padding-bottom: 60px; /* 分页条的高度，确保内容不会被遮挡 */
}

/* 操作列按钮居中 */
.action-buttons {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
