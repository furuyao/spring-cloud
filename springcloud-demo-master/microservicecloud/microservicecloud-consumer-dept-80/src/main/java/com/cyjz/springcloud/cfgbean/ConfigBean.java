package com.cyjz.springcloud.cfgbean;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {
    @Bean
    @LoadBalanced //ribbon负载均衡
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean//LB算法的设置
    public IRule myRule(){
        return new RandomRule();
    }
}
