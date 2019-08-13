package com.shuzhi.common;

import com.shuzhi.cache.Cache;
import com.shuzhi.entity.CommandInfo;
import com.shuzhi.entity.DeviceInfo;
import com.shuzhi.entity.SystemInfoData;
import com.shuzhi.entity.command.CommonParameters;
import com.shuzhi.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

/**
 * @Description: 上报相关业务工具类
 * @Author: lirb
 * @CreateDate: 2019/7/15 13:35
 * @Version: 1.0
 **/
@Component
public class ReportUtils {

    private final static Logger logger = LoggerFactory.getLogger(ReportUtils.class);

    @Autowired
    private ReportService reportService;
    @Autowired
    private ConfigData configData;


    /**
     * 上报
     */
    public String getReportUrl(DeviceInfo deviceInfo) {

        CommandInfo commandInfo =null;
        Map<String, CommandInfo> commandMap = Cache.commandMap;
        for(CommandInfo command:commandMap.values()){
            commandInfo=command;
            if(commandInfo!=null){
                break;
            }
        }
        //获取url
        StringBuilder sb = new StringBuilder();
        sb.append("http://");
        sb.append(commandInfo.getTdeviceFactoryEntity().getServerIp());
        sb.append(":");
        sb.append(commandInfo.getTdeviceFactoryEntity().getServerPort());
        sb.append("/command/");
        sb.append( deviceInfo.getTdeviceTecnonEntity().getDeviceId());
        return sb.toString();
    }

    /**
     * 上报请求实体
     *
     * @return
     */
    public SystemInfoData getRequestBody() {
        //暂时写死，后期从数据库取
        SystemInfoData infoData = new SystemInfoData();
        infoData.setMsgid(UUID.randomUUID().toString());
        infoData.setMsgtype(4);
        infoData.setSystype(configData.getSysType());
        infoData.setSysid(Integer.parseInt(Cache.gatewayConfigEntity.getSysId()));
        infoData.setConnectid(Integer.parseInt(Cache.gatewayConfigEntity.getConnectId()));
        return infoData;
    }


    /**
     * 发送上报请求
     */
    public void reportRequest() {
        //获取设备缓存
        Map<String, DeviceInfo> deviceInfoMap = Cache.deviceInfoMap;
        for(String key :deviceInfoMap.keySet()){
            DeviceInfo deviceInfo = deviceInfoMap.get(key);
            String reportUrl = getReportUrl(deviceInfo);
            //获取屏幕开关状态
            CommonParameters cpScreen = new CommonParameters("callCardService","isScreenOpen");
            reportService.reportService(reportUrl,cpScreen.toString(),deviceInfo);
        }
        SystemInfoData systemInfoData = getRequestBody();
        reportService.reportSend(systemInfoData);
    }
}
