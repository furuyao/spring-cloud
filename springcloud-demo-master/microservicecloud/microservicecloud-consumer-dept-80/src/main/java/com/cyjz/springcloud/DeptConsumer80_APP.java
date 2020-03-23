package com.cyjz.springcloud;

import com.cyjz.irule.ZYLRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "MICROSERVICECLOUD-DEPT",configuration = ZYLRule.class) //在启动该微服务的时候就能去加载我们的自定义Ribbon配置类，从而使配置生效
public class DeptConsumer80_APP {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer80_APP.class,args);
    }
}
