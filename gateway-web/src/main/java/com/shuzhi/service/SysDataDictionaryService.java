package com.shuzhi.service;

import com.shuzhi.entity.SysDataDictionary;
import com.shuzhi.vo.R;

import javax.validation.Valid;

public interface SysDataDictionaryService {

    R saveDictionary(@Valid SysDataDictionary sysDataDictionary);


    R selectDictionary(Integer id);


    R updateDictionary(@Valid SysDataDictionary sysDataDictionary);


    R delectDictionary(Integer id);


}
