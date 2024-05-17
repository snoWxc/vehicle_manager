package com.xue.vehicle.mapper;

import java.util.List;
import com.xue.vehicle.entity.MaintainRecord;
import com.xue.vehicle.entity.vo.MaintainRecordVO;

/**
 * 车辆保养记录Mapper接口
 *
 * @author wxc
 * @date 2024-04-06
 */
public interface MaintainRecordMapper
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
    public List<MaintainRecordVO> selectMaintainRecordList(MaintainRecordVO maintainRecord);

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
     * 删除车辆保养记录
     *
     * @param id 车辆保养记录主键
     * @return 结果
     */
    public int deleteMaintainRecordById(Long id);

    /**
     * 批量删除车辆保养记录
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMaintainRecordByIds(Long[] ids);
}
