package com.shuzhi.entity;

import java.io.Serializable;

/**
 * @Description:设备信息
 * @Author: YHF
 * @date 17:10
 */
public class DeviceInfo implements Serializable {

    private TDeviceFactoryEntity tdeviceFactoryEntity;
    private TDeviceHoboEntity tdeviceSponEntity;

    public DeviceInfo() {

    }

    public TDeviceFactoryEntity getTdeviceFactoryEntity() {
        return tdeviceFactoryEntity;
    }

    public void setTdeviceFactoryEntity(TDeviceFactoryEntity tdeviceFactoryEntity) {
        this.tdeviceFactoryEntity = tdeviceFactoryEntity;
    }

    public TDeviceHoboEntity getTdeviceSponEntity() {
        return tdeviceSponEntity;
    }

    public void setTdeviceSponEntity(TDeviceHoboEntity tdeviceSponEntity) {
        this.tdeviceSponEntity = tdeviceSponEntity;
    }

    public DeviceInfo(TDeviceFactoryEntity tdeviceFactoryEntity, TDeviceHoboEntity tdeviceSponEntity) {
        this.tdeviceFactoryEntity = tdeviceFactoryEntity;
        this.tdeviceSponEntity = tdeviceSponEntity;
    }
}
