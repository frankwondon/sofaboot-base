package com.module.admin.back.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
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

    void allotUserRole(Integer uid,Integer roleId);

    IPage<BackUserResult> listUser(BackUserQuery backUserQuery);

    BackUserResult getUser(Integer uid);

    void lockedUser(Integer uid,Integer locked);

    void updatePwd(Integer uid, String pwd,String oldPwd);
}
