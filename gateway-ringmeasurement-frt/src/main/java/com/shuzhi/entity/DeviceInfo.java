package com.shuzhi.entity;

import java.io.Serializable;

/**
 * @Description:设备信息
 * @Author: YHF
 * @date 17:10
 */
public class DeviceInfo implements Serializable {

    private TDeviceFactoryEntity tdeviceFactoryEntity;

    private TDeviceRingmeasurementEntity tdeviceFrtEntity;

    public DeviceInfo() {

    }

    public DeviceInfo(TDeviceFactoryEntity tdeviceFactoryEntity, TDeviceRingmeasurementEntity tdeviceFrtEntity) {
        this.tdeviceFactoryEntity = tdeviceFactoryEntity;
        this.tdeviceFrtEntity = tdeviceFrtEntity;
    }

    public TDeviceFactoryEntity getTdeviceFactoryEntity() {
        return tdeviceFactoryEntity;
    }

    public void setTdeviceFactoryEntity(TDeviceFactoryEntity tdeviceFactoryEntity) {
        this.tdeviceFactoryEntity = tdeviceFactoryEntity;
    }

    public TDeviceRingmeasurementEntity getTdeviceFrtEntity() {
        return tdeviceFrtEntity;
    }

    public void setTdeviceFrtEntity(TDeviceRingmeasurementEntity tdeviceFrtEntity) {
        this.tdeviceFrtEntity = tdeviceFrtEntity;
    }
}
