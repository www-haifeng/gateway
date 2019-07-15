package com.shuzhi.schedule;

import com.shuzhi.cache.Cache;
import com.shuzhi.common.ConfigData;
import com.shuzhi.service.CommandService;
import io.micrometer.core.instrument.util.StringUtils;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

@Component
@Configuration
@EnableScheduling
public class DynamicScheduleTask implements SchedulingConfigurer {

    @Autowired
    private ConfigData configData;
    @Autowired
    private CommandService commandService;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> {

                    System.out.println("执行动态定时任务: " + LocalDateTime.now().toLocalTime());
                    Map<String, ChannelHandlerContext> channelCache = Cache.channelCache;
                    for(String key :channelCache.keySet()){
                        //if("192.168.10.157".equals(key)) {
                            ChannelHandlerContext channelHandlerContext = channelCache.get(key);
                            System.out.println("循环中的ctx"+channelHandlerContext);
                            byte[] mgsWindSpeed = new byte[]{(byte)0x01,(byte)0x03,
                                    (byte)0x00,(byte)0x07,(byte)0x00,(byte)0x1C,(byte)0xF5,(byte)0xC2};
                            channelHandlerContext.channel().writeAndFlush(mgsWindSpeed);
                            System.out.println("写入数据完毕");
                            //commandService.commandService(channelHandlerContext);
                        //}
                    }
                },
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.2 合法性校验.
                    if (StringUtils.isEmpty(configData.getCron())) {
                        // Omitted Code ..
                    }
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(configData.getCron()).nextExecutionTime(triggerContext);
                }
        );
    }
}
