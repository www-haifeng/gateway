package com.shuzhi.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shuzhi.cache.Cache;
import com.shuzhi.dao.MessageData;
import com.shuzhi.dao.SystemInfoData;
import com.shuzhi.entity.CommandInfo;
import com.shuzhi.entity.DeviceInfo;
import com.shuzhi.entity.command.*;
import com.shuzhi.entity.commandResult.XwalkLoadPageResultArgs;
import com.shuzhi.service.CommandService;
import org.apache.commons.lang3.StringUtils;
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
            //获取命令对象缓存
            CommandInfo commandInfo = Cache.commandMap.get(messageData.getCmdid());
            //获取设备缓存
            DeviceInfo deviceInfo = Cache.deviceInfoMap.get(messageData.getDid());
            if (commandInfo == null){
                logger.error("未查询到太龙灯杆屏cmdid为:"+messageData.getCmdid()+"的命令,放弃请求");
                return;
            }
            if (deviceInfo == null){
                logger.error("未查询到控制卡为编号为:"+messageData.getDid()+"的太龙灯杆屏设备,放弃请求");
                return;
            }
            //获取url
            String url = "http://"+commandInfo.getTdeviceFactoryEntity().getServerIp()+":"+commandInfo.getTdeviceFactoryEntity().getServerPort()+"/command/"+deviceInfo.getTdeviceTecnonEntity().getDeviceId();

                //匹配命令
                switch (commandInfo.getTmsgInfoEntity().getInterfaceId()){
                    case "screenState":
                        //获取屏幕开关状态
                        CommonParameters cpScreen = new CommonParameters("callCardService","isScreenOpen");
                        commandService.commandServiceBoolean(url, cpScreen.toString(),systemInfoData);
                        break;
                    case "switchTheScreen":
                        //开关屏幕
                        JsonNode jsonNodeScreen = jsonParentNode.path("msg").path("data").get("arg1");
                        //获取参数 转换接口协议类型
                        Integer arg1 = jsonNodeScreen.traverse(mapper).readValueAs(Integer.class);
                        CommandParametersOne cpoScreen = new CommandParametersOne();
                        cpoScreen.setType("callCardService");
                        cpoScreen.setFn("setScreenOpen");
                        if (0 == arg1){
                            //0代表关闭屏幕
                            cpoScreen.setArg1(false);
                        }else if (1 == arg1){
                            //1代表开启屏幕
                            cpoScreen.setArg1(true);
                        }
                        commandService.commandServiceBoolean(url, cpoScreen.toString(),systemInfoData);
                        break;
                    case "queryVolume":
                        //获取音量
                        CommonParameters cpVolume = new CommonParameters("callCardService","getVolume");
                        commandService.commandServiceInteger(url, cpVolume.toString(),systemInfoData);
                        break;
                    case "setVolume":
                        //设置音量
                        JsonNode jsonNodeVolume = jsonParentNode.path("msg").path("data").get("arg1");
                        //获取参数 转换接口协议类型
                        Integer arg1Volume = jsonNodeVolume.traverse(mapper).readValueAs(Integer.class);
                        CommandParametersOne cpoVolume  = new CommandParametersOne();
                        cpoVolume.setType("callCardService");
                        cpoVolume.setFn("setVolume");
                        cpoVolume.setArg1(arg1Volume);
                        commandService.commandServiceBoolean(url, cpoVolume.toString(),systemInfoData);
                        break;
                    case "queryBrightness":
                        //获取亮度
                        CommonParameters cprightness = new CommonParameters("callCardService","getBrightness");
                        commandService.commandServiceInteger(url, cprightness.toString(),systemInfoData);
                        break;
                    case "setBrightness":
                        //设置亮度
                        JsonNode jsonBrightness = jsonParentNode.path("msg").path("data").get("arg1");
                        //获取参数 转换接口协议类型
                        Integer arg1Brightness = jsonBrightness.traverse(mapper).readValueAs(Integer.class);
                        CommandParametersOne cpoBrightness = new CommandParametersOne();
                        cpoBrightness.setType("callCardService");
                        cpoBrightness.setFn("setBrightness");
                        cpoBrightness.setArg1(arg1Brightness);
                        commandService.commandServiceBoolean(url, cpoBrightness.toString(),systemInfoData);
                        break;
                    case "restartDevice":
                        //重启设备
                        CommandParametersOne cpRestart = new CommandParametersOne();
                        cpRestart.setType("callCardService");
                        cpRestart.setFn("reboot");
                        cpRestart.setArg1(1);
                        commandService.commandServiceBoolean(url, cpRestart.toString(),systemInfoData);
                        break;
                    case "xwalkLoadH5":
                        //加载H5需要先启动xwalk
                        String xwalkStart = "{ \"type\": \"startActivity\",\"apk\": \"com.xixun.xy.xwalk\"}";
                        //调用xwalk
                        String startStutas = HttpCommandUtils.postHTTP(url, xwalkStart);
                        if (!StringUtils.isEmpty(startStutas)){
                            JsonNode typeJson = mapper.readTree(startStutas).get("_type");
                            if ("success".equals(typeJson.traverse(mapper).readValueAs(String.class))){
                                //启动xwalk成功 进行显示
                                JsonNode argJSON = jsonParentNode.path("msg").path("data").path("arg");
                                XwalkLoadPageResultArgs xlpra = mapper.readValue(argJSON.toString(), XwalkLoadPageResultArgs.class);
                                if (0 == Integer.parseInt(xlpra.getPersistent().toString())){
                                    xlpra.setPersistent(false);
                                }else if (1 == Integer.parseInt(xlpra.getPersistent().toString())){
                                    xlpra.setPersistent(true);
                                }
                                XwalkLoad xLoadPage = new XwalkLoad();
                                xLoadPage.setFn("loadUrl");
                                xLoadPage.setArg(xlpra.toString());
                                commandService.commandServiceString(url, xLoadPage.toString(),systemInfoData);
                            }
                        }
                        break;
                    case "xwalkLoadJS":
                        //加载网页中的js
                        JsonNode argJSON = jsonParentNode.path("msg").path("data").get("arg");
                        XwalkLoad xLoadJS = new XwalkLoad();
                        xLoadJS.setFn("callFnInLoaded");
                        xLoadJS.setArg(argJSON.toString());
                        commandService.commandServiceString(url, xLoadJS.toString(),systemInfoData);
                        break;
                    case "downFileToLocal":
                        //下载文件 等待与世邦广播一起特殊处理
                        break;
                    case "delFileToLocal":
                        //删除内存中的文件
                        FileToLocal delFileToLocal = mapper.readValue(jsonParentNode.path("msg").path("data").toString(), FileToLocal.class);
                        delFileToLocal.setType("deleteFileFromLocal");
                        commandService.commandServiceString(url, delFileToLocal.toString(),systemInfoData);
                        break;
                    case "queryLoaclFileSize":
                        //查询内部储存文件的大小
                        FileToLocal queryFileToLocal = mapper.readValue(jsonParentNode.path("msg").path("data").toString(), FileToLocal.class);
                        queryFileToLocal.setType("getLocalFileLength");
                        commandService.commandServiceClass(url, queryFileToLocal.toString(),systemInfoData,"queryLoaclFileSize");
                        break;
                    case "getScreenshots":
                        //获取截图
                        GetScreenShots gss = mapper.readValue(jsonParentNode.path("msg").path("data").toString(), GetScreenShots.class);
                        commandService.commandServiceClass(url, gss.toString(),systemInfoData,"getScreenshots");
                        break;
                    case "automaticBrightness":
                        break;
                    case "queryAutomaticBrightness":
                        break;
                    case "timingBrightness":
                        break;
                    case "queryTimingBrightness":
                        break;
                    case "timingSwitchScreen":
                        break;
                    case "queryTimingSwitchScreen":
                        break;
                    case "timingRestartDevice":
                        break;
                    case "queryTimingRestartDevice":
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
