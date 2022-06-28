package com.zhuyaning8002.microservicecloudproviderdept8002.service.impl;

import com.zhuyaning.entity.Dept;

import com.zhuyaning8002.microservicecloudproviderdept8002.mapper.DeptMapper;
import com.zhuyaning8002.microservicecloudproviderdept8002.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("deptService")
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public Dept get(Integer deptNo) {
        return deptMapper.selectByPrimaryKey(deptNo);
    }

    @Override
    public List<Dept> selectAll() {
        return deptMapper.GetAll();
    }
}
