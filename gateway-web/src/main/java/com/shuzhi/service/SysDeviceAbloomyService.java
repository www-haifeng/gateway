package com.shuzhi.service;

import com.shuzhi.entity.SysDeviceAbloomy;
import com.shuzhi.vo.R;

import javax.validation.Valid;

public interface SysDeviceAbloomyService {
    R saveAbloomy(@Valid SysDeviceAbloomy sysDeviceAbloomy);


    R selectAbloomyList(Integer id);


    R updateAbloomy(@Valid SysDeviceAbloomy sysDeviceAbloomy);


    R delectAbloomy(Integer id);

}
