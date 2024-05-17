package com.xue.vehicle.entity.vo;

import com.xue.vehicle.entity.VehicleMoveRecord;

public class VehicleMoveRecordVO extends VehicleMoveRecord {
    private String vehiclePlateNumber;

    private String driverName;

    public String getVehiclePlateNumber() {
        return vehiclePlateNumber;
    }

    public void setVehiclePlateNumber(String vehiclePlateNumber) {
        this.vehiclePlateNumber = vehiclePlateNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
}
