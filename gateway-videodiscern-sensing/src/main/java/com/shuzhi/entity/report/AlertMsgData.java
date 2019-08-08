package com.shuzhi.entity.report;

import com.shuzhi.entity.AlertMsgBody;
import lombok.Data;

/**
 * @Description: 告警消息参数封装
 * @Author: lirb
 * @CreateDate: 2019/8/6 11:38
 * @Version: 1.0
 **/
@Data
public class AlertMsgData {

    private String alertUuid;
    private String alertAckTime;
    private long icapTime;
    private String capTimeTag;
    private String templateDbName;
    private String objIdNo;
    private int iobjIdType;
    private String objIdTypeTag;
    private String objName;
    private int iobjSex;
    private String objSexTag;
    private String objBirthDataTag;
    private String channelAddr;
    private String channelAreaName;
    private double dcmpSecondSocre;
    private String jobName;
    private String seceneImgUrl;
    private String capImgUrl;
    private int iackStat = 0;
    private String ackStatTag;
    private String capUuid;
    private String dbId;
    private String channelIp;
    private long ialertTime;
    private String objUuid;
    private String cmpUuid;
    private long ialertAppearNumber;
    private String jobUuid;
    private String channelUuid;
    private String lastPersionUuid;
    private String alertDbColor;
    private long ialertAction;
    private double dcmpFirstSocre;
    private long icapLastTime;
    private String alertVulue1;
    private String alertVulue2;
    private String alertVulue3;
    private String alertVulue4;
    private String alertVulue5;
    private String objImgUrl;
    private String channelName;

    public AlertMsgData(AlertMsgBody.pbAlertInfoEx alertInfoEx) {
        this.alertUuid = alertInfoEx.getAlertUuid();
        this.alertAckTime = alertInfoEx.getAlertAckTime();
        this.icapTime = alertInfoEx.getIcapTime();
        this.capTimeTag = alertInfoEx.getCapTimeTag();
        this.templateDbName = alertInfoEx.getTemplateDbName();
        this.objIdNo = alertInfoEx.getObjIdNo();
        this.iobjIdType = alertInfoEx.getIobjIdType();
        this.objIdTypeTag = alertInfoEx.getObjIdTypeTag();
        this.objName = alertInfoEx.getObjName();
        this.iobjSex = alertInfoEx.getIobjSex();
        this.objSexTag = alertInfoEx.getObjSexTag();
        this.objBirthDataTag = alertInfoEx.getObjBirthDataTag();
        this.channelAddr = alertInfoEx.getChannelAddr();
        this.channelAreaName = alertInfoEx.getChannelAreaName();
        this.dcmpSecondSocre = alertInfoEx.getDcmpSecondSocre();
        this.jobName = alertInfoEx.getJobName();
        this.seceneImgUrl = alertInfoEx.getSeceneImgUrl();
        this.capImgUrl = alertInfoEx.getCapImgUrl();
        this.iackStat = alertInfoEx.getIackStat();
        this.ackStatTag = alertInfoEx.getAckStatTag();
        this.capUuid = alertInfoEx.getCapUuid();
        this.dbId = alertInfoEx.getDbId();
        this.channelIp = alertInfoEx.getChannelIp();
        this.ialertTime = alertInfoEx.getIalertTime();
        this.objUuid = alertInfoEx.getObjUuid();
        this.cmpUuid = alertInfoEx.getCmpUuid();
        this.ialertAppearNumber = alertInfoEx.getIalertAppearNumber();
        this.jobUuid = alertInfoEx.getJobUuid();
        this.channelUuid = alertInfoEx.getChannelUuid();
        this.lastPersionUuid = alertInfoEx.getLastPersionUuid();
        this.alertDbColor = alertInfoEx.getAlertDbColor();
        this.ialertAction = alertInfoEx.getIalertAction();
        this.dcmpFirstSocre = alertInfoEx.getDcmpFirstSocre();
        this.icapLastTime = alertInfoEx.getIcapLastTime();
        this.alertVulue1 = alertInfoEx.getAlertVulue1();
        this.alertVulue2 = alertInfoEx.getAlertVulue2();
        this.alertVulue3 = alertInfoEx.getAlertVulue3();
        this.alertVulue4 = alertInfoEx.getAlertVulue4();
        this.alertVulue5 = alertInfoEx.getAlertVulue5();
        this.objImgUrl = alertInfoEx.getObjImgUrl();
        this.channelName = alertInfoEx.getChannelName();
    }
}
