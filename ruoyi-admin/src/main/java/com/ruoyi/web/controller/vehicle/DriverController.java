package com.ruoyi.web.controller.vehicle;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.xue.vehicle.service.IDriverService;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.xue.vehicle.entity.Driver;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 驾驶员信息Controller
 *
 * @author wxc
 * @date 2024-04-06
 */
@RestController
@RequestMapping("/system/driver")
public class DriverController extends BaseController
{
    private final IDriverService driverService;

    public DriverController(IDriverService driverService) {
        this.driverService = driverService;
    }


    /**
     * 查询驾驶员信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:driver:list')")
    @GetMapping("/list")
    public TableDataInfo list(Driver driver)
    {
        startPage();
        List<Driver> list = driverService.selectDriverList(driver);
        return getDataTable(list);
    }

    /**
     * 导出驾驶员信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:driver:export')")
    @Log(title = "驾驶员信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Driver driver)
    {
        List<Driver> list = driverService.selectDriverList(driver);
        ExcelUtil<Driver> util = new ExcelUtil<Driver>(Driver.class);
        util.exportExcel(response, list, "驾驶员信息数据");
    }

    /**
     * 获取驾驶员信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:driver:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(driverService.selectDriverById(id));
    }

    /**
     * 新增驾驶员信息
     */
    @PreAuthorize("@ss.hasPermi('system:driver:add')")
    @Log(title = "驾驶员信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Driver driver)
    {
        return toAjax(driverService.insertDriver(driver));
    }

    /**
     * 修改驾驶员信息
     */
    @PreAuthorize("@ss.hasPermi('system:driver:edit')")
    @Log(title = "驾驶员信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Driver driver)
    {
        return toAjax(driverService.updateDriver(driver));
    }

    /**
     * 删除驾驶员信息
     */
    @PreAuthorize("@ss.hasPermi('system:driver:remove')")
    @Log(title = "驾驶员信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(driverService.deleteDriverByIds(ids));
    }
}
