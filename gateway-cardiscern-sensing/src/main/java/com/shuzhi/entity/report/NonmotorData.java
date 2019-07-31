package com.shuzhi.entity.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.shuzhi.entity.KafkaCapMsgM.pbcapturemsg;

/**
 * @Description: 非机动车参数封装
 * @Author: lirb
 * @CreateDate: 2019/7/31 9:35
 * @Version: 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NonmotorData {

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
     * 朝向
     */
    private int orientation;
    /**
     * 车辆颜色
     */
    private int vehicleColor;
    /**
     * 车辆类型
     */
    private int vehicleClass;
    /**
     * 运动状态
     */
    private int motion;
    /**
     * 性别
     */
    private int genderCode;
    /**
     * 年龄
     */
    private int age;
    /**
     * 帽子
     */
    private int cap;
    /**
     * 眼镜
     */
    private int glass;
    /**
     * 口罩
     */
    private int respirator;
    /**
     * 上身颜色
     */
    private int coatColor;
    /**
     * 上身纹理
     */
    private int coatTexture;
    /**
     * 上身类型
     */
    private int coatLength;
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

    public NonmotorData(pbcapturemsg pbcm) {
        this.uuid = pbcm.getUuid();
        this.deviceId = pbcm.getDeviceId();
        this.capTime = pbcm.getCapTime();
        this.frameTime = pbcm.getFrameTime();
        this.orientation = pbcm.getOrientation();
        this.vehicleColor = pbcm.getVehicleColor();
        this.vehicleClass = pbcm.getVehicleClass();
        this.motion = pbcm.getMotion();
        this.genderCode = pbcm.getGenderCode();
        this.age = pbcm.getAge();
        this.cap = pbcm.getCap();
        this.glass = pbcm.getGlass();
        this.respirator = pbcm.getRespirator();
        this.coatColor = pbcm.getCoatColor();
        this.coatTexture = pbcm.getCoatTexture();
        this.coatLength = pbcm.getCoatLength();
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
        sb.append(",\"orientation\":")
                .append(orientation);
        sb.append(",\"vehicleColor\":")
                .append(vehicleColor);
        sb.append(",\"vehicleClass\":")
                .append(vehicleClass);
        sb.append(",\"motion\":")
                .append(motion);
        sb.append(",\"genderCode\":")
                .append(genderCode);
        sb.append(",\"age\":")
                .append(age);
        sb.append(",\"cap\":")
                .append(cap);
        sb.append(",\"glass\":")
                .append(glass);
        sb.append(",\"respirator\":")
                .append(respirator);
        sb.append(",\"coatColor\":")
                .append(coatColor);
        sb.append(",\"coatTexture\":")
                .append(coatTexture);
        sb.append(",\"coatLength\":")
                .append(coatLength);
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
