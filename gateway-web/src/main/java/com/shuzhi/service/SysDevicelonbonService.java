package com.shuzhi.service;

import com.shuzhi.entity.SysDevicelonbon;
import com.shuzhi.vo.R;

import javax.validation.Valid;

public interface SysDevicelonbonService {
    R savelonbon(@Valid SysDevicelonbon sysDevicelonbon);

    R selectlonbonList(Integer id);


    R updatelonbon(@Valid SysDevicelonbon sysDevicelonbon);

    R delectlonbon(Integer id);


}
