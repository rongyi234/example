package com.rong.example.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private UserInfoInterceptor userInfoInterceptor;

    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(userInfoInterceptor)
                //配置拦截路径，其中/**表示当前目录以及所有子目录（递归），/*表示当前目录，不包括子目录。
                //配置"/userMsg"，就只能拦截 ..../userMsg 这种
                .addPathPatterns("/userMsg/**")
                .excludePathPatterns("/userMsg/select","/userMsg/select.do");
    }

    @Bean
    public FilterRegistrationBean pageLimitFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new PageLimitHolderFilter());
        registrationBean.setUrlPatterns(Arrays.asList(new String[]{"/*"}));
        registrationBean.setOrder(3);
        return registrationBean;
    }
}
