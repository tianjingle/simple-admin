//package com.scaffold.simple.admin.lock.config;
//
//import com.scaffold.simple.admin.utils.SessionUtils;
//import org.springframework.core.MethodParameter;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.http.server.ServletServerHttpResponse;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
//
//import javax.servlet.http.Cookie;
//
///**
// * @Author: tianjl
// * @Date: 2021/2/4 13:33
// * @Eamil: 2695062879@qq.com
// */
//@ControllerAdvice
//public class DoubleSubmitAdvice implements ResponseBodyAdvice<Object> {
//
//
//    @Override
//    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
//        return true;
//    }
//
//
//    @Override
//    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
//        if (!StringUtils.isEmpty(SessionUtils.getSubmitToken())){
//            //token有效 则将token放入cookie中
//            Cookie tokenCookie = new Cookie("ztoken", SessionUtils.getSubmitToken());
//            tokenCookie.setPath("/");
//            tokenCookie.setDomain("localhost");
//            // 会话级cookie，关闭浏览器失效
//            tokenCookie.setMaxAge(-1);
//            ServletServerHttpResponse resp = (ServletServerHttpResponse)response;
//            resp.getServletResponse().addCookie(tokenCookie);
//        }
//        return body;
//    }
//}