package com.shuzhi.entity;

import java.io.Serializable;

/**
 * @Description:设备信息
 * @Author: YHF
 * @date 17:10
 */
public class DeviceInfo implements Serializable {

    private TDeviceFactoryEntity tdeviceFactoryEntity;
    private TDeviceTecnonEntity tdeviceTecnonEntity;

    public DeviceInfo() {

    }

    public DeviceInfo(TDeviceFactoryEntity tdeviceFactoryEntity) {
        this.tdeviceFactoryEntity = tdeviceFactoryEntity;
    }

    public DeviceInfo(TDeviceTecnonEntity tdeviceTecnonEntity) {
        this.tdeviceTecnonEntity = tdeviceTecnonEntity;
    }

    public DeviceInfo(TDeviceFactoryEntity tdeviceFactoryEntity, TDeviceTecnonEntity tdeviceTecnonEntity) {
        this.tdeviceFactoryEntity = tdeviceFactoryEntity;
        this.tdeviceTecnonEntity = tdeviceTecnonEntity;
    }

    public TDeviceFactoryEntity getTdeviceFactoryEntity() {
        return tdeviceFactoryEntity;
    }

    public void setTdeviceFactoryEntity(TDeviceFactoryEntity tdeviceFactoryEntity) {
        this.tdeviceFactoryEntity = tdeviceFactoryEntity;
    }

    public TDeviceTecnonEntity getTdeviceTecnonEntity() {
        return tdeviceTecnonEntity;
    }

    public void setTdeviceTecnonEntity(TDeviceTecnonEntity tdeviceTecnonEntity) {
        this.tdeviceTecnonEntity = tdeviceTecnonEntity;
    }
}
