package com.scaffold.simple.admin.other;

import java.lang.reflect.InvocationTargetException;

/**
 * @Author: tianjl
 * @Date: 2020/12/22 19:03
 * @Eamil: 2695062879@qq.com
 */
public interface MyPlugin {

    Object interceptor(MyInvocation myInvocation) throws InvocationTargetException, IllegalAccessException;

    //插件
    Object plugin(Object o);
}
