package com.shuzhi.common;

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
    /**
     * @Description:匹配数据指令
     * @Author: YHF
     * @date 2019/6/5
     */
    public void commandSelect(String commanddata){
        try{
            //处理参数 _ 问题
            String data = processingParameter(commanddata);
            //read原始json
            JsonNode jsonParentNode = mapper.readTree(data);

            //解析第一层json 转换成对象
            SystemInfoData systemInfoData = mapper.readValue(data, SystemInfoData.class);

            //解析第二层json
            MessageData messageData = mapper.readValue(jsonParentNode.path("msg").toString(), MessageData.class);

            //判断数据中sign校验
            boolean signVerify = utils.signVerify(systemInfoData,jsonParentNode);

            //if (signVerify){
            //获取命令对象缓存
            CommandInfo commandInfo = Cache.commandMap.get(messageData.getCmdid());
            if (commandInfo == null){
                logger.error("未查询到WiFi设备cmdid为:"+messageData.getCmdid()+"的命令,放弃请求");
                return;
            }
            String url = "http://"+commandInfo.getTdeviceFactoryEntity().getServerIp()+":"+commandInfo.getTdeviceFactoryEntity().getServerPort()+commandInfo.getTmsgInfoEntity().getInterfaceId();
            //匹配命令
            switch (commandInfo.getTmsgInfoEntity().getInterfaceId()){
                    case "/vds/monitor/realtimemonitor?accesstoken=":
                        //实时监控接口 用websocket实现
                        break;
                   /* case "/vds/system/dimension/accesstoken/":
                        //维度查询
                        commandService.commandService(getCommandUrl(jsonParentNode,url), systemInfoData);
                        break;
                    case "/vds/system/area/accesstoken/":
                        //区域查询
                        commandService.commandService(getCommandUrl(jsonParentNode,url), systemInfoData);
                        break;
                    case "/vds/system/strategy/accesstoken/":
                        //维度分组查询
                        commandService.commandService(getCommandUrl(jsonParentNode,url), systemInfoData);
                        break;
                    case "/vds/statistic/dimension/accesstoken/":
                        //维度统计
                        commandService.commandService(getCommandUrl(jsonParentNode,url), systemInfoData);
                        break;
                    case "/vds/statistic/customerCounts/accesstoken/":
                        //进店人数统计
                        commandService.commandService(getCommandUrl(jsonParentNode,url), systemInfoData);
                        break;
                    case "/vds/statistic/visitCounts/accesstoken/":
                        //到店频率统计
                        commandService.commandService(getCommandUrl(jsonParentNode,url), systemInfoData);
                        break;
                    case "/vds/statistic/avgTime/accesstoken/":
                        //驻留时间统计
                        commandService.commandService(getCommandUrl(jsonParentNode,url), systemInfoData);
                        break;
                    case "/vds/statistic/peakValleyCounts/accesstoken/":
                        //峰谷时刻人流统计
                        commandService.commandService(getCommandUrl(jsonParentNode,url), systemInfoData);
                        break;
                    case "/vds/statistic/newOldCounts/accesstoken/":
                        //新老顾客统计
                        commandService.commandService(getCommandUrl(jsonParentNode,url), systemInfoData);
                        break;
                    case "/vds/statistic/phoneBrand/accesstoken/":
                        //手机品牌统计
                        commandService.commandService(getCommandUrl(jsonParentNode,url), systemInfoData);
                        break;
                    case "/vds/statistic/hisClients/accesstoken/":
                        //历史用户查询
                        commandService.commandService(getCommandUrl(jsonParentNode,url), systemInfoData);
                        break;
                    case "/vds/statistic/hisFootprint/accesstoken/":
                        //历史轨迹查询
                        commandService.commandService(getCommandUrl(jsonParentNode,url), systemInfoData);
                        break;
                    case "/vds/client/summary/accesstoken/":
                        //用户统计汇总
                        commandService.commandService(getCommandUrl(jsonParentNode,url), systemInfoData);
                        break;
                    case "/vds/client/detail/accesstoken/":
                        //用户统计明细
                        commandService.commandService(getCommandUrl(jsonParentNode,url), systemInfoData);
                        break;
                    case "/vds/client/device/summary/accesstoken/":
                        //设备数据汇总
                        commandService.commandService(getCommandUrl(jsonParentNode,url), systemInfoData);
                        break;
                    case "/vds/client/device/detail/accesstoken/":
                        //设备数据明细
                        commandService.commandService(getCommandUrl(jsonParentNode,url), systemInfoData);
                        break;
                    case "/vds/client/device/compare/accesstoken/":
                        //设备对比统计数据
                        commandService.commandService(getCommandUrl(jsonParentNode,url), systemInfoData);
                        break;
                    case "/vds/client/apgroups/accesstoken/":
                        //分组数据统计
                        commandService.commandService(getCommandUrl(jsonParentNode,url), systemInfoData);
                        break;
                    case "/vds/client/apgroups/history/summary/accesstoken/":
                        //指定分组数据汇总
                        commandService.commandService(getCommandUrl(jsonParentNode,url), systemInfoData);
                        break;
                    case "/vds/client/apgroups/history/detail/accesstoken/":
                        //分组历史明细数据统计
                        commandService.commandService(getCommandUrl(jsonParentNode,url), systemInfoData);
                        break;
                    case "/vds/client/aplocation/accesstoken/":
                        //位置数据统计
                        commandService.commandService(getCommandUrl(jsonParentNode,url), systemInfoData);
                        break;
                    case "/vds/client/phone/summary/accesstoken/":
                        //手机终端数据汇总
                        commandService.commandService(getCommandUrl(jsonParentNode,url), systemInfoData);
                        break;
                    case "/vds/client/phone/hisdetail/accesstoken/":
                        //手机终端数据明细
                        commandService.commandService(getCommandUrl(jsonParentNode,url), systemInfoData);
                        break;*/
                        default:
                            commandService.commandService(getCommandUrl(jsonParentNode,url), systemInfoData);
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
     * @Description: 封装参数
     * @Author: YHF
     * @date 2019/6/14
     */
    private String getCommandUrl(JsonNode jsonParentNode, String url){

        try {
            Map<String, Object> map  = mapper.readValue(jsonParentNode.path("msg").path("data").toString(), Map.class);
            //维度查询
            StringBuilder sb = new StringBuilder();
            //获取url
            sb.append(url);
            sb.append(Cache.accesstoken);
            sb.append(utils.mapToCommandStr(map));
            return sb.toString();
        } catch (IOException e) {
            logger.error("数据出错请查看",e);
            return null;
        }
    }

    private String processingParameter(String data){
        String newData = data.replace("dimid", "dim_id").replace("dimensionid", "dimension_id").replace("isparent", "is_parent")
                .replace("isall", "is_all").replace("devname", "devName").replace("groupid", "groupId");
        return newData;
    }

}
