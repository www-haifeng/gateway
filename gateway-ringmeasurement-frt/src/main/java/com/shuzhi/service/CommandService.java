package com.shuzhi.service;

import com.shuzhi.cache.Cache;
import com.shuzhi.common.ByteUtils;
import com.shuzhi.common.ConfigData;
import com.shuzhi.entity.DataEntity;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;

/**
 * 任务业务处理
 */
@Component
@EnableConfigurationProperties(ConfigData.class)
public class CommandService {

    @Autowired
    ConfigData configData;

    @Autowired
    ByteUtils byteUtils;

    public void commandService(ChannelHandlerContext ctx) {
        byte[] mgsWindSpeed = new byte[]{(byte) 0x01, (byte) 0x03,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x0A, (byte) 0xC5, (byte) 0xCD};
        ctx.channel().writeAndFlush(mgsWindSpeed);
        //new CommandThread(ctx, mgsWindSpeed).start();
    }


    /**
     * 处理接受的消息
     *
     * @param msgBytes
     */
    public void handleResult(byte[] msgBytes) {
        byte[] resultbytes = msgBytes;
        //拼接上一条缓存数组
        if (Cache.sendOneByte != null) {
            resultbytes = ArrayUtils.addAll(Cache.sendOneByte, msgBytes);
        }
        if (resultbytes.length == 61) {
            ByteBuffer bf = ByteBuffer.wrap(resultbytes);
            System.out.println(resultbytes.length);
            //取出设备id
            byte[] idBytes = new byte[1];
            bf.get(idBytes);
            Integer deviceId = Integer.valueOf(idBytes[0]);
            //取出功能码
            byte[] functionBytes = new byte[1];
            bf.get(functionBytes);
            //取出字节数
            byte[] dataLengthBytes = new byte[1];
            bf.get(dataLengthBytes);
            Integer datalength = Integer.valueOf(dataLengthBytes[0]);
            //取出环温
            byte[] ringTemperatureBytes = new byte[2];
            bf.get(ringTemperatureBytes);
            int ringTemperatureInt = byteUtils.byte2Int(ringTemperatureBytes, 0, 2);
            double ringTemperature = ringTemperatureInt / 10;
            //环湿
            byte[] ringWettingBytes = new byte[2];
            bf.get(ringWettingBytes);
            int ringWettingInt = byteUtils.byte2Int(ringWettingBytes, 0, 2);
            double ringWetting = ringWettingInt / 10.0;
            //露点
            byte[] ludianBytes = new byte[2];
            bf.get(ludianBytes);
            //气压
            byte[] pressureBytes = new byte[2];
            bf.get(pressureBytes);
            int pressureInt = byteUtils.byte2Int(pressureBytes, 0, 2);
            double pressure = pressureInt / 10;
            //海拔
            byte[] haibaBytes = new byte[2];
            bf.get(haibaBytes);
            //风速
            byte[] windSpeedBytes = new byte[2];
            bf.get(windSpeedBytes);
            int windSpeedInt = byteUtils.byte2Int(windSpeedBytes, 0, 2);
            double windSpeed = windSpeedInt /  10.0;
            //2分钟平均风速
            byte[] windSpeed2Bytes = new byte[2];
            bf.get(windSpeed2Bytes);
            //10分钟平均风速
            byte[] windSpeed10Bytes = new byte[2];
            bf.get(windSpeed10Bytes);
            //风向
            byte[] windDirectionBytes = new byte[2];
            bf.get(windDirectionBytes);
            int windDirectionInt = byteUtils.byte2Int(windDirectionBytes, 0, 2);
            double windDirection = windDirectionInt /  10.0;
            //辐射1
            byte[] radiationOneBytes = new byte[2];
            bf.get(radiationOneBytes);
            //辐射2
            byte[] radiationTwoBytes = new byte[2];
            bf.get(radiationTwoBytes);
            //土湿
            byte[] soilMoistureBytes = new byte[2];
            bf.get(soilMoistureBytes);
            //电量
            byte[] electricQuantityBytes = new byte[2];
            bf.get(electricQuantityBytes);
            //雨量日累计
            byte[] rainfallBytes = new byte[2];
            bf.get(rainfallBytes);
            int rainfallInt = byteUtils.byte2Int(rainfallBytes, 0, 2);
            double rainfall = rainfallInt /  10.0;
            //能见度
            byte[] visibilityBytes = new byte[2];
            bf.get(visibilityBytes);
            //能见度10分钟平均
            byte[] visibilityTenBytes = new byte[2];
            bf.get(visibilityTenBytes);
            //日照时日累计
            byte[] sunshineTimeBytes = new byte[2];
            bf.get(sunshineTimeBytes);
            //CO2
            byte[] co2Bytes = new byte[2];
            bf.get(co2Bytes);
            //电子罗盘
            byte[] electronicCompassBytes = new byte[2];
            bf.get(electronicCompassBytes);
            //PM2.5
            byte[] pmTwoPointFiveBytes = new byte[2];
            bf.get(pmTwoPointFiveBytes);
            int pmTwoPointFive = byteUtils.byte2Int(pmTwoPointFiveBytes, 0, 2);
            //PM10
            byte[] pmTenBytes = new byte[2];
            bf.get(pmTenBytes);
            int pmTen = byteUtils.byte2Int(pmTenBytes, 0, 2);
            //噪声
            byte[] noiseBytes = new byte[2];
            bf.get(noiseBytes);
            int noiseInt = byteUtils.byte2Int(noiseBytes, 0, 2);
            double noise = noiseInt /  10.0;

            DataEntity dataEntity = new DataEntity(deviceId, ringTemperature, ringWetting, pressure, windDirection,
                    windSpeed, rainfall, pmTwoPointFive, pmTen, noise);
            System.out.println(dataEntity);
            //发送数据到mq

            //清空结果缓存
            Cache.sendOneByte = null;
        }
    }
}
