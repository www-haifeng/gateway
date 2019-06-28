package com.shuzhi.service;

import com.shuzhi.entity.SysLog;
import com.shuzhi.vo.R;

/**
* @Program: SysLogService
* @Description:
* @Author: YuJQ
* @Create: 2019/6/26 11:03
**/
public interface SysLogService {
    public R saveSysLog(SysLog sysLog);
}
