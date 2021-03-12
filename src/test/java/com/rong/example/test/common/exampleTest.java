package com.rong.example.test.common;

import com.rong.example.api.TestController;
import com.rong.example.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class exampleTest extends BaseTest {

    @Autowired
    private TestController testController;

    @Test
    public void testProject(){
        testController.testProject();

    }
}
