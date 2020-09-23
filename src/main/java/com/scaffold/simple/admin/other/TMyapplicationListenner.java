package com.scaffold.simple.admin.other;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @Author: tianjl
 * @Date: 2020/9/13 14:19
 * @Eamil: 2695062879@qq.com
 */
public class TMyapplicationListenner implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("TmyApplication....");
        System.out.println(event.toString());
    }
}
