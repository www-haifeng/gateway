package com.shuzhi.commen;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shuzhi.entity.SystemInfoData;
import com.shuzhi.service.impl.ReceiverMessagesImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 *功能描述 命令工具
 * @author ztt
 * @date 2019/6/5
 * @params
 * @return
 */
@Component
public class CommandUtils {
    private final static Logger logger = LoggerFactory.getLogger(CommandUtils.class);
    @Autowired
    private Utils utils;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private ReceiverMessagesImpl receiverMessages;
    /**
     * @Description:匹配数据指令
     * @Author: ztt
     * @date 2019/6/5
     */
    public void commandSelect(String data){
        try{
            //read原始json
            JsonNode jsonParentNode = mapper.readTree(data);
            //解析第一层json 转换成对象
            SystemInfoData systemInfoData = mapper.readValue(data, SystemInfoData.class);

            //判断数据中sign校验
            boolean signVerify = utils.signVerify(systemInfoData,jsonParentNode);
            //@TODO 验证
            //if (signVerify){
            receiverMessages.receiver(jsonParentNode,systemInfoData);

            /*}else{
                logger.error("数据包sign校验有误,数据丢弃");
                return;
            }*/
        }catch (Exception e){
            logger.error("数据出错请查看",e);
        }
    }
}
