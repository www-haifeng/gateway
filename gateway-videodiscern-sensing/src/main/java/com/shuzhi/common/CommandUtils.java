package com.shuzhi.common;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shuzhi.cache.Cache;
import com.shuzhi.entity.CommandInfo;
import com.shuzhi.entity.MessageData;
import com.shuzhi.entity.SystemInfoData;
import com.shuzhi.entity.command.*;
import com.shuzhi.service.CommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @Description: 功能描述 命令工具
 * @Author:     lirb
 * @CreateDate:   2019/7/23 13:34
 * @Version:   1.0
 **/
@Component
public class CommandUtils {
    private final static Logger logger = LoggerFactory.getLogger(CommandUtils.class);
    @Autowired
    private CommandService commandService;
    @Autowired
    private Utils utils;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private ConfigData configData;

    /**
     * @Description:匹配数据指令
     * @Author: YHF
     * @date 2019/6/5
     */
    public void commandSelect(String data){
        try{
            //read原始json
            JsonNode jsonParentNode = mapper.readTree(data);

            //解析第一层json 转换成对象
            SystemInfoData systemInfoData = mapper.readValue(data, SystemInfoData.class);

            //解析第二层json
            MessageData messageData = mapper.readValue(jsonParentNode.path("msg").toString(), MessageData.class);

            //判断数据中sign校验
            boolean signVerify = utils.signVerify(systemInfoData,jsonParentNode);

            //if (signVerify){
                CommandInfo commandInfo = Cache.commandMap.get(messageData.getCmdid());

                if (commandInfo == null){
                    logger.error("未查询到cmdid为:"+messageData.getCmdid()+"的命令,放弃请求");
                    return;
                }

                //获取url
                String url = "http://"+commandInfo.getTdeviceFactoryEntity().getServerIp()+":"+commandInfo.getTdeviceFactoryEntity().getServerPort()+commandInfo.getTmsgInfoEntity().getInterfaceId();

                //匹配命令
                switch (commandInfo.getTmsgInfoEntity().getInterfaceId()){

                    case "/face/dispositions/create":
                        DispositionCreateData cdd = mapper.readValue(jsonParentNode.path("msg").path("data").toString(), DispositionCreateData.class);
                        //添加partnerId
                        cdd.setPartnerId(configData.getPartnerId());
                        commandService.commandService(url, JSON.toJSONString(cdd), systemInfoData);
                        break;

                    case "/face/dispositions/search":
                        DispositionSearchData dsd = mapper.readValue(jsonParentNode.path("msg").path("data").toString(), DispositionSearchData.class);
                        //添加partnerId
                        dsd.setPartnerId(configData.getPartnerId());
                        commandService.commandService(url, JSON.toJSONString(dsd), systemInfoData);
                        break;

                    case "/face/dispositions/delete":
                        DispositionDeleteData ddd = mapper.readValue(jsonParentNode.path("msg").path("data").toString(), DispositionDeleteData.class);
                        //添加partnerId
                        ddd.setPartnerId(configData.getPartnerId());
                        commandService.commandService(url, JSON.toJSONString(ddd), systemInfoData);
                        break;
                    case "/face/dispositions/update":
                        DispositionUpdateData dud = mapper.readValue(jsonParentNode.path("msg").path("data").toString(), DispositionUpdateData.class);
                        //添加partnerId
                        dud.setPartnerId(configData.getPartnerId());
                        commandService.commandService(url, JSON.toJSONString(dud), systemInfoData);
                        break;

                    case "/face/faceTemplate/create":
                        FaceTemplateCreateData fcd = mapper.readValue(jsonParentNode.path("msg").path("data").toString(), FaceTemplateCreateData.class);
                        //添加partnerId
                        fcd.setPartnerId(configData.getPartnerId());
                        commandService.commandService(url, JSON.toJSONString(fcd), systemInfoData);
                        break;

                    case "/face/faceTemplate/search":
                        FaceTemplateSearchData fsd = mapper.readValue(jsonParentNode.path("msg").path("data").toString(), FaceTemplateSearchData.class);
                        //添加partnerId
                        fsd.setPartnerId(configData.getPartnerId());
                        commandService.commandService(url, JSON.toJSONString(fsd), systemInfoData);
                        break;

                    case "/face/faceTemplate/delete":
                        FaceTemplateDeleteData fdd = mapper.readValue(jsonParentNode.path("msg").path("data").toString(), FaceTemplateDeleteData.class);
                        //添加partnerId
                        fdd.setPartnerId(configData.getPartnerId());
                        commandService.commandService(url, JSON.toJSONString(fdd), systemInfoData);
                        break;

                    case "/face/cmp/passerDbSearch":
                        PasserDbSearchData psd = mapper.readValue(jsonParentNode.path("msg").path("data").toString(), PasserDbSearchData.class);
                        //添加partnerId
                        psd.setPartnerId(configData.getPartnerId());
                        commandService.commandService(url, JSON.toJSONString(psd), systemInfoData);
                        break;

                        default:
                            logger.error("没有找到对应请求命令,请查证");
                            break;
                }
            /*}else{
                logger.error("数据包sign校验有误,数据丢弃");
                return;
            }*/
        }catch (Exception e){
            logger.error("数据出错请查看",e);
        }
    }



}
