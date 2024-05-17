package com.xue.vehicle.service;

import java.util.List;
import com.xue.vehicle.entity.ServiceRecord;
import com.xue.vehicle.entity.vo.ServiceRecordVO;

/**
 * 车辆维修记录Service接口
 *
 * @author wxc
 * @date 2024-04-06
 */
public interface IServiceRecordService
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
    public List<ServiceRecordVO> selectServiceRecordList(ServiceRecordVO serviceRecord);

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
     * 批量删除车辆维修记录
     *
     * @param ids 需要删除的车辆维修记录主键集合
     * @return 结果
     */
    public int deleteServiceRecordByIds(Long[] ids);

    /**
     * 删除车辆维修记录信息
     *
     * @param id 车辆维修记录主键
     * @return 结果
     */
    public int deleteServiceRecordById(Long id);
}
