package com.rong.example.advice.accessCheck;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 方法校验服务工厂类
 */
@Component
public class AccessCheckFacotry implements ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;

    private static List<AccessCheckService> checkImpls=new ArrayList<>();


    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, AccessCheckService> map = this.applicationContext.getBeansOfType(AccessCheckService.class);
        for(Map.Entry<String, AccessCheckService> entry: map.entrySet()){
            checkImpls.add(entry.getValue());
        }

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext= applicationContext;
    }


    /**
     * 判断方法对应的校验实现类，然后执行
     */
    public static void  methodCheck(HttpServletRequest request, HttpServletResponse response) throws Exception{

        checkImpls.stream().forEach( e -> {if(e.accept(request, response)) e.execute(request, response);});
    }

}
