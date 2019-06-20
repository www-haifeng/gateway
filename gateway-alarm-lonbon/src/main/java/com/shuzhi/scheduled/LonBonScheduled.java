package com.shuzhi.scheduled;

import com.shuzhi.commen.CommandUtils;
import com.shuzhi.commen.Utils;
import com.shuzhi.dao.FactoryIdRepository;
import com.shuzhi.dao.LonBonEventDao;
import com.shuzhi.dao.TGatewayConfigEntityRepository;
import com.shuzhi.entity.*;
import com.shuzhi.producer.RabbitSender;
import com.shuzhi.service.CLonBonLib;
import com.shuzhi.service.impl.Action_param;
import com.shuzhi.service.impl.LonBon;
import com.shuzhi.util.JsonConvertBeanUtil;
import com.shuzhi.util.ToolUtils;
import com.sun.jna.Pointer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ztt on 2019/6/12
 **/
@Service
public class LonBonScheduled {
    private final static Logger logger = LoggerFactory.getLogger(CommandUtils.class);
    @Resource(name = "LonBon")
    private LonBon lonBon;
    @Autowired
    private RabbitSender rabbitSender;
    @Autowired
    private LonBonEventDao lonBonEventDao;
    @Value("${gateway.key}")
    private String key;
    @Autowired
    TGatewayConfigEntityRepository tGatewayConfigEntity;
    /**
     * 事件
     */
    @Scheduled(fixedRate = 1800000)
    public void callActionNotify() {
        int ret = lonBon.INSTANCE.lb_CallActionNotify(new CLonBonLib.ACTION_CALLBACK() {
            // 去掉 定长byte[1024]中的\u0000
            public String getByteToString(byte[] bytes){
                String str = new String(bytes);
                Pattern pattern = Pattern.compile("([^\u0000]*)");
                Matcher matcher = pattern.matcher(str);
                String rdf = "";
                if(matcher.find(0)){
                    byte[] bytearr = new byte[0];
                    try {
                        bytearr = matcher.group(1).getBytes("gbk");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    rdf = new String(bytearr);
                }
                return rdf;
            }

            public void sendMessage(TLonbonEventEntity tLonbonEventEntity){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            TGatewayConfigEntity gc = tGatewayConfigEntity.getOne(Long.parseLong("5"));
                            //构建上报报文
                            // @TODO 修改
                            MessageData messageData = new MessageData(1004,1004,tLonbonEventEntity.getSender() + "","qwer","",tLonbonEventEntity);
                            String msgts = Utils.getTimeStamp();
                            // @TODO 修改
                            SystemInfoData systemInfoData = new SystemInfoData(ToolUtils.generateUUID()+"",4,1004,gc.getSysId(),gc.getConnectId(),"",msgts,messageData);
                            String sign = ToolUtils.getBussesSha(systemInfoData.getMsgid()+key+ToolUtils.md5Hex(systemInfoData.getMsg().toString())+msgts);
                            systemInfoData.setSign(sign);

                            System.out.println("======================="+ JsonConvertBeanUtil.bean2json(systemInfoData));

                            rabbitSender.send("alarmMessage", JsonConvertBeanUtil.bean2json(systemInfoData));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }

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
        System.out.println(LocalDateTime.now() + "回调事件-----事件--------返回值：" + ret);
    }

    /**
     * 在线状态回调
     */
//    @Scheduled(fixedRate = 1800000)
//    @Scheduled(fixedRate = 1000)
    public void lb_onlineState_set_callBack() {
        int ret = lonBon.INSTANCE.lb_onlineState_set_callBack(new CLonBonLib.ONLINE_STATE_CALLBACK() {

            @Override
            public void invoke(int userEvent, int displayNum, Pointer userData) {
                System.out.println(LocalDateTime.now() + "---------------------------------------------------");
                System.out.println("事件---" + userEvent);
                System.out.println("终端---" + displayNum);
            }
        }, null);
        System.out.println(LocalDateTime.now() + "回调状态-----在线状态--------返回值：" + ret);
    }

    /**
     * 事件

    @Scheduled(fixedRate = 1800000)
    public void callActionNotify() {
        int ret = lonBon.INSTANCE.lb_CallActionNotify(new CLonBonLib.ACTION_CALLBACK() {

            @Override
            public void invoke(int userEvent, Action_param.ByReference action, Pointer userData) {
                System.out.println(LocalDateTime.now() + "*********************************************");
                System.out.println("事件---" + userEvent);
                System.out.println("发送端---" + action.sender);
                System.out.println("接收端---" + action.receiver);
                String acceptBc = null;
                try {
                    acceptBc = new String(action.acceptBc, "gbk");
                    System.out.println("广播接收端---" + acceptBc);
                    String SessionId = new String(action.SessionId, "gbk");
                    System.out.println("会话标识---" + SessionId);
                    System.out.println("广播组序(标识)/门磁编号---" + action.broadId);
                    String rdFile = new String(action.rdFile, "gbk");
                    System.out.println("录音文件名---" + rdFile);
                    System.out.println("Atm编号---" + action.atmTerNum);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }, null);
        System.out.println(LocalDateTime.now() + "回调事件-----事件--------返回值：" + ret);
    }

    /**
     * 在线状态回调

    @Scheduled(fixedRate = 1800000)
    public void lb_onlineState_set_callBack() {
        int ret = lonBon.INSTANCE.lb_onlineState_set_callBack(new CLonBonLib.ONLINE_STATE_CALLBACK() {

            @Override
            public void invoke(int userEvent, int displayNum, Pointer userData) {
                System.out.println(LocalDateTime.now() + "---------------------------------------------------");
                System.out.println("事件---" + userEvent);
                System.out.println("终端---" + displayNum);
            }
        }, null);
        System.out.println(LocalDateTime.now() + "回调状态-----在线状态--------返回值：" + ret);
    }
    */
}
