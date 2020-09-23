package com.scaffold.simple.admin;

import com.scaffold.simple.admin.other.MyApplicationEvent;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: tianjl
 * @Date: 2020/9/1 9:53
 * @Eamil: 2695062879@qq.com
 */
@SpringBootApplication
public class SimpleAdminService extends SpringBootServletInitializer implements ApplicationContextAware {

    private AnnotationConfigApplicationContext test;

    private static ApplicationContext context;
    public static void main(String[] args) {
        AnnotationConfigServletWebServerApplicationContext tesst;
        SpringApplication.run(SimpleAdminService.class, args);
        System.out.println(System.getProperty("server.port"));
        MyApplicationEvent myApplicationEvent=new MyApplicationEvent("test","123","tjl");
        context.publishEvent(myApplicationEvent);
    }





        @Override
        protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SimpleAdminService.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context=applicationContext;
    }
}