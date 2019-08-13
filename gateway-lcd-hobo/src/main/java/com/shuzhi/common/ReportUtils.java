package com.shuzhi.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shuzhi.cache.Cache;
import com.shuzhi.entity.CommandInfo;
import com.shuzhi.entity.SystemInfoData;
import com.shuzhi.service.ReportService;
import org.apache.commons.lang3.StringUtils;
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
    private Utils utils;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private ConfigData configData;

    private final String reqUrl = "/vs/api/apiselclient.vs";

    /**
     * 上报
     */
    public String getReportUrl() {

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
        sb.append(reqUrl);
        sb.append("?account=");
        sb.append(configData.getAccount());
        sb.append("&password=");
        sb.append(configData.getPassword());

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
        String reportUrl = getReportUrl();
        if (StringUtils.isNotEmpty(reportUrl)) {
            reportService.reportService(reportUrl, getRequestBody());
        }
    }
}
