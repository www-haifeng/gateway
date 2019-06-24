package com.shuzhi.controller;

import com.shuzhi.entity.Terminal;
import com.shuzhi.service.impl.LonBon;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ztt on 2019/6/11
 **/
@RestController
public class LonBonController {
    String serverIp = "192.168.8.235";
    int hostNum = 111000;
    int terNum = 111001;
    //    int terNum = 111002;
    @Resource(name = "LonBon")
    private LonBon lonBon;

    /**
     * 接听
     */
    @RequestMapping("/answer")
    public void answer() {
        // 接听
        int ret = lonBon.lb_answer(serverIp, hostNum, terNum);
        System.out.println(LocalDateTime.now() + "  answer               " + ret);
    }

    /**
     * 挂断
     */
    @RequestMapping("/hangUp")
    public void hangUp() {
        // 挂断
        int hangup = lonBon.lb_hangUp(serverIp, hostNum, terNum, 1);
        System.out.println(LocalDateTime.now() + "  hangUp                " + hangup);
    }

    /**
     * 广播
     */
    @RequestMapping("/startbroadcast")
    public void startBroadcast() {
        int[] groupNum = new int[2];
        groupNum[0] = 111001;
        groupNum[1] = 111002;
        int broad = lonBon.lb_start_broadcast(serverIp, hostNum, groupNum, groupNum.length);
        System.out.println(LocalDateTime.now() + "  startbroadcast                " + broad);
    }

    /**
     * 停止广播
     */
    @RequestMapping("/stopbroadcast")
    public void stopBroadcast() {
        int[] groupNum = new int[2];
        groupNum[0] = 111001;
        groupNum[1] = 111002;
        int stop = lonBon.lb_stop_broadcast(serverIp, hostNum);
        System.out.println(LocalDateTime.now() + "  stopbroadcast                " + stop);
    }

    /**
     * 监视
     */
    @RequestMapping("/call")
    public void call() {
        int[] groupNum = new int[2];
        groupNum[0] = 111001;
        groupNum[1] = 111002;
        int call = lonBon.lb_call(serverIp, hostNum, terNum);
        System.out.println(LocalDateTime.now() + "   call                " + call);
    }


    /**
     * 在线主机
     */
    @RequestMapping("/online")
    public void online() {
        // 全部在线主机
        int oncount = lonBon.lb_get_online_master_count(serverIp);
        if (oncount > 0) {
            int[] mstList = new int[oncount];
            int onmcount = lonBon.lb_get_online_master(serverIp, mstList, oncount);
            if (onmcount > 0) {
                System.out.println(LocalDateTime.now() + "   " + onmcount);
            }
        }
        System.out.println(LocalDateTime.now() + "  hangUp                " + oncount);
    }

    /**
     * 主机下在线分机
     */
    @RequestMapping("/terminal")
    public void terminal() {
        // 全部在线主机
        int tocount = lonBon.lb_get_terminal_online_from_master_count(serverIp, hostNum);
        if (tocount > 0) {
            int[] terList = new int[tocount];
            int aa = lonBon.lb_get_terminal_online_from_master(serverIp, hostNum, terList, tocount);
            if (aa > 0) {
                int kk = 0;
            }
        }
        System.out.println(LocalDateTime.now() + "  hangUp                " + tocount);
    }

}
