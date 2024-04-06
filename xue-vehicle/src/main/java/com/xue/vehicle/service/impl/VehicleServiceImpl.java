package com.xue.vehicle.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xue.vehicle.mapper.VehicleMapper;
import com.xue.vehicle.entity.Vehicle;
import com.xue.vehicle.service.IVehicleService;

/**
 * 车辆Service业务层处理
 *
 * @author wxc
 * @date 2024-04-06
 */
@Service
public class VehicleServiceImpl implements IVehicleService
{
    @Autowired
    private VehicleMapper vehicleMapper;

    /**
     * 查询车辆
     *
     * @param id 车辆主键
     * @return 车辆
     */
    @Override
    public Vehicle selectVehicleById(Long id)
    {
        return vehicleMapper.selectVehicleById(id);
    }

    /**
     * 查询车辆列表
     *
     * @param vehicle 车辆
     * @return 车辆
     */
    @Override
    public List<Vehicle> selectVehicleList(Vehicle vehicle)
    {
        return vehicleMapper.selectVehicleList(vehicle);
    }

    /**
     * 新增车辆
     *
     * @param vehicle 车辆
     * @return 结果
     */
    @Override
    public int insertVehicle(Vehicle vehicle)
    {
        return vehicleMapper.insertVehicle(vehicle);
    }

    /**
     * 修改车辆
     *
     * @param vehicle 车辆
     * @return 结果
     */
    @Override
    public int updateVehicle(Vehicle vehicle)
    {
        return vehicleMapper.updateVehicle(vehicle);
    }

    /**
     * 批量删除车辆
     *
     * @param ids 需要删除的车辆主键
     * @return 结果
     */
    @Override
    public int deleteVehicleByIds(Long[] ids)
    {
        return vehicleMapper.deleteVehicleByIds(ids);
    }

    /**
     * 删除车辆信息
     *
     * @param id 车辆主键
     * @return 结果
     */
    @Override
    public int deleteVehicleById(Long id)
    {
        return vehicleMapper.deleteVehicleById(id);
    }
}
