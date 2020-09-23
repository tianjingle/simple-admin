package com.scaffold.simple.admin.other;

/**
 * @Author: tianjl
 * @Date: 2020/9/20 15:49
 * @Eamil: 2695062879@qq.com
 */
public class TestMyJavaMethod {

    public static String print(String tt, MyFunctionTest<String,String> t){
        System.out.println(t.toString());
       return t.apply(tt);
    }

}
