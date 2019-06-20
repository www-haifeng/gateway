package com.shuzhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:设备信息
 * @Author: ztt
 * @date 17:10
 */
@Data
@AllArgsConstructor
public class DeviceInfo implements Serializable {

    private TDeviceFactoryEntity tdeviceFactoryEntity;
    private TDeviceLonBonEntity tdeviceLonBonEntity;

}
