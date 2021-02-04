package com.scaffold.simple.admin.lock.annotation;

import java.lang.annotation.*;

/**
 * @Author: tianjl
 * @Date: 2021/2/4 8:10
 * @Eamil: 2695062879@qq.com
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DoubleSubmitAnnotation {



    /**
     * 是否校验token，默认为false
     * @return
     */
    boolean check() default false;


    /**
     * 是否生成新的token
     * @return
     */
    boolean generate() default false;
}
