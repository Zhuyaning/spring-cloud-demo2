package com.zhuyaning8003.microservicecloudproviderdept8003.controller;

import com.zhuyaning.entity.Dept;
import com.zhuyaning8003.microservicecloudproviderdept8003.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 服务提供者的控制层
 */
@RestController
@Slf4j
public class DeptController {
    @Autowired
    private DeptService deptService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/dept/get/{id}")
    public Dept get(@PathVariable("id") int id) {
        return deptService.get(id);
    }

    @GetMapping(value = "/dept/list")
    public List<Dept> list() {
        return deptService.selectAll();
    }
}
