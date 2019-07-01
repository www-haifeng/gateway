package com.shuzhi.service;

import com.shuzhi.entity.SysDataDictionaryGroup;
import com.shuzhi.vo.R;

import javax.validation.Valid;

public interface SysDataDictionaryGroupService {

    R saveDictionaryGroup(@Valid SysDataDictionaryGroup sysDataDictionaryGroup);

    R selectDictionaryGroup(Integer id);

    R updateDictionaryGroup(@Valid SysDataDictionaryGroup sysDataDictionaryGroup);

    R delectDictionaryGroup(Integer id);
}
