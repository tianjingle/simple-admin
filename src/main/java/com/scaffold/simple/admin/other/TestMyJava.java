package com.scaffold.simple.admin.other;

import lombok.Data;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author: tianjl
 * @Date: 2020/9/20 15:47
 * @Eamil: 2695062879@qq.com
 */
@Configuration
@ComponentScan(value = "com.scaffold.simple.san")
public class TestMyJava {
    private String name="test my java";
}
