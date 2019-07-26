package com.shuzhi.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @Description: 功能描述 http请求工具类
 * @Author:     lirb
 * @CreateDate:   2019/7/23 13:35
 * @Version:   1.0
 **/
@Component
public class HttpCommandUtils {
    private final static Logger logger = LoggerFactory.getLogger(HttpCommandUtils.class);


    /**
     * get请求
     * @param url
     * @return
     */
    public String getHttp(String url){
        RestTemplate restTemplate=new RestTemplate();
        try {
            String data = restTemplate.getForObject(url, String.class);
            logger.info("命令执行结果:" + data);
            return data;
        }catch (Exception e){
            logger.error("请求出错,详细信息为:"+ e.getMessage());
            return null;
        }
    }


    /**
     * post请求
     * @param url ： 请求路径
     * @param jsondata ：请求参数
     * @return
     */
    public String postHTTP(String url,String jsondata){
        //设置请求头信息
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
        //设置body部分
        HttpEntity<String> entity = new HttpEntity<String>(jsondata,headers);
        RestTemplate restTemplate=new RestTemplate();
        try {
            ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            logger.info("接收到请求返回数据"+result.getBody());
            return result.getBody();
        }catch (Exception e){
            logger.error("请求出错,详细信息为:"+ e.getMessage());
            return null;
        }

    }


}
