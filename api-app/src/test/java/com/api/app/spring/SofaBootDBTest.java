package com.api.app.spring;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.module.base.common.service.SMSSendService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;


@SpringBootTest
@ContextConfiguration
@RunWith(SpringRunner.class)
public class SofaBootDBTest {
    @Resource
    RedissonClient redissonClient;
    @SofaReference
    SMSSendService smsSendService;

    /**
     * 限流测试
     */
    @Test
    public void list(){
        RRateLimiter rateLimiter = redissonClient.getRateLimiter("limit");
        if (!rateLimiter.isExists()){
            rateLimiter.trySetRate(RateType.OVERALL,10,5, RateIntervalUnit.SECONDS);
        }
        while (true){
            if ( rateLimiter.tryAcquire()){
                System.out.println(LocalDateTime.now().toString()+"-取到");
            }
        }
    }

    /**
     * 发短信测试
     */
    @Test
    public void tset(){
        smsSendService.sendLoginVerifyCode("15001200836");
    }


}
