package com.rong.example.advice;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.rong.example.bean.bo.CommonHttpResponse;
import com.rong.example.constant.ExampleConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletResponse;

/**
 * 统一处理controller层返回的数据格式
 */
@Configuration
@RestControllerAdvice(basePackages = {"com.rong.example.api","com.rong.example.advice"})
@Slf4j
public class ResultResponseAdvice implements ResponseBodyAdvice<Object> {

    @Autowired
    private HttpServletResponse httpServletResponse;


    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {

        //消息头标志位存在，原样返回
        if(StrUtil.isNotEmpty(httpServletResponse.getHeader(ExampleConstants.HTTP_FLAG_ORIGIN))){
            return jsonSerialize( body, body);
        }

        CommonHttpResponse commonHttpResponse= CommonHttpResponse.deserialize(body);

        return jsonSerialize( body, commonHttpResponse);
    }

    /**
     * 判断是否需要主动json序列化
     */
    private Object jsonSerialize(Object body, Object outPut){
        //处理返回值是String的情况，系统不会再进行json序列化，需要手动，否则报错：xxx cannot be cast to java.lang.String
        if (body instanceof String) {
            return JSON.toJSONString(outPut);
        }

        //系统自动进行json序列化
        return outPut;
    }

}
