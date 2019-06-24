package com.api.back.test;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.module.admin.app.service.AppProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class SofaBootModuleTest {

    @SofaReference
    private AppProductService productService;
    @Test
    public void sayHello(){
        System.out.println(productService.productCountDown());
    }
}
