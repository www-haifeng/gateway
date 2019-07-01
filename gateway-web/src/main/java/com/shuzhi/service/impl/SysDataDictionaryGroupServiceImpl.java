package com.shuzhi.service.impl;

import com.shuzhi.dao.SysDataDictionaryGroupRepository;
import com.shuzhi.entity.SysDataDictionaryGroup;
import com.shuzhi.service.SysDataDictionaryGroupService;
import com.shuzhi.utils.RUtil;
import com.shuzhi.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

/**
 * @ClassName SysDataDictionaryGroupServiceImpl
 * @Author DiaoxiuChong@shuzhi.com
 * @Date 2019/6/28 15:55
 * @Version 1.0
 * @Description:
 **/
@Service
@Transactional
@Slf4j
public class SysDataDictionaryGroupServiceImpl implements SysDataDictionaryGroupService {

    @Autowired
    SysDataDictionaryGroupRepository sysDataDictionaryGroupRepository;

    @Override
    public R saveDictionaryGroup(@Valid SysDataDictionaryGroup sysDataDictionaryGroup) {
        @Valid SysDataDictionaryGroup dataDictionaryGroup = sysDataDictionaryGroupRepository.save(sysDataDictionaryGroup);
        log.info("配置基本信息保存：dataDictionaryGroup = {}"+dataDictionaryGroup);
        return  RUtil.success();
    }

    @Override
    public R selectDictionaryGroup(Integer id) {
        Optional<SysDataDictionaryGroup> sysDataDictionaryGroupRepositoryById = sysDataDictionaryGroupRepository.findById(id);
        log.info("配置基本信息：sysDataDictionaryGroupRepositoryById = {}"+sysDataDictionaryGroupRepositoryById);
        return  RUtil.success(sysDataDictionaryGroupRepositoryById);
    }

    @Override
    public R updateDictionaryGroup(@Valid SysDataDictionaryGroup sysDataDictionaryGroup) {
        @Valid SysDataDictionaryGroup save = sysDataDictionaryGroupRepository.save(sysDataDictionaryGroup);
        log.info("配置更新：save = {}"+ save);
        return  RUtil.success();
    }

    @Override
    public R delectDictionaryGroup(Integer id) {
        sysDataDictionaryGroupRepository.deleteById(id);
        return RUtil.success();
    }
}
