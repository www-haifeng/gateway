package com.shuzhi.common;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shuzhi.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述 http请求工具类
 * @author YHF
 * @date 2019/6/6
 * @params
 * @return
 */
@Component
@EnableConfigurationProperties(ConfigData.class)
public class HttpCommandUtils {
    private final static Logger logger = LoggerFactory.getLogger(HttpCommandUtils.class);
    @Autowired
    private ConfigData configData;
    @Autowired
    private ObjectMapper mapper;
    /**
     * @Description: get请求
     * @Author: YHF
     * @date 2019/6/13
     */
    public String getHttp(String url){
        // uri = UriComponentsBuilder.fromHttpUrl(url).build().encode().toUri();
        //String uriStr = uri.toString();
        //uriStr = uriStr.replace("%5B","[\"").replace("%5D","\"]");
        RestTemplate restTemplate=new RestTemplate();
        String data=restTemplate.getForObject(url,String.class);
        logger.info("命令执行结果:"+data);
        return data;
    }

    /**
     * @Description: 更新请求令牌
     * @Author: YHF
     * @date 2019/6/13
     */
    public void getAccessToken(){

        try {
            String httpJSON = getHttp("http://" + configData.getIp() + ":" + configData.getPort() + "/account/hash/" + configData.getUserhash());
            JSONArray data = mapper.readTree(httpJSON).get("data").traverse(mapper).readValueAs(JSONArray.class);
            Cache.accesstoken = mapper.readTree(data.getJSONObject(0).toString()).get("accesstoken").traverse(mapper).readValueAs(String.class);
            logger.info("更新令牌成功:"+Cache.accesstoken);
        } catch (Exception e) {
            this.getAccessToken();
            logger.error("更新令牌失败",e);
        }
    }

}
