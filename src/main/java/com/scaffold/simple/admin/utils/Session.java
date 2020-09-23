package com.scaffold.simple.admin.utils;

import com.scaffold.simple.admin.application.dto.user.UserDto;
import lombok.Data;

import java.util.Map;

/**
 * @Author: tianjl
 * @Date: 2020/4/16 14:53
 * @Eamil: 2695062879@qq.com
 */
@Data
public class Session {

    /**
     * 用户信息
     */
    private UserDto userDto;


    /**
     * 权限列表
     */
    private Map<String,Boolean> roleMap;
}
