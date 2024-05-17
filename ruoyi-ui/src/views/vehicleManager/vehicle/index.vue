<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item prop="vehicleColor">
        <el-input v-model="queryParams.vehicleColor" placeholder="请输入车辆颜色" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item prop="vehiclePlateNumber">
        <el-input v-model="queryParams.vehiclePlateNumber" placeholder="请输入车辆车牌号" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item prop="vehicleBrand">
        <el-input v-model="queryParams.vehicleBrand" placeholder="请输入车辆品牌" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item prop="vehicleModel">
        <el-input v-model="queryParams.vehicleModel" placeholder="请输入车辆型号" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item prop="isScrap">
        <el-radio-group v-model="queryParams.isScrap">
          <el-radio :label="0">
            未报废
          </el-radio>
          <el-radio :label="1">
            已报废
          </el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['vehicle:vehicle:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['vehicle:vehicle:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['vehicle:vehicle:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['vehicle:vehicle:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="vehicleList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" type="index" />
      <el-table-column label="车辆颜色" align="center" prop="vehicleColor" />
      <el-table-column label="车辆车牌号" align="center" prop="vehiclePlateNumber" />
      <el-table-column label="车辆品牌" align="center" prop="vehicleBrand" />
      <el-table-column label="车辆型号" align="center" prop="vehicleModel" />
      <el-table-column label="是否报废" align="center" prop="isScrap">
        <template slot-scope="scope">
          <span v-if="scope.row.isScrap == 0">未报废</span>
          <span v-if="scope.row.isScrap == 1">已报废</span>
        </template>
      </el-table-column>
      <el-table-column label="当前状态" align="center" prop="status">
        <template slot-scope="scope">
          <span v-if="scope.row.status == 0">未出车</span>
          <span v-if="scope.row.status == 1">已出车</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)">删除</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="setScrap(scope.row)">已报废</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改车辆对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="车辆颜色" prop="vehicleColor">
          <el-input v-model="form.vehicleColor" placeholder="请输入车辆颜色" />
        </el-form-item>
        <el-form-item label="车牌号" prop="vehiclePlateNumber">
          <el-input v-model="form.vehiclePlateNumber" placeholder="请输入车辆车牌号" />
        </el-form-item>
        <el-form-item label="车辆品牌" prop="vehicleBrand">
          <el-input v-model="form.vehicleBrand" placeholder="请输入车辆品牌" />
        </el-form-item>
        <el-form-item label="车辆型号" prop="vehicleModel">
          <el-input v-model="form.vehicleModel" placeholder="请输入车辆型号" />
        </el-form-item>
        <el-form-item label="是否报废" prop="isScrap">
          <el-radio-group v-model="form.isScrap">
            <el-radio :label="0">未报废</el-radio>
            <el-radio :label="1">已报废</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listVehicle, getVehicle, delVehicle, addVehicle, updateVehicle } from "@/api/vehicle/vehicle";

export default {
  name: "Vehicle",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 车辆表格数据
      vehicleList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        vehicleColor: null,
        vehiclePlateNumber: null,
        vehicleBrand: null,
        vehicleModel: null,
        isScrap: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询车辆列表 */
    getList() {
      this.loading = true;
      listVehicle(this.queryParams).then(response => {
        this.vehicleList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        vehicleColor: null,
        vehiclePlateNumber: null,
        vehicleBrand: null,
        vehicleModel: null,
        isScrap: null,
        status: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加车辆";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getVehicle(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改车辆";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateVehicle(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addVehicle(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除车辆编号为"' + ids + '"的数据项？').then(function () {
        return delVehicle(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('vehicle/vehicle/export', {
        ...this.queryParams
      }, `vehicle_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
