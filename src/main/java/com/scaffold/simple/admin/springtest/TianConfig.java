package com.scaffold.simple.admin.springtest;

import lombok.Data;

/**
 * @Author: tianjl
 * @Date: 2020/12/20 12:26
 * @Eamil: 2695062879@qq.com
 */
@TianMapper
@Data
public class TianConfig {

    public String toString(){
        return this.getClass()+"TianConfig";
    }
}
