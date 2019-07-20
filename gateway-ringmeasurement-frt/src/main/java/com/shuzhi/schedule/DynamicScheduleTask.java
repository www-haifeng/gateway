package com.shuzhi.schedule;

import com.shuzhi.cache.Cache;
import com.shuzhi.common.ByteUtils;
import com.shuzhi.common.Utils;
import com.shuzhi.dao.FactoryCronDao;
import com.shuzhi.entity.DeviceInfo;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.lang.ArrayUtils;
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
    private FactoryCronDao factoryCronDao;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> {

                    System.out.println("    执行动态定时任务: " + LocalDateTime.now().toLocalTime());
                    Map<String, ChannelHandlerContext> channelCache = Cache.channelCache;
                    for (String key : channelCache.keySet()) {
                        ChannelHandlerContext channelHandlerContext = channelCache.get(key);
                        System.out.println("循环中的ctx" + channelHandlerContext);
                        //获取device id
                        DeviceInfo deviceInfo = Cache.deviceIpMap.get(key);
                        if (deviceInfo != null) {
                            String deviceId = deviceInfo.getTdeviceFrtEntity().getDeviceId();
                            int deviceIdInt = Integer.parseInt(deviceId);
                            String id = Integer.toHexString(deviceIdInt);
                            byte[] idBytes = ByteUtils.str2Bcd(id);
                            //获取crc校验码
                            byte[] mgsBytes = ArrayUtils.addAll(idBytes, new byte[]{(byte) 0x03,
                                    (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x0F});
                            String crc2 = Utils.getCRC2(mgsBytes);
                            byte[] crcBytes = ByteUtils.str2Bcd(crc2);
                            byte[] msg = ArrayUtils.addAll(mgsBytes, crcBytes);
                            System.out.println(ByteUtils.bytesToHexString(msg));
                            channelHandlerContext.channel().writeAndFlush(msg);
                            System.out.println("写入数据完毕");
                        }

                    }
                },
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.2 合法性校验.
                    if (Cache.cronEntity == null) {
                        Cache.cronEntity = factoryCronDao.getByFactoryName("富奥通");
                    }
                    //2.3 返回执行周期(Date)
                    if ("1".equals(Cache.cronEntity.getStartFlag())) {
                        return new CronTrigger(Cache.cronEntity.getCron()).nextExecutionTime(triggerContext);
                    } else {
                        return null;
                    }
                }
        );
    }
}
