package com.zhuyaning8003.microservicecloudproviderdept8003.service;

import com.zhuyaning.entity.Dept;

import java.util.List;

public interface DeptService {

    Dept get(Integer deptNo);

    List<Dept> selectAll();
}
