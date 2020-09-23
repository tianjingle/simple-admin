package com.scaffold.simple.admin.other;

import lombok.Builder;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

/**
 * @Author: tianjl
 * @Date: 2020/9/13 14:10
 * @Eamil: 2695062879@qq.com
 */
@Data
public class MyApplicationEvent extends ApplicationEvent {

    private String age;

    private String name;
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public MyApplicationEvent(Object source,String age,String name) {
        super(source);
        this.age=age;
        this.name=name;
    }
}
