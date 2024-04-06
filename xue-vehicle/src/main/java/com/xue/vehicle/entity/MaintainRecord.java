package com.xue.vehicle.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 车辆保养记录对象 maintain_record
 *
 * @author wxc
 * @date 2024-04-06
 */
public class MaintainRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 保养时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "保养时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date maintainTime;

    /** 保养项目名称 */
    @Excel(name = "保养项目名称")
    private String maintainName;

    /** 保养进行时长 */
    @Excel(name = "保养进行时长")
    private Long maintainDuration;

    /** 进行保养的车辆id */
    @Excel(name = "进行保养的车辆id")
    private Long fkVehicleId;

    /** 驾驶员id */
    @Excel(name = "驾驶员id")
    private Long fkDriverId;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setMaintainTime(Date maintainTime)
    {
        this.maintainTime = maintainTime;
    }

    public Date getMaintainTime()
    {
        return maintainTime;
    }
    public void setMaintainName(String maintainName)
    {
        this.maintainName = maintainName;
    }

    public String getMaintainName()
    {
        return maintainName;
    }
    public void setMaintainDuration(Long maintainDuration)
    {
        this.maintainDuration = maintainDuration;
    }

    public Long getMaintainDuration()
    {
        return maintainDuration;
    }
    public void setFkVehicleId(Long fkVehicleId)
    {
        this.fkVehicleId = fkVehicleId;
    }

    public Long getFkVehicleId()
    {
        return fkVehicleId;
    }
    public void setFkDriverId(Long fkDriverId)
    {
        this.fkDriverId = fkDriverId;
    }

    public Long getFkDriverId()
    {
        return fkDriverId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("maintainTime", getMaintainTime())
            .append("maintainName", getMaintainName())
            .append("maintainDuration", getMaintainDuration())
            .append("fkVehicleId", getFkVehicleId())
            .append("fkDriverId", getFkDriverId())
            .toString();
    }
}
