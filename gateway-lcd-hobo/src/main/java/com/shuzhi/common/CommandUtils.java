package com.shuzhi.common;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;


/**
 *功能描述 命令工具
 * @author YHF
 * @date 2019/6/5
 * @params
 * @return
 */
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
    @Autowired
    private FtpUtil ftpUtil;
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
                    logger.error("未查询到lcd设备cmdid为:"+messageData.getCmdid()+"的命令,放弃请求");
                    return;
                }

                //获取url
                String url = "http://"+commandInfo.getTdeviceFactoryEntity().getServerIp()+":"+commandInfo.getTdeviceFactoryEntity().getServerPort()+commandInfo.getTmsgInfoEntity().getInterfaceId();

                //匹配命令
                switch (commandInfo.getTmsgInfoEntity().getInterfaceId()){

                    case "/vs/api/apiselclient.vs":
                        commandService.commandService(getCommandUrl(jsonParentNode,url), systemInfoData);
                        break;

                    case "/vs/api/apiselzxclient.vs":
                        commandService.commandService(getCommandUrl(jsonParentNode,url), systemInfoData);
                        break;

                    case "/vs/api/apisellxclient.vs":
                        commandService.commandService(getCommandUrl(jsonParentNode,url), systemInfoData);
                        break;

                    case "/vs/api/apirestart.vs":
                        commandService.commandService(getCommandUrl(jsonParentNode,url), systemInfoData);
                        break;

                    case "/vs/api/apirouse.vs":
                        commandService.commandService(getCommandUrl(jsonParentNode,url), systemInfoData);
                        break;

                    case "/vs/api/apistandby.vs":
                        commandService.commandService(getCommandUrl(jsonParentNode,url), systemInfoData);
                        break;

                    case "/vs/api/apinewds.vs":
                        commandService.commandService(getCommandUrl(jsonParentNode,url), systemInfoData);
                        break;

                    case "/vs/api/apivolset.vs":
                        commandService.commandService(getCommandUrl(jsonParentNode,url),systemInfoData);
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


    /**
     * 拼接参数到url 上
     * @param jsonParentNode ：json串node
     * @param url ：参数前边的url
     * @return
     */
    private String getCommandUrl(JsonNode jsonParentNode, String url){

        try {
            Map<String, Object> map  = mapper.readValue(jsonParentNode.path("msg").path("data").toString(), Map.class);
            //如果参数有cids，则转换成设备id
            if(map.containsKey("cids")){
                String deviceIds = utils.didToDeviceId(map.get("cids").toString());
                map.put("cids",deviceIds);
            }
            //维度查询
            StringBuilder sb = new StringBuilder();
            //获取url
            sb.append(url);
            sb.append("?account=");
            sb.append(configData.getAccount());
            sb.append("&password=");
            sb.append(configData.getPassword());
            sb.append(utils.mapToCommandStr(map));
            return sb.toString();
        } catch (IOException e) {
            logger.error("数据出错请查看",e);
            return null;
        }
    }

}
