package com.module.base.common.service;

import com.module.base.common.cache.CacheFactory;
import com.module.base.common.cache.LoginVerifyCache;

/**
 * @author wangdong
 * @date: 2019/6/24 18:25
 */
public class SMSSendServiceImpl implements SMSSendService {
    @Override
    public void sendLoginVerifyCode(String phone) {
        CacheFactory.getCache(LoginVerifyCache.class);
    }
}
