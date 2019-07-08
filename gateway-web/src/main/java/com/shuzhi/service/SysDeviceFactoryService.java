package com.shuzhi.service;

import com.shuzhi.entity.SysDeviceFactory;
import com.shuzhi.vo.R;

/**
 * @ProjectName: gateway-parent
 * @Package: com.shuzhi.service
 * @ClassName: SysDeviceFactoryService
 * @Author: 陈鑫晖
 * @Date: 2019/7/8 17:07
 */
public interface SysDeviceFactoryService {

    /**
     * 查询设备列表
     * @return
     */
    R selectSysDeviceFactory();

    /**
     * 添加设备
     * @param sysDeviceFactory
     * @return
     */
    R insertSysDeviceFactory(SysDeviceFactory sysDeviceFactory);

    /**
     * 修改设备信息
     * @param sysDeviceFactory
     * @return
     */
    R updateSysDeviceFactory(SysDeviceFactory sysDeviceFactory);

    /**
     * 删除设备信息
     * @param id
     * @return
     */
    R deleteSysDeviceFactory(Integer id);
}
