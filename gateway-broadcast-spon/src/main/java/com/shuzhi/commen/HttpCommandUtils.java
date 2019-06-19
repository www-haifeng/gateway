package com.shuzhi.commen;

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
     * @Description:世邦广播 post
     * @Author: YHF
     * @date 2019/6/6
     */
    public String postHTTP(String url,String jsondata){
        //设置请求头信息
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/x-www-form-urlencoded;charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        //设置body部分
        HttpEntity<String> entity = new HttpEntity<String>(jsondata,headers);
        RestTemplate restTemplate=new RestTemplate();
        try {
            ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            logger.info("接收到请求返回数据"+result.getBody());

            return new String(result.getBody().getBytes("ISO-8859-1"),"UTF-8");
        }catch (Exception e){
            logger.error("请求出错,详细信息:"+ e.getMessage());
            return null;
        }

    }
}
