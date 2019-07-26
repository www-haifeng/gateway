package com.shuzhi.entity.command;

import lombok.Data;

/**
 * @Description: 创建目标人参数实体
 * @Author:     lirb
 * @CreateDate:   2019/7/22 16:44
 * @Version:   1.0
 **/
@Data
public class FaceTemplateCreateData {

    /**
     * 图片文件; Base64 编码
     */
    private  String ftImage;

    /**
     * 目标库 ID
     */
    private  String ftdbId;

    /**
     * 目标人姓名
     */
    private  String name ;

    /**
     * 用户标识
     */
    private  String  partnerId;


    /**
     * 用户自定义 ID
     */
    private String customerId;

    /**
     * 用户自定义 Data
     */
    private String customerData;
}
