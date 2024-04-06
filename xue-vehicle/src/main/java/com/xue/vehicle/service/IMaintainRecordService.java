package com.xue.vehicle.service;

import java.util.List;
import com.xue.vehicle.entity.MaintainRecord;

/**
 * 车辆保养记录Service接口
 *
 * @author wxc
 * @date 2024-04-06
 */
public interface IMaintainRecordService
{
    /**
     * 查询车辆保养记录
     *
     * @param id 车辆保养记录主键
     * @return 车辆保养记录
     */
    public MaintainRecord selectMaintainRecordById(Long id);

    /**
     * 查询车辆保养记录列表
     *
     * @param maintainRecord 车辆保养记录
     * @return 车辆保养记录集合
     */
    public List<MaintainRecord> selectMaintainRecordList(MaintainRecord maintainRecord);

    /**
     * 新增车辆保养记录
     *
     * @param maintainRecord 车辆保养记录
     * @return 结果
     */
    public int insertMaintainRecord(MaintainRecord maintainRecord);

    /**
     * 修改车辆保养记录
     *
     * @param maintainRecord 车辆保养记录
     * @return 结果
     */
    public int updateMaintainRecord(MaintainRecord maintainRecord);

    /**
     * 批量删除车辆保养记录
     *
     * @param ids 需要删除的车辆保养记录主键集合
     * @return 结果
     */
    public int deleteMaintainRecordByIds(Long[] ids);

    /**
     * 删除车辆保养记录信息
     *
     * @param id 车辆保养记录主键
     * @return 结果
     */
    public int deleteMaintainRecordById(Long id);
}
