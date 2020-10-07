package com.scaffold.simple.admin.other;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.ServletConfig;

/**
 * @Author: tianjl
 * @Date: 2020/9/6 14:23
 * @Eamil: 2695062879@qq.com
 */
@Component
public class MyAppTest implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {

        InterceptorRegistration interceptorRegistration;
        DispatcherServlet ttt;
        BeanNameUrlHandlerMapping beanNameUrlHandlerMapping;
        RequestMappingHandlerMapping requestMappingHandlerMapping;
        DispatcherServletAutoConfiguration dispatcherServletAutoConfiguration;
        System.out.println("注册bean");
        //这里的BeanDefinitionRegistry应该就是content
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        //这里就是bean工厂

        System.out.println("这里是工厂");
    }
}
