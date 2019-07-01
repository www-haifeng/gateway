package com.shuzhi.controller;

import com.shuzhi.annotation.LogInfo;
import com.shuzhi.entity.SysDeviceTecnon;
import com.shuzhi.enums.REnum;
import com.shuzhi.exception.SystemException;
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
 * @ClassName SysDeviceTecnonController
 * @Author DiaoxiuChong@shuzhi.com
 * @Date 2019/6/27 16:16
 * @Version 1.0
 * @Description: 太隆设备配置广播接口
 **/
@RestController
@RequestMapping("/sysDevice")
@Slf4j
public class SysDeviceTecnonController {

    @Autowired
    SysDeviceTecnonService sysDeviceTecnonService;

    /**
     * 新增太隆设备配置
     */
    //@LogInfo(decription = "新增太隆设备配置")
    //@RequiresPermissions("sys:device:insert")
    @PostMapping("/saveTecnon")
    public R saveTecnon(@Valid @RequestBody SysDeviceTecnon sysDeviceTecnon,
                        BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【新增太隆设备配置】参数不正确:sysDeviceTecnon={}"+ sysDeviceTecnon);
            throw new SystemException(REnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        return sysDeviceTecnonService.saveConfig(sysDeviceTecnon);
    }

    /**
     * 查询太隆设备配置
     */
    //@RequiresPermissions("sys:device:list")
    //@LogInfo(decription = "查询太隆设备配置")
    @GetMapping("/selectTecnonList/{id}")
    public R selectTecnonList(@PathVariable("id") Integer id){

        Assert.isNull(id,"id不能为空");
        return sysDeviceTecnonService.selectConfigList(id);
    }

    /**
     * 更新太隆设备配置
     */
    // @RequiresPermissions("sys:device:update")
    //@LogInfo(decription = "更新太隆设备配置")
    @PutMapping("/updateTecnon")
    public R updateTecnon(@Valid @RequestBody SysDeviceTecnon sysDeviceTecnon,
                          BindingResult bindingResult){

        Assert.isNull(sysDeviceTecnon.getId(),"id不能为空");

        if(bindingResult.hasErrors()){
            log.error("【更新太隆设备配置】参数不正确:sysGatewayConfig={}"+ sysDeviceTecnon);
            throw new SystemException(REnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        return sysDeviceTecnonService.updateConfig(sysDeviceTecnon);
    }
    /**
     * 删除太隆设备配置
     */
    //@RequiresPermissions("sys:device:delete")
    //@LogInfo(decription = "删除太隆设备配置")
    @DeleteMapping("/delectTecnon/{id}")
    public R delectTecnon(@PathVariable Integer id){
        return sysDeviceTecnonService.delectConfig(id);
    }
}
