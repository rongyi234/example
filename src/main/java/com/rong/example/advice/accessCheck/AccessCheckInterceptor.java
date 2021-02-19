package com.rong.example.advice.accessCheck;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 方法准入规则校验 拦截器
 */
@Component
@Slf4j
public class AccessCheckInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)	throws Exception {

        //各方法执行自己的校验逻辑
        AccessCheckFacotry.methodCheck(request,response);

        return true;
    }
}
