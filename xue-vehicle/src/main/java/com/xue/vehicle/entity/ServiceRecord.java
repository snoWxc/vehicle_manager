package com.xue.vehicle.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 车辆维修记录对象 service_record
 *
 * @author wxc
 * @date 2024-04-06
 */
public class ServiceRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 维修项目名称 */
    @Excel(name = "维修项目名称")
    private String serviceName;

    /** 维修时长 */
    @Excel(name = "维修时长")
    private String serviceDuration;

    /** 维修时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "维修时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date serviceTime;

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
    public void setServiceName(String serviceName)
    {
        this.serviceName = serviceName;
    }

    public String getServiceName()
    {
        return serviceName;
    }
    public void setServiceDuration(String serviceDuration)
    {
        this.serviceDuration = serviceDuration;
    }

    public String getServiceDuration()
    {
        return serviceDuration;
    }
    public void setServiceTime(Date serviceTime)
    {
        this.serviceTime = serviceTime;
    }

    public Date getServiceTime()
    {
        return serviceTime;
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
            .append("serviceName", getServiceName())
            .append("serviceDuration", getServiceDuration())
            .append("serviceTime", getServiceTime())
            .append("fkVehicleId", getFkVehicleId())
            .append("fkDriverId", getFkDriverId())
            .toString();
    }
}
