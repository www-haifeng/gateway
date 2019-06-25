package com.shuzhi.controller;

import com.alibaba.fastjson.JSONObject;
import com.shuzhi.enums.REnum;
import com.shuzhi.model.SysUser;
import com.shuzhi.service.SysUserService;
import com.shuzhi.utils.Assert;
import com.shuzhi.utils.RUtil;
import com.shuzhi.utils.ShiroUtil;
import com.shuzhi.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * author: Yujq
 * date: 2018/4/7
 */
@RestController
@RequestMapping("/anno")
@Slf4j
public class AnnoController {

    @Autowired
    SysUserService sysUserService;
    @PostMapping("/login")
    public R login(@RequestBody Map<String,String> map,HttpServletRequest request){

        Assert.isBlank(map.get("account"),"账号不能为空");
        Assert.isBlank(map.get("password"),"密码不能为空");


        try{
            Subject subject = ShiroUtil.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(map.get("account"),map.get("password"));
            subject.login(token);
        }catch (UnknownAccountException e) {
           return RUtil.error(REnum.USERNAME_OR_PASSWORD_ERROR.getCode(),REnum.USERNAME_OR_PASSWORD_ERROR.getMessage());
        }catch (IncorrectCredentialsException e) {
           return RUtil.error(REnum.USERNAME_OR_PASSWORD_ERROR.getCode(),REnum.USERNAME_OR_PASSWORD_ERROR.getMessage());
        }catch (DisabledAccountException e) {
           return RUtil.error(REnum.ACCOUNT_DISABLE.getCode(),REnum.ACCOUNT_DISABLE.getMessage());
        }catch (AuthenticationException e) {
           return RUtil.error(REnum.AUTH_ERROR.getCode(),REnum.AUTH_ERROR.getMessage());
        }
        SysUser sysUser = sysUserService.findByAccount(map.get("account"));
        JSONObject object = new JSONObject();
        object.put("authority",sysUser.getAuthority());
        return RUtil.success(object,request.getSession().getId());
    }

    /**
     * 退出
     * @return
     */
    @GetMapping("/logout")
    public R logout(){
        ShiroUtil.logout();
        return RUtil.success();
    }

    /**
     * 登录页面
     * @return
     */
    @GetMapping("/notLogin")
    public R notLogin(){
       return RUtil.error(REnum.NOT_LOGIN.getCode(),REnum.NOT_LOGIN.getMessage());
    }
}
