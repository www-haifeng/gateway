package com.shuzhi.entity;

import lombok.Data;

/**
 * Created by ztt on 2019/6/17
 **/
@Data
public class LonBonEntity {
    private String svrip;//服务器IP
    private int hostnum;//对讲主机编号
    private int ternum;//对讲分机编号
    private int[] groupnum;//对讲终端编号列表
    private String[] filelist;//音频文件
    private int ncyccnt;//循环播放次数， 0 表示无限循环播放
    private int result;// 0 成功，其他失败
    private String bcid;//广播组序号
    private int displaynum;//指定设备终端的编号
    private int errorid;//错误ID
    private String rdfile;//文件
    private String uploadaddr;//长传地址
}
