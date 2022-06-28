package com.zhuyaning.controller;

import com.zhuyaning.entity.Dept;
import com.zhuyaning.service.DeptService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    public Dept get(@PathVariable("id") int id) {
        return deptService.get(id);
    }

    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public List<Dept> list() {
        return deptService.selectAll();
    }
}