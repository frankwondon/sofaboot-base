package com.module.base.common.service.impl;

import com.module.base.common.constant.SmsPrefix;
import com.module.base.common.dto.SmsVerifyCodeDto;
import com.module.base.common.service.SMSSendService;
import com.module.base.common.util.HandlerSmsSender;
import com.module.common.util.RandomUtil;

import javax.annotation.Resource;

/**
 * @author wangdong
 * @date: 2019/6/24 18:25
 */
public class SMSSendServiceImpl implements SMSSendService {
    @Resource
    private HandlerSmsSender smsSender;

    @Override
    public void sendLoginVerifyCode(String phone) {
        SmsVerifyCodeDto smsBaseDto = new SmsVerifyCodeDto();
        smsBaseDto.setSmsMsg(RandomUtil.fourVerifyCode());
        smsBaseDto.setMobile(phone);
        smsSender.sendVerifyCode(SmsPrefix.LOGIN_VERIFY_CODE,smsBaseDto);
    }


}
