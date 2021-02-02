package com.rong.example;

import com.rong.example.advice.PageLimitHolderFilter;
import com.rong.example.support.QualifiedBeanNameGenerator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.boot.Banner;

import java.util.Arrays;


@SpringBootApplication
@ComponentScan(basePackages = "com.rong.example", nameGenerator = QualifiedBeanNameGenerator.class)
@MapperScan(basePackages = { "com.rong.example.mapper"}, nameGenerator = QualifiedBeanNameGenerator.class)
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableCaching(proxyTargetClass = true)
@EnableTransactionManagement
@EnableFeignClients
@EnableScheduling
public class ExampleApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ExampleApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
		System.out.println("\n-------------------example project start successfully------------------\n");
	}






}
