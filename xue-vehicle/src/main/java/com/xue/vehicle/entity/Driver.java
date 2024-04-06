package com.xue.vehicle.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 驾驶员信息对象 driver
 *
 * @author wxc
 * @date 2024-04-06
 */
public class Driver extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 驾驶员姓名 */
    @Excel(name = "驾驶员姓名")
    private String driverName;

    /** 驾驶员电话 */
    @Excel(name = "驾驶员电话")
    private Long driverPhone;

    /** 驾驶员性别，0女性，1男性 */
    @Excel(name = "驾驶员性别，0女性，1男性")
    private Long driverSex;

    /** 外键车辆id */
    @Excel(name = "外键车辆id")
    private Long fkVehicleId;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String vehiclePlateNumber;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setDriverName(String driverName)
    {
        this.driverName = driverName;
    }

    public String getDriverName()
    {
        return driverName;
    }
    public void setDriverPhone(Long driverPhone)
    {
        this.driverPhone = driverPhone;
    }

    public Long getDriverPhone()
    {
        return driverPhone;
    }
    public void setDriverSex(Long driverSex)
    {
        this.driverSex = driverSex;
    }

    public Long getDriverSex()
    {
        return driverSex;
    }
    public void setFkVehicleId(Long fkVehicleId)
    {
        this.fkVehicleId = fkVehicleId;
    }

    public Long getFkVehicleId()
    {
        return fkVehicleId;
    }
    public void setVehiclePlateNumber(String vehiclePlateNumber)
    {
        this.vehiclePlateNumber = vehiclePlateNumber;
    }

    public String getVehiclePlateNumber()
    {
        return vehiclePlateNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("driverName", getDriverName())
            .append("driverPhone", getDriverPhone())
            .append("driverSex", getDriverSex())
            .append("fkVehicleId", getFkVehicleId())
            .append("vehiclePlateNumber", getVehiclePlateNumber())
            .toString();
    }
}
