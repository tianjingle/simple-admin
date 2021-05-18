package com.scaffold.simple.admin.api;

import com.scaffold.simple.admin.application.UserService;
import com.scaffold.simple.admin.utils.ResponseResult;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: tianjl
 * @Date: 2021/4/16 18:23
 * @Eamil: 2695062879@qq.com
 */
@Log4j2
@RestController
@RequestMapping(value = "/user")
public class UserApi {

    Logger logger= LoggerFactory.getLogger(UserApi.class);

    @Autowired
    private UserService userService;

    /**
     * user
     * @return
     */
    @GetMapping(value = "/zhang")
    public ResponseResult test(){
        logger.info("test");
        return userService.getUser();
    }
}
