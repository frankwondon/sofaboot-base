package com.module.admin.back.service;

import com.module.admin.back.BackUserResult;
import com.module.admin.back.entity.BackUser;
import com.module.admin.back.query.BackUserQuery;

import java.util.List;

/**
 * @author wangdong
 * @date 2019/5/10
 */
public interface BackUserService {

    /**
     * 根据账号获取用户
     *
     * @param account 账号
     * @return
     */
    BackUser getByAccount(String account);

    void insertUser(BackUser backUser);

    void updateUser(BackUser backUser);

    List<BackUserResult> listUser(BackUserQuery backUserQuery);
}
