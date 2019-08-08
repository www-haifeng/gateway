package com.shuzhi.entity.report;

import com.shuzhi.entity.MQMsg;
import lombok.Data;

/**
 * @Description: java类作用描述
 * @Author: lirb
 * @CreateDate: 2019/8/6 13:43
 * @Version: 1.0
 **/
@Data
public class CapFaceInfoData {

    private String personId;
    private String fcapId;
    private String fcapDcid;
    private long fcapTime;
    private int fcapQuality;
    private com.google.protobuf.ByteString fcapObjImg;
    private com.google.protobuf.ByteString fcapObjFeat;
    private com.google.protobuf.ByteString fcapSceneImg;
    private int fcapFaceX;
    private int fcapFaceY;
    private int fcapFaceCx;
    private int fcapFaceCy;
    private int fcapSex;
    private int fcapAge;
    private int fcapYaw;
    private int fcapPitch;
    private int fcapRoll;
    private int fcapLeftEyeX;
    private int fcapLeftEyeY;
    private int fcapRightEyeX;
    private int fcapRightEyeY;
    private int fcapMouthX;
    private int fcapMouthY;
    private int fcapNoseX;
    private int fcapNoseY;
    private double confidence;
    private String fcapCacheimageid;

    public CapFaceInfoData(MQMsg.pbCapFaceInfoEx capFaceInfoEx) {
        personId = capFaceInfoEx.getPersonId();
        fcapId = capFaceInfoEx.getFcapId();
        fcapDcid = capFaceInfoEx.getFcapDcid();
        fcapTime = capFaceInfoEx.getFcapTime();
        fcapQuality = capFaceInfoEx.getFcapQuality();
        fcapObjImg = capFaceInfoEx.getFcapObjImg();
        fcapObjFeat = capFaceInfoEx.getFcapObjFeat();
        fcapSceneImg = capFaceInfoEx.getFcapSceneImg();
        fcapFaceX = capFaceInfoEx.getFcapFaceX();
        fcapFaceY = capFaceInfoEx.getFcapFaceY();
        fcapFaceCx = capFaceInfoEx.getFcapFaceCx();
        fcapFaceCy = capFaceInfoEx.getFcapFaceCy();
        fcapSex = capFaceInfoEx.getFcapSex();
        fcapAge = capFaceInfoEx.getFcapAge();
        fcapYaw = capFaceInfoEx.getFcapYaw();
        fcapPitch = capFaceInfoEx.getFcapPitch();
        fcapRoll = capFaceInfoEx.getFcapRoll();
        fcapLeftEyeX = capFaceInfoEx.getFcapLeftEyeX();
        fcapLeftEyeY = capFaceInfoEx.getFcapLeftEyeY();
        fcapRightEyeX = capFaceInfoEx.getFcapRightEyeX();
        fcapRightEyeY = capFaceInfoEx.getFcapRightEyeY();
        fcapMouthX = capFaceInfoEx.getFcapMouthX();
        fcapMouthY = capFaceInfoEx.getFcapMouthY();
        fcapNoseX = capFaceInfoEx.getFcapNoseX();
        fcapNoseY = capFaceInfoEx.getFcapNoseY();
        confidence = capFaceInfoEx.getConfidence();
        fcapCacheimageid = capFaceInfoEx.getFcapCacheimageid();

    }

}
