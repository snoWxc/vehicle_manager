<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="保养时间" prop="maintainTime">
        <el-date-picker clearable
          v-model="queryParams.maintainTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择保养时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="保养项目名称" prop="maintainName">
        <el-input
          v-model="queryParams.maintainName"
          placeholder="请输入保养项目名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="进行保养的车辆id" prop="fkVehicleId">
        <el-input
          v-model="queryParams.fkVehicleId"
          placeholder="请输入进行保养的车辆id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="驾驶员id" prop="fkDriverId">
        <el-input
          v-model="queryParams.fkDriverId"
          placeholder="请输入驾驶员id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['vehicle:maintainRecord:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['vehicle:maintainRecord:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['vehicle:maintainRecord:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['vehicle:maintainRecord:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="maintainRecordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" type="index" />
      <el-table-column label="保养时间" align="center" prop="maintainTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.maintainTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="保养项目名称" align="center" prop="maintainName" />
      <el-table-column label="保养进行时长" align="center" prop="maintainDuration" />
      <el-table-column label="进行保养的车辆id" align="center" prop="fkVehicleId" />
      <el-table-column label="驾驶员id" align="center" prop="fkDriverId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['vehicle:maintainRecord:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['vehicle:maintainRecord:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改车辆保养记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="保养时间" prop="maintainTime">
          <el-date-picker clearable
            v-model="form.maintainTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择保养时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="保养项目名称" prop="maintainName">
          <el-input v-model="form.maintainName" placeholder="请输入保养项目名称" />
        </el-form-item>
        <el-form-item label="保养进行时长" prop="maintainDuration">
          <el-input v-model="form.maintainDuration" placeholder="请输入保养进行时长" />
        </el-form-item>
        <el-form-item label="进行保养的车辆id" prop="fkVehicleId">
          <el-input v-model="form.fkVehicleId" placeholder="请输入进行保养的车辆id" />
        </el-form-item>
        <el-form-item label="驾驶员id" prop="fkDriverId">
          <el-input v-model="form.fkDriverId" placeholder="请输入驾驶员id" />
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
import { listMaintainRecord, getMaintainRecord, delMaintainRecord, addMaintainRecord, updateMaintainRecord } from "@/api/vehicle/maintainRecord";

export default {
  name: "MaintainRecord",
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
      // 车辆保养记录表格数据
      maintainRecordList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        maintainTime: null,
        maintainName: null,
        maintainDuration: null,
        fkVehicleId: null,
        fkDriverId: null
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
    /** 查询车辆保养记录列表 */
    getList() {
      this.loading = true;
      listMaintainRecord(this.queryParams).then(response => {
        this.maintainRecordList = response.rows;
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
        maintainTime: null,
        maintainName: null,
        maintainDuration: null,
        fkVehicleId: null,
        fkDriverId: null
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
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加车辆保养记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getMaintainRecord(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改车辆保养记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateMaintainRecord(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMaintainRecord(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除车辆保养记录编号为"' + ids + '"的数据项？').then(function() {
        return delMaintainRecord(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('vehicle/maintainRecord/export', {
        ...this.queryParams
      }, `maintainRecord_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
