package com.xue.vehicle.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 车辆回车或出勤对象 vehicle_move_record
 *
 * @author wxc
 * @date 2024-04-06
 */
public class VehicleMoveRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 车辆id */
    @Excel(name = "车辆id")
    private Long vehicleId;

    /** 驾驶员id */
    @Excel(name = "驾驶员id")
    private Long driverId;

    /** 登记类型：0出勤，1回车 */
    @Excel(name = "登记类型：0出勤，1回车")
    private Integer type;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setVehicleId(Long vehicleId)
    {
        this.vehicleId = vehicleId;
    }

    public Long getVehicleId()
    {
        return vehicleId;
    }
    public void setDriverId(Long driverId)
    {
        this.driverId = driverId;
    }

    public Long getDriverId()
    {
        return driverId;
    }
    public void setType(Integer type)
    {
        this.type = type;
    }

    public Integer getType()
    {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("createTime", getCreateTime())
            .append("vehicleId", getVehicleId())
            .append("driverId", getDriverId())
            .append("type", getType())
            .toString();
    }
}
