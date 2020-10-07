package com.scaffold.simple.admin.other;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportAware;
import org.springframework.stereotype.Component;

/**
 * @Author: tianjl
 * @Date: 2020/9/6 14:23
 * @Eamil: 2695062879@qq.com
 */
@Import(TestImport.class)
@Configuration
public class MyAppTest1 implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {

        ConfigurationClassPostProcessor configurationClassPostProcessor;
//        ImportAware
        AnnotatedBeanDefinition annotatedBeanDefinition;
        System.out.println("注册bean1");
        //这里的BeanDefinitionRegistry应该就是content
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        //这里就是bean工厂

        System.out.println("这里是工厂1");
    }

}
