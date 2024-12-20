<template>
  <div class="content-wrapper">
    <!-- 滚动条组件包裹内容区域 -->
    <el-scrollbar class="scrollbar-container">
      <el-table :data="tableData" class="custom-table-row" style="width: 100%">
        <el-table-column prop="oid" label="订单号"/>
        <el-table-column prop="gid" label="商品号"/>
        <el-table-column prop="gname" label="商品名称"/>
        <el-table-column prop="ophone" label="联系电话"/>
        <el-table-column prop="oaddress" label="地址"/>
        <el-table-column prop="oremark" label="备注"/>
        <el-table-column label="操作">
          <template #default="scope">
            <el-dialog
              title="修改订单信息"
              v-model="dialogVisible"
              width="30%"
              modal="false"
              @close="resetForm"
            >
              <el-form :model="orderForm" :rules="rules" ref="formRef">
                <el-form-item label="联系电话" prop="phone">
                  <el-input v-model="orderForm.phone" ></el-input>
                </el-form-item>
                <el-form-item label="地址" prop="address">
                  <el-input v-model="orderForm.address"></el-input>
                </el-form-item>
                <el-form-item label="备注">
                  <el-input
                    type="textarea"
                    v-model="orderForm.remark"
                  ></el-input>
                </el-form-item>
              </el-form>
              <template v-slot:footer>
                <span  class="dialog-footer">
                  <el-button type="primary" @click="submitOrder">提交</el-button>
                </span>
</template>
            </el-dialog>
            <el-button type="primary" size="small" @click="editOrder(scope.row)">修改订单</el-button>
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
import {ref, onMounted} from 'vue';
import axios from 'axios';
import {jwtDecode} from "jwt-decode";
import {ElForm, ElMessage} from "element-plus";

// 定义全部数据的响应式变量
const allData = ref([]);
// 定义表格数据的响应式变量
const tableData = ref([]);
// 定义分页参数
const pageSize = ref(10);
const pageNum = ref(1);
const total = ref(0);
const token = localStorage.getItem('token');
let account = '';

const rules = ref({
  phone: [
    { required: true, message: '请输入联系电话', trigger: 'blur' }
  ],
  address: [
    { required: true, message: '请输入地址', trigger: 'blur' }
  ]
});
const formRef = ref(null);
// 获取所有订单数据的函数
const fetchAllOrders = async () => {
  try {
    const response = await axios.get('http://localhost:8090/order/list', {
      headers: {
        'Authorization': `${token}`,
      }
    });
    const orders = response.data.filter(item => item.ostate === 0 && item.uaccount === account);
    const allDataWithGname = await Promise.all(orders.map(async (order) => {
      const gname = await fetchGnameByGid(order.gid);
      return { ...order, gname };
    }));
    allData.value = allDataWithGname;
    total.value = allData.value.length;
    paginate();
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
const paginate = () => {
  const startIndex = (pageNum.value - 1) * pageSize.value;
  const endIndex = startIndex + pageSize.value;
  tableData.value = allData.value.slice(startIndex, endIndex);
};

// 分页事件处理函数
const handleSizeChange = (val) => {
  pageSize.value = val;
  paginate();
};

const handleCurrentChange = (val) => {
  pageNum.value = val;
  paginate();
};
// 定义对话框的可见性
const dialogVisible = ref(false);
// 定义表单数据的响应式变量

const orderForm = ref({
  phone: '',
  address: '',
  remark: ''
});

// 编辑订单时设置表单数据
const editOrder = (row) => {
  orderForm.value = {
    id: row.oid, // 假设订单号是id
    phone: row.ophone,
    address: row.oaddress,
    remark: row.oremark
  };
  dialogVisible.value = true;
};

// 提交表单数据
const submitOrder = async () => {
  await formRef.value.validate(async (valid) => {
    if (valid) {
      console.log('Valid:', valid);
      console.log('Order Form:', orderForm.value);
      try {
        const response = await axios.post('http://localhost:8090/order/update_Oinfo', orderForm.value, {
          headers: {
            'Authorization': `${token}`,
          }
        });
        if (response.data) {
          await fetchAllOrders(); // 重新获取订单数据
          ElMessage.success('订单修改成功！');
          dialogVisible.value = false; // 关闭对话框
        } else {
          ElMessage.error('订单修改失败！');
        }
      } catch (error) {
        console.error('Error updating order:', error);
      }
    } else {
      ElMessage.error('请填写所有必填项！');
    }
  });
};

// 重置表单数据
const resetForm = () => {
  orderForm.value = {
    phone: '',
    address: '',
    remark: ''
  };
};
// 组件挂载时获取所有订单数据
onMounted(() => {
  fetchAllOrders();
  if (token) {
    const claims = jwtDecode(token);
    account = claims.account;
  } else {
    this.$router.push({ path: '/'});
  }
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
