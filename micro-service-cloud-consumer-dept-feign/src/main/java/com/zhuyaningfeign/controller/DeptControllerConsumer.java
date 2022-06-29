package com.zhuyaningfeign.controller;


import com.zhuyaning.entity.Dept;
import com.zhuyaningfeign.service.DeptFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptControllerConsumer {

    private final DeptFeignService deptFeignService;

    @Autowired
    public DeptControllerConsumer(DeptFeignService deptFeignService) {
        this.deptFeignService = deptFeignService;
    }

    @GetMapping(value = "/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Integer id) {
        return deptFeignService.get(id);
    }

    @GetMapping(value = "/consumer/dept/list")
    public List<Dept> list() {
        return deptFeignService.list();
    }
}