package com.shuzhi.util;


import com.alibaba.fastjson.JSONObject;
import com.shuzhi.pojo.MessagePojo;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
* @Program: json转bean
* @Description: 
* @Author: YuJQ
* @Create: 2019/6/5 19:26
**/

public class JsonConvertBeanUtil {
	
  public static Object copyProperties(Object obj,Map map){
      try {
          BeanUtils.copyProperties(obj, map);
      } catch (Exception e) {

      }
      return obj;
  }
  //bean 转json
    public static String bean2json(Object object) {
        String jsonObject = JSONObject.toJSONString(object);
        return jsonObject;
    }
    //json 转 bean
    public static Object json2Object(String json, Class beanClz) {
        return JSONObject.parseObject(json, beanClz);
    }

//    public static void main(String[] args) {
//    String json = "{"+
//            "	\"msgid\": \"550e8400-e29b-41d4-a716-446655440000\","+
//            "	\"msgtype\": 1,"+
//            "	\"systype\": 1001,"+
//            "	\"sysid\": 1,"+
//            "	\"connectid\": 1,"+
//            "	\"sign\": \"4634e0d2f0b2b423936eb7651eacc54b98cb248f\","+
//            "	\"msgts\": \"2015-03-05 17:59:00.567\","+
//            "	\"msg\": {"+
//            "		\"overtime\": 5,"+
//            "		\"type\": 12,"+
//            "		\"subtype\": 10,"+
//            "		\"did\": \"867725032979092\","+
//            "		\"cmdid \": \"100001\","+
//            "		\"data\": {}"+
//            "	}"+
//            "}";
//        MessagePojo bean = (MessagePojo) json2Object(json,MessagePojo.class);
//        System.out.println(bean);
//        JsonConvertBeanUtil.copyProperties(bean,bean.getMsg());
//
//        System.out.println(bean);
//
//
//      System.out.println();
//    }
}
