package com.api.app.spring;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.module.api.app.entity.AppCart;
import com.module.api.app.mapper.AppCartMapper;
import com.module.api.app.mapper.ProductMapper;
import com.module.api.app.query.LoginQuery;
import com.module.api.app.query.ProductQuery;
import com.module.api.app.service.ProductService;
import com.module.api.app.service.UserService;
import com.module.base.common.constant.RedisPrefix;
import com.module.base.common.dto.SmsVerifyCodeDto;
import com.module.base.common.service.SMSSendService;
import com.module.common.bean.PageQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;


@SpringBootTest
@RunWith(SpringRunner.class)
public class SofaBootDBTest {
    @SofaReference
    RedissonClient redissonClient;
    @SofaReference
    SMSSendService smsSendService;
    @SofaReference
    UserService userService;
    @Resource
    private AppCartMapper cartMapper;

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
     * 测试正常返回
     */
    @Test
    public void successMethod(){
        SmsVerifyCodeDto dto=new SmsVerifyCodeDto();
        dto.setMobile("15001200836");
        dto.setSmsMsg("1234");
        smsSendService.sendVerifyCode(RedisPrefix.LOGIN_VERIFY_CODE,dto);
        LoginQuery query=new LoginQuery();
        query.setMobile("15001200836");
        query.setVerifyCode("1234");
        userService.login(query);
//        smsSendService.validVerifyCode(RedisPrefix.LOGIN_VERIFY_CODE,"15001200836","1234");
    }


    @Test
    public void test3(){
        RBucket<Object> bucket = redissonClient.getBucket("1");
        if (!bucket.isExists()){
            bucket.set("abcd");
        }

    }

    @Test
    public void instert(){
        AppCart cart=new AppCart();
        cart.setCreateTime(LocalDateTime.now());
        cartMapper.insert(cart);
    }






}
