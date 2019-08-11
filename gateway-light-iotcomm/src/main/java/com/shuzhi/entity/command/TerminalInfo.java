package com.shuzhi.entity.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 集中器下终端明细
 * @Author: lirb
 * @CreateDate: 2019/8/11 12:51
 * @Version: 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TerminalInfo {

    /**
     * 集中器主键标识
     */
    private int ccuID;
    /**
     * 集中器代码标识 UID
     */
    private String ccuUid;
    /**
     * 集中器名称
     */
    private String ccuName;
    /**
     * 终端主键标识
     */
    private int rtuID;
    /**
     * 终端设备标识
     */
    private String rtuUid;
    /**
     * 终端名称
     */
    private String name;
    /**
     * 终端型号标识
     */
    private int modeID;
    /**
     * 终端型号名称
     */
    private String modelName;
    /**
     * 额定功率
     */
    private double ratingPower;
    /**
     * 终端所在灯杆表示
     */
    private int poleID;
    /**
     * 谷歌经度
     */
    private double longitude;
    /**
     * 谷歌纬度
     */
    private double latitude;
    /**
     * 配置状态：Y=已配置（可控），N=未配置（不可控）
     */
    private String result;

}
