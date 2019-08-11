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
 * @Description: 命令业务逻辑处理
 * @Author:     lirb
 * @CreateDate:   2019/7/23 13:40
 * @Version:   1.0
 **/
@Component
@EnableConfigurationProperties(ConfigData.class)
public class CommandService {
    private final static Logger logger = LoggerFactory.getLogger(CommandService.class);
    @Autowired
    ConfigData configData;
    @Autowired
    private RabbitSender rabbitSender;
    @Autowired
    private Utils utils;
    @Autowired
    private HttpCommandUtils httpCommandUtils;


    /**
     * 统一处理请求
     * @param url : 请求路径
     * @param commandJSON ：请求参数
     * @param systemInfoData ： 接收的消息体
     */
    public void commandService(String url,String commandJSON,SystemInfoData systemInfoData){
        try {
            //调用请求
            logger.info("请求url为："+url+";请求参数为"+commandJSON);
            String resultJSON = httpCommandUtils.postHTTP(url, commandJSON);
            logger.info("请求返回结果:"+resultJSON);
            commandSend(url,resultJSON,systemInfoData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 组装命令返回rabbit
     * @param resultJSON ：结果集
     * @param systemInfoData ：接收的消息体
     */
    public void commandSend(String url,String resultJSON, SystemInfoData systemInfoData){
        String timeStamp = utils.getTimeStamp();
        //命令正确执行
        if (resultJSON !=null && !"".equals(resultJSON)){
            MessageRevertData messageRevertData = utils.getMessageRevertData(url,configData.getSeccussCode(), timeStamp, resultJSON);
            String mrdJSON = messageRevertData.toString();
            systemInfoData.setMsgts(timeStamp);
            systemInfoData.setMsgtype(configData.getMsgtypeCommandReturn());
            systemInfoData.setMsg(mrdJSON);

            systemInfoData.setSign(utils.getSignVerify(systemInfoData));
            String commandRevertJSON = systemInfoData.toString();
            try {
                rabbitSender.send("lowerControlMessage",commandRevertJSON);
                logger.info("命令回执发送完毕 :"+commandRevertJSON);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            //命令执行未成功
            MessageRevertData messageRevertData = utils.getMessageRevertData(url,configData.getFailedCode(), timeStamp, resultJSON);
            String mrdJSON = messageRevertData.toString();
            systemInfoData.setMsgts(timeStamp);
            systemInfoData.setMsg(mrdJSON);
            systemInfoData.setSign(utils.getSignVerify(systemInfoData));
            String commandRevertJSON = systemInfoData.toString();
            try {
                rabbitSender.send("lowerControlMessage",commandRevertJSON);
                logger.error("命令执行失败，请查看失败原因:"+commandRevertJSON);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
