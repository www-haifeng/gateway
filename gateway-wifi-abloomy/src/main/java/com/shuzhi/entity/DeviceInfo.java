package com.shuzhi.entity;

import java.io.Serializable;

/**
 * @Description:设备信息
 * @Author: YHF
 * @date 17:10
 */
public class DeviceInfo implements Serializable {

    private TDeviceFactoryEntity tdeviceFactoryEntity;
    private TDeviceAbloomyEntity tdeviceAbloomyEntity;

    public DeviceInfo() {
    }

    public DeviceInfo(TDeviceFactoryEntity tdeviceFactoryEntity) {
        this.tdeviceFactoryEntity = tdeviceFactoryEntity;
    }

    public DeviceInfo(TDeviceAbloomyEntity tdeviceAbloomyEntity) {
        this.tdeviceAbloomyEntity = tdeviceAbloomyEntity;
    }

    public DeviceInfo(TDeviceFactoryEntity tdeviceFactoryEntity, TDeviceAbloomyEntity tdeviceAbloomyEntity) {
        this.tdeviceFactoryEntity = tdeviceFactoryEntity;
        this.tdeviceAbloomyEntity = tdeviceAbloomyEntity;
    }

    public TDeviceFactoryEntity getTdeviceFactoryEntity() {
        return tdeviceFactoryEntity;
    }

    public void setTdeviceFactoryEntity(TDeviceFactoryEntity tdeviceFactoryEntity) {
        this.tdeviceFactoryEntity = tdeviceFactoryEntity;
    }

    public TDeviceAbloomyEntity getTdeviceAbloomyEntity() {
        return tdeviceAbloomyEntity;
    }

    public void setTdeviceAbloomyEntity(TDeviceAbloomyEntity tdeviceAbloomyEntity) {
        this.tdeviceAbloomyEntity = tdeviceAbloomyEntity;
    }
}
