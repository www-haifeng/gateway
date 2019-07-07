package com.shuzhi.controller;

import com.shuzhi.annotation.LogInfo;
import com.shuzhi.entity.SysDataDictionary;
import com.shuzhi.entity.SysDataDictionaryGroup;
import com.shuzhi.enums.REnum;
import com.shuzhi.exception.SystemException;
import com.shuzhi.service.SysDataDictionaryGroupService;
import com.shuzhi.service.SysDataDictionaryService;
import com.shuzhi.utils.Assert;
import com.shuzhi.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @ClassName SysDataDictionaryGroupController
 * @Author DiaoxiuChong@shuzhi.com
 * @Date 2019/6/28 15:37
 * @Version 1.0
 * @Description: 数据字典组管理
 **/
@RestController
@RequestMapping("/sysData")
@Slf4j
public class SysDataDictionaryGroupController {

    @Autowired
    SysDataDictionaryGroupService sysDataDictionaryGroupService;

    /**
     * 新增数据字典管理
     */
    //@LogInfo(decription = "新增数据字典组管理")
    //@RequiresPermissions("sys:dictionary:insert")
    @PostMapping("/saveDictionaryGroup")
    public R saveDictionaryGroup(@Valid @RequestBody SysDataDictionaryGroup sysDataDictionaryGroup,
                            BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【新增数据字典组管理】参数不正确:sysDataDictionaryGroup={}"+ sysDataDictionaryGroup);
            throw new SystemException(REnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        return sysDataDictionaryGroupService.saveDictionaryGroup(sysDataDictionaryGroup);
    }

    /**
     * 查询太隆设备配置
     */
    //@RequiresPermissions("sys:dictionary:list")
    //@LogInfo(decription = "查询数据字典组管理")
    //@GetMapping("/selectDictionaryGroupList/{id}")
    @RequestMapping(value = {"/selectDictionaryGroupList/{id}", "/selectDictionaryGroupList/"},method = RequestMethod.GET)
    public R selectDictionaryGroupList(@PathVariable(required = false) Integer id){

        //Assert.isNull(id,"id不能为空");
        return sysDataDictionaryGroupService.selectDictionaryGroup(id);
    }

    /**
     * 更新太隆设备配置
     */
    // @RequiresPermissions("sys:dictionary:update")
    //@LogInfo(decription = "更新数据字典组管理")
    @PutMapping("/updateDictionaryGroup")
    public R updateDictionaryGroup(@Valid @RequestBody SysDataDictionaryGroup sysDataDictionaryGroup,
                              BindingResult bindingResult){

        Assert.isNull(sysDataDictionaryGroup.getId(),"id不能为空");

        if(bindingResult.hasErrors()){
            log.error("【更新数据字典组管理】参数不正确:sysDataDictionaryGroup={}"+ sysDataDictionaryGroup);
            throw new SystemException(REnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        return sysDataDictionaryGroupService.updateDictionaryGroup(sysDataDictionaryGroup);
    }
    /**
     * 删除太隆设备配置
     */
    //@RequiresPermissions("sys:dictionary:delete")
    //@LogInfo(decription = "删除数据字典组管理")
    @DeleteMapping("/delectDictionaryGroup/{id}")
    public R delectDictionaryGroup(@PathVariable Integer id){
        return sysDataDictionaryGroupService.delectDictionaryGroup(id);
    }

}
