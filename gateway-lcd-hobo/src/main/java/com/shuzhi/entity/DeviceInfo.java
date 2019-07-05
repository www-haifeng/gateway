package com.shuzhi.entity;

import java.io.Serializable;

/**
 * @Description:设备信息
 * @Author: YHF
 * @date 17:10
 */
public class DeviceInfo implements Serializable {

    private TDeviceFactoryEntity tdeviceFactoryEntity;
    private TDeviceHoboEntity tdeviceHoboEntity;

    public DeviceInfo() {

    }

    public DeviceInfo(TDeviceFactoryEntity tdeviceFactoryEntity, TDeviceHoboEntity tdeviceHoboEntity) {
        this.tdeviceFactoryEntity = tdeviceFactoryEntity;
        this.tdeviceHoboEntity = tdeviceHoboEntity;
    }

    public TDeviceFactoryEntity getTdeviceFactoryEntity() {
        return tdeviceFactoryEntity;
    }

    public void setTdeviceFactoryEntity(TDeviceFactoryEntity tdeviceFactoryEntity) {
        this.tdeviceFactoryEntity = tdeviceFactoryEntity;
    }

    public TDeviceHoboEntity getTdeviceHoboEntity() {
        return tdeviceHoboEntity;
    }

    public void setTdeviceHoboEntity(TDeviceHoboEntity tdeviceHoboEntity) {
        this.tdeviceHoboEntity = tdeviceHoboEntity;
    }
}
