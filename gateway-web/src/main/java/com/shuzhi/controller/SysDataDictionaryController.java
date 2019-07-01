package com.shuzhi.controller;

import com.shuzhi.annotation.LogInfo;
import com.shuzhi.entity.SysDataDictionary;
import com.shuzhi.entity.SysDeviceTecnon;
import com.shuzhi.enums.REnum;
import com.shuzhi.exception.SystemException;
import com.shuzhi.service.SysDataDictionaryService;
import com.shuzhi.service.SysDeviceTecnonService;
import com.shuzhi.utils.Assert;
import com.shuzhi.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @ClassName SysDataDictionaryController
 * @Author DiaoxiuChong@shuzhi.com
 * @Date 2019/6/27 17:31
 * @Version 1.0
 * @Description: 数据字典管理
 **/
@RestController
@RequestMapping("/sysData")
@Slf4j
public class SysDataDictionaryController {

    @Autowired
    SysDataDictionaryService sysDataDictionaryService;

    /**
     * 新增数据字典管理
     */
    //@LogInfo(decription = "新增数据字典管理")
    //@RequiresPermissions("sys:dictionary:insert")
    @PostMapping("/saveDictionary")
    public R saveDictionary(@Valid @RequestBody SysDataDictionary sysDataDictionary,
                        BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【新增数据字典管理】参数不正确:sysDataDictionary={}"+ sysDataDictionary);
            throw new SystemException(REnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        return sysDataDictionaryService.saveDictionary(sysDataDictionary);
    }

    /**
     * 查询数据字典管理
     */
    //@RequiresPermissions("sys:dictionary:list")
    //@LogInfo(decription = "查询数据字典管理")
    @GetMapping("/selectDictionaryList/{id}")
    public R selectDictionaryList(@PathVariable("id") Integer id){

        Assert.isNull(id,"id不能为空");
        return sysDataDictionaryService.selectDictionary(id);
    }

    /**
     * 更新数据字典管理
     */
    // @RequiresPermissions("sys:dictionary:update")
    //@LogInfo(decription = "更新数据字典管理")
    @PutMapping("/updateDictionary")
    public R updateDictionary(@Valid @RequestBody SysDataDictionary sysDataDictionary,
                          BindingResult bindingResult){

        Assert.isNull(sysDataDictionary.getId(),"id不能为空");

        if(bindingResult.hasErrors()){
            log.error("【更新数据字典管理】参数不正确:sysDataDictionary={}"+ sysDataDictionary);
            throw new SystemException(REnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        return sysDataDictionaryService.updateDictionary(sysDataDictionary);
    }
    /**
     * 删除数据字典管理
     */
    //@RequiresPermissions("sys:dictionary:delete")
    //@LogInfo(decription = "删除数据字典管理")
    @DeleteMapping("/delectDictionary/{id}")
    public R delectDictionary(@PathVariable Integer id){
        return sysDataDictionaryService.delectDictionary(id);
    }
}
