package com.shuzhi.entity;

import java.io.Serializable;

/**
 * @Description:设备信息
 * @Author: YHF
 * @date 17:10
 */
public class DeviceInfo implements Serializable {

    private TDeviceFactoryEntity tdeviceFactoryEntity;
    private TDeviceRingmeasurementREntity tdeviceFrtEntity;

    public DeviceInfo() {

    }

    public DeviceInfo(TDeviceFactoryEntity tdeviceFactoryEntity, TDeviceRingmeasurementREntity tdeviceFrtEntity) {
        this.tdeviceFactoryEntity = tdeviceFactoryEntity;
        this.tdeviceFrtEntity = tdeviceFrtEntity;
    }

    public TDeviceFactoryEntity getTdeviceFactoryEntity() {
        return tdeviceFactoryEntity;
    }

    public void setTdeviceFactoryEntity(TDeviceFactoryEntity tdeviceFactoryEntity) {
        this.tdeviceFactoryEntity = tdeviceFactoryEntity;
    }

    public TDeviceRingmeasurementREntity getTdeviceFrtEntity() {
        return tdeviceFrtEntity;
    }

    public void setTdeviceFrtEntity(TDeviceRingmeasurementREntity tdeviceFrtEntity) {
        this.tdeviceFrtEntity = tdeviceFrtEntity;
    }
}
