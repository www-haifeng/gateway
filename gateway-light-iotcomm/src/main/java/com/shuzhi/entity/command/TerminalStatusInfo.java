package com.shuzhi.entity.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 集中器下终端状态 明细
 * @Author: lirb
 * @CreateDate: 2019/8/11 13:55
 * @Version: 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TerminalStatusInfo {

    /**
     * 终端主键标识
     */
    private Integer rtuID;
    /**
     * 终端代码UID
     */
    private String rtuUid;
    /**
     * 终端名称
     */
    private String name;
    /**
     * 终端运行程序版本
     */
    private Integer rtuVersion;
    /**
     * 终端运行当前时间
     */
    private Long rtuTime;
    /**
     * 当前电压
     */
    private String voltage;
    /**
     * 当前电流
     */
    private String current;
    /**
     * 当前功率
     */
    private String power;
    /**
     * 电能读数
     */
    private String energy;
    /**
     * 功率因数
     */
    private String powerFactor;
    /**
     * 漏电电压
     */
    private Integer leakageVoltage;
    /**
     * 通讯状态（Y-在线，N-离线）
     */
    private String comm;
    /**
     * 灯具的状态(1 开灯，2 关灯），双灯的话（1,1 代表都开，2,2 都关，1,2 一开一关，2,1 一开一关）
     */
    private String stat;
    /**
     * 亮灯状态（1-ON，2-OFF）
     */
    private Integer status;
    /**
     * 集中器Uid
     */
    private String ccuUid;
    /**
     * 集中器名称
     */
    private String ccuName;
}
