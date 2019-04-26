package com.web.controller;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.module.a.service.ServiceA;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.module.b.service.ServiceB;

import java.util.Date;

@RestController
public class IndexController {
    @SofaReference
    private ServiceA serviceA;
    @SofaReference
    private ServiceB serviceB;
    @RequestMapping("/hello")
    public String sayHello(){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(serviceA.sayHello(new Date()));
        stringBuilder.append(serviceB.sayHello(new Date()));
        return stringBuilder.toString();
    }
}
