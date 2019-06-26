package com.module.base.common.util;

import com.module.base.common.dto.SmsBaseDto;
import com.module.base.common.dto.SmsVerifyCodeDto;
import org.redisson.api.*;

import javax.annotation.Resource;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author wangdong
 * @date: 2019/6/25 8:53
 */
public class HandlerSmsSender {
    private static int EXPIRE_SECOND=59;
    @Resource
    private RedissonClient redissonClient;

    public void sendVerifyCode(SmsVerifyCodeDto dto){
        RBucket<SmsVerifyCodeDto> bucket = redissonClient.getBucket(dto.getMobile());
        if (!bucket.isExists()){
            //每天只能发送10次
            RRateLimiter rateLimiter = redissonClient.getRateLimiter(dto.getMobile());
            if (rateLimiter.isExists()){
            }else {
                rateLimiter.trySetRate(RateType.OVERALL, 10, 1, RateIntervalUnit.DAYS);
            }
            //todo 发送验证码放在redis之前
            bucket.set(dto);
            bucket.expireAt(Date.from(Instant.now().plus(EXPIRE_SECOND, ChronoUnit.SECONDS)));
        }
    }
}
