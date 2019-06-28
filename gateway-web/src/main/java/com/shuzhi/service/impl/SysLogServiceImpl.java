package com.shuzhi.service.impl;

import com.shuzhi.dao.SysLogEntityRepository;
import com.shuzhi.entity.SysLog;
import com.shuzhi.service.SysLogService;
import com.shuzhi.utils.RUtil;
import com.shuzhi.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * author: Yujq
 * date: 2018/4/5
 */
@Service
@Slf4j
@Transactional
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    SysLogEntityRepository sysLogEntityRepository;

    @Override
    public R saveSysLog(SysLog sysLog) {


        SysLog logSave = sysLogEntityRepository.save(sysLog);
        log.info("日志信息保存：logSave = {}"+logSave);
        return RUtil.success();
    }

}
