package com.shuzhi.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shuzhi.annotation.LogInfo;
import com.shuzhi.entity.SysLog;
import com.shuzhi.entity.SysUser;
import com.shuzhi.service.SysLogService;
import com.shuzhi.utils.ConstantUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

/**
* @Program: SysLogAspect
* @Description: 
* @Author: YuJQ
* @Create: 2019/6/25 13:53
**/
@Aspect
@Component
public class SysLogAspect {
    @Autowired
    private SysLogService sysLogService;

    //定义切点 @Pointcut
    //在注解的位置切入代码
    @Pointcut("@annotation(com.shuzhi.annotation.LogInfo)")
    public void logPoinCut() {
    }

    //切面 配置通知
    @AfterReturning("logPoinCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        System.out.println("切面。。。。。");
        //保存日志
        SysLog sysLog = new SysLog();

        //从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获取切入点所在的方法
        Method method = signature.getMethod();
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        //获取操作
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        String username = sysUser.getAccount();
        String value = "";
        LogInfo logInfo = method.getAnnotation(LogInfo.class);
        if (logInfo != null) {
             value = logInfo.decription();
        }
        sysLog.setDescribe("登录人："+username+" 调用"+className + "." + methodName + " " +value);
        sysLog.setTimestamp(new Date());
        sysLog.setLogType("系统日志");

        sysLog.setServerType(ConstantUtils.LOG_SYSTEM);
        //调用service保存SysLog实体类到数据库
        sysLogService.saveSysLog(sysLog);
    }
}
