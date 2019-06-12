package com.shuzhi.scheduled;

import com.shuzhi.service.CLonBonLib;
import com.shuzhi.service.impl.Action_param;
import com.shuzhi.service.impl.LonBon;
import com.sun.jna.Pointer;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

/**
 * Created by ztt on 2019/6/12
 **/
@Service
public class LonBonScheduled {
    @Resource(name="LonBon")
    private LonBon lonBon;
    /**
     * 事件
     */
    @Scheduled(fixedRate=1800000)
    public void callActionNotify(){
        int ret = lonBon.INSTANCE.lb_CallActionNotify(new CLonBonLib.ACTION_CALLBACK() {

            @Override
            public void invoke(int userEvent, Action_param.ByReference action, Pointer userData) {
                System.out.println( LocalDateTime.now() + "*********************************************");
                System.out.println("事件---"+userEvent);
                System.out.println("发送端---"+action.sender);
                System.out.println("接收端---"+action.receiver);
                String acceptBc = null;
                try {
                    acceptBc = new String(action.acceptBc,"gbk");
                    System.out.println("广播接收端---"+acceptBc);
                    String SessionId = new String(action.SessionId,"gbk");
                    System.out.println("会话标识---"+SessionId);
                    System.out.println("广播组序(标识)/门磁编号---"+action.broadId);
                    String rdFile = new String(action.rdFile,"gbk");
                    System.out.println("录音文件名---"+rdFile);
                    System.out.println("Atm编号---"+action.atmTerNum);

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }, null);
        System.out.println( LocalDateTime.now() + "回调事件-----事件--------返回值："+ret);
    }

    /**
     * 在线状态回调
     */
    @Scheduled(fixedRate=1800000)
    public void lb_onlineState_set_callBack(){
        int  ret = lonBon.INSTANCE.lb_onlineState_set_callBack(new CLonBonLib.ONLINE_STATE_CALLBACK() {

            @Override
            public void invoke(int userEvent, int displayNum, Pointer userData){
                System.out.println( LocalDateTime.now() + "---------------------------------------------------");
                System.out.println("事件---"+userEvent);
                System.out.println("终端---"+displayNum);
            }
        },null);
        System.out.println( LocalDateTime.now() + "回调状态-----在线状态--------返回值："+ret);
    }
}
