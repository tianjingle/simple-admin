package com.scaffold.simple.admin.other;

import javax.crypto.spec.PSource;
import java.lang.reflect.InvocationTargetException;

/**
 * @Author: tianjl
 * @Date: 2020/12/22 19:03
 * @Eamil: 2695062879@qq.com
 */
public class MyPluginOne implements MyPlugin {
    @Override
    public Object interceptor(MyInvocation myInvocation) throws InvocationTargetException, IllegalAccessException {
        System.out.println("--插件1");

       Object o= myInvocation.Testinterceptor();
        System.out.println("插件1结束");
        return o;
    }

    @Override
    public Object plugin(Object o) {
        //将当前插件整合到上层代理中，并返回整合之后的代理类
        return MybatisPluginsProxy.wrap(o,this);
    }


}
