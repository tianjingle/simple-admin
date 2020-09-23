package com.scaffold.simple.admin.other;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @Author: tianjl
 * @Date: 2020/9/4 9:57
 * @Eamil: 2695062879@qq.com
 */
@Data
@AllArgsConstructor
public class TestApplicationListener implements SpringApplicationRunListener {

    public TestApplicationListener(SpringApplication application, String[]  args){
        System.out.println("constructor");
    }

    @Override
    public void starting() {
        System.out.println("start");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        System.out.println(environment.toString());
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println("拿到容器");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {

        System.out.println("容器加载完毕");
    }

    @Override
    public void started(ConfigurableApplicationContext context) {

    }

    @Override
    public void running(ConfigurableApplicationContext context) {

        System.out.println("run方法");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {

        System.out.println("失败");
    }
}
