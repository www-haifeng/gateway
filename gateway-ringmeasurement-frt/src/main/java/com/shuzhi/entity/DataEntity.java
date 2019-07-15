package com.shuzhi.entity;

/**
 * 设备返回数据实体类封装
 */
public class DataEntity {


    /**
     * 设备id
     */
    private  int deviceId;

    /**
     * 环温
     */
    private  double ringTemperature;
    /**
     * 环湿
     */
    private  double ringWetting;
    /**
     * 气压
     */
    private  double pressure;
    /**
     * 风向
     */
    private  double windDirection;
    /**
     * 风速
     */
    private  double windSpeed;
    /**
     * 雨量
     */
    private  double rainfall;
    /**
     * PM2.5
     */
    private  double pmTwoPointFive;
    /**
     * PM10
     */
    private  double pmTen;
    /**
     * 噪声
     */
    private  double noise;

    public DataEntity(int deviceId, double ringTemperature, double ringWetting, double pressure, double windDirection, double windSpeed, double rainfall, double pmTwoPointFive, double pmTen, double noise) {
        this.deviceId = deviceId;
        this.ringTemperature = ringTemperature;
        this.ringWetting = ringWetting;
        this.pressure = pressure;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
        this.rainfall = rainfall;
        this.pmTwoPointFive = pmTwoPointFive;
        this.pmTen = pmTen;
        this.noise = noise;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public double getRingTemperature() {
        return ringTemperature;
    }

    public void setRingTemperature(double ringTemperature) {
        this.ringTemperature = ringTemperature;
    }

    public double getRingWetting() {
        return ringWetting;
    }

    public void setRingWetting(double ringWetting) {
        this.ringWetting = ringWetting;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(double windDirection) {
        this.windDirection = windDirection;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getRainfall() {
        return rainfall;
    }

    public void setRainfall(double rainfall) {
        this.rainfall = rainfall;
    }

    public double getPmTwoPointFive() {
        return pmTwoPointFive;
    }

    public void setPmTwoPointFive(double pmTwoPointFive) {
        this.pmTwoPointFive = pmTwoPointFive;
    }

    public double getPmTen() {
        return pmTen;
    }

    public void setPmTen(double pmTen) {
        this.pmTen = pmTen;
    }

    public double getNoise() {
        return noise;
    }

    public void setNoise(double noise) {
        this.noise = noise;
    }

    @Override
    public String toString() {
        return "DataEntity{" +
                "deviceId=" + deviceId +
                ", ringTemperature=" + ringTemperature +
                ", ringWetting=" + ringWetting +
                ", pressure=" + pressure +
                ", windDirection=" + windDirection +
                ", windSpeed=" + windSpeed +
                ", rainfall=" + rainfall +
                ", pmTwoPointFive=" + pmTwoPointFive +
                ", pmTen=" + pmTen +
                ", noise=" + noise +
                '}';
    }
}
