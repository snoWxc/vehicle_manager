<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item prop="vehiclePlateNumber">
        <el-input v-model="queryParams.vehiclePlateNumber" placeholder="请输入车牌号" @keyup.enter.native="handleQuery"></el-input>
      </el-form-item>
      <el-form-item prop="type">
        <el-radio-group v-model="queryParams.type" @change="handleQuery">
          <el-radio :label="0">出勤</el-radio>
          <el-radio :label="1">回车</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['vehicle:moveRecord:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['vehicle:moveRecord:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['vehicle:moveRecord:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['vehicle:moveRecord:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="moveRecordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" type="index" />
      <el-table-column label="车辆车牌" align="center" prop="vehiclePlateNumber" />
      <el-table-column label="驾驶员" align="center" prop="driverName" />
      <el-table-column label="登记类型" align="center" prop="type">
        <template slot-scope="scope">
          <span v-if="scope.row.type == 0">回车</span>
          <span v-if="scope.row.type == 1">出勤</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['vehicle:moveRecord:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['vehicle:moveRecord:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改车辆回车或出勤对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="车辆" prop="vehicleId">
          <el-select v-model="form.vehicleId">
            <el-option v-for="item in vehicleList" :key="item.id" :value="item.id" :label="item.vehiclePlateNumber"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="驾驶员" prop="driverId">
          <el-select v-model="form.driverId">
            <el-option v-for="item in driverList" :key="item.id" :value="item.id" :label="item.driverName"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="行为类型" prop="type">
          <el-radio-group v-model="form.type">
            <el-radio :label="0">回车</el-radio>
            <el-radio :label="1">出勤</el-radio>
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
import { listMoveRecord, getMoveRecord, delMoveRecord, addMoveRecord, updateMoveRecord } from "@/api/vehicle/moveRecord";
import { listDriver } from "@/api/vehicle/driver";
import { listVehicle } from "@/api/vehicle/vehicle";
export default {
  name: "MoveRecord",
  data() {
    return {
      //驾驶员列表
      driverList:[],
      //车辆列表
      vehicleList: [],
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
      // 车辆回车或出勤表格数据
      moveRecordList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        vehicleId: null,
        driverId: null,
        type: null
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
    /** 查询车辆回车或出勤列表 */
    getList() {
      this.loading = true;
      listMoveRecord(this.queryParams).then(response => {
        this.moveRecordList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 获取所有驾驶员 */
    getDriver(){
      let query = {}
      listDriver(query).then(res => {
        this.driverList = res.rows
      })
    },
    /** 获取所有车辆 */
    getVehicle(){
      let query = {}
      listVehicle(query).then( res => {
        this.vehicleList = res.rows
      })
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
        createTime: null,
        vehicleId: null,
        driverId: null,
        type: null
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
      this.getVehicle();
      this.getDriver();
      this.open = true;
      this.title = "添加车辆回车或出勤";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getMoveRecord(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改车辆回车或出勤";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateMoveRecord(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMoveRecord(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除车辆回车或出勤编号为"' + ids + '"的数据项？').then(function () {
        return delMoveRecord(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('vehicle/moveRecord/export', {
        ...this.queryParams
      }, `moveRecord_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
