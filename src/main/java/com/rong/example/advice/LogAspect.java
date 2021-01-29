package com.rong.example.advice;

import com.alibaba.fastjson.JSON;
import com.rong.example.bean.bo.PageLimit;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 记录请求进和出的参数以及一定格式的返回值
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("execution(public * com.rong.example.api.*.*(..))")
    public void Pointcut() {
    }

    //环绕增强
    @Around("Pointcut()")
    public Object aroundMethod(ProceedingJoinPoint point) throws Throwable {

        long startTimeMillis = System.currentTimeMillis();

        ServletRequestAttributes sra = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        String uri = request.getRequestURI();
        String query = StringUtils.isEmpty(request.getQueryString())? "":(", 查询参数："+request.getQueryString());


        //记录请求日志
        log.info("进入方法："+uri+", 请求："+JSON.toJSONString(pachageHttpRequestBody(point))+query);

        Object result = point.proceed();


        //记录响应日志
        String resultStr=result==null? "{}":JSON.toJSONString(result);
        long consumeTime=System.currentTimeMillis() - startTimeMillis;
        log.info("退出方法："+uri+", 耗时： "+ consumeTime +" ms, 响应："+ resultStr);

        return result;
    }

    /***
    * @Description:  封装入参
    * @Param: [point]
    * @return: java.util.Map
    * @Author: rongyi
    * @Date: 2020-09-19 14:35:35
    */
    private Map pachageHttpRequestBody(ProceedingJoinPoint point) {
        Map parmterMap = new HashMap();
        Object[] args = point.getArgs();
        String[] paramsName =  ((MethodSignature) point.getSignature()).getParameterNames();

        if(args != null && paramsName != null && args.length > 0 && paramsName.length > 0) {
            for (int i=0; i< paramsName.length; i++) {
                // sb.append(" ").append(paramsName[i]).append(" = ").append(args[i]).append(",");
                if (args[i] instanceof HttpServletRequest
                        || args[i] instanceof HttpServletResponse)
                {
                    continue;
                }
                parmterMap.put(paramsName[i],args[i]);
            }
        }
        return parmterMap;
    }

}
