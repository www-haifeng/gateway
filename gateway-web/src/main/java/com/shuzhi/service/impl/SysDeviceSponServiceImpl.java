package com.shuzhi.service.impl;

import com.shuzhi.dao.SysDeviceSponRepository;
import com.shuzhi.entity.SysDeviceSpon;
import com.shuzhi.service.SysDeviceSponService;
import com.shuzhi.utils.RUtil;
import com.shuzhi.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName SysDeviceSponServiceImpl
 * @Author DiaoxiuChong@shuzhi.com
 * @Date 2019/6/28 9:23
 * @Version 1.0
 * @Description:
 **/
@Service
@Transactional
@Slf4j
public class SysDeviceSponServiceImpl implements SysDeviceSponService {

    @Autowired
    SysDeviceSponRepository sysDeviceSponRepository;

    @Override
    public R saveSpon(@Valid SysDeviceSpon sysDeviceSpon) {
        @Valid SysDeviceSpon sysDeviceSpon1 = sysDeviceSponRepository.save(sysDeviceSpon);
        log.info("配置基本信息保存：sysDeviceSpon1 = {}"+sysDeviceSpon1);
        return  RUtil.success();
    }

    @Override
    public R selectSponList(Integer id) {
        if(id != null){
            Optional<SysDeviceSpon> sysDeviceSponRepositoryOne = sysDeviceSponRepository.findById(id);
            log.info("配置基本信息：sysDeviceSponRepositoryOne = {}"+sysDeviceSponRepositoryOne);
            return RUtil.success(sysDeviceSponRepositoryOne);
        }else {
            List<SysDeviceSpon> sysDeviceSponRepositoryAll = sysDeviceSponRepository.findAll();
            log.info("配置基本信息：sysDeviceSponRepositoryAll = {}"+sysDeviceSponRepositoryAll);
            return RUtil.success(sysDeviceSponRepositoryAll);
        }
    }

    @Override
    public R updateSpon(@Valid SysDeviceSpon sysDeviceSpon) {
        @Valid SysDeviceSpon save = sysDeviceSponRepository.save(sysDeviceSpon);
        log.info("配置更新：save = {}"+ save);
        return RUtil.success();
    }

    @Override
    public R delectSpon(Integer id) {
        sysDeviceSponRepository.deleteById(id);
        return RUtil.success();
    }
}
