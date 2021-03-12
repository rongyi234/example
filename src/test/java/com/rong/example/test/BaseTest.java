package com.rong.example.test;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 测试基类，其他测试类直接继承这个类就好了，不用再加注解
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class BaseTest {

    @Before
    public void init() {
        System.out.println(">> 开始测试-----------------");
    }

    @After
    public void after() {
        System.out.println(">> 测试结束-----------------");
    }
}
