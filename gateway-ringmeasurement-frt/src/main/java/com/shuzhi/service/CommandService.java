package com.shuzhi.service;

import com.shuzhi.cache.Cache;
import com.shuzhi.common.ByteUtils;
import com.shuzhi.common.ConfigData;
import com.shuzhi.common.Utils;
import com.shuzhi.entity.DataEntity;
import com.shuzhi.producer.RabbitSender;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;

/**
 * 任务业务处理
 */
@Component
@EnableConfigurationProperties(ConfigData.class)
public class CommandService {

    private final static Logger logger = LoggerFactory.getLogger(CommandService.class);

    @Autowired
    ConfigData configData;

    @Autowired
    ByteUtils byteUtils;

    @Autowired
    private RabbitSender rabbitSender;

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
        if (resultbytes.length == 35) {
            ByteBuffer bf = ByteBuffer.wrap(resultbytes);
            System.out.println(resultbytes.length);
            //取出地址码
            byte[] idBytes = new byte[1];
            bf.get(idBytes);
            Integer deviceId = Integer.valueOf(idBytes[0]);
            String did = Utils.deviceIdToDid(String.valueOf(deviceId));
            //取出功能码
            byte[] functionBytes = new byte[1];
            bf.get(functionBytes);
            //取出字节数
            byte[] dataLengthBytes = new byte[1];
            bf.get(dataLengthBytes);
            Integer datalength = Integer.valueOf(dataLengthBytes[0]);
            if(datalength!=30){
                return ;
            }
            //取出最小风向
            byte[] ringTemperatureBytes = new byte[2];
            bf.get(ringTemperatureBytes);
            //平均风向
            byte[] avgWindDirectBytes = new byte[2];
            bf.get(avgWindDirectBytes);
            int avgWindDirectInt = byteUtils.byte2Int(avgWindDirectBytes, 0, 2);
            //最大风向
            byte[] ludianBytes = new byte[2];
            bf.get(ludianBytes);
            //最小风速
            byte[] pressureByte = new byte[2];
            bf.get(pressureByte);
            //平均风速
            byte[] avgWindSpeedBytes = new byte[2];
            bf.get(avgWindSpeedBytes);
            int avgWindSpeedInt = byteUtils.byte2Int(avgWindSpeedBytes, 0, 2);
            int avgWindSpeed=avgWindSpeedInt*10;
            //最大风速
            byte[] windSpeedBytes = new byte[2];
            bf.get(windSpeedBytes);
            //大气温度
            byte[] temperatureBytes = new byte[2];
            bf.get(temperatureBytes);
            int temperatureInt = byteUtils.byte2Int(temperatureBytes, 0, 2);
            int temperature=temperatureInt*10;

            //大气湿度
            byte[] wettingBytes = new byte[2];
            bf.get(wettingBytes);
            int wettingInt = byteUtils.byte2Int(wettingBytes, 0, 2);
            int wetting=wettingInt*10;

            //大气气压
            byte[] pressureBytes = new byte[2];
            bf.get(pressureBytes);
            int pressureInt = byteUtils.byte2Int(pressureBytes, 0, 2);
            int pressure=pressureInt*10;

            //雨量
            byte[] rainfallBytes = new byte[2];
            bf.get(rainfallBytes);
            int rainfallInt = byteUtils.byte2Int(rainfallBytes, 0, 2);
            int rainfall=rainfallInt*10;

            //总辐射
            byte[] radiationTwoBytes = new byte[2];
            bf.get(radiationTwoBytes);
            //紫外强度
            byte[] soilMoistureBytes = new byte[2];
            bf.get(soilMoistureBytes);
            //噪声
            byte[] noiseBytes = new byte[2];
            bf.get(noiseBytes);
            int noiseInt = byteUtils.byte2Int(noiseBytes, 0, 2);
            int noise=noiseInt*10;
            //pm2.5
            byte[] pmTwoPointFiveBytes = new byte[2];
            bf.get(pmTwoPointFiveBytes);
            int pmTwoPointFiveInt = byteUtils.byte2Int(pmTwoPointFiveBytes, 0, 2);
            int pmTwoPointFive=pmTwoPointFiveInt*10;
            //pm10
            byte[] pmTenBytes = new byte[2];
            bf.get(pmTenBytes);
            int pmTenInt = byteUtils.byte2Int(pmTenBytes, 0, 2);
            int pmTen=pmTenInt*10;

            DataEntity dataEntity = new DataEntity(did, temperature, wetting, pressure, avgWindDirectInt, avgWindSpeed
                    , rainfall, pmTwoPointFive, pmTen, noise);

            System.out.println(dataEntity);
            //发送数据到mq
            try {
                rabbitSender.send("lowerControlMessage", dataEntity.toString());
                logger.info("命令回执发送完毕:"+dataEntity.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            //清空结果缓存
            Cache.sendOneByte = null;
        }
    }
}
