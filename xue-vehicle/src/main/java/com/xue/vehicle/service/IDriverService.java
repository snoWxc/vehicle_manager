package com.xue.vehicle.service;

import java.util.List;
import com.xue.vehicle.entity.Driver;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 驾驶员信息Service接口
 *
 * @author wxc
 * @date 2024-04-06
 */
public interface IDriverService
{
    /**
     * 查询驾驶员信息
     *
     * @param id 驾驶员信息主键
     * @return 驾驶员信息
     */
    public Driver selectDriverById(Long id);

    /**
     * 查询驾驶员信息列表
     *
     * @param driver 驾驶员信息
     * @return 驾驶员信息集合
     */
    public List<Driver> selectDriverList(Driver driver);

    /**
     * 新增驾驶员信息
     *
     * @param driver 驾驶员信息
     * @return 结果
     */
    public int insertDriver(Driver driver);

    /**
     * 修改驾驶员信息
     *
     * @param driver 驾驶员信息
     * @return 结果
     */
    public int updateDriver(Driver driver);

    /**
     * 批量删除驾驶员信息
     *
     * @param ids 需要删除的驾驶员信息主键集合
     * @return 结果
     */
    public int deleteDriverByIds(Long[] ids);

    /**
     * 删除驾驶员信息信息
     *
     * @param id 驾驶员信息主键
     * @return 结果
     */
    public int deleteDriverById(Long id);
}
