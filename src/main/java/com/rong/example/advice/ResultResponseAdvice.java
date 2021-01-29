package com.rong.example.advice;


import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.rong.example.bean.bo.CommonHttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 统一处理controller层返回的数据格式
 */
@Configuration
@RestControllerAdvice(basePackages = {"com.rong.example.api","com.rong.example.advice"})
@Slf4j
public class ResultResponseAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {


        CommonHttpResponse commonHttpResponse= CommonHttpResponse.deserialize(body);

        return commonHttpResponse;
    }
}
