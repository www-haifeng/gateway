package com.shuzhi.exception;

import com.shuzhi.enums.REnum;
import com.shuzhi.utils.RUtil;
import com.shuzhi.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 * author: Yujq
 * date: 2018/4/7
 */
@Slf4j
@RestControllerAdvice
public class SystemExceptionHandler {

    /**
     * 缺少权限异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(AuthorizationException.class)
    public R handleAuthorizationException(AuthorizationException e){
        log.error(REnum.NOT_PERMSSION.getMessage());
        return RUtil.error(REnum.NOT_PERMSSION.getCode(),REnum.NOT_PERMSSION.getMessage());
    }
}
