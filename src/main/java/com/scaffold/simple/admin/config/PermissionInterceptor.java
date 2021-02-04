package com.scaffold.simple.admin.config;

import com.scaffold.simple.admin.application.dto.user.UserDto;
import com.scaffold.simple.admin.utils.SessionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: tianjl
 * @Date: 2020/5/28 16:52
 * @Eamil: 2695062879@qq.com
 */
@Slf4j
@Service
public class PermissionInterceptor implements HandlerInterceptor {


    DispatcherServlet dispatcherServlet;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器");
        UserDto userDto=new UserDto();
        userDto.setUserId("tianjingle");
        userDto.setUserName("tianjingle");
        SessionUtils.setUser(userDto);
        return true;
    }

    /**
     * 向前端暴露异常
     * @param response
     */
    private void makeFail(HttpServletResponse response) {

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
