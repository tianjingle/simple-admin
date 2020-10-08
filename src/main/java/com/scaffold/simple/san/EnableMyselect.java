package com.scaffold.simple.san;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author: tianjl
 * @Date: 2020/10/8 13:51
 * @Eamil: 2695062879@qq.com
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({Myselector.class})
public @interface EnableMyselect {
}
