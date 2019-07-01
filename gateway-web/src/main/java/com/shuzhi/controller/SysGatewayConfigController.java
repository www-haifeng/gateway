package com.shuzhi.controller;

import com.shuzhi.annotation.LogInfo;
import com.shuzhi.entity.SysGatewayConfig;
import com.shuzhi.enums.REnum;
import com.shuzhi.exception.SystemException;
import com.shuzhi.service.SysGatewayConfigService;
import com.shuzhi.utils.Assert;
import com.shuzhi.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @ClassName SysGatewayConfigController
 * @Author DiaoxiuChong@shuzhi.com
 * @Date 2019/6/27 10:50
 * @Version 1.0
 * @Description: 网关配置接口
 **/
@RestController
@RequestMapping("/sysGateway")
@Slf4j
public class SysGatewayConfigController {

    @Autowired
    SysGatewayConfigService sysGatewayConfigService;

    /**
     * 新增网关配置
     */
    //@LogInfo(decription = "新增网关配置")
    //@RequiresPermissions("sys:config:insert")
    @PostMapping("/saveConfig")
    public R saveConfig(@Valid @RequestBody SysGatewayConfig sysGatewayConfig,
                      BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【新增网关配置】参数不正确:sysGatewayConfig={}"+ sysGatewayConfig);
            throw new SystemException(REnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        return sysGatewayConfigService.saveConfig(sysGatewayConfig);
    }

    /**
     * 查询网关配置
     */
    //@RequiresPermissions("sys:config:list")
    //@LogInfo(decription = "查询网关配置")
    @GetMapping("/selectConfigList/{id}")//id = 1
    public R selectConfigList( @PathVariable("id") Integer id){

        Assert.isNull(id,"id不能为空");
        return sysGatewayConfigService.selectConfigList(id);
    }

    /**
     * 更新网关配置
     */
    // @RequiresPermissions("sys:config:update")
    //@LogInfo(decription = "更新网关配置")
    @PutMapping("/updateConfig")
    public R updateConfig(@Valid @RequestBody SysGatewayConfig sysGatewayConfig,
                        BindingResult bindingResult){

        Assert.isNull(sysGatewayConfig.getId(),"id不能为空");

        if(bindingResult.hasErrors()){
            log.error("【更新网关配置】参数不正确:sysGatewayConfig={}"+ sysGatewayConfig);
            throw new SystemException(REnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        return sysGatewayConfigService.updateConfig(sysGatewayConfig);
    }
    /**
     * 删除网关配置
     */
    //@RequiresPermissions("sys:config:delete")
    //@LogInfo(decription = "删除网关配置")
    @DeleteMapping("/delectConfig/{id}")
    public R delectConfig(@PathVariable Integer id){
        return sysGatewayConfigService.delectConfig(id);
    }
}
