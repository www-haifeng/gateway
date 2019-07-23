package com.shuzhi.commen;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shuzhi.cache.Cache;
import com.shuzhi.entity.CommandInfo;
import com.shuzhi.entity.MessageData;
import com.shuzhi.entity.SystemInfoData;
import com.shuzhi.service.CommandService;
import com.shuzhi.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


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
                    logger.error("未查询到车辆识别命令mdid为:"+messageData.getCmdid()+"的命令,放弃请求");
                    return;
                }

                //获取url
                String url = "http://"+commandInfo.getTdeviceFactoryEntity().getServerIp()+":"+commandInfo.getTdeviceFactoryEntity().getServerPort()+commandInfo.getTmsgInfoEntity().getInterfaceId();
                String commandJSON = jsonParentNode.path("msg").path("data").toString();
                //匹配命令
                switch (commandInfo.getTmsgInfoEntity().getInterfaceId()){
                    //一次性加载所有通道和分组(VC_WS_FZ_006)
                    case "/videostructure/regions/queryAllChild":
                        //发送请求
                        commandService.commandService(url,commandJSON,systemInfoData);
                        break;
                    //根据父id查询下属子分组和通道(VC_WS_FZ_001)
                    case "/videostructure/regions/queryChildsById":
                        //发送请求
                        commandService.commandService(url,commandJSON,systemInfoData);
                        break;
                    //导出检索数据(VS_WS_DC_001)
                    case "/videostructure/export/exportSearchToExcel":

                        //导出检索数据(VS_WS_DC_001)未调通
                        //发送请求
                        break;
                    //根据检索条件返回检索结果_行人;机动车;非机动车;(VS_WS_JS_001)
                    case "/videostructure/export/exporttrafficCountInfoToExcel":
                        //发送请求
                        commandService.commandService(url,commandJSON,systemInfoData);
                        break;
                    //根据检索条件返回检索结果_行人;机动车;非机动车;(VS_WS_JS_001)
                    case "/videostructure/search/query":
                        //发送请求
                        commandService.commandService(url,commandJSON,systemInfoData);
                        break;
                    //检索详情查询_行人;机动车;非机动车;(VS_WS_JS_002)
                    case "/videostructure/search/detail":
                        //发送请求
                        commandService.commandService(url,commandJSON,systemInfoData);
                        break;
                    //通道通行量统计(VS_WS_JS_003)
                    case "/videostructure/search/channelTrafficCount":
                        //发送请求
                        commandService.commandService(url,commandJSON,systemInfoData);
                        break;
                    //根据图片内容获取图片特征(VS_WS_JS_006)
                    case "/videostructure/search/queryImageFeature":
                        //发送请求
                        commandService.commandService(url,commandJSON,systemInfoData);
                        break;
                    //根据检索条件返回检索结果_行人;机动车;非机动车;(VS_WS_JS_009)
                    case "/videostructure/search/queryByKeyword":
                        //发送请求
                        //目前不知道参数中keyword的值
                        commandService.commandService(url,commandJSON,systemInfoData);
                        break;
                    //根据typeCodeList查询属性值列表(VS_WS_SX_001)
                    case "/videostructure/sysTypecode/queryBatch":
                        //发送请求
                        commandService.commandService(url,commandJSON,systemInfoData);
                        break;
                    //根据父id查询子分类(VS_WS_SX_002)
                    case "/videostructure/sysCarbrand/query":
                        //发送请求
                        commandService.commandService(url,commandJSON,systemInfoData);
                        break;
                    //通行量统计(VS_WS_TJ_001)
                    case "/videostructure/overview/trafficCount":
                        //发送请求
                        commandService.commandService(url,commandJSON,systemInfoData);
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
