package com.zhuyaninghystrix8004.controller;

import com.zhuyaninghystrix8004.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DeptController {

    @Value("${server.port}")
    private String serverPort;
    private final DeptService deptService;

    @Autowired
    public DeptController(DeptService deptService) {
        this.deptService = deptService;

    }

    @RequestMapping(value = "/dept/hystrix/ok/{id}")
    public String deptInfoOk(@PathVariable("id") Integer id) {
        String result = deptService.deptInfoOk(id);
        log.info("端口号：" + serverPort + " result:" + result);
        return result + "，   端口号：" + serverPort;
    }

    // Hystrix 服务超时降级
    @RequestMapping(value = "/dept/hystrix/timeout/{id}")
    public String deptInfoTimeout(@PathVariable("id") Integer id) {
        String result = deptService.deptInfoTimeout(id);
        log.info("端口号：" + serverPort + " result:" + result);
        return result + "，   端口号：" + serverPort;
    }

    // Hystrix 服务熔断
    @RequestMapping(value = "/dept/hystrix/circuit/{id}")
    public String deptCircuitBreaker(@PathVariable("id") Integer id){
        String result = deptService.deptCircuitBreaker(id);
        log.info("result:"+result);
        return result;
    }

}
