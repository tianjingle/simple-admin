//package com.scaffold.simple.admin.config;
//
//import com.alibaba.druid.support.http.StatViewServlet;
//import com.alibaba.druid.support.http.WebStatFilter;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @Author: tianjl
// * @Date: 2020/12/28 7:52
// * @Eamil: 2695062879@qq.com
// */
//
//@Configuration
//public class DruidConfigDemo {
//
//    /**
//     * 配置监控服务器
//     *
//     * @return 返回监控注册的servlet对象
//     */
//    @Bean
//    public ServletRegistrationBean statViewServletDemo() {
//        ServletRegistrationBean srb = new ServletRegistrationBean(new StatViewServlet(), "/tjl/druid/*");
//        // 添加IP白名单
//        srb.addInitParameter("allow", "192.168.21.119");
//        // 添加IP黑名单，当白名单和黑名单重复时，黑名单优先级更高
//        srb.addInitParameter("deny", "192.168.25.123");
//        // 添加控制台管理用户
//        srb.addInitParameter("loginUsername", "tjl");
//        srb.addInitParameter("loginPassword", "tjl");
//        // 是否能够重置数据
//        srb.addInitParameter("resetEnable", "false");
//        return srb;
//    }
//    /**
//     * 配置服务过滤器
//     *
//     * @return 返回过滤器配置对象
//     */
//    @Bean
//    public FilterRegistrationBean statFilterDemo() {
//        FilterRegistrationBean frb = new FilterRegistrationBean(new WebStatFilter());
//        // 添加过滤规则
//        frb.addUrlPatterns("/*");
//        // 忽略过滤格式
//        frb.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*,");
//        return frb;
//    }
//}
