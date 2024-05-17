package com.ruoyi.web.controller.vehicle;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.xue.vehicle.entity.vo.VehicleMoveRecordVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.xue.vehicle.entity.VehicleMoveRecord;
import com.xue.vehicle.service.IVehicleMoveRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 车辆回车或出勤Controller
 *
 * @author wxc
 * @date 2024-04-06
 */
@RestController
@RequestMapping("/vehicle/moveRecord")
public class VehicleMoveRecordController extends BaseController
{
    @Autowired
    private IVehicleMoveRecordService vehicleMoveRecordService;

    /**
     * 查询车辆回车或出勤列表
     */
//    @PreAuthorize("@ss.hasPermi('vehicle:moveRecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(VehicleMoveRecordVO vehicleMoveRecord)
    {
        startPage();
        List<VehicleMoveRecord> list = vehicleMoveRecordService.selectVehicleMoveRecordList(vehicleMoveRecord);
        return getDataTable(list);
    }

    /**
     * 导出车辆回车或出勤列表
     */
    @PreAuthorize("@ss.hasPermi('vehicle:moveRecord:export')")
    @Log(title = "车辆回车或出勤", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VehicleMoveRecord vehicleMoveRecord)
    {
        List<VehicleMoveRecord> list = vehicleMoveRecordService.selectVehicleMoveRecordList(vehicleMoveRecord);
        ExcelUtil<VehicleMoveRecord> util = new ExcelUtil<VehicleMoveRecord>(VehicleMoveRecord.class);
        util.exportExcel(response, list, "车辆回车或出勤数据");
    }

    /**
     * 获取车辆回车或出勤详细信息
     */
    @PreAuthorize("@ss.hasPermi('vehicle:moveRecord:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(vehicleMoveRecordService.selectVehicleMoveRecordById(id));
    }

    /**
     * 新增车辆回车或出勤
     */
    @PreAuthorize("@ss.hasPermi('vehicle:moveRecord:add')")
    @Log(title = "车辆回车或出勤", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VehicleMoveRecord vehicleMoveRecord)
    {
        return toAjax(vehicleMoveRecordService.insertVehicleMoveRecord(vehicleMoveRecord));
    }

    /**
     * 修改车辆回车或出勤
     */
    @PreAuthorize("@ss.hasPermi('vehicle:moveRecord:edit')")
    @Log(title = "车辆回车或出勤", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VehicleMoveRecord vehicleMoveRecord)
    {
        return toAjax(vehicleMoveRecordService.updateVehicleMoveRecord(vehicleMoveRecord));
    }

    /**
     * 删除车辆回车或出勤
     */
    @PreAuthorize("@ss.hasPermi('vehicle:moveRecord:remove')")
    @Log(title = "车辆回车或出勤", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(vehicleMoveRecordService.deleteVehicleMoveRecordByIds(ids));
    }
}
