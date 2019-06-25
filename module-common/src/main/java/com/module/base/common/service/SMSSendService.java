package com.module.base.common.service;

import com.module.base.common.dto.SmsBaseDto;

/**
 * 发短信的service
 * @author wangdong
 * @date: 2019/6/24 16:23
 */
public interface SMSSendService {

    /**
     * 发送手机号登陆验证码
     * @param phone 手机号
     */
    public void sendLoginVerifyCode(String phone);
}
