package com.rong.example.feign.advice;

import cn.hutool.core.util.StrUtil;
import com.rong.example.advice.SessionContextHolder;
import com.rong.example.bean.bo.SessionContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * feign拦截器
 */
@Component
@Slf4j
public class FeignInterceptor implements RequestInterceptor{

    @Override
    public void apply(RequestTemplate template) {
        SessionContext sessionContext = SessionContextHolder.getContext();
        if(sessionContext != null){
            // appVersion
            if(StrUtil.isNotEmpty(sessionContext.getAppVersion())) {
                log.info("feign请求添加消息头：appVersion="+sessionContext.getAppVersion());
                template.header("appVersion", sessionContext.getAppVersion());
            }


        }
    }
}
