package com.scaffold.simple.admin.other;

import lombok.Data;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Author: tianjl
 * @Date: 2020/9/13 14:04
 * @Eamil: 2695062879@qq.com
 */
@Component
public class MyApplicationListener implements ApplicationListener {

//    AutowiredAnnotationBeanPostProcessor
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
