package com.api.app.controller.common;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.module.api.app.service.ProductService;
import com.module.api.app.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wangdong
 * @date: 2019/7/1 11:12
 */
@RequestMapping("test")
@Controller
public class TestCountroller {
    @SofaReference
    private TestService testService;
    @RequestMapping("t1")
    public String test1(){
        return testService.sayHello();
    }
}
