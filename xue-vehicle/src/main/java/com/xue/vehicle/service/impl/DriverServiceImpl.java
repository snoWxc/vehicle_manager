package com.xue.vehicle.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import com.xue.vehicle.mapper.DriverMapper;
import com.xue.vehicle.entity.Driver;
import com.xue.vehicle.service.IDriverService;

import javax.annotation.Resource;

/**
 * 驾驶员信息Service业务层处理
 *
 * @author wxc
 * @date 2024-04-06
 */
@Service
public class DriverServiceImpl implements IDriverService
{

    @Resource
    private  DriverMapper driverMapper;


    /**
     * 查询驾驶员信息
     *
     * @param id 驾驶员信息主键
     * @return 驾驶员信息
     */
    @Override
    public Driver selectDriverById(Long id)
    {
        return driverMapper.selectDriverById(id);
    }

    /**
     * 查询驾驶员信息列表
     *
     * @param driver 驾驶员信息
     * @return 驾驶员信息
     */
    @Override
    public List<Driver> selectDriverList(Driver driver)
    {
        return driverMapper.selectDriverList(driver);
    }

    /**
     * 新增驾驶员信息
     *
     * @param driver 驾驶员信息
     * @return 结果
     */
    @Override
    public int insertDriver(Driver driver)
    {
        return driverMapper.insertDriver(driver);
    }

    /**
     * 修改驾驶员信息
     *
     * @param driver 驾驶员信息
     * @return 结果
     */
    @Override
    public int updateDriver(Driver driver)
    {
        return driverMapper.updateDriver(driver);
    }

    /**
     * 批量删除驾驶员信息
     *
     * @param ids 需要删除的驾驶员信息主键
     * @return 结果
     */
    @Override
    public int deleteDriverByIds(Long[] ids)
    {
        return driverMapper.deleteDriverByIds(ids);
    }

    /**
     * 删除驾驶员信息信息
     *
     * @param id 驾驶员信息主键
     * @return 结果
     */
    @Override
    public int deleteDriverById(Long id)
    {
        return driverMapper.deleteDriverById(id);
    }
}
