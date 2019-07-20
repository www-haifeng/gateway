package com.shuzhi.commen;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shuzhi.cache.Cache;
import com.shuzhi.entity.CommandInfo;
import com.shuzhi.entity.MessageData;
import com.shuzhi.entity.SystemInfoData;
import com.shuzhi.entity.command.*;
import com.shuzhi.service.CommandService;
import com.shuzhi.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 *功能描述 命令工具
 * @author YHF
 * @date 2019/6/5
 * @params
 * @return
 */
@Component
public class CommandUtils {
    private final static Logger logger = LoggerFactory.getLogger(CommandUtils.class);
    @Autowired
    private CommandService commandService;
    @Autowired
    private Utils utils;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private FtpUtil ftpUtil;
    /**
     * @Description:匹配数据指令
     * @Author: YHF
     * @date 2019/6/5
     */
    public void commandSelect(String data){
        try{
            //read原始json
            JsonNode jsonParentNode = mapper.readTree(data);

            //解析第一层json 转换成对象
            SystemInfoData systemInfoData = mapper.readValue(data, SystemInfoData.class);

            //解析第二层json
            MessageData messageData = mapper.readValue(jsonParentNode.path("msg").toString(), MessageData.class);

            //判断数据中sign校验
            boolean signVerify = utils.signVerify(systemInfoData,jsonParentNode);

            //if (signVerify){
                CommandInfo commandInfo = Cache.commandMap.get(messageData.getCmdid());

                if (commandInfo == null){
                    logger.error("未查询到广播设备cmdid为:"+messageData.getCmdid()+"的命令,放弃请求");
                    return;
                }

                //获取url
                String url = "http://"+commandInfo.getTdeviceFactoryEntity().getServerIp()+":"+commandInfo.getTdeviceFactoryEntity().getServerPort()+commandInfo.getTmsgInfoEntity().getInterfaceId();

                //匹配命令
                switch (commandInfo.getTmsgInfoEntity().getInterfaceId()){
                    case "/php/getzoneterminaldata.php":
                        //处理命令  转为设备协议
                        GetZoneTerminalData gztd = mapper.readValue(jsonParentNode.path("msg").path("data").toString(), GetZoneTerminalData.class);
                        commandService.commandServiceGetzoneterminaldata(url, gztd.toString(),systemInfoData);
                        break;
                    case "/php/getsingleterminaldata.php":
                        //处理命令  转为设备协议
                        GetSingLeterminalData gsld = mapper.readValue(jsonParentNode.path("msg").path("data").toString(), GetSingLeterminalData.class);
                        String deviceId = utils.didToDeviceId(gsld.getSearchTxt(),",");
                        if (StringUtil.isEmpty(deviceId)){
                            logger.info("数据有误请查证,did为空或缺失标识符,");
                            break;
                        }
                        gsld.setSearchTxt(deviceId);
                        commandService.commandServiceGetzoneterminaldata(url, gsld.toString(),systemInfoData);
                        break;
                    case "/php/setterminal.php":
                        SetTerminal st = mapper.readValue(jsonParentNode.path("msg").path("data").toString(), SetTerminal.class);
                        st.setTid(Integer.parseInt(Cache.deviceInfoMap.get(st.getTid().toString())));
                        commandService.commandService(url, st.toString(),systemInfoData);
                        break;
                    case "/php/settervolume.php":
                        SetTerVolume stv = mapper.readValue(jsonParentNode.path("msg").path("data").toString(), SetTerVolume.class);
                        stv.setTerid(Integer.parseInt(Cache.deviceInfoMap.get(stv.getTerid().toString())));
                        commandService.commandService(url, stv.toString(),systemInfoData);
                        break;
                    case "/php/exesdkcommand.php":
                        ExesdkCommand ec = mapper.readValue(jsonParentNode.path("msg").path("data").toString(), ExesdkCommand.class);
                        ec.setSource(Cache.deviceInfoMap.get(ec.getSource()));
                        if (ec.getTarget().indexOf("-") != -1){
                            ec.setTarget(utils.didToDeviceId(ec.getTarget(),"-").replace(",","-"));
                        }else{
                            ec.setTarget(Cache.deviceInfoMap.get(ec.getTarget()));
                        }

                        commandService.commandService(url, ec.toString(),systemInfoData);
                        break;
                    case "/php/gettaskdata.php":
                        GetTaskData gtd = mapper.readValue(jsonParentNode.path("msg").path("data").toString(), GetTaskData.class);
                        commandService.commandService(url, gtd.toString(),systemInfoData);
                        break;
                    case "/php/gettaskinfo.php":
                        GetTaskInfo gti = mapper.readValue(jsonParentNode.path("msg").path("data").toString(), GetTaskInfo.class);

                        commandService.commandServiceGettaskinfo(url, gti.toString(),systemInfoData);
                        break;
                    case "/php/addtaskinfo.php":
                        AddTaskInfo ati = mapper.readValue(jsonParentNode.path("msg").path("data").toString(), AddTaskInfo.class);
                        String adddids = ati.getCommands().substring(ati.getCommands().indexOf("idstart")+"idstart".length(),ati.getCommands().indexOf("idend"));
                        String adddevice_id = utils.didToDeviceId(adddids, "<");
                        ati.setCommands(ati.getCommands().replaceAll("idstart"+adddids+"idend",adddevice_id));
                        commandService.commandService(url, ati.toString(),systemInfoData);
                        break;
                    case "/php/modifytaskinfo.php":
                        ModifyTaskInfo mti = mapper.readValue(jsonParentNode.path("msg").path("data").toString(), ModifyTaskInfo.class);
                        String modifydids = mti.getCommands().substring(mti.getCommands().indexOf("idstart")+"idstart".length(),mti.getCommands().indexOf("idend"));
                        String modifydevice_id = utils.didToDeviceId(modifydids, "<");
                        mti.setCommands(mti.getCommands().replaceAll("idstart"+modifydids+"idend",modifydevice_id));
                        commandService.commandService(url, mti.toString(),systemInfoData);
                        break;
                    case "/php/exetaskcmd.php":
                        //删除计划任务、手动开始任务、手动停止任务  依靠data中taskCommand区分
                        ExeTaskCmd etc = mapper.readValue(jsonParentNode.path("msg").path("data").toString(), ExeTaskCmd.class);
                        commandService.commandService(url, etc.toString(),systemInfoData);
                        break;
                    case "/php/getmediadata.php":
                        GetMediaData gmd = mapper.readValue(jsonParentNode.path("msg").path("data").toString(), GetMediaData.class);
                        commandService.commandService(url, gmd.toString(),systemInfoData);
                        break;
                    case "/php/addmediadata.php":
                        //添加媒体文件
                        AddMediaData amd = mapper.readValue(jsonParentNode.path("msg").path("data").toString(), AddMediaData.class);
                        ftpUtil.connectServer("192.168.8.150",21,"shuzhi","shuzhi","/");
                        String resultStr = ftpUtil.uploadMediaFile(amd, url);
                        commandService.commandSend(resultStr,systemInfoData);
                        break;
                    case "/php/delmediadata.php":
                        DelMediaData dmd = mapper.readValue(jsonParentNode.path("msg").path("data").toString(), DelMediaData.class);
                        commandService.commandService(url, dmd.toString(),systemInfoData);
                        break;
                    case "/php/exeRealPlayFile.php":
                        ExeRealPlayFile erpf = mapper.readValue(jsonParentNode.path("msg").path("data").toString(), ExeRealPlayFile.class);
                        erpf.getParam1();
                        if (erpf.getParam1().indexOf("<") != -1){
                            erpf.setParam1(utils.didToDeviceId(erpf.getParam1(),"<").replace(",","<"));
                        }else{
                            erpf.setParam1(Cache.deviceInfoMap.get(erpf.getParam1()));
                        }
                        if (erpf.getParam3() != 0 && erpf.getParam3()!= null){
                            erpf.setParam3(Integer.parseInt(Cache.deviceInfoMap.get(erpf.getParam3().toString())));
                        }
                        commandService.commandService(url, erpf.toString(),systemInfoData);
                        break;
                        default:
                            logger.error("没有找到对应请求命令,请查证");
                            break;
                }
            /*}else{
                logger.error("数据包sign校验有误,数据丢弃");
                return;
            }*/
        }catch (Exception e){
            logger.error("数据出错请查看",e);
        }
    }
}
