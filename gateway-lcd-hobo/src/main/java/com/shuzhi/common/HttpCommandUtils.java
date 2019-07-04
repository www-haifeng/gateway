package com.shuzhi.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 功能描述 http请求工具类
 * @author YHF
 * @date 2019/6/6
 * @params
 * @return
 */
@Component
public class HttpCommandUtils {
    private final static Logger logger = LoggerFactory.getLogger(HttpCommandUtils.class);

    /**
     * @Description: get请求
     * @Author: YHF
     * @date 2019/6/13
     */
    public String getHttp(String url){
        RestTemplate restTemplate=new RestTemplate();
        String data=restTemplate.getForObject(url,String.class);
        logger.info("命令执行结果:"+data);
        return data;
    }


}
