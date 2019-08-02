package com.shuzhi.schedule;

import com.shuzhi.commen.ConfigData;
import com.shuzhi.commen.ReportUtils;
import com.shuzhi.dao.FactoryCronDao;
import com.shuzhi.entity.TDeviceFactoryCronEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Configuration
@EnableScheduling
public class DynamicScheduleTask implements SchedulingConfigurer {

    @Autowired
    private FactoryCronDao factoryCronDao;
    @Autowired
    private ReportUtils reportUtils;
    @Autowired
    private ConfigData configData;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> {
                    System.out.println("执行动态定时任务: " + LocalDateTime.now().toLocalTime());
                    reportUtils.reportRequest();
                },
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.2 合法性校验.
                    TDeviceFactoryCronEntity cron = factoryCronDao.getByFactoryName(configData.getName());
                    //2.3 返回执行周期(Date)
                    if ("1".equals(cron.getStartFlag())) {
                        return new CronTrigger(cron.getCron()).nextExecutionTime(triggerContext);
                    } else {
                        return null;
                    }
                }
        );
    }
}
