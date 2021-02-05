package com.rong.example.staticFactory.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.rong.example.advice.SessionContextHolder;
import com.rong.example.bean.bo.SessionContext;
import com.rong.example.constant.ErrorCodeEnum;
import com.rong.example.constant.ExampleConstants;
import com.rong.example.expection.ServiceException;
import com.rong.example.staticFactory.MethodCheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * /test/testProject 接口校验实现类
 */
@Component
@Slf4j
public class TestProjectMethodCheckImpl implements MethodCheckService {

    /**
     * 方法 uri
     */
    final String METHOD_URI = "/test/testProject";

    @Override
    public boolean accept(HttpServletRequest request, HttpServletResponse response) {

        return METHOD_URI.equals(request.getRequestURI());
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String appVersion = request.getHeader("appVersion");

        //存在消息头appVersion时，校验是否高于 1.5.0
        if(StrUtil.isNotEmpty(appVersion) && appVersion.compareTo(ExampleConstants.APP_VERSION) < 0){

            //这里抛出异常，也能被ControllerAdvice全局异常捕获到
            log.error("app版本过低：appVersion="+appVersion);
            throw new ServiceException(ErrorCodeEnum.APP_VERSION_ERROR.getCode());
        }

        SessionContext context = new SessionContext();
        context.setAppVersion(appVersion);
        SessionContextHolder.setContext(context);
        log.info("设置上下文："+ JSON.toJSONString(context));
    }
}
