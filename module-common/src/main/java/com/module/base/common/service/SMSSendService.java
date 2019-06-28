package com.module.base.common.service;


import com.module.base.common.constant.RedisPrefix;
import com.module.base.common.dto.SmsVerifyCodeDto;

/**
 * 发短信的service
 * @author wangdong
 * @date: 2019/6/24 16:23
 */
public interface SMSSendService {

    void sendVerifyCode(RedisPrefix prefix, SmsVerifyCodeDto dto);

    /**
     * 验证手机验证码
     *
     * @param prefix 验证码类型
     * @param phone  手机号
     * @param code   验证码
     */
    boolean validVerifyCode(RedisPrefix prefix, String phone, String code);
}
