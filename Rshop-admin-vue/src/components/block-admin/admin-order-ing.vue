<template>
  <div class="content-wrapper">
    <!-- 滚动条组件包裹内容区域 -->
    <el-scrollbar class="scrollbar-container">
      <el-table :data="tableData" class="custom-table-row" style="width: 100%">
        <el-table-column prop="oid" label="订单号"/>
        <el-table-column prop="gid" label="商品号"/>
        <el-table-column prop="gname" label="商品名称"/>
        <el-table-column prop="uaccount" label="下单用户"/>
        <el-table-column prop="ophone" label="联系电话"/>
        <el-table-column prop="oaddress" label="地址"/>
        <el-table-column prop="oremark" label="备注"/>
        <!-- 新增的按钮列 -->
        <el-table-column label="操作">
          <template #default="scope">
            <div style="justify-content: center;align-items: center;">
              <el-button v-if="scope.row.ostate === 1" type="primary" size="small" @click="acceptOrder(scope.row)">备货</el-button>
              <el-button v-if="scope.row.ostate === 2" type="primary" size="small" @click="acceptOrder(scope.row)">发货</el-button>
              <el-button v-if="scope.row.ostate === 3" type="primary" size="small" @click="acceptOrder(scope.row)">完成</el-button>
              <el-button type="primary" size="small" style="background-color: #ea4444" @click="canselOrder(scope.row)">取消接受</el-button>
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
import {ElMessage} from "element-plus";

// 定义全部数据的响应式变量
const allData = ref([]);
// 定义表格数据的响应式变量
const tableData = ref([]);
// 定义分页参数
const pageSize = ref(10);
const pageNum = ref(1);
const total = ref(0);
const token = localStorage.getItem('token');
// 获取所有用户数据的函数
const fetchOrders = async () => {
  try {
    const response = await axios.get('http://localhost:8090/order/list', {
      headers: {
        'Authorization': `${token}`,
      }
    });
    const orders = response.data.filter(item => item.ostate === 1 || item.ostate === 2 || item.ostate === 3);
    const allDataWithGname = await Promise.all(orders.map(async (order) => {
      const gname = await fetchGnameByGid(order.gid);
      return { ...order, gname };
    }));
    allData.value = allDataWithGname;
    total.value = allData.value.length; // 总数据量
    paginate(allData.value); // 进行分页
  } catch (error) {
    console.error('Error fetching orders:', error);
  }
};
const fetchGnameByGid = async (gid) => {
  try {
    const response = await axios.post(`http://localhost:8090/goods/get_info`, {gid:gid},{
      headers: {
        'Authorization': `${token}`,
      }
    });
    return response.data[0].gname; // 假设返回的数据中包含商品名
  } catch (error) {
    console.error('Error fetching gname:', error);
    return '未知商品'; // 出错时返回默认值
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

// 完成订单的方法
const acceptOrder = async (order) => {
  try {
    const state = parseInt(order.ostate) + 1;
    console.log("下一阶段：",state);
    const response = await axios.post('http://localhost:8090/order/update_Ostate', {id: order.oid, state: state}, {
      headers: {
        'Authorization': `${token}`,
      }
    });
    if (response.data) {
      if(state === 2){
        ElMessage.success("备货完成，待发货")
      }else if(state === 3){
        ElMessage.success("已发货，待收货")
      }else{
        ElMessage.success("已收货，订单完成")
      }
      // 重新获取商品列表
      fetchOrders();
    }
    else {
      console.error('Error accepting order:', response.data.message);
    }
  } catch (error) {
    console.error('Error accepting order:', error);
  }
};
// 取消订单的方法
const canselOrder = async (order) => {
  try {
    const response = await axios.post('http://localhost:8090/order/update_Ostate', {id: order.oid, state: 0}, {
      headers: {
        'Authorization': `${token}`,
      }
    });
    if (response.data) {
      ElMessage.success("订单已取消")
      // 重新获取商品列表
      fetchOrders();
    }
    else {
      console.error('Error accepting order:', response.data.message);
    }
  } catch (error) {
    console.error('Error accepting order:', error);
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
</style>
