package com.module.base.common.cache;

import com.module.base.common.dto.CacheDto;
import com.module.base.common.dto.LoginVerifyCodeDto;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 短信验证码验证
 * @author wangdong
 * @date: 2019/6/24 17:11
 */
public class LoginVerifyCache implements Cache<LoginVerifyCodeDto> {
    /**
     * 登陆验证码的缓存
     */
    private static final Map<String, CacheDto<LoginVerifyCodeDto>> loginVerifyCodeMap = new ConcurrentHashMap<>();


    @Override
    public void putCache(String key, LoginVerifyCodeDto value, long expired, TimeUnit unit) {
        CacheDto<LoginVerifyCodeDto> dto = new CacheDto<>();
        dto.setCreateTime(LocalDateTime.now());
        dto.setExpired(expired);
        dto.setValue(value);
        loginVerifyCodeMap.put(key, dto);
    }

    @Override
    public CacheDto<LoginVerifyCodeDto> getCache(String key) {
        CacheDto<LoginVerifyCodeDto> dto = loginVerifyCodeMap.get(key);
        if (expired(dto)) {
            return dto;
        } else {
            loginVerifyCodeMap.remove(key);
            return null;
        }
    }
}
