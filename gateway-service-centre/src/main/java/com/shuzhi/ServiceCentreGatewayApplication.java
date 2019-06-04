package com.shuzhi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 *
 * @作者： Yujq
 * @创建： 2019年6月3日,下午13:15
 * @描述： 网关服务
 *
 */

@SpringBootApplication
@EnableJpaAuditing
public class ServiceCentreGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCentreGatewayApplication.class, args);
    }
}
