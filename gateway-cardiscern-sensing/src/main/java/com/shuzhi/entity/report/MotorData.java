package com.shuzhi.entity.report;

import com.shuzhi.entity.KafkaCapMsgM.pbcapturemsg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 机动车参数封装
 * @Author: lirb
 * @CreateDate: 2019/7/31 9:42
 * @Version: 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MotorData {

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
     * 车牌号码
     */
    private String plateNo;
    /**
     * 车辆颜色
     */
    private int vehicleColor;
    /**
     * 朝向
     */
    private int orientation;
    /**
     * 车牌颜色
     */
    private int plateColor;
    /**
     * 车辆类型
     */
    private int vehicleClass;
    /**
     * 车牌类型
     */
    private int plateClass;
    /**
     * 品牌
     */
    private String vehicleBrandTag;
    /**
     * 子品牌
     */
    private String vehicleModelTag;
    /**
     * 车辆年款
     */
    private String vehicleStylesTag;
    /**
     * 年检标
     */
    private int vehicleMarkerMot;
    /**
     * 纸巾盒
     */
    private int vehicleMarkerTissuebox;
    /**
     * 遮阳板
     */
    private int vehicleMarkerPendant;
    /**
     * 遮阳板
     */
    private int sunvisor;
    /**
     * 主驾驶系安全带
     */
    private int safetyBelt;
    /**
     * 副驾驶系安全带
     */
    private int safetyBeltCopilot;
    /**
     * 主驾驶打手机
     */
    private int calling;
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


    public MotorData(pbcapturemsg pbcm) {
        this.uuid = pbcm.getUuid();
        this.deviceId = pbcm.getDeviceId();
        this.capTime = pbcm.getCapTime();
        this.frameTime = pbcm.getFrameTime();
        this.plateNo = pbcm.getPlateNo();
        this.vehicleColor = pbcm.getVehicleColor();
        this.orientation = pbcm.getOrientation();
        this.plateColor = pbcm.getPlateColor();
        this.vehicleClass = pbcm.getVehicleClass();
        this.plateClass = pbcm.getPlateClass();
        this.vehicleBrandTag = pbcm.getVehicleBrandTag();
        this.vehicleModelTag = pbcm.getVehicleModelTag();
        this.vehicleStylesTag = pbcm.getVehicleStylesTag();
        this.vehicleMarkerMot = pbcm.getVehicleMarkerMot();
        this.vehicleMarkerTissuebox = pbcm.getVehicleMarkerTissuebox();
        this.vehicleMarkerPendant = pbcm.getVehicleMarkerPendant();
        this.sunvisor = pbcm.getSunvisor();
        this.safetyBelt = pbcm.getSafetyBelt();
        this.safetyBeltCopilot = pbcm.getSafetyBeltCopilot();
        this.calling = pbcm.getCalling();
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
        sb.append(",\"plateNo\":")
                .append(plateNo);
        sb.append(",\"vehicleColor\":")
                .append(vehicleColor);
        sb.append(",\"orientation\":")
                .append(orientation);
        sb.append(",\"plateColor\":")
                .append(plateColor);
        sb.append(",\"vehicleClass\":")
                .append(vehicleClass);
        sb.append(",\"plateClass\":")
                .append(plateClass);
        sb.append(",\"vehicleBrandTag\":")
                .append(vehicleBrandTag);
        sb.append(",\"vehicleModelTag\":")
                .append(vehicleModelTag);
        sb.append(",\"vehicleStylesTag\":")
                .append(vehicleStylesTag);
        sb.append(",\"vehicleMarkerMot\":")
                .append(vehicleMarkerMot);
        sb.append(",\"vehicleMarkerTissuebox\":")
                .append(vehicleMarkerTissuebox);
        sb.append(",\"vehicleMarkerPendant\":")
                .append(vehicleMarkerPendant);
        sb.append(",\"sunvisor\":")
                .append(sunvisor);
        sb.append(",\"safetyBelt\":")
                .append(safetyBelt);
        sb.append(",\"safetyBeltCopilot\":")
                .append(safetyBeltCopilot);
        sb.append(",\"calling\":")
                .append(calling);
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
