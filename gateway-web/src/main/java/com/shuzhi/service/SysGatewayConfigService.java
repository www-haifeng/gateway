package com.shuzhi.service;

import com.shuzhi.entity.SysGatewayConfig;
import com.shuzhi.vo.R;

import javax.validation.Valid;

public interface SysGatewayConfigService {

    R saveConfig(@Valid SysGatewayConfig sysGatewayConfig);


    R selectConfigList(Integer id);


    R updateConfig(@Valid SysGatewayConfig sysGatewayConfig);


    R delectConfig(Integer id);

}
