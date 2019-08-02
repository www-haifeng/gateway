package com.shuzhi.commen;

import com.shuzhi.cache.Cache;
import com.shuzhi.entity.KafkaCapMsgM.pbcapturemsg;
import com.shuzhi.entity.SystemInfoData;
import com.shuzhi.entity.report.MotorData;
import com.shuzhi.entity.report.NonmotorData;
import com.shuzhi.entity.report.PersonData;
import com.shuzhi.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Description: 上报相关业务工具类
 * @Author: lirb
 * @CreateDate: 2019/7/15 13:35
 * @Version: 1.0
 **/
@Component
public class ReportUtils {

    private final static Logger logger = LoggerFactory.getLogger(ReportUtils.class);

    @Autowired
    private ReportService reportService;
    @Autowired
    private ConfigData configData;


    /**
     * 上报类型解析
     *
     * @param pbcm
     */
    public void reportTypeSelect(pbcapturemsg pbcm) throws Exception {
        SystemInfoData systemInfoData = getRequestBody();
        switch (pbcm.getCapType()) {
            case 1:
                logger.info("上报消息类型为：" + pbcm.getCapType());
                PersonData personData = new PersonData(pbcm);
                reportService.reportSend(personData, systemInfoData);
                break;
            case 3:
                logger.info("上报消息类型为：" + pbcm.getCapType());
                MotorData motorData = new MotorData(pbcm);
                reportService.reportSend(motorData, systemInfoData);
                break;
            case 4:
                logger.info("上报消息类型为：" + pbcm.getCapType());
                NonmotorData nonmotorData = new NonmotorData(pbcm);
                reportService.reportSend(nonmotorData, systemInfoData);
                break;
            default:
                throw new Exception("上报类型错误：" + pbcm.getCapType());
        }
    }

    /**
     * 上报请求实体
     *
     * @return
     */
    public SystemInfoData getRequestBody() {
        //暂时写死，后期从数据库取
        SystemInfoData infoData = new SystemInfoData();
        infoData.setMsgid(UUID.randomUUID().toString());
        infoData.setMsgtype(4);
        infoData.setSystype(configData.getSysType());
        infoData.setSysid(Integer.parseInt(Cache.gatewayConfigEntity.getSysId()));
        infoData.setConnectid(Integer.parseInt(Cache.gatewayConfigEntity.getConnectId()));
        return infoData;
    }


}
