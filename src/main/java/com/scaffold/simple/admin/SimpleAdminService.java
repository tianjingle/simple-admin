package com.scaffold.simple.admin;

import com.scaffold.simple.admin.other.*;
import com.scaffold.simple.admin.utils.MyMapper;
import com.scaffold.simple.san.TianTest;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author: tianjl
 * @Date: 2020/9/1 9:53
 * @Eamil: 2695062879@qq.com
 */
@SpringBootApplication
@MapperScan(basePackages = "com.scaffold.simple.admin.infrestraction.db",markerInterface = MyMapper.class)
public class SimpleAdminService extends SpringBootServletInitializer implements ApplicationContextAware, EnvironmentAware {

    private AnnotationConfigApplicationContext test;

    private static ApplicationContext context;

    private static Environment environment;
    public static void main(String[] args) {
        AnnotationConfigServletWebServerApplicationContext tesst;
        SpringApplication.run(SimpleAdminService.class, args);
        System.out.println(System.getProperty("server.port"));
        MyApplicationEvent myApplicationEvent=new MyApplicationEvent("test","123","tjl");
        context.publishEvent(myApplicationEvent);
        System.out.println(context.getBean(TestImport.class).toString());
        System.out.println(context.getBean(TestMember.MemberClass.class).toString());
        System.out.println(context.getBean(TestMyJava.class).toString());
        System.out.println(environment.toString());
        System.out.println(context.getBean(TianTest.class));
        System.out.println(environment.getProperty("tianjingle"));
    }





        @Override
        protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SimpleAdminService.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context=applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment=environment;
    }
}