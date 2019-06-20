package com.api.back.test;

import cn.hutool.core.util.StrUtil;
import com.module.admin.app.mapper.AppProductMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangdong
 * @date: 2019/6/17 18:30
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class GeneratorIdTest {
    Map<String, AtomicInteger> map=new ConcurrentHashMap<>();
    @Resource
    private AppProductMapper productMapper;


    @Test
    public void  test1(){
        for (int i = 0; i <20 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j <4000 ; j++) {
                        String s = productMapper.callProductNum(1);
                        AtomicInteger localDateTime = map.get(s);
                        if (localDateTime!=null){
                            localDateTime.addAndGet(1);
                            System.out.println(StrUtil.format("============有重复出现key:{}，次数:{}",s,localDateTime.get()));
                        }else {
                            map.put(s,new AtomicInteger(0));
                        }
                    }
                }
            }).start();
        }
        try {
            Thread.sleep(300000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(map.size());
    }
}
