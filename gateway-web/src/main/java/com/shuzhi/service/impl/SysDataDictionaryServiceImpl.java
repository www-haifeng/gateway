package com.shuzhi.service.impl;

import com.shuzhi.dao.SysDataDictionaryRepository;
import com.shuzhi.entity.SysDataDictionary;
import com.shuzhi.service.SysDataDictionaryService;
import com.shuzhi.utils.RUtil;
import com.shuzhi.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

/**
 * @ClassName SysDataDictionaryServiceImpl
 * @Author DiaoxiuChong@shuzhi.com
 * @Date 2019/6/28 9:24
 * @Version 1.0
 * @Description:
 **/
@Service
@Transactional
@Slf4j
public class SysDataDictionaryServiceImpl implements SysDataDictionaryService {

    @Autowired
    SysDataDictionaryRepository sysDataDictionaryRepository;

    @Override
    public R saveDictionary(@Valid SysDataDictionary sysDataDictionary) {
        @Valid SysDataDictionary dataDictionary = sysDataDictionaryRepository.save(sysDataDictionary);
        log.info("配置基本信息保存：dataDictionary = {}"+dataDictionary);
        return  RUtil.success();
    }

    @Override
    public R selectDictionary(Integer id) {
        Optional<SysDataDictionary> sysDataDictionaryRepositoryOne = sysDataDictionaryRepository.findById(id);
        log.info("配置基本信息：sysDataDictionaryRepositoryOne = {}"+sysDataDictionaryRepositoryOne);
        return  RUtil.success(sysDataDictionaryRepositoryOne);
    }

    @Override
    public R updateDictionary(@Valid SysDataDictionary sysDataDictionary) {
        @Valid SysDataDictionary save = sysDataDictionaryRepository.save(sysDataDictionary);
        log.info("配置更新：save = {}"+ save);
        return  RUtil.success();
    }

    @Override
    public R delectDictionary(Integer id) {
        sysDataDictionaryRepository.deleteById(id);
        return RUtil.success();
    }
}
