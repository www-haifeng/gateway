package com.shuzhi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *
 * @作者： Yujq
 * @创建： 2019年6月3日,下午13:15
 * @描述： WIFI韵盛发
 *
 */

@EnableScheduling
@SpringBootApplication
public class WifiAbloomyGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(WifiAbloomyGatewayApplication.class, args);
    }
}
