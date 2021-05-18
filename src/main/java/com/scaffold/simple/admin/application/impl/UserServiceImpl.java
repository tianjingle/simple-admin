package com.scaffold.simple.admin.application.impl;

import com.scaffold.simple.admin.application.ProjectManagerService;
import com.scaffold.simple.admin.application.UserService;
import com.scaffold.simple.admin.application.dto.user.UserDto;
import com.scaffold.simple.admin.utils.ResponseResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: tianjl
 * @Date: 2021/4/16 18:24
 * @Eamil: 2695062879@qq.com
 */
@Log4j2
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    ProjectManagerService projectManagerService;
    /**
     * 获取user
     * @return
     */
    @Override
    public ResponseResult getUser() {
        UserDto userDto=projectManagerService.getUser();
        log.error(userDto.toString());
        userDto.setUserName("tianjingle");
        return ResponseResult.success(userDto);
    }
}
