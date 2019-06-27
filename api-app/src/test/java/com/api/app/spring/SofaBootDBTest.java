package com.api.app.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@SpringBootTest
@ContextConfiguration
@RunWith(SpringRunner.class)
public class SofaBootDBTest {
    @Resource
    RedissonClient redissonClient;

    @Test
    public void list(){
        RBucket<Object> bucket = redissonClient.getBucket("1");
    }


}
