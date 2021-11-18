package com.scaffold.simple.admin.application;

import com.scaffold.simple.admin.application.dto.user.OOO;

import java.util.List;

/**
 * @Author tianjl
 * @Date 2021/11/17 14:30
 * @Discription disc
 */
public interface TestService {

    List<OOO> query(String name);
}
