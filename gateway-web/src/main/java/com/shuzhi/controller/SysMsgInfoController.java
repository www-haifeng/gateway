package com.shuzhi.controller;

import com.shuzhi.entity.SysMsgInfo;
import com.shuzhi.service.SysMsgInfoService;
import com.shuzhi.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ProjectName: gateway-parent
 * @Package: com.shuzhi.controller
 * @ClassName: SysMsgInfoController
 * @Author: 陈鑫晖
 * @Date: 2019/7/8 17:10
 */
@RestController
@RequestMapping("/sysMsgInfo")
@Slf4j
public class SysMsgInfoController {

    @Autowired
    SysMsgInfoService sysMsgInfoService;


    /**
     * 查询命令列表
     * @return
     */
    @RequestMapping(value = "/selectMsgInfoList",method = RequestMethod.GET)
    public R selectMsgInfoList(){
        return sysMsgInfoService.selectMsgInfo();
    }

    /**
     * 新增命令
     * @param sysMsgInfo
     * @return
     */
    @RequestMapping(value = "/insertSysMsgInfo",method = RequestMethod.POST)
    public R insertSysMsgInfo(@RequestBody SysMsgInfo sysMsgInfo){
        return sysMsgInfoService.insertSysMsgInfo(sysMsgInfo);
    }

    /**
     * 修改命令信息
     * @param sysMsgInfo
     * @return
     */
    @RequestMapping(value = "/updateSysMsgInfo",method = RequestMethod.PUT)
    public R updateSysMsgInfo(@RequestBody SysMsgInfo sysMsgInfo){
        return sysMsgInfoService.updateSysMsgInfo(sysMsgInfo);
    }

    /**
     * 删除命令
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteSysMsgInfo/{id}",method = RequestMethod.DELETE)
    public  R deleteSysMsgInfo(@PathVariable Integer id){
        return sysMsgInfoService.deleteSysMsgInfo(id);
    }






}
