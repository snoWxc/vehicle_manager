package com.ruoyi.web.controller.vehicle;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.xue.vehicle.entity.vo.ServiceRecordVO;
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
import com.xue.vehicle.entity.ServiceRecord;
import com.xue.vehicle.service.IServiceRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 车辆维修记录Controller
 *
 * @author wxc
 * @date 2024-04-06
 */
@RestController
@RequestMapping("/vehicle/serviceRecord")
public class ServiceRecordController extends BaseController
{
    @Autowired
    private IServiceRecordService serviceRecordService;

    /**
     * 查询车辆维修记录列表
     */
    @PreAuthorize("@ss.hasPermi('vehicle:serviceRecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(ServiceRecordVO serviceRecord)
    {
        startPage();
        List<ServiceRecordVO> list = serviceRecordService.selectServiceRecordList(serviceRecord);
        return getDataTable(list);
    }

    /**
     * 导出车辆维修记录列表
     */
    @PreAuthorize("@ss.hasPermi('vehicle:serviceRecord:export')")
    @Log(title = "车辆维修记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ServiceRecordVO serviceRecord)
    {
//        List<ServiceRecord> list = serviceRecordService.selectServiceRecordList(serviceRecord);
//        ExcelUtil<ServiceRecord> util = new ExcelUtil<ServiceRecord>(ServiceRecord.class);
//        util.exportExcel(response, list, "车辆维修记录数据");
    }

    /**
     * 获取车辆维修记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('vehicle:serviceRecord:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(serviceRecordService.selectServiceRecordById(id));
    }

    /**
     * 新增车辆维修记录
     */
    @PreAuthorize("@ss.hasPermi('vehicle:serviceRecord:add')")
    @Log(title = "车辆维修记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ServiceRecord serviceRecord)
    {
        return toAjax(serviceRecordService.insertServiceRecord(serviceRecord));
    }

    /**
     * 修改车辆维修记录
     */
    @PreAuthorize("@ss.hasPermi('vehicle:serviceRecord:edit')")
    @Log(title = "车辆维修记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ServiceRecord serviceRecord)
    {
        return toAjax(serviceRecordService.updateServiceRecord(serviceRecord));
    }

    /**
     * 删除车辆维修记录
     */
    @PreAuthorize("@ss.hasPermi('vehicle:serviceRecord:remove')")
    @Log(title = "车辆维修记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(serviceRecordService.deleteServiceRecordByIds(ids));
    }
}
