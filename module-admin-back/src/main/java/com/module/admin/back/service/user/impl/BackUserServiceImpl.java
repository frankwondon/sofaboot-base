package com.module.admin.back.service.user.impl;

import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.module.admin.back.service.user.BackUserService;
import com.module.common.model.BackUser;

/**
 *
 *@author wangdong
 *@date 2019/5/10
 */
@SofaService
public class BackUserServiceImpl implements BackUserService {
    @Override
    public BackUser getByAccount(String account) {
        BackUser backUser=new BackUser();
        backUser.setId(1);
        backUser.setPassword("123456");
        backUser.setUsername("admin");
        return backUser;
    }
}
