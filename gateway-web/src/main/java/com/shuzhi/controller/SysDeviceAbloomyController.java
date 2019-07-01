package com.shuzhi.controller;

import com.shuzhi.annotation.LogInfo;
import com.shuzhi.entity.SysDeviceAbloomy;
import com.shuzhi.entity.SysDeviceTecnon;
import com.shuzhi.enums.REnum;
import com.shuzhi.exception.SystemException;
import com.shuzhi.service.SysDeviceAbloomyService;
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
 * @ClassName SysDeviceAbloomyController
 * @Author DiaoxiuChong@shuzhi.com
 * @Date 2019/6/27 17:09
 * @Version 1.0
 * @Description: 韵盛发设备配置wifi接口
 **/
@RestController
@RequestMapping("/sysDevice")
@Slf4j
public class SysDeviceAbloomyController {

    @Autowired
    SysDeviceAbloomyService sysDeviceAbloomyService;

    /**
     * 新增韵盛发设备配置wifi
     */
    //@LogInfo(decription = "新增韵盛发设备配置wifi")
    //@RequiresPermissions("sys:device:insert")
    @PostMapping("/saveAbloomy")
    public R saveAbloomy(@Valid @RequestBody SysDeviceAbloomy sysDeviceAbloomy,
                        BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【新增韵盛发设备配置wifi】参数不正确:sysDeviceAbloomy={}"+ sysDeviceAbloomy);
            throw new SystemException(REnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        return sysDeviceAbloomyService.saveAbloomy(sysDeviceAbloomy);
    }

    /**
     * 查询韵盛发设备配置wifi
     */
    //@RequiresPermissions("sys:device:list")
    //@LogInfo(decription = "查询韵盛发设备配置wifi")
    @GetMapping("/selectAbloomyList/{id}")
    public R selectAbloomyList(@PathVariable("id") Integer id){

        Assert.isNull(id,"id不能为空");
        return sysDeviceAbloomyService.selectAbloomyList(id);
    }

    /**
     * 更新韵盛发设备配置wifi
     */
    // @RequiresPermissions("sys:device:update")
    //@LogInfo(decription = "更新韵盛发设备配置wifi")
    @PutMapping("/updateAbloomy")
    public R updateAbloomy(@Valid @RequestBody SysDeviceAbloomy sysDeviceAbloomy,
                          BindingResult bindingResult){

        Assert.isNull(sysDeviceAbloomy.getId(),"id不能为空");

        if(bindingResult.hasErrors()){
            log.error("【更新韵盛发设备配置wifi】参数不正确:sysGatewayConfig={}"+ sysDeviceAbloomy);
            throw new SystemException(REnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        return sysDeviceAbloomyService.updateAbloomy(sysDeviceAbloomy);
    }
    /**
     * 删除韵盛发设备配置wifi
     */
    //@RequiresPermissions("sys:device:delete")
    //@LogInfo(decription = "删除韵盛发设备配置wifi")
    @DeleteMapping("/delectAbloomy/{id}")
    public R delectAbloomy(@PathVariable Integer id){
        return sysDeviceAbloomyService.delectAbloomy(id);
    }
}
