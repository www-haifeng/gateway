package com.shuzhi.common;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shuzhi.entity.MessageRevertData;
import com.shuzhi.entity.SystemInfoData;
import com.shuzhi.entity.command.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 工具类
 * @Author:     lirb
 * @CreateDate:   2019/8/9 11:37
 * @Version:   1.0
 **/
@Component
@EnableConfigurationProperties(ConfigData.class)
public class Utils {
    @Autowired
    ConfigData configData;
    @Autowired
    ObjectMapper om;
    //十六进制下数字到字符的映射数组
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

    private static final String KEY_SHA = "SHA";

    private final static Logger logger = LoggerFactory.getLogger(Utils.class);

    /**
     * @Description:封装命令回执message层
     * @Author: YHF
     * @date 2019/6/6
     */
    public MessageRevertData getMessageRevertData( String url,int code, String timestamp, String data)  {
        MessageRevertData mrd = new MessageRevertData();
        mrd.setCode(code);
        mrd.setTimestamp(timestamp);
        try {

            if (url.contains("/monitor/module/light/equipment/deploy/findCcu.action")) {
                ConcentratorData concentratorData = om.readValue(data, ConcentratorData.class);
                String rowsStr = om.writeValueAsString(concentratorData.getRows());
                JavaType javaType = getCollectionType(ArrayList.class, ConcentratorInfo.class);
                List<ConcentratorInfo> rows= om.readValue(rowsStr, javaType);
                concentratorData.setRows(rows);
                mrd.setData(JSON.toJSONString(concentratorData));
            }else if(url.contains("/monitor/module/light/equipment/deploy/findBreaker.action")){
                ConcentratorData concentratorData = om.readValue(data, ConcentratorData.class);
                String rowsStr = om.writeValueAsString(concentratorData.getRows());
                JavaType javaType = getCollectionType(ArrayList.class, LoopSwitchInfo.class);
                List<LoopSwitchInfo> rows= om.readValue(rowsStr, javaType);
                concentratorData.setRows(rows);
                mrd.setData(JSON.toJSONString(concentratorData));
            }else if(url.contains("/monitor/module/light/equipment/deploy/findRtu.action")){
                ConcentratorData concentratorData = om.readValue(data, ConcentratorData.class);
                String rowsStr = om.writeValueAsString(concentratorData.getRows());
                JavaType javaType = getCollectionType(ArrayList.class, TerminalInfo.class);
                List<TerminalInfo> rows= om.readValue(rowsStr, javaType);
                concentratorData.setRows(rows);
                mrd.setData(JSON.toJSONString(concentratorData));
            }else if(url.contains("/monitor/module/light/monitoring/queryBreakerPageList.action")){
                ConcentratorData concentratorData = om.readValue(data, ConcentratorData.class);
                String rowsStr = om.writeValueAsString(concentratorData.getRows());
                JavaType javaType = getCollectionType(ArrayList.class, LoopSwitchSatusInfo.class);
                List<LoopSwitchSatusInfo> rows= om.readValue(rowsStr, javaType);
                concentratorData.setRows(rows);
                mrd.setData(JSON.toJSONString(concentratorData));
            } else if(url.contains("/monitor/module/light/monitoring/queryRtuPageList.action")){
                ConcentratorData concentratorData = om.readValue(data, ConcentratorData.class);
                String rowsStr = om.writeValueAsString(concentratorData.getRows());
                JavaType javaType = getCollectionType(ArrayList.class, TerminalStatusInfo.class);
                List<TerminalStatusInfo> rows= om.readValue(rowsStr, javaType);
                concentratorData.setRows(rows);
                mrd.setData(JSON.toJSONString(concentratorData));
            }else {
                mrd.setData(data);
            }
        }catch (Exception e){
            logger.error("返回数据序列化异常: "+e.getMessage());
            e.printStackTrace();
        }
        return mrd;
    }


    /**
     * 获取泛型的Collection Type
     * @param collectionClass 泛型的Collection
     * @param elementClasses 元素类
     * @return JavaType Java类型
     * @since 1.0
     */
    public  JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return om.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }


    /**
     * @Description:获取时间戳
     * @Author: YHF
     * @date 2019/6/10
     */
    public String getTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
    }

    /**
     * @Description: sign校验
     * @Author: YHF
     * @date 2019/6/10
     */
    public boolean signVerify(SystemInfoData systemInfoData, JsonNode jsonParentNode) {
        String md5Str = encodeByMD5(jsonParentNode.path("msg").toString());
        String shaStr = addPwd(systemInfoData.getMsgid() + configData.getKey() + md5Str + systemInfoData.getMsgts());
        if (systemInfoData.getSign().equals(shaStr)) {
            return true;
        }
        return false;
    }

    /**
     * 功能描述 生成sign校验
     *
     * @return java.lang.String
     * @author YHF
     * @date 2019/6/10
     * @params [systemInfoData]
     */
    public String getSignVerify(SystemInfoData systemInfoData) {
        String md5Str = encodeByMD5((String) systemInfoData.getMsg());
        String shaStr = addPwd(systemInfoData.getMsgid() + configData.getKey() + md5Str + systemInfoData.getMsgts());
        return shaStr;
    }

    private String addPwd(String inputStr) {
        BigInteger sha = null;
        System.out.println("=======加密前的数据:" + inputStr);
        byte[] inputData = inputStr.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(KEY_SHA);
            messageDigest.update(inputData);
            sha = new BigInteger(messageDigest.digest());
            System.out.println("SHA加密后:" + sha.toString(32));
            return sha.toString(32);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对字符串进行MD5编码
     */
    private String encodeByMD5(String originString) {
        if (originString != null) {
            try {
                //创建具有指定算法名称的信息摘要
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                //使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
                byte[] results = md5.digest(originString.getBytes());
                //将得到的字节数组变成字符串返回
                String result = byteArrayToHexString(results);
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 轮换字节数组为十六进制字符串
     *
     * @param b 字节数组
     * @return 十六进制字符串
     */
    private String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    //将一个字节转化成十六进制形式的字符串
    private String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }


    /**
     * 将map中的数据拼接到url上
     *
     * @param map
     * @return
     */
    public String mapToCommandStr(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> m : map.entrySet()) {
            if (!"".equals(m.getValue()) && "null" != m.getValue() && null != m.getValue()) {
                sb.append("&");
                sb.append(m.getKey());
                sb.append("=");
                sb.append(m.getValue());
            }
        }
        return sb.toString();
    }




}
