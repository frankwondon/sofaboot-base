package com.module.admin.back.service.user;

import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.module.common.model.BackUser;

/**
 *
 *@author wangdong
 *@date 2019/5/10
 */

public interface BackUserService {

     /**
     * 根据账号获取用户
     * @param account 账号
     * @return
     */
     BackUser getByAccount(String account);
}
