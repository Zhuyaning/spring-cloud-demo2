package com.zhuyaning8003.microservicecloudproviderdept8003.service.impl;

import com.zhuyaning.entity.Dept;

import com.zhuyaning8003.microservicecloudproviderdept8003.mapper.DeptMapper;
import com.zhuyaning8003.microservicecloudproviderdept8003.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("deptService")
public class DeptServiceImpl implements DeptService {

    private final DeptMapper deptMapper;
    @Autowired
    public DeptServiceImpl(DeptMapper deptMapper) {
        this.deptMapper = deptMapper;
    }

    @Override
    public Dept get(Integer deptNo) {
        return deptMapper.selectByPrimaryKey(deptNo);
    }

    @Override
    public List<Dept> selectAll() {
        return deptMapper.getAll();
    }
}
