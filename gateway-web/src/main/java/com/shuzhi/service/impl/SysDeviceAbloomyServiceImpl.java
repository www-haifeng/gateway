package com.shuzhi.service.impl;

import com.shuzhi.dao.SysDeviceAbloomyRepository;
import com.shuzhi.entity.SysDeviceAbloomy;
import com.shuzhi.service.SysDeviceAbloomyService;
import com.shuzhi.utils.RUtil;
import com.shuzhi.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

/**
 * @ClassName SysDeviceAbloomyServiceImpl
 * @Author DiaoxiuChong@shuzhi.com
 * @Date 2019/6/28 9:24
 * @Version 1.0
 * @Description:
 **/
@Service
@Transactional
@Slf4j
public class SysDeviceAbloomyServiceImpl implements SysDeviceAbloomyService {

    @Autowired
    SysDeviceAbloomyRepository sysDeviceAbloomyRepository;

    @Override
    public R saveAbloomy(@Valid SysDeviceAbloomy sysDeviceAbloomy) {
        @Valid SysDeviceAbloomy deviceAbloomy = sysDeviceAbloomyRepository.save(sysDeviceAbloomy);
        log.info("配置基本信息保存：deviceAbloomy = {}"+deviceAbloomy);
        return  RUtil.success();
    }

    @Override
    public R selectAbloomyList(Integer id) {
        Optional<SysDeviceAbloomy> sysDeviceAbloomyRepositoryOne = sysDeviceAbloomyRepository.findById(id);
        log.info("配置基本信息：sysDeviceAbloomyRepositoryOne = {}"+sysDeviceAbloomyRepositoryOne);
        return  RUtil.success(sysDeviceAbloomyRepositoryOne);
    }

    @Override
    public R updateAbloomy(@Valid SysDeviceAbloomy sysDeviceAbloomy) {
        @Valid SysDeviceAbloomy save = sysDeviceAbloomyRepository.save(sysDeviceAbloomy);
        log.info("配置更新：save = {}"+ save);
        return  RUtil.success();
    }

    @Override
    public R delectAbloomy(Integer id) {
        sysDeviceAbloomyRepository.deleteById(id);
        return RUtil.success();
    }
}
