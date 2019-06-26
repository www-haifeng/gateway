package com.shuzhi.scheduled;

import com.shuzhi.commen.Utils;
import com.shuzhi.dao.*;
import com.shuzhi.entity.*;
import com.shuzhi.ftp.FTPProperties;
import com.shuzhi.ftp.FTPUtils;
import com.shuzhi.producer.RabbitSender;
import com.shuzhi.service.CLonBonLib;
import com.shuzhi.service.impl.Action_param;
import com.shuzhi.service.impl.LonBon;
import com.shuzhi.util.JsonConvertBeanUtil;
import com.shuzhi.util.ToolUtils;
import com.sun.jna.Pointer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ztt on 2019/6/12
 **/
@Slf4j
@Service
public class LonBonScheduled {
    @Resource(name = "LonBon")
    private LonBon lonBon;
    @Autowired
    private RabbitSender rabbitSender;
    @Autowired
    private LonBonEventDao lonBonEventDao;
    @Value("${gateway.key}")
    private String key;
    @Value("${jna.serverIp}")
    private String serverIp;
    @Autowired
    FactoryIdRepository factoryIdRepository;
    @Autowired
    TGatewayConfigEntityRepository tGatewayConfigEntity;
    @Autowired
    TLonBonOnlineRepository tLonBonOnlineRepository;
    @Autowired
    FTPProperties ftpProperties;
    @Autowired
    FTPUtils ftpUtils;
    @Autowired
    TDeviceLonBonRepository tdr;


