package com.cyjz.springcloud.controller;

import com.cyjz.springcloud.entities.Dept;
import com.cyjz.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    public boolean add(@RequestBody Dept dept) {
        return deptService.add(dept);
    }

    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "testHystrixCommand")
    public Dept get(@PathVariable("id") Long id) {
        Dept dept = deptService.get(id);
        if(null == dept){
            throw new RuntimeException("该ID：" + id + "没有对应的信息");
        }
        return dept;
    }

    public Dept testHystrixCommand(@PathVariable("id")Long id){
        return new Dept().setDeptno(id).setDname("该ID：" + id + "没有对应的信息,null-@HystrixCommand").setDb_source("no this database in MYSQL");

    }

    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public List<Dept> list() {
        return deptService.list();
    }

    @RequestMapping(value = "/dept/discovery", method = RequestMethod.GET)
    public Object discovery(){
        List<String> list = discoveryClient.getServices();
        System.out.println("**********" + list);
        List<ServiceInstance> srvList = discoveryClient.getInstances("MICROSERVICECLOUD-DEPT");
        for (ServiceInstance serviceInstance : srvList) {
            System.out.println(serviceInstance.getServiceId() + "\t" + serviceInstance.getHost() + "\t" + serviceInstance.getPort() + "\t" + serviceInstance.getUri());
        }
        return this.discoveryClient;
    }
}
