package com.scaffold.simple.admin;

import com.scaffold.simple.admin.other.*;
import com.scaffold.simple.admin.springtest.TianConfig;
import com.scaffold.simple.admin.springtest.TianImportBeanDefinitionRegistrar;
import com.scaffold.simple.admin.utils.MyMapper;
import com.scaffold.simple.san.EnableMyselect;
import com.scaffold.simple.san.Myselect;
import com.scaffold.simple.san.Myselector;
import com.scaffold.simple.san.TianTest;
import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import tk.mybatis.mapper.session.Configuration;
import tk.mybatis.spring.annotation.MapperScan;
import tk.mybatis.spring.mapper.MapperFactoryBean;

/**
 * @Author: tianjl
 * @Date: 2020/9/1 9:53
 * @Eamil: 2695062879@qq.com
 */
@EnableAspectJAutoProxy
@EnableMyselect
@SpringBootApplication
@Import(TianImportBeanDefinitionRegistrar.class)
@MapperScan(basePackages = "com.scaffold.simple.admin.infrestraction.db",markerInterface = MyMapper.class)
public class SimpleAdminService extends SpringBootServletInitializer implements ApplicationContextAware, EnvironmentAware {

    Configuration configuration;
    MapperRegistry mapperRegistry;

    MapperFactoryBean mapperFactoryBean;

    private AnnotationConfigApplicationContext test;

    private static ApplicationContext context;

    private static Environment environment;

    DefaultSqlSession defaultSqlSession;

    Plugin b;
    public static void main(String[] args) {
        AnnotationConfigServletWebServerApplicationContext tesst;
        SpringApplicationBuilder springApplicationBuilder=new SpringApplicationBuilder().sources(SimpleAdminService.class).web(WebApplicationType.SERVLET);
        springApplicationBuilder.run();
        System.out.println(System.getProperty("server.port"));
        MyApplicationEvent myApplicationEvent=new MyApplicationEvent("test","123","tjl");
        context.publishEvent(myApplicationEvent);
        System.out.println(context.getBean(TestImport.class).toString());
        System.out.println(context.getBean(TestMember.MemberClass.class).toString());
        System.out.println(context.getBean(TestMyJava.class).toString());
        System.out.println(environment.toString());
        System.out.println(context.getBean(TianTest.class));
        System.out.println(environment.getProperty("tianjingle"));
        System.out.println("selector测试："+context.getBean(Myselect.class).toString());
        System.out.println("动态注入bean测试："+context.getBean(TianConfig.class).toString());
//        System.exit(1);
    }





//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(SimpleAdminService.class).web(WebApplicationType.REACTIVE);
//    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context=applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment=environment;
    }
}