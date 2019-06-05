package com.shuzhi.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * @类功能说明： String字符串工具类.
 */
public final class StringUtil {



    /**
     * 私有构造方法,将该工具类设为单例模式.
     */
    private StringUtil() {
    }
	/**
	 * @Description:  拼接socketurl
	 * @Param :
	 * @Author: YuJQ
	 * @Date : 2019/6/5 11:23
	 */
	public static String webSocketUrl(String ip,Integer port,String socketName){
		return "ws://"+ip+":"+port+"/"+socketName;
	}
    /**
     * 函数功能说明 ： 判断字符串是否为空 . 修改者名字： 修改日期： 修改内容：
     * 
     * @参数： @param str
     * @参数： @return
     * @return boolean
     * @throws
     */
    public static boolean isEmpty(String str) {
        return null == str || "".equals(str);
    }

    /**
     * 函数功能说明 ： 判断对象数组是否为空. 修改者名字： 修改日期： 修改内容：
     * 
     * @参数： @param obj
     * @参数： @return
     * @return boolean
     * @throws
     */
    public static boolean isEmpty(Object[] obj) {
        return null == obj || 0 == obj.length;
    }

    /**
     * 函数功能说明 ： 判断对象是否为空. 修改者名字： 修改日期： 修改内容：
     * 
     * @参数： @param obj
     * @参数： @return
     * @return boolean
     * @throws
     */
    public static boolean isEmpty(Object obj) {
        if (null == obj) {
            return true;
        }
        if (obj instanceof String) {
            return ((String) obj).trim().isEmpty();
        }
        return !(obj instanceof Number) ? false : false;
    }

    /**
     * 函数功能说明 ： 判断集合是否为空. 修改者名字： 修改日期： 修改内容：
     * 
     * @参数： @param obj
     * @参数： @return
     * @return boolean
     * @throws
     */
    public static boolean isEmpty(List<?> obj) {
        return null == obj || obj.isEmpty();
    }

    /**
     * 函数功能说明 ： 判断Map集合是否为空. 修改者名字： 修改日期： 修改内容：
     * 
     * @参数： @param obj
     * @参数： @return
     * @return boolean
     * @throws
     */
    public static boolean isEmpty(Map<?, ?> obj) {
        return null == obj || obj.isEmpty();
    }

