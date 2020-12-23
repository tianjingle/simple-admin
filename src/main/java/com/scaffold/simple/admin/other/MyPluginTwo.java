package com.scaffold.simple.admin.other;

import tk.mybatis.mapper.genid.GenId;

import java.awt.print.Book;
import java.lang.reflect.InvocationTargetException;

/**
 * @Author: tianjl
 * @Date: 2020/12/22 19:03
 * @Eamil: 2695062879@qq.com
 */
public class MyPluginTwo implements MyPlugin {
    @Override
    public Object interceptor(MyInvocation myInvocation) throws InvocationTargetException, IllegalAccessException {
        System.out.println("--插件2开始");
        Object o=myInvocation.Testinterceptor();
        System.out.println("--插件2结束");
        return o;
    }

    @Override
    public Object plugin(Object o) {
        //将当前插件整合到上层代理中，并返回整合之后的代理类
        return MybatisPluginsProxy.wrap(o,this);
    }
}
