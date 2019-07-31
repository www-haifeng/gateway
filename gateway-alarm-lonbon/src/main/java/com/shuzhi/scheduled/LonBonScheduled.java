package com.shuzhi.scheduled;

import com.alibaba.fastjson.JSON;
import com.shuzhi.cache.Cache;
import com.shuzhi.commen.ProtocolProperties;
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
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    ProtocolProperties protocolProperties;
    @Autowired
    FTPUtils ftpUtils;
    @Autowired
    TDeviceLonBonRepository tdr;
    @Autowired
    Utils utils;


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
            public void sendMessage(TLonbonEventEntity tLonbonEventEntity,LonBonEntity lonBonEntity) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            TDeviceFactoryEntity deviceFactoryEntity = factoryIdRepository.findByMqTypeAndMqSubType("alarmexchange", "lonbon");
                            TGatewayConfigEntity gatewayConfigEntity = tGatewayConfigEntity.findByTypeGroupCode("4");
                            //构建上报报文
                            // @TODO 修改
                            MessageData messageData = null;
                            if (tLonbonEventEntity != null){
                                messageData = new MessageData(deviceFactoryEntity.getType(), deviceFactoryEntity.getSubtype(), tLonbonEventEntity.getSender() + "", "infoid", null, tLonbonEventEntity);
                            }else if(lonBonEntity != null){
                                messageData = new MessageData(deviceFactoryEntity.getType(), deviceFactoryEntity.getSubtype(), lonBonEntity.getHostnum() + "", "infoid", null, lonBonEntity);
                            }

                            String msgts = Utils.getTimeStamp();
                            // @TODO 修改
                            SystemInfoData systemInfoData = new SystemInfoData(ToolUtils.generateUUID() + "", 4, deviceFactoryEntity.getType(),Integer.parseInt(gatewayConfigEntity.getSysId()), Integer.parseInt(gatewayConfigEntity.getConnectId()), "", msgts, messageData);
                            String sign = ToolUtils.getBussesSha(systemInfoData.getMsgid() + key + ToolUtils.md5Hex(systemInfoData.getMsg().toString()) + msgts);
                            systemInfoData.setSign(sign);

                            log.info("===========上报事件============" + JsonConvertBeanUtil.bean2json(systemInfoData));

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
                TLonbonEventEntity tLonbonEventEntity = null;
                LonBonEntity lonBonEntity = null;
                //如果是普通呼入参数(3),单独进行处理
              /*  if (userEvent == 3){
                    lonBonEntity = new LonBonEntity();
                    lonBonEntity.setSvrip("");
                    lonBonEntity.setHostnum(action.sender);
                    lonBonEntity.setTernum(action.receiver);
                    sendMessage(tLonbonEventEntity,lonBonEntity);
                    return;
                }*/

                tLonbonEventEntity = new TLonbonEventEntity();

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
                sendMessage(tLonbonEventEntity,lonBonEntity);
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
                            try {
                                readConfigfileForFTP(ftpProperties.getBaseUrl()+"/"+path,fileName);
                            } catch (IOException e) {
                                log.error("上传文件错误");
                                e.printStackTrace();
                            }
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
                            SystemInfoData systemInfoData = new SystemInfoData(ToolUtils.generateUUID() + "", 4, deviceFactoryEntity.getType(), Integer.parseInt(gatewayConfigEntity.getSysId()), Integer.parseInt(gatewayConfigEntity.getConnectId()), "", msgts, messageData);

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
        log.info(LocalDateTime.now() + "uploadFile-----上传状态--------uploadFile：");
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

            //记录设备状态
            DeviceState deviceState = new DeviceState();
            deviceState.setTernum(Integer.parseInt(tDeviceLonBonEntity.getDid()));
            deviceState.setState(Integer.parseInt(tDeviceLonBonEntity.getDid()));
            deviceState.setType(tDeviceLonBonEntity.getDeviceType());
            Cache.reportResultList.add(deviceState);
            log.info(LocalDateTime.now() + "-----在线状态-----" + tDeviceLonBonEntity.getDid() + "---返回值：" + ret +tDeviceLonBonEntity.getDeviceType()+"---设备类型");
        });

        if ( protocolProperties.getRunMsg()!=0 ){
            SystemInfoData systemInfoData = utils.getRequestBody();
            reportSend(systemInfoData);
        }
    }

    private void reportSend(SystemInfoData systemInfoData) {
        String timeStamp = utils.getTimeStamp();
        //命令正确执行
        if (CollectionUtils.isNotEmpty(Cache.reportResultList)) {
            ReportMsgRevertData messageRevertData = getReportMsgRevertData(Cache.reportResultList);
            String mrdJSON = JSON.toJSONString(messageRevertData);
            systemInfoData.setMsgts(timeStamp);
            systemInfoData.setMsg(mrdJSON);

            systemInfoData.setSign(utils.getSignVerify(systemInfoData));
            String commandRevertJSON = systemInfoData.toString();
            try {
                rabbitSender.send("upMessage","upMessage", commandRevertJSON);
                log.info("上报命令发送完毕:" + commandRevertJSON);
                //清空缓存
                Cache.reportResultList = new ArrayList<>();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //命令执行未成功
            ReportMsgRevertData messageRevertData = getReportMsgRevertData(Cache.reportResultList);
            String mrdJSON = JSON.toJSONString(messageRevertData);
            systemInfoData.setMsgts(timeStamp);
            systemInfoData.setMsg(mrdJSON);
            systemInfoData.setSign(utils.getSignVerify(systemInfoData));
            String commandRevertJSON = systemInfoData.toString();
            try {
                rabbitSender.send("upMessage","upMessage", commandRevertJSON);
                log.error("上报命令执行失败，请查看原因:" + commandRevertJSON);
                //清空缓存
                Cache.reportResultList.clear();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 封装 msg层数据
     * @param reportResultList :结果list
     * @return
     */
    private ReportMsgRevertData getReportMsgRevertData( List<DeviceState> reportResultList) {
        ReportMsgRevertData mrd = new ReportMsgRevertData();
        Map<String, CommandInfo> commandMap = Cache.commandMap;
        String fristKey = commandMap.keySet().iterator().next();
        CommandInfo commandInfo = commandMap.get(fristKey);
        mrd.setType(commandInfo.getTdeviceFactoryEntity().getType());
        mrd.setSubtype(commandInfo.getTdeviceFactoryEntity().getSubtype());
        mrd.setInfoid("123456");
        mrd.setDid("\"\"");
        Map<String, List<DeviceState>> dataMap = new HashMap<>();
        dataMap.put("clientlist",reportResultList);
        mrd.setData(dataMap);
        return mrd;
    }

    public String readConfigfileForFTP(String localPath,String localName) throws SocketException, IOException {
      FTPClient ftpOne = new FTPClient();
      FTPClient ftpTwo = new FTPClient();
      ftpOne.connect(ftpProperties.getHost(), ftpProperties.getPort());
      //连接FTPOne
      boolean isloginOne = ftpOne.login(ftpProperties.getUsername(), ftpProperties.getPassword());
      if (!FTPReply.isPositiveCompletion(ftpOne.getReplyCode())) {
            log.info("连接失败，用户名或密码错误");
      } else {
            log.info("FTP连接成功");
      }
      ftpTwo.connect(ftpProperties.getTargetHost(), ftpProperties.getTargetPort());

      //连接FTPTwo
      boolean isloginTwo = ftpTwo.login(ftpProperties.getTargetUserName(), ftpProperties.getTargetPassWord());
      if (!FTPReply.isPositiveCompletion(ftpOne.getReplyCode())) {
            log.info("连接失败，用户名或密码错误");
      } else {
            log.info("备份FTP连接成功");
      }
      if (isloginOne && isloginTwo) {
            ftpOne.enterLocalPassiveMode();
            log.info("获取ftpOne路径" + localPath + "文件");
            // 获取ftpOne目录下的文件
            FTPFile[] ftp = ftpOne.listFiles(localPath);
            System.out.println("文件数量:"+ftp.length);
            for (FTPFile file : ftp) {
                if (!localName.equals(file.getName())){
                    continue;
                }
                String st = new String(file.getName().getBytes(), "UTF-8");
                log.info("开始备份"+st+"文书");
                if (st.endsWith("mp4")) {

                      log.info("" + "传输开始时间");
                      ftpTwo.setRemoteVerificationEnabled(false);
                      // 获取ftpTwo输出流
                      OutputStream is = ftpTwo.storeFileStream(ftpTwo.printWorkingDirectory()+ "/video/"+ file.getName());
                      if (is == null) {
                        log.error("目标文件不存在");
                      }
                      ftpOne.changeWorkingDirectory(localPath);
                      ftpOne.setFileType(FTP.BINARY_FILE_TYPE);
                      // 通过流把FTPOne复制到FTPTwo
                      ftpOne.retrieveFile(file.getName(), is);
                        log.info(st + "备份完成");
                      is.close();
                      // 检查返回值是否成功
                      ftpTwo.completePendingCommand();
                        log.info("结束时间");
                }
            }
      }
      ftpOne.logout();
      if (ftpOne.isConnected()) {
            ftpOne.disconnect();
      }
      if (ftpTwo.isConnected()) {
            ftpTwo.disconnect();
      } else {
            log.error("ftp路径错误");
      }
      return "数据全部已完成";
      }
}
