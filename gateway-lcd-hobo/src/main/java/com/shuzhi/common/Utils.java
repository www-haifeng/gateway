package com.shuzhi.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shuzhi.cache.Cache;
import com.shuzhi.entity.MessageRevertData;
import com.shuzhi.entity.SystemInfoData;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;

import static io.lettuce.core.MigrateArgs.Builder.replace;

@Component
@EnableConfigurationProperties(ConfigData.class)
public class Utils {
    @Autowired
    ConfigData configData;
    //十六进制下数字到字符的映射数组
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

    private static final String KEY_SHA = "SHA";

    private final static Logger logger = LoggerFactory.getLogger(Utils.class);

    /**
     * @Description:封装命令回执message层
     * @Author: YHF
     * @date 2019/6/6
     */
    public MessageRevertData getMessageRevertData(String requestUrl, int code, String timestamp, String data) {
        MessageRevertData mrd = new MessageRevertData();
        mrd.setCode(code);
        mrd.setTimestamp(timestamp);
        try {
            JSONArray objects = JSONArray.parseArray(data);
            JSONObject jsonObject = objects.getJSONObject(0);
            //如果是查询终端相关接口，并且结果集有数据 ，将 设备id转换成 平台id
            if (requestUrl.contains("/vs/api/apiselclient.vs") ||
                    requestUrl.contains("/vs/api/apiselzxclient.vs") ||
                    requestUrl.contains("/vs/api/apisellxclient.vs")) {

                String clientlistStr = JSON.toJSONString(jsonObject.get("clientlist"));
                List<Map<String, Object>> clientList = (List<Map<String, Object>>) JSONArray.parse(clientlistStr);
                if (CollectionUtils.isNotEmpty(clientList)) {
                    for (Map<String, Object> datamap : clientList) {
                        if (datamap.containsKey("id")) {
                            String did = this.deviceIdToDid(datamap.get("id").toString());
                            datamap.put("id", did);
                        }
                    }
                    jsonObject.put("clientlist", clientList);
                    mrd.setData(jsonObject.toJSONString());
                }
            } else {
                mrd.setData(jsonObject.toJSONString());
            }

        } catch (Exception e) {
            logger.error("命令执行返回结果集解析失败");
        }
        return mrd;
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

    /**
     * 平台id 转换为设备id
     *
     * @param did : 平台id
     * @return
     */
    public String didToDeviceId(String did) {

        if (did.contains("_")) {
            StringBuffer stringBuffer = new StringBuffer();
            String[] dids = did.split("_");
            for (String d : dids) {
                String deviceId = Cache.deviceInfoMap.get(d);
                stringBuffer.append(deviceId);
                stringBuffer.append("_");
            }
            String deviceIds = stringBuffer.toString();
            return deviceIds.substring(0, deviceIds.length() - 1);
        }
        return Cache.deviceInfoMap.get(did);
    }

    /**
     * 设备id转换为平台id
     *
     * @param deviceId : 设备id
     * @return
     */
    public String deviceIdToDid(String deviceId) {

        Map<String, String> deviceInfoMap = Cache.deviceInfoMap;
        for (String did : deviceInfoMap.keySet()) {
            if (deviceInfoMap.get(did).equals(deviceId)) {
                return did;
            }
        }

        return null;
    }
}
