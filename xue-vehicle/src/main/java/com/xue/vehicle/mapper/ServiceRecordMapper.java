package com.xue.vehicle.mapper;

import java.util.List;
import com.xue.vehicle.entity.ServiceRecord;

/**
 * 车辆维修记录Mapper接口
 *
 * @author wxc
 * @date 2024-04-06
 */
public interface ServiceRecordMapper
{
    /**
     * 查询车辆维修记录
     *
     * @param id 车辆维修记录主键
     * @return 车辆维修记录
     */
    public ServiceRecord selectServiceRecordById(Long id);

    /**
     * 查询车辆维修记录列表
     *
     * @param serviceRecord 车辆维修记录
     * @return 车辆维修记录集合
     */
    public List<ServiceRecord> selectServiceRecordList(ServiceRecord serviceRecord);

    /**
     * 新增车辆维修记录
     *
     * @param serviceRecord 车辆维修记录
     * @return 结果
     */
    public int insertServiceRecord(ServiceRecord serviceRecord);

    /**
     * 修改车辆维修记录
     *
     * @param serviceRecord 车辆维修记录
     * @return 结果
     */
    public int updateServiceRecord(ServiceRecord serviceRecord);

    /**
     * 删除车辆维修记录
     *
     * @param id 车辆维修记录主键
     * @return 结果
     */
    public int deleteServiceRecordById(Long id);

    /**
     * 批量删除车辆维修记录
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteServiceRecordByIds(Long[] ids);
}
