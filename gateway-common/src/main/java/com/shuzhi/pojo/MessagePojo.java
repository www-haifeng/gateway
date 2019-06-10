/**
 * 
 */
package com.shuzhi.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
* @Program: 封装json
* @Description: 
* @Author: YuJQ
* @Create: 2019/6/5 19:15
**/
@Data
public  class  MessagePojo implements Serializable{
	//第一层结构
	private String msgid; //报文消息id
	private String msgtype;//消息类型：0:心跳 1：命令 2：回执 3：命令执行结果 4：上报信息 5：首次建立连接 6、建连回复 7、告警信息
	private String systype; //系统类型 应用网关 使能平台
	private String sysid; //系统id
	private String connectid;//链路id
	private String sign; //sign = sha(msgid+key+MD5(msg)+ msgts)
	private String msgts;//时间戳 “2015-03-05 17:59:00.567”
	private Map<String,Object> msg; //报文数据信息

	//第二层结构
	private String timestamp; //"timestamp": "2015-03-05 17:59:00"
	private String code; //建连信息状态码
	private String cycletime; //心跳包上传周期，默认30秒
	private String overtime; //命令下发超时时间
	private String type; //设备类型
	private String subtype;//厂商类型
	private String did; //集中控制器编号
	private String cmdid; //命令id，统一分配
	private String alarmid; //告警id,暂未定义，统一分配

	//第三层结构
	private Map<String,Object> data ;





}
