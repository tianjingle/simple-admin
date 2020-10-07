package com.scaffold.simple.admin.other;

import lombok.Data;

/**
 * @Author: tianjl
 * @Date: 2020/9/27 8:34
 * @Eamil: 2695062879@qq.com
 */
@Data
public class TestImport {

    private String name="tianjingle de ceshi";
    TestImport(){
        System.out.println("测试import注册bean");
    }
}
