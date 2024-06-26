package com.xue.vehicle.mapper;

import java.util.List;
import com.xue.vehicle.entity.VehicleMoveRecord;
import com.xue.vehicle.entity.vo.VehicleMoveRecordVO;

/**
 * 车辆回车或出勤Mapper接口
 *
 * @author wxc
 * @date 2024-04-06
 */
public interface VehicleMoveRecordMapper
{
    /**
     * 查询车辆回车或出勤
     *
     * @param id 车辆回车或出勤主键
     * @return 车辆回车或出勤
     */
    public VehicleMoveRecord selectVehicleMoveRecordById(Long id);

    /**
     * 查询车辆回车或出勤列表
     *
     * @param vehicleMoveRecord 车辆回车或出勤
     * @return 车辆回车或出勤集合
     */
    public List<VehicleMoveRecordVO> selectVehicleMoveRecordList(VehicleMoveRecordVO vehicleMoveRecord);

    /**
     * 新增车辆回车或出勤
     *
     * @param vehicleMoveRecord 车辆回车或出勤
     * @return 结果
     */
    public int insertVehicleMoveRecord(VehicleMoveRecord vehicleMoveRecord);

    /**
     * 修改车辆回车或出勤
     *
     * @param vehicleMoveRecord 车辆回车或出勤
     * @return 结果
     */
    public int updateVehicleMoveRecord(VehicleMoveRecord vehicleMoveRecord);

    /**
     * 删除车辆回车或出勤
     *
     * @param id 车辆回车或出勤主键
     * @return 结果
     */
    public int deleteVehicleMoveRecordById(Long id);

    /**
     * 批量删除车辆回车或出勤
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteVehicleMoveRecordByIds(Long[] ids);
}
