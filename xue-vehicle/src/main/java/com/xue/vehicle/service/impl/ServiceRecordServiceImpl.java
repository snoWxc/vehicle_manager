package com.xue.vehicle.service.impl;

import java.util.List;

import com.xue.vehicle.entity.vo.ServiceRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xue.vehicle.mapper.ServiceRecordMapper;
import com.xue.vehicle.entity.ServiceRecord;
import com.xue.vehicle.service.IServiceRecordService;

/**
 * 车辆维修记录Service业务层处理
 *
 * @author wxc
 * @date 2024-04-06
 */
@Service
public class ServiceRecordServiceImpl implements IServiceRecordService
{
    @Autowired
    private ServiceRecordMapper serviceRecordMapper;

    /**
     * 查询车辆维修记录
     *
     * @param id 车辆维修记录主键
     * @return 车辆维修记录
     */
    @Override
    public ServiceRecord selectServiceRecordById(Long id)
    {
        return serviceRecordMapper.selectServiceRecordById(id);
    }

    /**
     * 查询车辆维修记录列表
     *
     * @param serviceRecord 车辆维修记录
     * @return 车辆维修记录
     */
    @Override
    public List<ServiceRecordVO> selectServiceRecordList(ServiceRecordVO serviceRecord)
    {
        return serviceRecordMapper.selectServiceRecordList(serviceRecord);
    }

    /**
     * 新增车辆维修记录
     *
     * @param serviceRecord 车辆维修记录
     * @return 结果
     */
    @Override
    public int insertServiceRecord(ServiceRecord serviceRecord)
    {
        return serviceRecordMapper.insertServiceRecord(serviceRecord);
    }

    /**
     * 修改车辆维修记录
     *
     * @param serviceRecord 车辆维修记录
     * @return 结果
     */
    @Override
    public int updateServiceRecord(ServiceRecord serviceRecord)
    {
        return serviceRecordMapper.updateServiceRecord(serviceRecord);
    }

    /**
     * 批量删除车辆维修记录
     *
     * @param ids 需要删除的车辆维修记录主键
     * @return 结果
     */
    @Override
    public int deleteServiceRecordByIds(Long[] ids)
    {
        return serviceRecordMapper.deleteServiceRecordByIds(ids);
    }

    /**
     * 删除车辆维修记录信息
     *
     * @param id 车辆维修记录主键
     * @return 结果
     */
    @Override
    public int deleteServiceRecordById(Long id)
    {
        return serviceRecordMapper.deleteServiceRecordById(id);
    }
}