    /**
     * 事件
     * 设置时间防止中断
     */
    @Scheduled(fixedRate = 1800000)
    public void callActionNotify() {
        int ret = lonBon.INSTANCE.lb_CallActionNotify(new CLonBonLib.ACTION_CALLBACK() {
            // 去掉 定长byte[1024]中的\u0000
            public String getByteToString(byte[] bytes) {
                String str = new String(bytes);
                Pattern pattern = Pattern.compile("([^\u0000]*)");
                Matcher matcher = pattern.matcher(str);
                String rdf = "";
                if (matcher.find(0)) {
                    byte[] bytearr = new byte[0];
                    try {
                        bytearr = matcher.group(1).getBytes("gbk");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        log.error(e.getMessage());
                    }
                    rdf = new String(bytearr);
                }
                return rdf;
            }

            /**
             * 上报 设备告警信息
             * @param tLonbonEventEntity
             */
            public void sendMessage(TLonbonEventEntity tLonbonEventEntity) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            TDeviceFactoryEntity deviceFactoryEntity = factoryIdRepository.findByMqTypeAndMqSubType("alarmexchange", "lonbon");
                            TGatewayConfigEntity gatewayConfigEntity = tGatewayConfigEntity.findByTypeGroupCode("4");
                            //构建上报报文
                            // @TODO 修改
                            MessageData messageData = new MessageData(deviceFactoryEntity.getType(), deviceFactoryEntity.getSubtype(), tLonbonEventEntity.getSender() + "", "infoid", null, tLonbonEventEntity);
                            String msgts = Utils.getTimeStamp();
                            // @TODO 修改
                            SystemInfoData systemInfoData = new SystemInfoData(ToolUtils.generateUUID() + "", 4, deviceFactoryEntity.getType(), gatewayConfigEntity.getSysId(), gatewayConfigEntity.getConnectId(), "", msgts, messageData);
                            String sign = ToolUtils.getBussesSha(systemInfoData.getMsgid() + key + ToolUtils.md5Hex(systemInfoData.getMsg().toString()) + msgts);
                            systemInfoData.setSign(sign);

                            log.info("=======================" + JsonConvertBeanUtil.bean2json(systemInfoData));

                            rabbitSender.send("alarmMessage", JsonConvertBeanUtil.bean2json(systemInfoData));
                        } catch (Exception e) {
                            e.printStackTrace();
                            log.error(e.getMessage());
                        }
                    }
                }).start();
            }

            /**
             * 接收设备端发来的数据
             * @param userEvent 反馈给用户的事件，见枚举类型lb_event_message_e
             * @param action
             * @param userData  注册回调时传入的用户自定义信息
             */
            @Override
            public void invoke(int userEvent, Action_param.ByReference action, Pointer userData) {
                TLonbonEventEntity tLonbonEventEntity = new TLonbonEventEntity();

                tLonbonEventEntity.setEventId(userEvent);//事件---
                tLonbonEventEntity.setSender(action.sender);//发送端---
                tLonbonEventEntity.setReceiver(action.receiver);// 接收端---
                tLonbonEventEntity.setAcceptBc(getByteToString(action.acceptBc));//广播接收端
                tLonbonEventEntity.setSessionId(getByteToString(action.SessionId));//会话标识
                tLonbonEventEntity.setBroadId(action.broadId);//广播组序(标识)/门磁编号
                tLonbonEventEntity.setRdFile(getByteToString(action.rdFile));//录音文件名
                tLonbonEventEntity.setAtmNum(action.atmTerNum);//Atm编号
                tLonbonEventEntity.setState(0);
                Timestamp d = new Timestamp(System.currentTimeMillis());
                tLonbonEventEntity.setCreateTime(d);
                tLonbonEventEntity.setUploadTime(d);
                lonBonEventDao.save(tLonbonEventEntity);
                sendMessage(tLonbonEventEntity);
            }
        }, null);
        log.info(LocalDateTime.now() + "回调事件-----事件--------返回值：" + ret);
    }

    /**
     * 文件是否已上传
     */
    @Transactional
    @Scheduled(cron = "${scheduled.uploadFile}")
    public void uploadFile() {
        //查询数据库中未上传文件列表 已排重
        List<Map> lonbonList = lonBonEventDao.findAllByRdFileAndAtmNum();
        if (lonbonList != null && lonbonList.size() > 0) {
            //指定目录下全部文件列表
            Map<String, FTPFile[]> ftpfilesMap = new HashMap<>();
            //获取全部未上传文件所在目录
            List<String> alldir = lonBonEventDao.findAllDir();
            //将目录中文件列表放到map中
            alldir.stream().forEachOrdered(dir -> {
                try {
                    ftpfilesMap.put(dir, ftpUtils.getFiles(ftpProperties.getBaseUrl() + "/" + dir));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            //遍历所有文件
            lonbonList.stream().forEachOrdered(tLonbonEventMap -> {
                String rdFile = tLonbonEventMap.get("rd_file").toString();
                String path = rdFile.substring(0, rdFile.lastIndexOf("/"));
                String fileName = rdFile.substring(rdFile.lastIndexOf("/") + 1);
                FTPFile[] files = ftpfilesMap.get(path);
                for (FTPFile file : files) {
                    if (file.isFile()) {
                        //文件已上传
                        if (file.getName().equals(fileName)) {
                            lonBonEventDao.updateFileStatus(new Timestamp(System.currentTimeMillis()), rdFile);
                            Map map = new HashMap();
                            map.put("sessionid", tLonbonEventMap.get("session_id"));
                            map.put("rdfile", rdFile);
                            TDeviceFactoryEntity deviceFactoryEntity = factoryIdRepository.findByMqTypeAndMqSubType("alarmexchange", "lonbon");
                            TGatewayConfigEntity gatewayConfigEntity = tGatewayConfigEntity.findByTypeGroupCode("4");
                            // @TODO 修改
                            MessageData messageData = new MessageData(deviceFactoryEntity.getType(), deviceFactoryEntity.getSubtype(), tLonbonEventMap.get("sender") + "", "infoid", null, map);
                            String msgts = Utils.getTimeStamp();
                            // @TODO 修改
                            SystemInfoData systemInfoData = new SystemInfoData(ToolUtils.generateUUID() + "", 4, deviceFactoryEntity.getType(), gatewayConfigEntity.getSysId(), gatewayConfigEntity.getConnectId(), "", msgts, messageData);

                            try {
                                String sign = ToolUtils.getBussesSha(systemInfoData.getMsgid() + key + ToolUtils.md5Hex(systemInfoData.getMsg().toString()) + msgts);
                                systemInfoData.setSign(sign);
                                log.info("=======================" + JsonConvertBeanUtil.bean2json(systemInfoData));
                                rabbitSender.send("alarmMessage", JsonConvertBeanUtil.bean2json(systemInfoData));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            log.info("uploadFile===========:" + fileName);
                            break;
                        }
                    }
                }
            });
        }
        log.info(LocalDateTime.now() + "uploadFile-----在线状态--------uploadFile：");
    }


    /**
     * 在线状态
     * 0 表示不在线, 非 0 表示在线.
     */
    @Scheduled(cron = "${scheduled.onlineState}")
    public void onlineState() {
        //获取数据库中全部设备 0 主机 1 分机
        List<TDeviceLonBonEntity> deviceLonBonEntityList = tdr.getAllByDeviceTypeIn(new int[]{0, 1});
        // 查看设备在线设备
        deviceLonBonEntityList.stream().forEachOrdered(tDeviceLonBonEntity -> {
            int ret = lonBon.INSTANCE.lb_get_state_from_terminal(serverIp, Integer.parseInt(tDeviceLonBonEntity.getDid()));
            TLonbonOnlineEntity tLonbonOnlineEntity = new TLonbonOnlineEntity();
            tLonbonOnlineEntity.setTerminalId(Integer.parseInt(tDeviceLonBonEntity.getDid()));
            tLonbonOnlineEntity.setState(ret + "");
            tLonbonOnlineEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
            tLonBonOnlineRepository.save(tLonbonOnlineEntity);
            log.info(LocalDateTime.now() + "-----在线状态-----" + tDeviceLonBonEntity.getDid() + "---返回值：" + ret);
        });

    }

}
