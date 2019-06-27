package com.module.base.common.util;

import com.module.base.common.constant.SmsPrefix;
import com.module.base.common.dto.SmsVerifyCodeDto;
import com.module.common.ResponseCode;
import com.module.common.exception.LimitException;
import org.redisson.api.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author wangdong
 * @date: 2019/6/25 8:53
 */
public class HandlerSmsSender {
    private static int EXPIRE_SECOND = 59;
    private static int DAY_RATE_LIMITER = 10;

    @Resource
    private RedissonClient redissonClient;

    public void sendVerifyCode(SmsPrefix prefix, SmsVerifyCodeDto dto) {
        //每个号码天只能发送10次
        RRateLimiter rateLimiter = redissonClient.getRateLimiter(prefix+dto.getMobile());
        if (!rateLimiter.isExists()) {
            rateLimiter.trySetRate(RateType.OVERALL, DAY_RATE_LIMITER, 1, RateIntervalUnit.DAYS);
        }
        if (rateLimiter.tryAcquire()) {
            RBucket<SmsVerifyCodeDto> bucket = redissonClient.getBucket(prefix+dto.getMobile());
            if (!bucket.isExists()) {
                //todo 发送验证码放在redis之前
                bucket.set(dto);
                bucket.expireAt(Date.from(Instant.now().plus(EXPIRE_SECOND, ChronoUnit.SECONDS)));
            }
        } else {
            throw new LimitException(ResponseCode.C_530001);
        }
    }
}
