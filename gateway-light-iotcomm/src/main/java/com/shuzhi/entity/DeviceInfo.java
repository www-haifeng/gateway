package com.shuzhi.entity;

import java.io.Serializable;

/**
 * @Description:设备信息
 * @Author: YHF
 * @date 17:10
 */
public class DeviceInfo implements Serializable {

    private TDeviceFactoryEntity tdeviceFactoryEntity;

    private TDeviceIotcommEntity tdeviceIotcommEntity;

    public DeviceInfo() {

    }

    public DeviceInfo(TDeviceFactoryEntity tdeviceFactoryEntity, TDeviceIotcommEntity tdeviceIotcommEntity) {
        this.tdeviceFactoryEntity = tdeviceFactoryEntity;
        this.tdeviceIotcommEntity = tdeviceIotcommEntity;
    }

    public TDeviceFactoryEntity getTdeviceFactoryEntity() {
        return tdeviceFactoryEntity;
    }

    public void setTdeviceFactoryEntity(TDeviceFactoryEntity tdeviceFactoryEntity) {
        this.tdeviceFactoryEntity = tdeviceFactoryEntity;
    }

    public TDeviceIotcommEntity getTDeviceIotcommEntity() {
        return tdeviceIotcommEntity;
    }

    public void setTDeviceIotcommEntity(TDeviceIotcommEntity tdeviceIotcommEntity) {
        this.tdeviceIotcommEntity = tdeviceIotcommEntity;
    }
}
