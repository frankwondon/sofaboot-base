package com.module.a.service;



import com.alipay.sofa.runtime.api.annotation.SofaService;

import java.util.Date;

@SofaService
public class ServiceAImpl implements ServiceA {
    public String sayHello(Date date) {
        String name="com.module.b.service-a";
        return "hello my name is "+name;
    }
}
