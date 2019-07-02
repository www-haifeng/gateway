package com.shuzhi.controller;

import com.shuzhi.annotation.LogInfo;
import com.shuzhi.entity.SysDeviceSpon;
import com.shuzhi.enums.REnum;
import com.shuzhi.exception.SystemException;
import com.shuzhi.service.SysDeviceSponService;
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
 * @ClassName SysDeviceSponController
 * @Author DiaoxiuChong@shuzhi.com
 * @Date 2019/6/27 16:31
 * @Version 1.0
 * @Description: 世邦设备配置灯杆屏接口
 **/
@RestController
@RequestMapping("/sysDevice")
@Slf4j
public class SysDeviceSponController {

    @Autowired
    SysDeviceSponService sysDeviceSponService;

    /**
     * 新增世邦设备配置
     */
    //@LogInfo(decription = "新增世邦设备配置")
    //@RequiresPermissions("sys:device:insert")
    @PostMapping("/saveSpon")
    public R saveSpon(@Valid @RequestBody SysDeviceSpon sysDeviceSpon,
                        BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【新增世邦设备配置】参数不正确:sysDeviceSpon={}"+ sysDeviceSpon);
            throw new SystemException(REnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        return sysDeviceSponService.saveSpon(sysDeviceSpon);
    }

    /**
     * 查询世邦设备配置
     */
    //@RequiresPermissions("sys:device:list")
    //@LogInfo(decription = "查询世邦设备配置")
    //@GetMapping("/selectSponList/{id}")
    @RequestMapping(value = {"/selectSponList/{id}", "/selectSponList/"},method = RequestMethod.GET)
    public R selectSponList(@PathVariable(required = false) Integer id){

        //Assert.isNull(id,"id不能为空");
        return sysDeviceSponService.selectSponList(id);
    }

    /**
     * 更新世邦设备配置
     */
    // @RequiresPermissions("sys:device:update")
    //@LogInfo(decription = "更新世邦设备配置")
    @PutMapping("/updateSpon")
    public R updateSpon(@Valid @RequestBody SysDeviceSpon sysDeviceSpon,
                          BindingResult bindingResult){

        Assert.isNull(sysDeviceSpon.getId(),"id不能为空");

        if(bindingResult.hasErrors()){
            log.error("【更新世邦设备配置】参数不正确:sysGatewayConfig={}"+ sysDeviceSpon);
            throw new SystemException(REnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        return sysDeviceSponService.updateSpon(sysDeviceSpon);
    }
    /**
     * 删除太隆设备配置
     */
    //@RequiresPermissions("sys:device:delete")
    //@LogInfo(decription = "删除世邦设备配置")
    @DeleteMapping("/delectSpon/{id}")
    public R delectSpon(@PathVariable Integer id){
        return sysDeviceSponService.delectSpon(id);
    }
}
