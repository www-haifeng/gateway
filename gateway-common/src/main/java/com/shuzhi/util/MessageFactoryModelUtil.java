package com.shuzhi.util;

import com.shuzhi.pojo.MessagePojo;

import java.util.Date;

import java.util.HashMap;
import java.util.Map;

/**
* @Program: 消息拼装工厂
* @Description:
* @Author: YuJQ
* @Create: 2019/6/6 14:04
**/
public class MessageFactoryModelUtil {


    //获取json 对象map
    public static Map<String,Object>  messageModel(Integer number) {
        Map<String, Object> stringObjectMap = MessageJson.getJsonMap(ConstantUtils.KYE_MESSAGE);
        stringObjectMap.put(ConstantUtils.KYE_MSG,MessageJson.getJsonMap(number.toString()));
        return stringObjectMap;
          }

    //返回MessagePojo 对象
    public static MessagePojo messagePojoModel(String key,Integer number,String systype,String sysid,String connectid)  {
        MessagePojo  pojo = new MessagePojo();
        Map<String,Object> msg = new HashMap<String,Object>();
        String msgid =ToolUtils.generateUUID();
        String msgtype = String.valueOf(number);
        String msgts =DateUtils.formatTimestamp.format(new Date());
        pojo.setMsgid(msgid);
        pojo.setMsgtype(msgtype);
        pojo.setSystype(systype);
        pojo.setSysid(sysid);
        pojo.setConnectid(connectid);
        pojo.setMsgts(msgts);
      // 0 心跳 5 首次建联 2 回执
        if(ConstantUtils.KYE_0==number || ConstantUtils.KYE_2==number || ConstantUtils.KYE_5==number){
            msg.put(ConstantUtils.KYE_TIME_STAMP,msgts);
            pojo.setMsg(msg);
            //转json 字符串
            String jsonMapString  =   MessageJson.getJsonMap(msg);
            try {
                pojo.setSign(ToolUtils.getBussesSha(msgid+key+ToolUtils.md5Hex(jsonMapString)+msgtype));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return pojo;
    }


}
