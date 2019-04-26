package com.web.test;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.module.a.service.ServiceA;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.module.b.service.ServiceB;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SofaBootModuleTest {
    @SofaReference
    private ServiceA serviceA;
    @SofaReference
    private ServiceB serviceB;


    @Test
    public void sayHello(){
        System.out.println(serviceA.sayHello(new Date()));
        System.out.println(serviceB.sayHello(new Date()));
    }
}
