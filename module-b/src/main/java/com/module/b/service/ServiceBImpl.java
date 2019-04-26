package com.module.b.service;

import com.alipay.sofa.runtime.api.annotation.SofaService;

import java.util.Date;
@SofaService
public class ServiceBImpl implements ServiceB {
    public String sayHello(Date date) {
        String name="com.module.b.service-b";
        return "hello my name is "+name;
    }
}
