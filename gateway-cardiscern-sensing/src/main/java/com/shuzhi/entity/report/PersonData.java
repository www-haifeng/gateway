package com.shuzhi.entity.report;

import com.shuzhi.entity.KafkaCapMsgM.pbcapturemsg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 人脸参数封装
 * @Author: lirb
 * @CreateDate: 2019/7/31 9:21
 * @Version: 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonData {

    /**
     * uuid
     */
    private String uuid;

    /**
     * 通道uuid
     */
    private String deviceId;
    /**
     * 抓拍时间
     */
    private long capTime;
    /**
     * 抓拍帧数
     */
    private int frameTime;
    /**
     * 年龄
     */
    private int age;
    /**
     * 性别
     */
    private int genderCode;
    /**
     * 携带物背包
     */
    private int bagStyle;
    /**
     * 携带物手提物
     */
    private int bigBagStyle;
    /**
     * 朝向
     */
    private int orientation;
    /**
     * 运动状态
     */
    private int motion;
    /**
     * 帽子
     */
    private int cap;
    /**
     * 口罩
     */
    private int respirator;
    /**
     * 眼镜
     */
    private int glass;
    /**
     * 上身颜色
     */
    private int coatColor;
    /**
     * 上身类型
     */
    private int coatLength;
    /**
     * 上身纹理
     */
    private int coatTexture;
    /**
     * 下身颜色
     */
    private int trousersColor;
    /**
     * 下身类型
     */
    private int trousersLen;
    /**
     * 下身纹理
     */
    private int trousersTexture;
    /**
     * 抓拍图位置
     */
    private String capLocation;
    /**
     * 抓拍图地址
     */

    private String capUrl;
    /**
     * 场景图地址
     */
    private String seceneUrl;

    public PersonData(pbcapturemsg pbcm){
        this.uuid = pbcm.getUuid();
        this.deviceId = pbcm.getDeviceId();
        this.capTime = pbcm.getCapTime();
        this.frameTime = pbcm.getFrameTime();
        this.age = pbcm.getAge();
        this.genderCode = pbcm.getGenderCode();
        this.bagStyle = pbcm.getBagStyle();
        this.bigBagStyle = pbcm.getBigBagStyle();
        this.orientation = pbcm.getOrientation();
        this.motion = pbcm.getMotion();
        this.cap = pbcm.getCap();
        this.respirator = pbcm.getRespirator();
        this.glass = pbcm.getGlass();
        this.coatColor = pbcm.getCoatColor();
        this.coatLength = pbcm.getCoatLength();
        this.coatTexture = pbcm.getCoatTexture();
        this.trousersColor = pbcm.getTrousersColor();
        this.trousersLen = pbcm.getTrousersLen();
        this.trousersTexture = pbcm.getTrousersTexture();
        this.capLocation = pbcm.getCapLocation();
        this.capUrl = pbcm.getCapUrl();
        this.seceneUrl = pbcm.getSeceneUrl();

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"uuid\":\"")
                .append(uuid).append('\"');
        sb.append(",\"deviceId\":")
                .append(deviceId);
        sb.append(",\"capTime\":")
                .append(capTime);
        sb.append(",\"frameTime\":")
                .append(frameTime);
        sb.append(",\"age\":")
                .append(age);
        sb.append(",\"genderCode\":")
                .append(genderCode);
        sb.append(",\"bagStyle\":")
                .append(bagStyle);
        sb.append(",\"bigBagStyle\":")
                .append(bigBagStyle);
        sb.append(",\"orientation\":")
                .append(orientation);
        sb.append(",\"motion\":")
                .append(motion);
        sb.append(",\"cap\":")
                .append(cap);
        sb.append(",\"respirator\":")
                .append(respirator);
        sb.append(",\"glass\":")
                .append(glass);
        sb.append(",\"coatColor\":")
                .append(coatColor);
        sb.append(",\"coatLength\":")
                .append(coatLength);
        sb.append(",\"coatTexture\":")
                .append(coatTexture);
        sb.append(",\"trousersColor\":")
                .append(trousersColor);
        sb.append(",\"trousersLen\":")
                .append(trousersLen);
        sb.append(",\"trousersTexture\":")
                .append(trousersTexture);
        sb.append(",\"capLocation\":")
                .append(capLocation);
        sb.append(",\"capUrl\":")
                .append(capUrl);
        sb.append(",\"seceneUrl\":")
                .append(seceneUrl);
        sb.append('}');
        return sb.toString();
    }
}
