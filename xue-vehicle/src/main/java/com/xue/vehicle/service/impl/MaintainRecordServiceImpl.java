package com.xue.vehicle.service.impl;

import java.util.List;

import com.xue.vehicle.entity.vo.MaintainRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xue.vehicle.mapper.MaintainRecordMapper;
import com.xue.vehicle.entity.MaintainRecord;
import com.xue.vehicle.service.IMaintainRecordService;

/**
 * 车辆保养记录Service业务层处理
 *
 * @author wxc
 * @date 2024-04-06
 */
@Service
public class MaintainRecordServiceImpl implements IMaintainRecordService
{
    @Autowired
    private MaintainRecordMapper maintainRecordMapper;

    /**
     * 查询车辆保养记录
     *
     * @param id 车辆保养记录主键
     * @return 车辆保养记录
     */
    @Override
    public MaintainRecord selectMaintainRecordById(Long id)
    {
        return maintainRecordMapper.selectMaintainRecordById(id);
    }

    /**
     * 查询车辆保养记录列表
     *
     * @param maintainRecord 车辆保养记录
     * @return 车辆保养记录
     */
    @Override
    public List<MaintainRecordVO> selectMaintainRecordList(MaintainRecordVO maintainRecord)
    {
        return maintainRecordMapper.selectMaintainRecordList(maintainRecord);
    }

    /**
     * 新增车辆保养记录
     *
     * @param maintainRecord 车辆保养记录
     * @return 结果
     */
    @Override
    public int insertMaintainRecord(MaintainRecord maintainRecord)
    {
        return maintainRecordMapper.insertMaintainRecord(maintainRecord);
    }

    /**
     * 修改车辆保养记录
     *
     * @param maintainRecord 车辆保养记录
     * @return 结果
     */
    @Override
    public int updateMaintainRecord(MaintainRecord maintainRecord)
    {
        return maintainRecordMapper.updateMaintainRecord(maintainRecord);
    }

    /**
     * 批量删除车辆保养记录
     *
     * @param ids 需要删除的车辆保养记录主键
     * @return 结果
     */
    @Override
    public int deleteMaintainRecordByIds(Long[] ids)
    {
        return maintainRecordMapper.deleteMaintainRecordByIds(ids);
    }

    /**
     * 删除车辆保养记录信息
     *
     * @param id 车辆保养记录主键
     * @return 结果
     */
    @Override
    public int deleteMaintainRecordById(Long id)
    {
        return maintainRecordMapper.deleteMaintainRecordById(id);
    }
}
