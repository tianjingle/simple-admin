package com.scaffold.simple.san;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: tianjl
 * @Date: 2020/10/8 13:52
 * @Eamil: 2695062879@qq.com
 */
@Configuration
public class MySelectApp {

    @Bean
    public Myselect get(){
        return new Myselect();
    }
}
