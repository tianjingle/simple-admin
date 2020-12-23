package com.scaffold.simple.admin.springtest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 * @Author: tianjl
 * @Date: 2020/12/20 12:27
 * @Eamil: 2695062879@qq.com
 */
public class TianImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, BeanFactoryAware {

    private ResourceLoader resourceLoader;
    private BeanFactory beanFactory;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        TianClassPathMapperScanner tianClassPathMapperScanner=new TianClassPathMapperScanner(registry);
        //设置一下mapper
        tianClassPathMapperScanner.setAnnotationClass(TianMapper.class);
        tianClassPathMapperScanner.addIncludeFilter(new AnnotationTypeFilter(TianMapper.class));
        tianClassPathMapperScanner.scan("com.scaffold.simple.admin.springtest");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
