package com.shuzhi.schedule;

import com.alibaba.fastjson.JSON;
import com.shuzhi.cache.Cache;
import com.shuzhi.common.ConfigData;
import com.shuzhi.common.ReportUtils;
import com.shuzhi.dao.FactoryCronDao;
import com.shuzhi.entity.AppLoginData;
import com.shuzhi.service.TaskService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;


@Configuration
@EnableScheduling
@Order(5)
public class DynamicScheduleTask implements SchedulingConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(DynamicScheduleTask.class);
    @Autowired
    private TaskService taskService;
    @Autowired
    private FactoryCronDao factoryCronDao;
    @Autowired
    private ConfigData configData;
    @Autowired
    private ReportUtils reportUtils;

    //时间表达式  每2秒执行一次
    private String cron = "";



    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> {
                    for (int i = 1; i < 3; i++) {
                        reportUtils.report(i);
                    }
                },
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.2 合法性校验.
                    if (Cache.cronEntity == null) {
                        Cache.cronEntity = factoryCronDao.getByFactoryName(configData.getName());
                    }
                    setCron(Cache.cronEntity.getCron());
                    //2.3 返回执行周期(Date)
                    if ("1".equals(Cache.cronEntity.getStartFlag())) {
                        return new CronTrigger(cron).nextExecutionTime(triggerContext);
                    }
                    return null;

                }
        );
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    /**
     * 定时获取访问令牌
     */
    @Scheduled(initialDelay=1000 * 60 * 28,fixedRate = 1000 * 60 * 28)
    public void getAppLogin() {
        String result = taskService.getAppLogin();
        logger.info("定时任务执行结果为：" + result);

    }
}
