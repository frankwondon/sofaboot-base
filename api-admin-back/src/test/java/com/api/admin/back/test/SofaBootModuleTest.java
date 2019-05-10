package com.api.admin.back.test;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SofaBootModuleTest {
    @SofaReference
    private BackUserService backUserService;

    @Test
    public void sayHello(){
        System.out.println(serviceA.sayHello(new Date()));
        System.out.println(serviceB.sayHello(new Date()));
    }
}
