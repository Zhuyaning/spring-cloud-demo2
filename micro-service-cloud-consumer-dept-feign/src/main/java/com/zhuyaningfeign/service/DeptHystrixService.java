package com.zhuyaningfeign.service;

import com.zhuyaningfeign.service.impl.DeptHystrixFallBackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@FeignClient(value = "MICROSERVICECLOUDPROVIDERDEPTHYSTRIX", fallback = DeptHystrixFallBackService.class)
public interface DeptHystrixService {
    @RequestMapping(value = "/dept/hystrix/ok/{id}")
    public String deptInfoOk(@PathVariable("id") Integer id);

    @RequestMapping(value = "/dept/hystrix/timeout/{id}")
    public String deptInfoTimeout(@PathVariable("id") Integer id);
}
