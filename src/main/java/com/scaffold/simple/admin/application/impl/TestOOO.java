package com.scaffold.simple.admin.application.impl;

import com.scaffold.simple.admin.application.dto.user.OOO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author tianjl
 * @Date 2021/11/17 14:32
 * @Discription disc
 */
@Service
public class TestOOO {


    public List<OOO> query(String name){
        String sql =  "select * from user where name = '"+name+"'";
        return new ArrayList<>();
    }
}
