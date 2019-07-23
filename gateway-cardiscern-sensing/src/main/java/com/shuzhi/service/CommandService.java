package com.shuzhi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shuzhi.commen.ConfigData;
import com.shuzhi.commen.HttpCommandUtils;
import com.shuzhi.commen.Utils;
import com.shuzhi.entity.MessageRevertData;
import com.shuzhi.entity.SystemInfoData;
import com.shuzhi.producer.RabbitSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 功能描述 命令业务逻辑处理
 * @author YHF
 * @date 2019/6/6
 * @params
 * @return
 */
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
    @Autowired
    private ObjectMapper mapper;
    /**
     * @Description: 统一处理请求
     * @Author: YHF
     * @date 2019/6/10
     */
    public void commandService(String url, String commandJSON, SystemInfoData systemInfoData){
        try {
            //调用请求
            String resultJSON = httpCommandUtils.postHTTP(url, commandJSON);
            logger.info("请求返回结果:"+resultJSON);
            commandSend(resultJSON,systemInfoData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description:组装命令返回rabbit
     * @Author: YHF
     * @date 2019/6/6
     */
    public void commandSend(String resultJSON, SystemInfoData systemInfoData){
            String timeStamp = utils.getTimeStamp();
            //命令正确执行
            if (resultJSON !=null && !"".equals(resultJSON)){
                MessageRevertData messageRevertData = utils.getMessageRevertData(configData.getSeccussCode(), timeStamp, resultJSON);
                String mrdJSON = messageRevertData.toString();
                systemInfoData.setMsgts(timeStamp);
                systemInfoData.setMsgtype(configData.getMsgtypeCommandReturn());
                systemInfoData.setMsg(mrdJSON);

                systemInfoData.setSign(utils.getSignVerify(systemInfoData));
                String commandRevertJSON = systemInfoData.toString();
                try {
                    rabbitSender.send("lowerControlMessage",commandRevertJSON);
                    logger.info("命令回执发送完毕:"+commandRevertJSON);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                //命令执行未成功
                MessageRevertData messageRevertData = utils.getMessageRevertData(configData.getFailedCode(), timeStamp, resultJSON);
                String mrdJSON = messageRevertData.toString();
                systemInfoData.setMsgts(timeStamp);
                systemInfoData.setMsg(mrdJSON);
                systemInfoData.setSign(utils.getSignVerify(systemInfoData));
                String commandRevertJSON = systemInfoData.toString();
                try {
                    rabbitSender.send("lowerControlMessage",commandRevertJSON);
                    logger.error("命令执行失败，请查看原因:"+commandRevertJSON);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

    }

}
