package com.shuzhi.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shuzhi.cache.Cache;
import com.shuzhi.entity.CommandInfo;
import com.shuzhi.entity.DeviceInfo;
import com.shuzhi.entity.SystemInfoData;
import com.shuzhi.entity.command.CommonParameters;
import com.shuzhi.service.CommandService;
import com.shuzhi.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

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
    private CommandService commandService;
    @Autowired
    private Utils utils;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private ConfigData configData;


    /**
     * 上报
     */
    public String getReportUrl(DeviceInfo deviceInfo) {
        CommandInfo commandInfo = Cache.commandMap.get("10001");

        if (commandInfo == null) {
            logger.error("未查询到lcd设备cmdid为:10001的命令,放弃请求");
            return null;
        }
        //获取url
        String url = "http://" + commandInfo.getTdeviceFactoryEntity().getServerIp() + ":" + commandInfo.getTdeviceFactoryEntity().getServerPort() + "/command/" + deviceInfo.getTdeviceTecnonEntity().getDeviceId();
        return url;
    }

    /**
     * 上报请求实体
     *
     * @return
     */
    public SystemInfoData getRequestBody() {
        //暂时写死，后期从数据库取
        SystemInfoData infoData = new SystemInfoData();
        infoData.setMsgid("550e8400-e29b-41d4-a716-446655440000");
        infoData.setMsgtype(4);
        infoData.setSystype(1001);
        infoData.setSysid(1);
        infoData.setConnectid(1);
        infoData.setSign("4634e0d2f0b2b423936eb7651eacc54b98cb248f");
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
