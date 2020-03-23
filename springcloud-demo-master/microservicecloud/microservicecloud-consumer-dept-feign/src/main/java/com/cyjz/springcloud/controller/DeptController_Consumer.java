package com.cyjz.springcloud.controller;

import com.cyjz.springcloud.entities.Dept;
import com.cyjz.springcloud.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DeptController_Consumer {

    @Autowired
    private DeptClientService deptClientService;

    @RequestMapping(value = "/consumer/dept/add")
    public boolean add(Dept dept){
        return deptClientService.add(dept);
    }

    @RequestMapping(value = "/consumer/dept/get/{id}")
    public Dept getById(@PathVariable Long id){
        return deptClientService.get(id);
    }

    @RequestMapping(value = "/consumer/dept/list")
    public List<Dept> getList(){
        return deptClientService.list();
    }

}
