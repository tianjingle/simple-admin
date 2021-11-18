package com.scaffold.simple.admin.application.dto.user;

/**
 * @Author tianjl
 * @Date 2021/11/17 14:33
 * @Discription disc
 */
public class OOO {

    private String name;

    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "OOO{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
