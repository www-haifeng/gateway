package com.shuzhi.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Map;

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
    public static JSONObject getJsonObject(){

        return jsonObject;
    }

    public static Map<String,Object> getJsonMap(){

        return jsonObject;
    }

    /*public static void main(String[] args) {

       Map<String,Object> map =  MessageJson.getJsonMap();
        System.out.println(map.get("4"));
       Map<String,Object> map1 = (Map<String, Object>) map.get("4");
        System.out.println( map1.get("data") instanceof Map);
    }*/
}
