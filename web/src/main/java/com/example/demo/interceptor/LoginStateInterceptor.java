package com.example.demo.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LoginStateInterceptor
 * @Description 登录状态拦截器
 * @Author scyking
 * @Date 2018/11/21 15:35
 * @Version 1.0
 */
public class LoginStateInterceptor extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // TODO 登录状态判断
        System.out.println("LoginStateInterceptor: to do something !");
        return true;
    }
}
