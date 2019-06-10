package com.shuzhi.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
* @Program: 读取配置文件 转换成json
* @Description: 
* @Author: YuJQ
* @Create: 2019/6/10 11:18
**/
public class MessageJson  {

   private static   JSONObject jsonObject = null;
    static{
        ClassPathResource resource = new ClassPathResource("messageModel.json");
        try {
            File filePath = resource.getFile();
            String input = FileUtils.readFileToString(filePath, "UTF-8");
            jsonObject = JSON.parseObject(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取json
    public static JSONObject getJsonObject(){

        return jsonObject;
    }
    //获取map
    public static Map<String,Object> getJsonMap(){

        return jsonObject;
    }

    //获取map by key
    public static Map<String,Object> getJsonMap(String key){
            return (Map<String, Object>) jsonObject.get(key);

    }

    //Map 转 Json 字符串
    public static String  getJsonMap(Map<String, Object> map){
        JSONObject json = new JSONObject(map);
        return json.toJSONString();
    }


    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        map.put("age", 24);
        map.put("name", "cool_summer_moon");
        getJsonMap(map);
    }
}
