package com.cyjz.irule;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 作者 ZYL
 * 功能描述 : 自定义配置类Ribbon
 * 日期 2019/12/13 10:32  
 * 参数 null
 * 返回值 
 */
@Configuration
public class ZYLRule {

    @Bean
    public IRule myRule(){
        return new RandomRule_DIY();
    }
}