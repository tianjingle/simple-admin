package com.scaffold.simple.admin.springtest;

import lombok.Data;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.lang.annotation.Annotation;

/**
 * @Author: tianjl
 * @Date: 2020/12/20 12:35
 * @Eamil: 2695062879@qq.com
 */
@Data
public class TianClassPathMapperScanner extends ClassPathBeanDefinitionScanner {

    private Class<? extends Annotation> annotationClass;


    public TianClassPathMapperScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }


}
