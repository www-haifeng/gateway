package com.shuzhi.common;

import com.alibaba.fastjson.JSON;
import com.shuzhi.cache.Cache;
import com.shuzhi.entity.CommandInfo;
import com.shuzhi.entity.DeviceInfo;
import com.shuzhi.entity.SystemInfoData;
import com.shuzhi.service.CommandService;
import com.shuzhi.service.ReportService;
import org.apache.commons.lang3.StringUtils;
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
     *
     * @param requestType :请求类型 1. 回路状态请求 2. 终端状态请求
     */
    public void report(int requestType) {
        //获取url
        String url = "";
        try {
             url = getReqUrl(requestType);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return;
        }
        //获取返回的消息主体
        SystemInfoData systemInfoData = getRequestBody();
        //循环设备请求上报
        Map<Integer, DeviceInfo> deviceInfoMap = Cache.deviceInfoMap;
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (DeviceInfo deviceInfo : deviceInfoMap.values()) {
            Integer cuuid = deviceInfo.getTDeviceIotcommEntity().getCuuid();
            Map<String, String> requestParams = getRequestParams(String.valueOf(cuuid));
            String finalUrl = url;
            executorService.execute(() -> {
                reportService.reportService(finalUrl,
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
     * @param ccuId :集中器id
     * @return
     */
    public Map<String, String> getRequestParams(String ccuId) {
        Map<String, String> params = new HashMap<>();
        params.put("ccuID", ccuId);
        return params;
    }

    /**
     * 获取请求url
     *
     * @param requestType : 请求类型 1. 回路状态请求 2. 终端状态请求
     * @return
     */
    public String getReqUrl(int requestType) throws Exception {
        //从设备缓存钟获取ip和端口号
        Map<Integer, DeviceInfo> deviceInfoMap = Cache.deviceInfoMap;
        DeviceInfo deviceInfo = null;
        for (DeviceInfo device : deviceInfoMap.values()) {
            deviceInfo = device;
            if (deviceInfo != null) {
                break;
            }
        }
        //拼接url
        StringBuffer sb = new StringBuffer("http://");
        sb.append(deviceInfo.getTdeviceFactoryEntity().getServerIp());
        sb.append(":");
        sb.append(deviceInfo.getTdeviceFactoryEntity().getServerPort());
        switch (requestType) {
            case 1:
                sb.append("/monitor/module/light/monitoring/queryBreakerPageList.action");
                break;
            case 2:
                sb.append("/monitor/module/light/monitoring/queryRtuPageList.action");
                break;
            default:
                throw new Exception("没有此上报类型");
        }

        return sb.toString();
    }
}
