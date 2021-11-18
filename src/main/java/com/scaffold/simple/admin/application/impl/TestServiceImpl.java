package com.scaffold.simple.admin.application.impl;

import com.scaffold.simple.admin.application.TestService;
import com.scaffold.simple.admin.application.dto.user.OOO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author tianjl
 * @Date 2021/11/17 14:30
 * @Discription disc
 */
@Service
public class TestServiceImpl implements TestService {


    @Autowired
    private TestOOO testOOO;


    public List<OOO> query(String name){
        return testOOO.query(name);
    }
}
