package com.shuzhi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shuzhi.cache.Cache;
import com.shuzhi.common.ByteUtils;
import com.shuzhi.common.ConfigData;
import com.shuzhi.common.HttpCommandUtils;
import com.shuzhi.common.Utils;
import com.shuzhi.entity.*;
import com.shuzhi.producer.RabbitSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * 功能描述 命令业务逻辑处理
 * @author YHF
 * @date 2019/6/6
 * @params
 * @return
 */
@Component
@EnableConfigurationProperties(ConfigData.class)
public class CommandService {
    private final static Logger logger = LoggerFactory.getLogger(CommandService.class);
    @Autowired
    ConfigData configData;
    @Autowired
    private RabbitSender rabbitSender;
    @Autowired
    private Utils utils;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private HttpCommandUtils httpCommandUtils;
    @Autowired
    private ByteUtils byteUtils;


    /**
     * @Description:组装命令返回rabbit
     * @Author: YHF
     * @date 2019/6/6
     */
    private void commandSend(String resultJSON, SystemInfoData systemInfoData){
            String timeStamp = utils.getTimeStamp();
            //命令正确执行
            if (resultJSON !=null && !"".equals(resultJSON)){
                try {
                    JsonNode msgNo =  mapper.readTree(resultJSON).get("msgNo");
                    Integer msgNoNum = msgNo.traverse(mapper).readValueAs(Integer.class);
                    if (msgNoNum == 10000){
                        //获取参数 转换接口协议类型
                        MessageRevertData messageRevertData = utils.getMessageRevertData(configData.getSeccussCode(), timeStamp, resultJSON);
                        String mrdJSON = messageRevertData.toString();
                        systemInfoData.setMsgts(timeStamp);
                        systemInfoData.setMsgtype(configData.getMsgtypeCommandReturn());
                        systemInfoData.setMsg(mrdJSON);

                        systemInfoData.setSign(utils.getSignVerify(systemInfoData));
                        String commandRevertJSON = systemInfoData.toString();
                        rabbitSender.send("lowerControlMessage",commandRevertJSON,"lowerControlMessage");
                        logger.info("命令回执发送完毕:"+commandRevertJSON);
                    }else{
                        //命令执行未成功
                        failedResult(systemInfoData,timeStamp,resultJSON);
                        logger.error("接口返回值有误,请查看数据:"+resultJSON);
                    }
                } catch (Exception e) {
                    logger.error("命令转换出错",e);
                }

            }else{
                //命令执行未成功
                failedResult(systemInfoData,timeStamp,resultJSON);
            }

    }

    public void commandService(String url, SystemInfoData systemInfoData) {
        String resultJson = httpCommandUtils.getHttp(url);
        commandSend(resultJson.replace("devName", "devname").replace("groupId", "groupid"),systemInfoData);
    }

    /**
     * @Description: 命令失败处理
     * @Author: YHF
     * @date 2019/6/17
     */
    private void failedResult(SystemInfoData systemInfoData,String timeStamp,String resultJSON){
        //命令执行未成功
        MessageRevertData messageRevertData = utils.getMessageRevertData(configData.getFailedCode(), timeStamp, resultJSON);
        String mrdJSON = messageRevertData.toString();
        systemInfoData.setMsgts(timeStamp);
        systemInfoData.setMsg(mrdJSON);
        systemInfoData.setSign(utils.getSignVerify(systemInfoData));
        String commandRevertJSON = systemInfoData.toString();
        try {
            rabbitSender.send("lowerControlMessage",commandRevertJSON,"lowerControlMessage");
            logger.info("命令-失败-回执发送完毕:"+commandRevertJSON);
            logger.error("命令执行失败，请查看原因:"+commandRevertJSON);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void WiFiStyletService(byte[] bytes){
        logger.info("接收到探针数据,内容为:"+byteUtils.bytesToHexString(bytes));
        if (bytes.length != 51){
            logger.error("数据包长度有误,请检查"+byteUtils.bytesToHexString(bytes));
        }

        ByteBuffer bf = ByteBuffer.wrap(bytes);
        //取出消息类型 消息体长度 保留字节
        byte[] noneBytes = new byte[3];
        bf.get(noneBytes);
        //取出终端mac地址
        byte[] staMacBytes = new byte[6];
        bf.get(staMacBytes);
        String staMac = byteUtils.bytesToHexMacString(staMacBytes);
        //取出基于每个终端的序号
        byte[] seqId = new byte[4];
        bf.get(seqId);

        //取出ap mac 地址
        byte[] apMacBytes = new byte[6];
        bf.get(apMacBytes);
        String apMac = byteUtils.bytesToHexMacString(apMacBytes);
        //取出地图相对坐标(X轴)
        byte[] xAxleBytes = new byte[8];
        bf.get(xAxleBytes);
        double xAxle = byteUtils.byte2double(xAxleBytes);
        //取出地图相对坐标(Y轴)
        byte[] yAxleBytes = new byte[8];
        bf.get(yAxleBytes);
        double yAxle = byteUtils.byte2double(yAxleBytes);
        //取出地图相对坐标(Z轴)
        byte[] zAxleBytes = new byte[8];
        bf.get(zAxleBytes);
        double zAxle = byteUtils.byte2double(zAxleBytes);
        //取出维度ID(地图的HASH值)
        byte[] mapIdBytes = new byte[4];
        bf.get(mapIdBytes);
        String mapId = byteUtils.bytesToHexString(mapIdBytes);
        WiFiStyletInfo wifi = new WiFiStyletInfo(staMac, apMac, xAxle, yAxle, zAxle, mapId);

        Cache.wifi.add(wifi);

        if (Cache.wifi.size() >= 50){
            sendWiFiStylet(Cache.wifi.toJSONString(),Cache.deviceInfoMap.get(apMac));
            Cache.wifi.clear();
        }
    }

    private void sendWiFiStylet(String jsonData, DeviceInfo deviceInfo){
        String msgts = utils.getTimeStamp();
        String msgid = UUID.randomUUID().toString();
        WiFiStyleData wsd = new WiFiStyleData(jsonData);
        WiFiStyleMsg wsm = new WiFiStyleMsg(deviceInfo.getTdeviceFactoryEntity().getType(),deviceInfo.getTdeviceFactoryEntity().getSubtype(),deviceInfo.getTdeviceAbloomyEntity().getDid(),
                configData.getWifiStyletId(),wsd.toString());
        SystemInfoData sid = new SystemInfoData(msgid,configData.getMsgtypeReportMsg(),configData.getSystype(),configData.getSysid(),configData.getConnectid(),"",msgts,wsm.toString());
        sid.setSign(utils.getSignVerify(sid));
        try {
            rabbitSender.send("wifiMessage",sid.toString(),"wifiMessage");
            logger.info("上报探针数据:"+sid.toString());
        } catch (Exception e) {
            logger.error("数据发送出错",e);
        }
    }
}
