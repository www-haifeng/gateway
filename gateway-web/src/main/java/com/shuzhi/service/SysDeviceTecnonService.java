package com.shuzhi.service;

import com.shuzhi.entity.SysDeviceTecnon;
import com.shuzhi.vo.R;

import javax.validation.Valid;

public interface SysDeviceTecnonService {

    R saveConfig(@Valid SysDeviceTecnon sysDeviceTecnon);

    R selectConfigList(Integer id);

    R updateConfig(@Valid SysDeviceTecnon sysDeviceTecnon);


    R delectConfig(Integer id);

}
