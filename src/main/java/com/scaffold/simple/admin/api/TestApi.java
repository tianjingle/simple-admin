package com.scaffold.simple.admin.api;

import com.scaffold.simple.admin.application.TestService;
import com.scaffold.simple.admin.application.dto.user.OOO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author tianjl
 * @Date 2021/11/17 14:29
 * @Discription disc
 */
@RestController
public class TestApi {

    @Autowired
    private TestService testService;

    public List<OOO> test(){
        return testService.query("tianjingle");
    }
}
