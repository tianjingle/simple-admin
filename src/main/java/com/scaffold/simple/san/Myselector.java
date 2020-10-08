package com.scaffold.simple.san;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author: tianjl
 * @Date: 2020/10/8 13:54
 * @Eamil: 2695062879@qq.com
 */
public class Myselector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        System.out.println("selector测试哦");
        importingClassMetadata.getAnnotationTypes().forEach(System.out::println);
        return new String[]{MySelectApp.class.getName()};
    }
}
