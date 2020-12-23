package com.scaffold.simple.admin.other;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tianjl
 * @Date: 2020/12/22 18:34
 * @Eamil: 2695062879@qq.com
 */
public class MybatisPluginMain {


    public static void main(String[] args) {
        MybatisPluginTest test=new MybatisPluginTestImpl();
        MyPlugin test1=new MyPluginOne();
        MyPlugin test2=new MyPluginTwo();
//        test= (MybatisPluginTest) test1.plugin(test);
//        test= (MybatisPluginTest) test2.plugin(test);
        InterceptorChain interceptorChain=new InterceptorChain();
        interceptorChain.addInterceptor(test1);
        interceptorChain.addInterceptor(test2);
        test= (MybatisPluginTest) interceptorChain.pluginAll(test);
        test.asy();
    }
}
