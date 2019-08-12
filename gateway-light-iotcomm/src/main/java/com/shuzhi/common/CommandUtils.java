package com.shuzhi.common;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shuzhi.cache.Cache;
import com.shuzhi.entity.CommandInfo;
import com.shuzhi.entity.MessageData;
import com.shuzhi.entity.SystemInfoData;
import com.shuzhi.service.CommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * @Description: 命令工具
 * @Author:     lirb
 * @CreateDate:   2019/8/9 11:36
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
                    logger.error("未查询到照明控制设备cmdid为:"+messageData.getCmdid()+"的命令,放弃请求");
                    return;
                }

                //获取url
                String url = "http://"+commandInfo.getTdeviceFactoryEntity().getServerIp()+":"+commandInfo.getTdeviceFactoryEntity().getServerPort()+commandInfo.getTmsgInfoEntity().getInterfaceId();
                HashMap params = mapper.readValue(jsonParentNode.path("msg").path("data").toString(), HashMap.class);

            //匹配命令
            switch (commandInfo.getTmsgInfoEntity().getInterfaceId()){
                    //集中器列表或集中器详情
                    case "/monitor/module/light/equipment/deploy/findCcu.action":
                        commandService.commandService(url, JSON.toJSONString(params), systemInfoData);
                        break;
                    //集中器下回路开关列表
                    case "/monitor/module/light/equipment/deploy/findBreaker.action":
                        commandService.commandService(url, JSON.toJSONString(params), systemInfoData);
                        break;
                    //集中器下终端列表
                    case "/monitor/module/light/equipment/deploy/findRtu.action":
                        commandService.commandService(url, JSON.toJSONString(params), systemInfoData);
                        break;
                    //重启集中器
                    case "/monitor/module/light/app/control/controlCcuReboot.action":
                        commandService.commandService(url, JSON.toJSONString(params), systemInfoData);
                        break;
                    //集中器控制操控
                    case "/monitor/module/light/app/control/controlCcuControl.action":
                        commandService.commandService(url, JSON.toJSONString(params), systemInfoData);
                        break;
                    //回路打开与关闭控制
                    case "/monitor/module/light/app/control/controlBreaker.action":
                        commandService.commandService(url, JSON.toJSONString(params), systemInfoData);
                        break;
                    //单灯控制器操控
                    case "/monitor/module/light/app/control/controlRtuControl.action":
                        commandService.commandService(url, JSON.toJSONString(params), systemInfoData);
                        break;
                    //集中器回路开关列表查询
                    case "/monitor/module/light/monitoring/queryBreakerPageList.action":
                        commandService.commandService(url, JSON.toJSONString(params), systemInfoData);
                        break;
                    //集中器下终端状态查询或指定终端运行状态数查询
                    case "/monitor/module/light/monitoring/queryRtuPageList.action":
                        commandService.commandService(url, JSON.toJSONString(params), systemInfoData);
                        break;
                    //集中器回路开关列表查询
                    case "/monitor/module/light/monitoring/queryLampPageList.action":
                        commandService.commandService(url, JSON.toJSONString(params), systemInfoData);
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
