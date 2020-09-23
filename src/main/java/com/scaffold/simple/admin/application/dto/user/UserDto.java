package com.scaffold.simple.admin.application.dto.user;

import lombok.Data;

/**
 * @Author: tianjl
 * @Date: 2020/9/23 18:09
 * @Eamil: 2695062879@qq.com
 */
@Data
public class UserDto {

    /**
     * userid
     */
    private String userId;

    /**
     * useranem
     */
    private String userName;

    /**
     * email
     */
    private String email;

    /**
     * pthon
     */
    private String phone;
}
