package com.xue.vehicle.service.impl;

import java.util.List;

import com.ruoyi.common.constant.VehicleConstants;
import com.ruoyi.common.utils.DateUtils;
import com.xue.vehicle.entity.Vehicle;
import com.xue.vehicle.entity.vo.VehicleMoveRecordVO;
import com.xue.vehicle.service.IVehicleService;
import org.springframework.stereotype.Service;
import com.xue.vehicle.mapper.VehicleMoveRecordMapper;
import com.xue.vehicle.entity.VehicleMoveRecord;
import com.xue.vehicle.service.IVehicleMoveRecordService;

/**
 * 车辆回车或出勤Service业务层处理
 *
 * @author wxc
 * @date 2024-04-06
 */
@Service
public class VehicleMoveRecordServiceImpl implements IVehicleMoveRecordService
{
    private final VehicleMoveRecordMapper vehicleMoveRecordMapper;
    private final IVehicleService vehicleService;

    public VehicleMoveRecordServiceImpl(VehicleMoveRecordMapper vehicleMoveRecordMapper, IVehicleService vehicleService) {
        this.vehicleMoveRecordMapper = vehicleMoveRecordMapper;
        this.vehicleService = vehicleService;
    }

    /**
     * 查询车辆回车或出勤
     *
     * @param id 车辆回车或出勤主键
     * @return 车辆回车或出勤
     */
    @Override
    public VehicleMoveRecord selectVehicleMoveRecordById(Long id)
    {
        return vehicleMoveRecordMapper.selectVehicleMoveRecordById(id);
    }

    /**
     * 查询车辆回车或出勤列表
     *
     * @param vehicleMoveRecord 车辆回车或出勤
     * @return 车辆回车或出勤
     */
    @Override
    public List<VehicleMoveRecordVO> selectVehicleMoveRecordList(VehicleMoveRecordVO vehicleMoveRecord)
    {
        return vehicleMoveRecordMapper.selectVehicleMoveRecordList(vehicleMoveRecord);
    }

    /**
     * 新增车辆回车或出勤
     *
     * @param vehicleMoveRecord 车辆回车或出勤
     * @return 结果
     */
    @Override
    public int insertVehicleMoveRecord(VehicleMoveRecord vehicleMoveRecord)
    {
        vehicleMoveRecord.setCreateTime(DateUtils.getNowDate());
        Vehicle vehicle = vehicleService.selectVehicleById(vehicleMoveRecord.getVehicleId());
        if(VehicleConstants.MOVE_RECORD_BACK.equals(vehicleMoveRecord.getType())){
            vehicle.setStatus(VehicleConstants.VEHICLE_IS_BACK);
        }
        if(VehicleConstants.MOVE_RECORD_OUT.equals(vehicleMoveRecord.getType())){
            vehicle.setStatus(VehicleConstants.VEHICLE_IS_OUT);
        }
        vehicleService.updateVehicle(vehicle);
        return vehicleMoveRecordMapper.insertVehicleMoveRecord(vehicleMoveRecord);
    }

    /**
     * 修改车辆回车或出勤
     *
     * @param vehicleMoveRecord 车辆回车或出勤
     * @return 结果
     */
    @Override
    public int updateVehicleMoveRecord(VehicleMoveRecord vehicleMoveRecord)
    {
        return vehicleMoveRecordMapper.updateVehicleMoveRecord(vehicleMoveRecord);
    }

    /**
     * 批量删除车辆回车或出勤
     *
     * @param ids 需要删除的车辆回车或出勤主键
     * @return 结果
     */
    @Override
    public int deleteVehicleMoveRecordByIds(Long[] ids)
    {
        return vehicleMoveRecordMapper.deleteVehicleMoveRecordByIds(ids);
    }

    /**
     * 删除车辆回车或出勤信息
     *
     * @param id 车辆回车或出勤主键
     * @return 结果
     */
    @Override
    public int deleteVehicleMoveRecordById(Long id)
    {
        return vehicleMoveRecordMapper.deleteVehicleMoveRecordById(id);
    }
}
