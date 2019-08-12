package com.shuzhi.common;

import com.alibaba.fastjson.JSON;
import com.shuzhi.cache.Cache;
import com.shuzhi.entity.CommandInfo;
import com.shuzhi.entity.DeviceInfo;
import com.shuzhi.entity.SystemInfoData;
import com.shuzhi.service.CommandService;
import com.shuzhi.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    public void report(String commandId) {
        CommandInfo commandInfo = Cache.commandMap.get(commandId);
        if (commandInfo == null) {
            logger.error("未查询到广播设备cmdid为:" + commandId + "的命令,放弃请求");
            return;
        }
        //获取url
        String url = "http://" + commandInfo.getTdeviceFactoryEntity().getServerIp() + ":" + commandInfo.getTdeviceFactoryEntity().getServerPort() + commandInfo.getTmsgInfoEntity().getInterfaceId();
        SystemInfoData systemInfoData = getRequestBody();

        Map<Integer, DeviceInfo> deviceInfoMap = Cache.deviceInfoMap;
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (DeviceInfo deviceInfo : deviceInfoMap.values()) {
            Integer cuuid = deviceInfo.getTDeviceIotcommEntity().getCuuid();
            Map<String, String> requestParams = getRequestParams(url, String.valueOf(cuuid));
            executorService.execute(() -> {
                reportService.reportService(url,
                        JSON.toJSONString(requestParams), systemInfoData);
            });
        }


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
     * 请求参数
     *
     * @return
     */
    public Map<String, String> getRequestParams(String requestUrl, String ccuId) {
        Map<String, String> params = new HashMap<>();
        params.put("ccuID", ccuId);
        return params;
    }


}
