package com.scaffold.simple.admin.other;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: tianjl
 * @Date: 2020/12/22 18:28
 * @Eamil: 2695062879@qq.com
 */
public class MybatisPluginsProxy implements InvocationHandler {

    private Object object;

    private MyPlugin interceptors;

    MybatisPluginsProxy(Object o,MyPlugin o1){
        this.object=o;
        this.interceptors=o1;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MyInvocation myInvocation=new MyInvocationTest(object,method,args);
        return interceptors.interceptor(myInvocation);
//        Object object1=method.invoke(object,args);
//        System.out.println("-----执行后");
//        return object1;
    }

    public static Object wrap(Object o,MyPlugin target){
        return Proxy.newProxyInstance(o.getClass().getClassLoader(),
                o.getClass().getInterfaces(),new MybatisPluginsProxy(o,target));
    }

}
