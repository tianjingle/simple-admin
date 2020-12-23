package com.scaffold.simple.admin.springtest;

import java.lang.annotation.*;

/**
 * @Author: tianjl
 * @Date: 2020/12/20 12:24
 * @Eamil: 2695062879@qq.com
 */

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
public @interface  TianMapper {
}
