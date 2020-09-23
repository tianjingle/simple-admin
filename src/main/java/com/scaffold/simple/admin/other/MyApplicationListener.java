package com.scaffold.simple.admin.other;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author: tianjl
 * @Date: 2020/9/13 14:04
 * @Eamil: 2695062879@qq.com
 */
@Component
public class MyApplicationListener implements ApplicationListener {

    /***
     * 监听事件
     * @param event
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("myApplication....");
        if (event instanceof MyApplicationEvent){
            System.out.println("myapplicationEvent...");
            System.out.println(event.toString());
        }
    }
}
