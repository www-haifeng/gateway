package com.shuzhi.controller;

import com.shuzhi.annotation.LogInfo;
import com.shuzhi.enums.REnum;
import com.shuzhi.exception.SystemException;
import com.shuzhi.from.SysUserFrom;
import com.shuzhi.service.SysUserService;
import com.shuzhi.utils.Assert;
import com.shuzhi.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * author: Yujq
 * date: 2018/4/5
 */
@RestController
@RequestMapping("/sys")
@Slf4j
public class SysUserController {

    @Autowired
    SysUserService sysUserService;

    /**
     * 新增用户
     * @param sysUserFrom
     * @param bindingResult
     * @return
     */
    @LogInfo(decription = "新增用户")
    //@RequiresPermissions("sys:user:insert")
    @PostMapping("/saveUser")
    public R saveUser(@Valid @RequestBody SysUserFrom sysUserFrom,
                      BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【新增用户】参数不正确:sysUserFrom={}"+ sysUserFrom);
            throw new SystemException(REnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        return sysUserService.saveUser(sysUserFrom);
    }


    /**
     * 查询用户列表
     * @param page
     * @param size
     * @param name
     * @return
     */
    @LogInfo(decription = "查询用户列表")
   // @RequiresPermissions("sys:user:list")
    @GetMapping("/selectUserList")
    public R selectUserList(@RequestParam(value = "page", defaultValue = "0") Integer page,
                            @RequestParam(value = "size", defaultValue = "10") Integer size,
                            @RequestParam(value = "name",defaultValue = "") String name){

        PageRequest pageRequest = new PageRequest(page,size);
        return sysUserService.selectUserList(name,pageRequest);
    }

    /**
     * 查询用户详情
     * @param id
     * @return
     */
    //@RequiresPermissions("sys:user:detail")
    @LogInfo(decription = "查询用户详情")
    @GetMapping("/selectUserDetail")
    public R selectUserDetail(@RequestParam(value = "id",required = false) Integer id){

        Assert.isNull(id,"id不能为空");
        return sysUserService.selectUserDetail(id);
    }

    /**
     * 更新用户
     * @param sysUserFrom
     * @param bindingResult
     * @return
     */
   // @RequiresPermissions("sys:user:update")
    @LogInfo(decription = "更新用户")
    @PutMapping("/updateUser")
    public R updateUser(@Valid @RequestBody SysUserFrom sysUserFrom,
                        BindingResult bindingResult){

        Assert.isNull(sysUserFrom.getId(),"id不能为空");

        if(bindingResult.hasErrors()){
            log.error("【更新用户】参数不正确:sysRoleFrom={}"+ sysUserFrom);
            throw new SystemException(REnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        return sysUserService.updateUser(sysUserFrom);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    //@RequiresPermissions("sys:user:delete")
    @LogInfo(decription = "删除用户")
    @DeleteMapping("/deleteUser/{id}")
    public R deleteUser(@PathVariable Integer id){
        return sysUserService.delectUser(id);
    }
}
