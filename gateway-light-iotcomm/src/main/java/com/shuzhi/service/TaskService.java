package com.shuzhi.service;

import com.alibaba.fastjson.JSON;
import com.shuzhi.cache.Cache;
import com.shuzhi.common.ConfigData;
import com.shuzhi.common.HttpCommandUtils;
import com.shuzhi.entity.AppLoginData;
import com.shuzhi.entity.CommandInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 定时任务服务类
 * @Author: lirb
 * @CreateDate: 2019/8/9 15:14
 * @Version: 1.0
 **/
@Component
public class TaskService {

    private final static Logger logger = LoggerFactory.getLogger(TaskService.class);

    private String appLoginUrl = "/monitor/module/pub/user/appLogin.action";

    @Autowired
    private ConfigData configData;

    /**
     * 获取获取访问令牌请求的url
     *
     * @return
     */
    private String getAppLoginUrl() {
        CommandInfo commandInfo = Cache.commandMap.get("10001");
        StringBuilder sb = new StringBuilder("http://");
        sb.append(commandInfo.getTdeviceFactoryEntity().getServerIp());
        sb.append(":");
        sb.append(commandInfo.getTdeviceFactoryEntity().getServerPort());
        sb.append(appLoginUrl);
        return sb.toString();
    }

    /**
     * 获取访问令牌的请求参数
     *
     * @return
     */
    private String getAppLoginReqParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("userName", configData.getUserName());
        params.put("password", configData.getPassword());
        return JSON.toJSONString(params);
    }

    /**
     * 定时任务请求
     */
    public String getAppLogin() {
        try {
            //调用请求
            String taskRequestUrl = getAppLoginUrl();
            String reqParams = getAppLoginReqParams();
            logger.info("请求url为：" + taskRequestUrl + ";请求参数为" + reqParams);
            String resultJSON = postHTTP(taskRequestUrl, reqParams);
            verifyAppLoginResult(resultJSON);
            return resultJSON;
        } catch (Exception e) {
            logger.error("定时任务执行失败");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * post请求
     *
     * @param url      ： 请求路径
     * @param jsondata ：请求参数
     * @return
     */
    private String postHTTP(String url, String jsondata) {
        //设置请求头信息
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Content-Type", MediaType.APPLICATION_JSON.toString());
        //设置body部分
        HttpEntity<String> entity = new HttpEntity<String>(jsondata, headers);
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            logger.info("接收到请求返回数据" + result.getBody());
            if (result.getHeaders().containsKey("set-cookie")) {
                List<String> cookies = result.getHeaders().get("set-cookie");
                String cookie = cookies.get(0);
                Cache.cookie = cookie.substring(0, cookie.indexOf(";") + 1);
            }
            return result.getBody();
        } catch (Exception e) {
            logger.error("请求出错,详细信息为:" + e.getMessage());
            return null;
        }

    }

    /**
     * 验证访问令牌结果集是否正确
     * @param result
     */
    public void verifyAppLoginResult(String result) {
        try {
            if (StringUtils.isNotEmpty(result)) {
                AppLoginData appLoginData = JSON.parseObject(result, AppLoginData.class);
                if (!appLoginData.getSuccess()) {
                    logger.info("定时获取访问令牌结果不正确，3秒后再次执行：" + result);
                    Thread.sleep(1000 * 3);
                    getAppLogin();
                }
            } else {
                logger.info("定时获取访问令牌结果为空，3分钟后再次执行");
                Thread.sleep(1000 * 60 * 3);
                getAppLogin();
            }
        } catch (InterruptedException e) {
            logger.error("请求获取访问令牌出现异常：{}", e);
        }
    }
}
