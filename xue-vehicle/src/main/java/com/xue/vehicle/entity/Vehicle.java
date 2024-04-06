package com.xue.vehicle.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 车辆对象 vehicle
 *
 * @author wxc
 * @date 2024-04-06
 */
public class Vehicle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 车辆颜色 */
    @Excel(name = "车辆颜色")
    private String vehicleColor;

    /** 车辆车牌号 */
    @Excel(name = "车辆车牌号")
    private String vehiclePlateNumber;

    /** 车辆品牌 */
    @Excel(name = "车辆品牌")
    private String vehicleBrand;

    /** 车辆型号 */
    @Excel(name = "车辆型号")
    private String vehicleModel;

    /** 是否报废：0未报废，1已报废 */
    @Excel(name = "是否报废：0未报废，1已报废")
    private Integer isScrap;

    /** 当前状态：0已回车，1已出勤 */
    @Excel(name = "当前状态：0已回车，1已出勤")
    private Long status;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setVehicleColor(String vehicleColor)
    {
        this.vehicleColor = vehicleColor;
    }

    public String getVehicleColor()
    {
        return vehicleColor;
    }
    public void setVehiclePlateNumber(String vehiclePlateNumber)
    {
        this.vehiclePlateNumber = vehiclePlateNumber;
    }

    public String getVehiclePlateNumber()
    {
        return vehiclePlateNumber;
    }
    public void setVehicleBrand(String vehicleBrand)
    {
        this.vehicleBrand = vehicleBrand;
    }

    public String getVehicleBrand()
    {
        return vehicleBrand;
    }
    public void setVehicleModel(String vehicleModel)
    {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleModel()
    {
        return vehicleModel;
    }
    public void setIsScrap(Integer isScrap)
    {
        this.isScrap = isScrap;
    }

    public Integer getIsScrap()
    {
        return isScrap;
    }
    public void setStatus(Long status)
    {
        this.status = status;
    }

    public Long getStatus()
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("vehicleColor", getVehicleColor())
            .append("vehiclePlateNumber", getVehiclePlateNumber())
            .append("vehicleBrand", getVehicleBrand())
            .append("vehicleModel", getVehicleModel())
            .append("isScrap", getIsScrap())
            .append("status", getStatus())
            .toString();
    }
}
