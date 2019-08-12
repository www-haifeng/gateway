package com.shuzhi.service;

import com.shuzhi.cache.Cache;
import com.shuzhi.common.ConfigData;
import com.shuzhi.common.HttpCommandUtils;
import com.shuzhi.common.Utils;
import com.shuzhi.entity.CommandInfo;
import com.shuzhi.entity.ReportMsgRevertData;
import com.shuzhi.entity.SystemInfoData;
import com.shuzhi.producer.RabbitSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description: 上报 相关 业务
 * @Author: lirb
 * @CreateDate: 2019/7/15 13:31
 * @Version: 1.0
 **/
@Component
@EnableConfigurationProperties(ConfigData.class)
public class ReportService {

    private final static Logger logger = LoggerFactory.getLogger(ReportService.class);

    @Autowired
    ConfigData configData;
    @Autowired
    private RabbitSender rabbitSender;
    @Autowired
    private Utils utils;
    @Autowired
    private HttpCommandUtils httpCommandUtils;

    /**
     * 上报业务处理
     *
     * @param url            ：请求url
     * @param commandJSON    ：请求参数
     * @param systemInfoData ：请求返回体
     */
    public synchronized void reportService(String url, String commandJSON, SystemInfoData systemInfoData) {
        try {
            //调用请求
            String resultJSON = httpCommandUtils.postHTTP(url, commandJSON);
            logger.info("请求返回结果:" + resultJSON);
            reportSend( url,resultJSON, systemInfoData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 上报结果集发送到 mq
     *
     * @param url     ：请求路径
     * @param resultJSON     ：结果数据
     * @param systemInfoData ：结果体
     */
    public void reportSend(String url,String resultJSON, SystemInfoData systemInfoData) {
        String timeStamp = utils.getTimeStamp();
        //命令正确执行
        if (resultJSON != null && !"".equals(resultJSON)) {
            ReportMsgRevertData messageRevertData = getReportMsgRevertData(url,resultJSON);
            String mrdJSON = messageRevertData.toString();
            systemInfoData.setMsgts(timeStamp);
            systemInfoData.setMsg(mrdJSON);

            systemInfoData.setSign(utils.getSignVerify(systemInfoData));
            String commandRevertJSON = systemInfoData.toString();
            try {
                rabbitSender.send("lowerControlMessage", commandRevertJSON);
                logger.info("命令回执发送完毕:" + commandRevertJSON);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //命令执行未成功
            ReportMsgRevertData messageRevertData = getReportMsgRevertData(url,resultJSON);
            String mrdJSON = messageRevertData.toString();
            systemInfoData.setMsgts(timeStamp);
            systemInfoData.setMsg(mrdJSON);
            systemInfoData.setSign(utils.getSignVerify(systemInfoData));
            String commandRevertJSON = systemInfoData.toString();
            try {
                rabbitSender.send("lowerControlMessage", commandRevertJSON);
                logger.error("命令执行失败，请查看原因:" + commandRevertJSON);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 封装 msg层数据
     *
     * @param url :请求路径
     * @param resultJson :结果及
     * @return
     */
    private ReportMsgRevertData getReportMsgRevertData(String url,String resultJson) {
        ReportMsgRevertData mrd = new ReportMsgRevertData();
        Map<String, CommandInfo> commandMap = Cache.commandMap;
        String fristKey = commandMap.keySet().iterator().next();
        CommandInfo commandInfo = commandMap.get(fristKey);
        mrd.setType(commandInfo.getTdeviceFactoryEntity().getType());
        mrd.setSubtype(commandInfo.getTdeviceFactoryEntity().getSubtype());
        if(url.contains("/monitor/module/light/monitoring/queryBreakerPageList.action")) {
            mrd.setInfoid(configData.getBreakerInfoId());
        }else if(url.contains("/monitor/module/light/monitoring/queryRtuPageList.action")){
            mrd.setInfoid(configData.getRtuInfoId());
        }
        mrd.setDid("\"\"");
        mrd.setData(resultJson);
        return mrd;
    }
}
