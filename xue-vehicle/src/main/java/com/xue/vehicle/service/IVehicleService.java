package com.xue.vehicle.service;

import java.util.List;
import com.xue.vehicle.entity.Vehicle;

/**
 * 车辆Service接口
 *
 * @author wxc
 * @date 2024-04-06
 */
public interface IVehicleService
{
    /**
     * 查询车辆
     *
     * @param id 车辆主键
     * @return 车辆
     */
    public Vehicle selectVehicleById(Long id);

    /**
     * 查询车辆列表
     *
     * @param vehicle 车辆
     * @return 车辆集合
     */
    public List<Vehicle> selectVehicleList(Vehicle vehicle);

    /**
     * 新增车辆
     *
     * @param vehicle 车辆
     * @return 结果
     */
    public int insertVehicle(Vehicle vehicle);

    /**
     * 修改车辆
     *
     * @param vehicle 车辆
     * @return 结果
     */
    public int updateVehicle(Vehicle vehicle);

    /**
     * 批量删除车辆
     *
     * @param ids 需要删除的车辆主键集合
     * @return 结果
     */
    public int deleteVehicleByIds(Long[] ids);

    /**
     * 删除车辆信息
     *
     * @param id 车辆主键
     * @return 结果
     */
    public int deleteVehicleById(Long id);
}
