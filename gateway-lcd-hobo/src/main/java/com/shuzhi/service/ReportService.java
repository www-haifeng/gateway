package com.shuzhi.service;

import com.shuzhi.common.ConfigData;
import com.shuzhi.common.HttpCommandUtils;
import com.shuzhi.common.Utils;
import com.shuzhi.entity.MessageRevertData;
import com.shuzhi.entity.SystemInfoData;
import com.shuzhi.producer.RabbitSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description: 上报 相关 业务
 * @Author: lirb
 * @CreateDate: 2019/7/15 13:31
 * @Version: 1.0
 **/
@Component
@EnableConfigurationProperties(ConfigData.class)
public class ReportService {

    private final static Logger logger = LoggerFactory.getLogger(ReportService.class);

    @Autowired
    ConfigData configData;
    @Autowired
    private RabbitSender rabbitSender;
    @Autowired
    private Utils utils;
    @Autowired
    private HttpCommandUtils httpCommandUtils;


    /**
     * 上报业务处理
     * @param reportUrl ：请求url
     * @param requestBody ：请求体
     */
    public void reportService(String reportUrl, SystemInfoData requestBody) {
        //调用请求
        String resultJSON = httpCommandUtils.getHttp(reportUrl);
        logger.info("请求返回结果:"+resultJSON);
        reportSend(reportUrl,resultJSON,requestBody);
    }

    /**
     * 上报结果集发送到 mq
     * @param reportUrl :请求url
     * @param resultJson ：结果集
     * @param requestBody ：请求body
     */
    public void reportSend(String reportUrl,String resultJson,SystemInfoData requestBody){
        String timeStamp = utils.getTimeStamp();
        //命令正确执行
        if (resultJson !=null && !"".equals(resultJson)){
            MessageRevertData messageRevertData = utils.getMessageRevertData(reportUrl,configData.getSeccussCode(), timeStamp, resultJson);
            String mrdJSON = messageRevertData.toString();
            requestBody.setMsgts(timeStamp);
            requestBody.setMsg(mrdJSON);

            requestBody.setSign(utils.getSignVerify(requestBody));
            String commandRevertJSON = requestBody.toString();
            System.out.println("结果集：----"+commandRevertJSON);
            try {
                rabbitSender.send("lowerControlMessage",commandRevertJSON);
                logger.info("命令回执发送完毕:"+commandRevertJSON);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            //命令执行未成功
            MessageRevertData messageRevertData = utils.getMessageRevertData(reportUrl,configData.getFailedCode(), timeStamp, resultJson);
            String mrdJSON = messageRevertData.toString();
            requestBody.setMsgts(timeStamp);
            requestBody.setMsg(mrdJSON);
            requestBody.setSign(utils.getSignVerify(requestBody));
            String commandRevertJSON = requestBody.toString();
            System.out.println("结果集：----"+commandRevertJSON);
            try {
                rabbitSender.send("lowerControlMessage",commandRevertJSON);
                logger.error("命令执行失败，请查看原因:"+commandRevertJSON);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
