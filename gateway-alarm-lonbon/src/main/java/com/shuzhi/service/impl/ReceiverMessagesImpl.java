package com.shuzhi.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shuzhi.cache.Cache;
import com.shuzhi.commen.Utils;
import com.shuzhi.dao.LonBonEventDao;
import com.shuzhi.entity.*;
import com.shuzhi.ftp.FTPProperties;
import com.shuzhi.scheduled.LonBonScheduled;
import com.shuzhi.util.JsonConvertBeanUtil;
import com.shuzhi.util.ToolUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by ztt on 2019/6/19
 **/
@Slf4j
@Service
public class ReceiverMessagesImpl {
    @Resource(name = "LonBon")
    private LonBon lonBon;
    @Autowired
    private Utils utils;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${gateway.key}")
    private String key;
    @Value("${gateway.msgcode}")
    private int msgcode;
    @Value("${gateway.routingKey}")
    private String routingKey;
    @Autowired
    private LonBonEventDao lonBonEventDao;
    @Autowired
    private LonBonScheduled lonBonScheduled;
    @Autowired
    FTPProperties ftpProperties;
    /**
     * 接收指令处理
     */
    public void receiver(JsonNode jsonParentNode, SystemInfoData systemInfoData) {
        try {
            //解析第二层json
            MessageData messageData = mapper.readValue(jsonParentNode.path("msg").toString(), MessageData.class);
            CommandInfo commandInfo = Cache.commandMap.get(messageData.getCmdid());
            String listTxt = JSONArray.toJSONString(messageData.getData());
            LonBonEntity lbe = (LonBonEntity) JsonConvertBeanUtil.json2Object(listTxt, LonBonEntity.class);
            ResultDataEntity resultDataEntity = new ResultDataEntity();
            int rest = -1;

            //匹配命令
            switch (commandInfo.getTmsgInfoEntity().getInterfaceId()) {
                case "lb_answer":
                    // 接听
                    rest = lonBon.lb_answer(lbe.getSvrip(), lbe.getHostnum(), lbe.getTernum());
                    resultDataEntity.setResult(rest);
                    break;
                case "lb_call":
                    // 发起呼叫
                    rest = lonBon.lb_call(lbe.getSvrip(), lbe.getHostnum(), lbe.getTernum());
                    resultDataEntity.setResult(rest);
                    break;
                case "lb_hangUp":
                    // 挂断  @TODO 需要更改 slaveCnt
                    rest = lonBon.lb_hangUp(lbe.getSvrip(), lbe.getHostnum(), lbe.getTernum(), 1);
                    resultDataEntity.setResult(rest);
                    break;
                case "lb_start_broadcast":
                    //开始 广播
                    rest = lonBon.lb_start_broadcast(lbe.getSvrip(), lbe.getHostnum(), lbe.getGroupnum(), lbe.getGroupnum().length);
                    resultDataEntity.setResult(rest);
                    break;
                case "lb_stop_broadcast":
                    // 停止广播
                    rest = lonBon.lb_stop_broadcast(lbe.getSvrip(), lbe.getHostnum());
                    resultDataEntity.setResult(rest);
                    break;
                case "lb_start_broadcast_file":
                    // 开启语音文件广播
                    rest = lonBon.lb_start_broadcast_file(lbe.getSvrip(), lbe.getHostnum(), lbe.getGroupnum(), lbe.getGroupnum().length, lbe.getFilelist(), lbe.getFilelist().length, lbe.getNcyccnt());
                    resultDataEntity.setResult(rest);
                    break;
                case "lb_stop_broadcast_file":
                    // 关闭语音文件广播
                    rest = lonBon.lb_stop_broadcast_file(lbe.getSvrip(), lbe.getHostnum(), lbe.getBcid());
                    resultDataEntity.setResult(rest);
                    break;
                case "lb_getTerminalInfos":
                    // 对讲服务器终端信息
                    List<Terminal> terminalList = new ArrayList<>();
                    rest = lonBon.lb_getTerminalInfos(lbe.getSvrip(), terminalList, 5);
                    resultDataEntity.setResult(rest);
                    resultDataEntity.setTerminalinfos(terminalList);
                    break;
                case "lb_getTerminalInfo":
                    // 指定终端信息
                    Terminal terminal = new Terminal();
                    rest = lonBon.lb_getTerminalInfo(lbe.getSvrip(), lbe.getTernum(), terminal);
                    terminalList = new ArrayList<>();
                    terminalList.add(terminal);
                    resultDataEntity.setResult(rest);
                    resultDataEntity.setTerminalinfos(terminalList);
                    break;
                case "lb_get_all_master":
                    // 查询全部主机
                    int oncount = lonBon.lb_get_all_master_count(lbe.getSvrip());
                    if (oncount > 0) {
                        int[] mstList = new int[oncount];
                        rest = lonBon.lb_get_all_master(lbe.getSvrip(), mstList, oncount);
                        resultDataEntity.setResult(rest > 0 ? 0 : rest);
                        resultDataEntity.setHostnums(mstList);
                    }
                    break;
                case "lb_get_terminal_from_master":
                    // 获取指定主机下所有在线分终端
                    int tocount = lonBon.lb_get_terminal_from_master_count(lbe.getSvrip(), lbe.getHostnum());
                    if (tocount > 0) {
                        int[] ternumsList = new int[tocount];
                        rest = lonBon.lb_get_terminal_from_master(lbe.getSvrip(), lbe.getHostnum(), ternumsList, tocount);
                        resultDataEntity.setResult(rest > 0 ? 0 : rest);
                        resultDataEntity.setHostnums(ternumsList);
                    }
                    break;
                case "lb_get_online_master":
                    // 查询全部在线主机
                    int oncount1 = lonBon.lb_get_online_master_count(lbe.getSvrip());
                    if (oncount1 > 0) {
                        int[] mstList = new int[oncount1];
                        rest = lonBon.lb_get_online_master(lbe.getSvrip(), mstList, oncount1);
                        resultDataEntity.setResult(rest > 0 ? 0 : rest);
                        resultDataEntity.setHostnums(mstList);
                    }
                    break;
                case "lb_get_terminal_online_from_master":
                    // 查询主机下在线分机
                    int oncount2 = lonBon.lb_get_terminal_online_from_master_count(lbe.getSvrip(), lbe.getHostnum());
                    if (oncount2 > 0) {
                        int[] mstList = new int[oncount2];
                        rest = lonBon.lb_get_terminal_online_from_master(lbe.getSvrip(), lbe.getHostnum(), mstList, oncount2);
                        resultDataEntity.setResult(rest > 0 ? 0 : rest);
                        resultDataEntity.setHostnums(mstList);
                    }
                    break;
                case "lb_get_state_from_terminal":
                    // 查询终端在线状态 非 0 表示在线， 0 表示不在线。
                    rest = lonBon.lb_get_state_from_terminal(lbe.getSvrip(), lbe.getDisplaynum());
                    resultDataEntity.setResult(rest == 0 ? -1 : rest);
                    break;
                case "lb_get_error_info":
                    // 获取错误信息
                    byte[] strErr = new byte[256];
                    rest = lonBon.INSTANCE.lb_get_error_info(lbe.getErrorid(), strErr, 256);
                    String errinfo = new String(strErr, "gbk");
                    resultDataEntity.setResult(rest);
                    resultDataEntity.setErrinfo(errinfo);
                    break;
                case "getfile":
                    // 	获取录音录像文件
                    String rdfile = lbe.getRdfile();
                    List<Integer> fileStatus = lonBonEventDao.findFileStatus(rdfile);
                    if (fileStatus != null && fileStatus.size() > 0) {
                        //数据库中 0 未上传 1已上传。  请求 返回中0已上传
                        rest = fileStatus.get(0);
                        if (rest == 0) {
                            rest = -1;
                        } else if (rest == 1) {
                            lonBonScheduled.readConfigfileForFTP(ftpProperties.getBaseUrl()+"/"+rdfile.split("/")[0],rdfile.split("/")[1]);
                            rest = 0;
                        }
                    }
                    break;
                default:
                    log.error("没有找到对应请求命令,请查证");
                    break;
            }
            String msgts = utils.getTimeStamp();
            systemInfoData.setMsgtype(3);
            systemInfoData.setMsg(utils.getMessageRevertData(msgts, msgcode, resultDataEntity));
            systemInfoData.setSign(ToolUtils.getBussesSha(systemInfoData.getMsgid() + key + ToolUtils.md5Hex(systemInfoData.getMsg().toString()) + msgts));
            log.info("返回json===============" + JsonConvertBeanUtil.bean2json(systemInfoData));
            this.rabbitTemplate.convertAndSend(routingKey, routingKey, JsonConvertBeanUtil.bean2json(systemInfoData));
        } catch (Exception e) {
            log.error("数据出错请查看", e);
        }
    }

}
