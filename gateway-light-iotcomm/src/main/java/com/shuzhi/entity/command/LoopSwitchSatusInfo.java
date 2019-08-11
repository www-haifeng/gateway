package com.shuzhi.entity.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 集中器下回路开关状态 明细
 * @Author:     lirb
 * @CreateDate:   2019/8/11 14:51
 * @Version:   1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoopSwitchSatusInfo {

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
     * 回路是否有电
     */
    private String powerStatus;
    /**
     * 开关状态（0-未知/1-打开/2-关闭）
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
