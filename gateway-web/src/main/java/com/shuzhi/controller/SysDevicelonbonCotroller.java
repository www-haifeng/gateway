package com.shuzhi.controller;

import com.shuzhi.annotation.LogInfo;
import com.shuzhi.entity.SysDevicelonbon;
import com.shuzhi.enums.REnum;
import com.shuzhi.exception.SystemException;
import com.shuzhi.service.SysDevicelonbonService;
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
 * @ClassName SysDevicelonbonCotroller
 * @Author DiaoxiuChong@shuzhi.com
 * @Date 2019/6/27 16:49
 * @Version 1.0
 * @Description: 来邦设备配置接口
 **/
@RestController
@RequestMapping("/sysDevice")
@Slf4j
public class SysDevicelonbonCotroller {

    @Autowired
    SysDevicelonbonService sysDevicelonbonService;

    /**
     * 新增来邦设备配置
     */
    //@LogInfo(decription = "新增来邦设备配置")
    //@RequiresPermissions("sys:device:insert")
    @PostMapping("/savelonbon")
    public R savelonbon(@Valid @RequestBody SysDevicelonbon sysDevicelonbon,
                        BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【新增来邦设备配置】参数不正确:sysDevicelonbon={}"+ sysDevicelonbon);
            throw new SystemException(REnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        return sysDevicelonbonService.savelonbon(sysDevicelonbon);
    }

    /**
     * 查询来邦设备配置
     */
    //@RequiresPermissions("sys:device:list")
    //@LogInfo(decription = "查询来邦设备配置")
    //@GetMapping("/selectlonbonList/{id}")
    @RequestMapping(value = {"/selectlonbonList/{id}", "/selectlonbonList/"},method = RequestMethod.GET)
    public R selectlonbonList(@PathVariable(required = false) Integer id){

        //Assert.isNull(id,"id不能为空");
        return sysDevicelonbonService.selectlonbonList(id);
    }

    /**
     * 更新来邦设备配置
     */
    // @RequiresPermissions("sys:device:update")
    //@LogInfo(decription = "更新来邦设备配置")
    @PutMapping("/updatelonbon")
    public R updatelonbon(@Valid @RequestBody SysDevicelonbon sysDevicelonbon,
                          BindingResult bindingResult){

        Assert.isNull(sysDevicelonbon.getId(),"id不能为空");

        if(bindingResult.hasErrors()){
            log.error("【更新来邦设备配置】参数不正确:sysDevicelonbon={}"+ sysDevicelonbon);
            throw new SystemException(REnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        return sysDevicelonbonService.updatelonbon(sysDevicelonbon);
    }
    /**
     * 删除来邦设备配置
     */
    //@RequiresPermissions("sys:device:delete")
    //@LogInfo(decription = "删除来邦设备配置")
    @DeleteMapping("/delectlonbon/{id}")
    public R delectlonbon(@PathVariable Integer id){
        return sysDevicelonbonService.delectlonbon(id);
    }

}
