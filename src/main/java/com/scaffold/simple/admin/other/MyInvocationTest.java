package com.scaffold.simple.admin.other;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: tianjl
 * @Date: 2020/12/22 19:26
 * @Eamil: 2695062879@qq.com
 */
public class MyInvocationTest implements MyInvocation{

    private Object object;

    private Object[] plugin;

    private Method method;

    MyInvocationTest(Object o, Method method, Object[] myPlugin){
        this.object=o;
        this.plugin=myPlugin;
        this.method=method;
    }

    @Override
    public Object Testinterceptor() throws InvocationTargetException, IllegalAccessException {
        return method.invoke(object,plugin);
    }
}