    /**
     * 函数功能说明 ： 获得文件名的后缀名. 修改者名字： 修改日期： 修改内容：
     * 
     * @参数： @param fileName
     * @参数： @return
     * @return String
     * @throws
     */
    public static String getExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * 获取去掉横线的长度为32的UUID串.
     * 
     * @author WuShuicheng.
     * @return uuid.
     */
    public static String get32UUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获取带横线的长度为36的UUID串.
     * 
     * @author WuShuicheng.
     * @return uuid.
     */
    public static String get36UUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 验证一个字符串是否完全由纯数字组成的字符串，当字符串为空时也返回false.
     * 
     * @author WuShuicheng .
     * @param str
     *            要判断的字符串 .
     * @return true or false .
     */
    public static boolean isNumeric(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        } else {
            return str.matches("\\d*");
        }
    }



    /**
     * 函数功能说明 ： 截取字符串拼接in查询参数. 修改者名字： 修改日期： 修改内容：
     * 
     * @参数： @param ids
     * @参数： @return
     * @return String
     * @throws
     */
    public static List<String> getInParam(String param) {
        boolean flag = param.contains(",");
        List<String> list = new ArrayList<String>();
        if (flag) {
            list = Arrays.asList(param.split(","));
        } else {
            list.add(param);
        }
        return list;
    }
    
    /*
     * 字符串列表转化为字符串
     */
	public static String toString(List<String> strList) {
		StringBuffer sb = new StringBuffer();
		if (strList == null || strList.size() == 0)
			return sb.toString();
		for (int i=0;i<strList.size();i++){
			String str = (String)strList.get(i);
			sb.append("'").append(str).append("'").append(",");
		}
		return sb.substring(0, sb.length() - 1);
	}
    /*
     * 转化为整数
     */
	public static int toInt(String srcStr, int defaultValue) {
		if (isEmpty(srcStr)) return defaultValue;
		int result = defaultValue;
		try {
			result = new Integer(srcStr).intValue();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static int toInt(String srcStr) {
		return toInt(srcStr, 0);
	}
    /*
     * 是否是数字
     */
	public static boolean isNumber(String str) {
		if (isEmpty(str)) return false;
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (!Character.isDigit(ch))
				return false;
		}
		return true;
	}
    /*
     * 转化为double
     */
	public static double toDouble(String srcStr, double defaultValue) {
		if (isEmpty(srcStr))
			return defaultValue;
		double result = defaultValue;
		try {
			result = new Double(srcStr).doubleValue();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static double toDouble(String srcStr) {
		return toDouble(srcStr, 0.0D);
	}
    /*
     * 转化为long
     */
	public static long toLong(String srcStr, long defaultValue) {
		if (isEmpty(srcStr))
			return defaultValue;
		long result = defaultValue;
		try {
			result = new Long(srcStr).longValue();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static long toLong(String srcStr) {
		return toLong(srcStr, 0L);
	}
    /*
     * 转化为boolean
     */
	public static boolean toBoolean(String srcStr, boolean defaultValue) {
		if (isEmpty(srcStr)) return defaultValue;
		boolean result = defaultValue;
		if ("true".equalsIgnoreCase(srcStr.trim()) || "1".equals(srcStr.trim())){
			result = true;
		}else{
			if ("false".equalsIgnoreCase(srcStr.trim()) || "0".equals(srcStr.trim())){
				result = false;
			}else{
				
			}
		}
		return result;
	}

	public static boolean toBoolean(String srcStr) {
		return toBoolean(srcStr, false);
	}
    /*
     * 格式化数字
     */
	public static String formatNumber(double db, String fmt) {
		String result = "";
		if (null == fmt || "".equals(fmt.trim()))
			return Double.toString(db);
		try {
			DecimalFormat decimalformat = new DecimalFormat(fmt);
			result = decimalformat.format(db);
			decimalformat = null;
		} catch (IllegalArgumentException iaex) {
			result = Double.toString(db);
		}
		return result;
	}

	public static String formatNumber(double db) {
		return formatNumber(db, "0.00");
	}

	public static String formatNumber(String numStr, String fmt) {
		double db = toDouble(numStr, 0.0D);
		return formatNumber(db, fmt);
	}

	public static String formatNumber(String numStr) {
		return formatNumber(numStr, "0.00");
	}

	public static String htmlEncode(String str) {
		String result = null;
		if (null == str || "".equals(str.trim())) {
			result = str;
		} else {
			result = result.replace("&", "&amp;");
			result = result.replace("<", "&lt;");
			result = result.replace(">", "&gt;");
			result = result.replace("\r\n", "\n");
			result = result.replace("\n", "<br>");
			result = result.replace("\t", "    ");
			result = result.replace(" ", "&nbsp;");
			result = result.replace("\"", "&quot;");
		}
		return result;
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static String toVisualHtmlString(String srcStr) {
		if (srcStr == null || srcStr.equals("")){
			return "&nbsp;";
		}else{
			return srcStr;
		}
	}
    
	public static String toVisualString(String srcStr) {
		if (srcStr == null || srcStr.equals(""))
			return "";
		else
			return srcStr;
	}
	
	public static List<String> split(String str,String split){
		ArrayList<String> list=new ArrayList<String>();
		String[] strs=str.split(split);
		for (String val:strs){
			list.add(val);
		}
		return list;
	}
	
	 /**
     * 将下划线风格替换为驼峰风格
     */
    public static String toCamelhump(String str) {
        Matcher matcher = Pattern.compile("_[a-z]").matcher(str);
        StringBuilder builder = new StringBuilder(str);
        for (int i = 0; matcher.find(); i++) {
            builder.replace(matcher.start() - i, matcher.end() - i, matcher.group().substring(1).toUpperCase());
        }
        if (Character.isUpperCase(builder.charAt(0))) {
            builder.replace(0, 1, String.valueOf(Character.toLowerCase(builder.charAt(0))));
        }
        return builder.toString();
    }
    
    /**
     * 将驼峰风格替换为下划线风格
     */
    public static String unCamelhump(String str) {
        final int size;
        final char[] chars;
        final StringBuilder sb = new StringBuilder(
                (size = (chars = str.toCharArray()).length) * 3 / 2 + 1);
        char c;
        for (int i = 0; i < size; i++) {
            c = chars[i];
            if (isUppercase(c)) {
                sb.append('_').append(toLowerAscii(c));
            } else {
                sb.append(toUpperAscii(c));
            }
        }
        return sb.charAt(0) == '_' ? sb.substring(1) : sb.toString();
    }
    
    private static boolean isUppercase(char c) {
        return (c >= 'A') && (c <= 'Z');
    }

    private static boolean isLowercase(char c) {
        return (c >= 'a') && (c <= 'z');
    }

    private static char toUpperAscii(char c) {
        if (isLowercase(c)) {
            c -= (char) 0x20;
        }
        return c;
    }

    private static char toLowerAscii(char c) {
        if (isUppercase(c)) {
            c += (char) 0x20;
        }
        return c;
    }

    public static List<String> getCommonKey(String carType){
    	List<String> list = new ArrayList<String>();
    	list.add(carType);
    	boolean flag = true;
    	if(StringUtils.isNotBlank(carType)){
    		while(flag){
    			if(carType.length()>3){
    				carType =carType.substring(0, carType.length()-3);
    				list.add(carType);
    			}else{
    				flag=false;
    			}
    		}
    	}
    	return list;
    }
    /**
     * 深度复制
     * @param o
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object deepCopy(Object o) {
    	try {
//		//先序列化，写入到流里
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(o);
		//然后反序列化，从流里读取出来，即完成复制
		ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
		ObjectInputStream oi = new ObjectInputStream(bi);
		return oi.readObject();
    	} catch (Exception e) {

    		e.printStackTrace();
    		return null;
    	}
    }

	/**
	 * 驼峰转下划线
	 * @param str
	 * @return
	 */
	public static StringBuffer underline(StringBuffer str) {
		Pattern pattern = Pattern.compile("[A-Z]");
		Matcher matcher = pattern.matcher(str);
		StringBuffer sb = new StringBuffer(str);
		if (matcher.find()) {
			sb = new StringBuffer();
			//将当前匹配子串替换为指定字符串，并且将替换后的子串以及其之前到上次匹配子串之后的字符串段添加到一个StringBuffer对象里。
			//正则之前的字符和被替换的字符
			matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
			//把之后的也添加到StringBuffer对象里
			matcher.appendTail(sb);
		} else {
			return sb;
		}
		return underline(sb);
	}

}
