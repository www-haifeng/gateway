package com.shuzhi.common;

/**
 * @Description: 返回数据编码格式处理工具
 * @Author: YHF
 * @date 2019/6/11
 */
public class EncodingUtil {
    public static String getEncoding(String str) {
        String encode;

        encode = "UTF-16";
        try{
            if(str.equals(new String(str.getBytes(), encode))) {
                return encode;
            }
        }catch(Exception ex) {

        }

        encode = "ASCII";
        try{
            if(str.equals(new String(str.getBytes(), encode))){
                return "未识别编码格式";
            }
        }
        catch(Exception ex) {

        }

        encode = "ISO-8859-1";
        try{
            if(str.equals(new String(str.getBytes(), encode))){
                return encode;
            }
        }catch(Exception ex) {

        }

        encode = "GB2312";
        try{
            if(str.equals(new String(str.getBytes(), encode))){
                return encode;
            }
        }catch(Exception ex) {}

        encode = "UTF-8";
        try{
            if(str.equals(new String(str.getBytes(), encode))){
                return encode;
            }
        }
        catch(Exception ex) {}

        return "未识别编码格式";
    }

}
