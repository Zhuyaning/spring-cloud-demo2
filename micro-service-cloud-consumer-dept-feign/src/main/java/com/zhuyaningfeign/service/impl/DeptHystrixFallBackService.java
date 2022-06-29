package com.zhuyaningfeign.service.impl;

import com.zhuyaningfeign.service.DeptHystrixService;
import org.springframework.stereotype.Service;

@Service
public class DeptHystrixFallBackService implements DeptHystrixService {

    @Override
    public String deptInfoOk(Integer id) {
        return "-------------------- 系统繁忙，请稍后重试！（ deptInfo_Ok 解耦回退方法触发）-----------------------";
    }

    @Override
    public String deptInfoTimeout(Integer id) {
        return "-------------------- 系统繁忙，请稍后重试！（ deptInfo_Timeout 解耦回退方法触发）-----------------------";
    }
}
