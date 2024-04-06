package com.ruoyi.web.controller.vehicle;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.xue.vehicle.entity.MaintainRecord;
import com.xue.vehicle.service.IMaintainRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 车辆保养记录Controller
 *
 * @author wxc
 * @date 2024-04-06
 */
@RestController
@RequestMapping("/vehicle/maintainRecord")
public class MaintainRecordController extends BaseController
{
    @Autowired
    private IMaintainRecordService maintainRecordService;

    /**
     * 查询车辆保养记录列表
     */
    @PreAuthorize("@ss.hasPermi('vehicle:maintainRecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(MaintainRecord maintainRecord)
    {
        startPage();
        List<MaintainRecord> list = maintainRecordService.selectMaintainRecordList(maintainRecord);
        return getDataTable(list);
    }

    /**
     * 导出车辆保养记录列表
     */
    @PreAuthorize("@ss.hasPermi('vehicle:maintainRecord:export')")
    @Log(title = "车辆保养记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MaintainRecord maintainRecord)
    {
        List<MaintainRecord> list = maintainRecordService.selectMaintainRecordList(maintainRecord);
        ExcelUtil<MaintainRecord> util = new ExcelUtil<MaintainRecord>(MaintainRecord.class);
        util.exportExcel(response, list, "车辆保养记录数据");
    }

    /**
     * 获取车辆保养记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('vehicle:maintainRecord:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(maintainRecordService.selectMaintainRecordById(id));
    }

    /**
     * 新增车辆保养记录
     */
    @PreAuthorize("@ss.hasPermi('vehicle:maintainRecord:add')")
    @Log(title = "车辆保养记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MaintainRecord maintainRecord)
    {
        return toAjax(maintainRecordService.insertMaintainRecord(maintainRecord));
    }

    /**
     * 修改车辆保养记录
     */
    @PreAuthorize("@ss.hasPermi('vehicle:maintainRecord:edit')")
    @Log(title = "车辆保养记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MaintainRecord maintainRecord)
    {
        return toAjax(maintainRecordService.updateMaintainRecord(maintainRecord));
    }

    /**
     * 删除车辆保养记录
     */
    @PreAuthorize("@ss.hasPermi('vehicle:maintainRecord:remove')")
    @Log(title = "车辆保养记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(maintainRecordService.deleteMaintainRecordByIds(ids));
    }
}
