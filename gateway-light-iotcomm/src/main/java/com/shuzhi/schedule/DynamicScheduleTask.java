package com.shuzhi.schedule;

import com.shuzhi.service.TaskService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableScheduling;
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

    //时间表达式  每2秒执行一次
    private String cron = "0 */1 * * * ?";


    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> {
                    String result = taskService.taskReqest();
                    if(StringUtils.isNotEmpty(result)){
                        logger.info("定时任务执行结果为："+result);
                    }else {
                        logger.info("定时任务执行结果为空，请检查");
                    }
                    setCron("0 */28 * * * ?");
                },
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }

    public void setCron(String cron) {
        this.cron = cron;
    }
}
