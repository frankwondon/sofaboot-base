package com.api.back.test;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class SofaBootModuleTest {
    @SofaReference
    @Test
    public void sayHello(){
    }
}
