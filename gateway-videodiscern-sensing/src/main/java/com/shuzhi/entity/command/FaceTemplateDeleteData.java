package com.shuzhi.entity.command;

import lombok.Data;
import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * @Description: 删除目标人请求参数实体
 * @Author:     lirb
 * @CreateDate:   2019/7/22 16:55
 * @Version:   1.0
 **/
@Data
public class FaceTemplateDeleteData {

    /**
     * 目标人id
     */
    private  String uuid;

    /**
     * 标识用户的身份
     */
    private String partnerId;
}
