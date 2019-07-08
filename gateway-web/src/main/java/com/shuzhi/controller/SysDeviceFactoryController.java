package com.shuzhi.controller;

import com.shuzhi.entity.SysDeviceFactory;
import com.shuzhi.service.SysDeviceFactoryService;
import com.shuzhi.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ProjectName: gateway-parent
 * @Package: com.shuzhi.controller
 * @ClassName: SysDeviceFactoryController
 * @Author: 陈鑫晖
 * @Date: 2019/7/8 17:11
 */
@RestController
@RequestMapping("/sysDeviceFactory")
@Slf4j
public class SysDeviceFactoryController {

    @Autowired
    SysDeviceFactoryService sysDeviceFactoryService;

    /**
     * 查询设备列表
     * @return
     */
    @RequestMapping(value = "/selectSysDeviceFactoryList" ,method= RequestMethod.GET)
    public R selectSysDeviceFactoryList(){
        return sysDeviceFactoryService.selectSysDeviceFactory();
    }

    /**
     * 新增命令
     * @param sysDeviceFactory
     * @return
     */
    @RequestMapping(value = "/insertSysDeviceFactory",method = RequestMethod.POST)
    public R insertSysDeviceFactory(@RequestBody SysDeviceFactory sysDeviceFactory){
        return sysDeviceFactoryService.insertSysDeviceFactory(sysDeviceFactory);
    }

    /**
     * 修改设备信息
     * @param sysDeviceFactory
     * @return
     */
    @RequestMapping(value = "/updateSysDeviceFactory",method = RequestMethod.PUT)
    public R updateSysDeviceFactory(@RequestBody SysDeviceFactory sysDeviceFactory){
        return sysDeviceFactoryService.updateSysDeviceFactory(sysDeviceFactory);
    }

    /**
     * 删除设备
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteSysDeviceFactory/{id}",method = RequestMethod.DELETE)
    public  R deleteSysDeviceFactory(@PathVariable Integer id){
        return sysDeviceFactoryService.deleteSysDeviceFactory(id);
    }
}
