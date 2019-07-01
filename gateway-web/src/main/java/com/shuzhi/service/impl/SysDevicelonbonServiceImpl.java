package com.shuzhi.service.impl;

import com.shuzhi.dao.SysDevicelonbonRepository;
import com.shuzhi.entity.SysDevicelonbon;
import com.shuzhi.service.SysDevicelonbonService;
import com.shuzhi.utils.RUtil;
import com.shuzhi.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

/**
 * @ClassName SysDevicelonbonServiceImpl
 * @Author DiaoxiuChong@shuzhi.com
 * @Date 2019/6/28 9:24
 * @Version 1.0
 * @Description:
 **/
@Service
@Transactional
@Slf4j
public class SysDevicelonbonServiceImpl implements SysDevicelonbonService {

    @Autowired
    SysDevicelonbonRepository sysDevicelonbonRepository;

    @Override
    public R savelonbon(@Valid SysDevicelonbon sysDevicelonbon) {
        @Valid SysDevicelonbon devicelonbon = sysDevicelonbonRepository.save(sysDevicelonbon);
        log.info("配置基本信息保存：devicelonbon = {}"+devicelonbon);
        return  RUtil.success();
    }

    @Override
    public R selectlonbonList(Integer id) {
        Optional<SysDevicelonbon> sysDevicelonbonRepositoryOne = sysDevicelonbonRepository.findById(id);
        log.info("配置基本信息：sysDevicelonbonRepositoryOne = {}"+sysDevicelonbonRepositoryOne);
        return  RUtil.success(sysDevicelonbonRepositoryOne);
    }

    @Override
    public R updatelonbon(@Valid SysDevicelonbon sysDevicelonbon) {
        @Valid SysDevicelonbon save = sysDevicelonbonRepository.save(sysDevicelonbon);
        log.info("配置更新：save = {}"+ save);
        return  RUtil.success();
    }

    @Override
    public R delectlonbon(Integer id) {
        sysDevicelonbonRepository.deleteById(id);
        return RUtil.success();
    }
}
