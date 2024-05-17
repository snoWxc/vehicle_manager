package com.xue.vehicle.entity.vo;

import com.xue.vehicle.entity.MaintainRecord;

public class MaintainRecordVO extends MaintainRecord {
    private String driverName;
    private String vehiclePlateNumber;

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getVehiclePlateNumber() {
        return vehiclePlateNumber;
    }

    public void setVehiclePlateNumber(String vehiclePlateNumber) {
        this.vehiclePlateNumber = vehiclePlateNumber;
    }
}
