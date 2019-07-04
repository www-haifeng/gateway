package com.shuzhi.entity;

import java.io.Serializable;

/**
 * @Description:设备信息
 * @Author: YHF
 * @date 17:10
 */
public class DeviceInfo implements Serializable {

    private TDeviceFactoryEntity tdeviceFactoryEntity;
    private TDeviceSponEntity tdeviceSponEntity;

    public DeviceInfo() {

    }

    public DeviceInfo(TDeviceFactoryEntity tdeviceFactoryEntity) {
        this.tdeviceFactoryEntity = tdeviceFactoryEntity;
    }

    public DeviceInfo(TDeviceSponEntity tdeviceSponEntity) {
        this.tdeviceSponEntity = tdeviceSponEntity;
    }

    public DeviceInfo(TDeviceFactoryEntity tdeviceFactoryEntity, TDeviceSponEntity tdeviceSponEntity) {
        this.tdeviceFactoryEntity = tdeviceFactoryEntity;
        this.tdeviceSponEntity = tdeviceSponEntity;
    }

    public TDeviceFactoryEntity getTdeviceFactoryEntity() {
        return tdeviceFactoryEntity;
    }

    public void setTdeviceFactoryEntity(TDeviceFactoryEntity tdeviceFactoryEntity) {
        this.tdeviceFactoryEntity = tdeviceFactoryEntity;
    }

    public TDeviceSponEntity getTdeviceSponEntity() {
        return tdeviceSponEntity;
    }

    public void setTdeviceSponEntity(TDeviceSponEntity tdeviceSponEntity) {
        this.tdeviceSponEntity = tdeviceSponEntity;
    }
}
