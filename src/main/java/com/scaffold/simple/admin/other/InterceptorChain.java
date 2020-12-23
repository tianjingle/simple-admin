package com.scaffold.simple.admin.other;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tianjl
 * @Date: 2020/12/23 7:28
 * @Eamil: 2695062879@qq.com
 */
public class InterceptorChain {

    private List<MyPlugin> interceptor=new ArrayList<>(10);


    public Object pluginAll(Object o){
        for (int i = 0; i <interceptor.size() ; i++) {
           o= interceptor.get(i).plugin(o);
        }
        return o;
    }

    public void addInterceptor(MyPlugin interceptor){
        this.interceptor.add(interceptor);
    }
}
