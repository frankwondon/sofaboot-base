package com.module.base.common.service.impl;

import com.module.base.common.constant.RedisPrefix;
import com.module.base.common.dto.SmsVerifyCodeDto;
import com.module.base.common.service.SMSSendService;
import com.module.base.common.util.DingWebHookUtil;
import com.module.common.ResponseCode;
import com.module.common.exception.LimitException;
import org.redisson.api.*;

import javax.annotation.Resource;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author wangdong
 * @date: 2019/6/24 18:25
 */
public class SMSSendServiceImpl implements SMSSendService {

    private static int EXPIRE_SECOND = 200;
    private static int DAY_RATE_LIMITER = 10;

    @Resource
    private RedissonClient redissonClient;

    /**
     * 发送手机验证码
     * 条件
     * 1同一个手机号每天只能发送10次（失败也算是一次）
     * 2发送成功后有效期1分钟内不再进行第二次发送
     *
     * @param prefix 验证码类型
     * @param dto    验证码实体
     */
    @Override
    public void sendVerifyCode(RedisPrefix prefix, SmsVerifyCodeDto dto) {
        //每个号码天只能发送10次
        RRateLimiter rateLimiter = redissonClient.getRateLimiter(RedisPrefix.RATE_LIMIT.key()+prefix.key() + dto.getMobile());
        if (!rateLimiter.isExists()) {
            rateLimiter.trySetRate(RateType.OVERALL, DAY_RATE_LIMITER, 1, RateIntervalUnit.DAYS);
        }
        if (rateLimiter.tryAcquire()) {
            RBucket<SmsVerifyCodeDto> bucket = redissonClient.getBucket(prefix.key() + dto.getMobile());
            if (!bucket.isExists()) {
                //todo 发送验证码放在redis之前
                DingWebHookUtil.sendCode(dto.getMobile(),dto.getSmsMsg());
                bucket.set(dto);
                bucket.expireAt(Date.from(Instant.now().plus(EXPIRE_SECOND, ChronoUnit.SECONDS)));
            }
        } else {
            throw new LimitException(ResponseCode.C_530001);
        }
    }

    /**
     * 验证手机验证码
     *
     * @param prefix 验证码类型
     * @param phone  手机号
     * @param code   验证码
     */
    @Override
    public boolean validVerifyCode(RedisPrefix prefix, String phone, String code) {
        RBucket<SmsVerifyCodeDto> bucket = redissonClient.getBucket(prefix.key() + phone);
        if (bucket.isExists()) {
            SmsVerifyCodeDto smsVerifyCodeDto = bucket.get();
            if (smsVerifyCodeDto.getSmsMsg().equals(code)) {
                return true;
            }
        }
        return false;
    }



}
