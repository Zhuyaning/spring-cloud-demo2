package com.zhuyaningfeign.service;


import com.zhuyaning.entity.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//添加为容器内的一个组件
@Service
// 服务提供者提供的服务名称，即 application.name
@FeignClient(value = "MICROSERVICECLOUDPROVIDERDEPT")
public interface DeptFeignService {
    //对应服务提供者（8001、8002、8003）Controller 中定义的方法
    @GetMapping(value = "/dept/get/{id}")
    public Dept get(@PathVariable("id") int id);

    @GetMapping(value = "/dept/list")
    public List<Dept> list();
}