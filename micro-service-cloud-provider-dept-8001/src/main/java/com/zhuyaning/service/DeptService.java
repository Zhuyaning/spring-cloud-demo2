package com.zhuyaning.service;

import com.zhuyaning.entity.Dept;

import java.util.List;

public interface DeptService {

    Dept get(Integer deptNo);

    List<Dept> selectAll();
}