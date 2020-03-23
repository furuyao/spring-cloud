package com.cyjz.irule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.Random;

public class RandomRule_DIY extends AbstractLoadBalancerRule{
    //
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

    //total = 0 //当total = 5以后，我们的指针才能往下走
    //index = 0 //当前对外提供服务的服务器地址
    //total需要重新置为零，但是已经达到过一个5次，我们的Index = 1
    //分析：我们5次，但是微服务只有8001，8002，8003三台
    //index到达3就要拉回来
    private int total = 0;
    private int currentIndex = 0;

    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        } else {
            Server server = null;

            while(server == null) {
                if (Thread.interrupted()) {
                    return null;
                }

                List<Server> upList = lb.getReachableServers();
                List<Server> allList = lb.getAllServers();
                int serverCount = allList.size();
                if (serverCount == 0) {
                    return null;
                }

                if(total < 5){
                    server = upList.get(currentIndex);
                    total ++;
                }else{
                    total = 0;
                    currentIndex ++;
                    if(currentIndex >= upList.size()){
                        currentIndex = 0;
                    }
                }

                if (server == null) {
                    Thread.yield();
                } else {
                    if (server.isAlive()) {
                        return server;
                    }

                    server = null;
                    Thread.yield();
                }
            }

            return server;
        }
    }

    public Server choose(Object key) {
        return this.choose(this.getLoadBalancer(), key);
    }

    public void initWithNiwsConfig(IClientConfig clientConfig) {
    }
}
