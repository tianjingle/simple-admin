package com.scaffold.simple.admin.other;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author: tianjl
 * @Date: 2020/9/27 9:39
 * @Eamil: 2695062879@qq.com
 */
@Configuration
@PropertySource(value = {"classpath:application-tianjl.properties"})
public class TestMember implements DefaultTest {

    @Data
    @Component
    public class MemberClass{

        private String name="cheng yuan lei";

        private MemberClass(){
            System.out.println("成员内部类");
        }
    }
}
