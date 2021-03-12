package com.rong.example.advice.accessCheck.impl;

import cn.hutool.core.util.StrUtil;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.rong.example.advice.session.SessionContextHolder;
import com.rong.example.constant.ErrorCodeEnum;
import com.rong.example.constant.ExampleConstants;
import com.rong.example.advice.expection.ServiceException;
import com.rong.example.propLoad.properties.LoadProperties;
import com.rong.example.advice.accessCheck.AccessCheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * /test/testProject 接口校验实现类
 */
@Component
@Slf4j
public class TestProjectAccessCheckImpl implements AccessCheckService {

    /**
     * 方法 uri
     */
    final String METHOD_URI = "/test/testProject";

    /**
     * 缓存计数器，2秒自动失效，初始值为0
     */
    private LoadingCache<String, AtomicInteger> counter= CacheBuilder.newBuilder()
                        .expireAfterWrite(2, TimeUnit.SECONDS)
                        .build(new CacheLoader<String, AtomicInteger>() {
                                    @Override
                                    public AtomicInteger load(String key) throws Exception {
                                        return new AtomicInteger(0);
                                    }
                                });

    @Override
    public boolean accept(HttpServletRequest request, HttpServletResponse response) {

        return METHOD_URI.equals(request.getRequestURI());
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        //频控校验
        rateLimit();

        //消息头校验
        extracted(request);
    }


    /**
     * 接口频控校验，每秒请求次数，不超过阈值（配置项）
     */
    private void rateLimit(){

        //当前秒
        long currentSecond= System.currentTimeMillis()/1000;
        String key = METHOD_URI + currentSecond;

        int currentCount = 1;
        try {
            currentCount = counter.get(key).incrementAndGet();
        } catch (ExecutionException e) {
            log.error("获取接口频控阈值错误"+e.getMessage());
            throw new ServiceException(ErrorCodeEnum.SYSTEM_ERROR.getCode());
        }

        if(currentCount > Integer.parseInt(LoadProperties.paramMap.get(METHOD_URI))){
            log.error("接口频控超过阈值：threshold="+ LoadProperties.paramMap.get(METHOD_URI));
            throw new ServiceException(ErrorCodeEnum.RATELIMIT_ERROR.getCode());
        }
    }



    /**
     * 校验消息头: 存在消息头appVersion时，校验是否高于 1.5.0
     */
    private void extracted(HttpServletRequest request) {
        String appVersion = request.getHeader("appVersion");

        if(StrUtil.isNotEmpty(appVersion) && appVersion.compareTo(ExampleConstants.APP_VERSION) < 0){

            //这里抛出异常，也能被ControllerAdvice全局异常捕获到
            log.error("app版本过低：appVersion="+ appVersion);
            throw new ServiceException(ErrorCodeEnum.APP_VERSION_ERROR.getCode());
        }

        SessionContextHolder.getContext().setAppVersion(appVersion);
    }


}
