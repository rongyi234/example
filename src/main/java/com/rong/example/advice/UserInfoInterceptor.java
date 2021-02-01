package com.rong.example.advice;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.rong.example.bean.bo.SessionContext;
import com.rong.example.constant.ErrorCodeEnum;
import com.rong.example.constant.ExampleConstants;
import com.rong.example.expection.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户信息拦截器
 */
@Component
@Slf4j
public class UserInfoInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)	throws Exception {

        String appVersion = request.getHeader("appVersion");

        if(StrUtil.isNotEmpty(appVersion) && appVersion.compareTo(ExampleConstants.APP_VERSION) < 0){

            //这里抛出异常，也能被ControllerAdvice全局异常捕获到
            log.error("app版本过低：appVersion="+appVersion);
            throw new ServiceException(ErrorCodeEnum.APP_VERSION_ERROR.getCode());
        }

        SessionContext context = new SessionContext();
        context.setAppVersion(appVersion);
        SessionContextHolder.setContext(context);
        log.info("设置上下文："+JSON.toJSONString(context));
        return true;
    }
}
