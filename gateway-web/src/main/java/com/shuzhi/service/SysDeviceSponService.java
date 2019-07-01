package com.shuzhi.service;

import com.shuzhi.entity.SysDeviceSpon;
import com.shuzhi.vo.R;

import javax.validation.Valid;

public interface SysDeviceSponService {
    R saveSpon(@Valid SysDeviceSpon sysDeviceSpon);


    R selectSponList(Integer id);


    R updateSpon(@Valid SysDeviceSpon sysDeviceSpon);


    R delectSpon(Integer id);


}
