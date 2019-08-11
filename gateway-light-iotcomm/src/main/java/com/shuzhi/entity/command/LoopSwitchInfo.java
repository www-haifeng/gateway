package com.shuzhi.entity.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 集中器下回路开关 明细
 * @Author:     lirb
 * @CreateDate:   2019/8/11 14:51
 * @Version:   1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoopSwitchInfo {

    /**
     * 开关主键标识
     */
    private Integer breakerID;
    /**
     * 开关名称
     */
    private String name;
    /**
     * 地址
     */
    private Integer address;
    /**
     * 通道
     */
    private Integer channel;
    /**
     * 开关模块
     */
    private String breakerModuleName;
    /**
     * 开关模块备注
     */
    private String breakerModuleRemark;
    /**
     * 集中器Uid
     */
    private String ccuUid;
    /**
     * 集中器名称
     */
    private String ccuName;
    /**
     * 备注
     */
    private String remark;
    /**
     * 是否启用（Y/N）
     */
    private String enabled;
}
