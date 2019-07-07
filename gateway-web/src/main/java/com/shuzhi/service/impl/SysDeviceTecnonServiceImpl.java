package com.shuzhi.service.impl;

import com.shuzhi.dao.SysDeviceTecnonRepository;
import com.shuzhi.entity.SysDeviceTecnon;
import com.shuzhi.service.SysDeviceTecnonService;
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
 * @ClassName SysDeviceTecnonServiceImpl
 * @Author DiaoxiuChong@shuzhi.com
 * @Date 2019/6/28 9:22
 * @Version 1.0
 * @Description:
 **/
@Service
@Transactional
@Slf4j
public class SysDeviceTecnonServiceImpl implements SysDeviceTecnonService {

    @Autowired
    SysDeviceTecnonRepository sysDeviceTecnonRepository;

    @Override
    public R saveConfig(@Valid SysDeviceTecnon sysDeviceTecnon) {
        @Valid SysDeviceTecnon deviceTecnon = sysDeviceTecnonRepository.save(sysDeviceTecnon);
        log.info("配置基本信息保存：deviceTecnon = {}"+deviceTecnon);
        return RUtil.success();
    }

    @Override
    public R selectConfigList(Integer id) {
        if(id != null){
            Optional<SysDeviceTecnon> sysDeviceTecnonRepositoryOne = sysDeviceTecnonRepository.findById(id);
            log.info("配置基本信息：sysDeviceTecnonRepositoryOne = {}"+sysDeviceTecnonRepositoryOne);
            return RUtil.success(sysDeviceTecnonRepositoryOne);
        }else {
            List<SysDeviceTecnon> sysDeviceTecnonRepositoryAll = sysDeviceTecnonRepository.findAll();
            log.info("配置基本信息：sysDeviceTecnonRepositoryAll = {}"+sysDeviceTecnonRepositoryAll);
            return RUtil.success(sysDeviceTecnonRepositoryAll);
        }

    }

    @Override
    public R updateConfig(@Valid SysDeviceTecnon sysDeviceTecnon) {

        @Valid SysDeviceTecnon save = sysDeviceTecnonRepository.save(sysDeviceTecnon);
        log.info("配置更新：save = {}"+ save);
        return RUtil.success();
    }

    @Override
    public R delectConfig(Integer id) {
        sysDeviceTecnonRepository.deleteById(id);
        return RUtil.success();
    }
}
