package com.module.admin.back.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.admin.back.result.BackUserResult;
import com.module.admin.back.entity.BackUser;
import com.module.admin.back.query.BackUserQuery;

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

    /**
     * 新增用户
     * @param backUser
     */
    void insertUser(BackUser backUser);
    /**
     * 更新用户
     * @param backUser
     */
    void updateUser(BackUser backUser);
    /**
     * 分配角色
     */
    void allotUserRole(Integer uid,Integer roleId);

    /**
     * 查询后台用户
     * @param backUserQuery
     * @return
     */
    IPage<BackUserResult> listUser(BackUserQuery backUserQuery);

    /**
     * 查询商户用户
     * @param backUserQuery
     * @return
     */
    IPage<BackUserResult> listMerchantUser(BackUserQuery backUserQuery);

    /**
     * 查询单个用户
     * @param uid
     * @return
     */
    BackUserResult getUser(Integer uid);

    /**
     * 锁定/解锁用户
     * @param uid
     * @param locked
     */
    void lockedUser(Integer uid,Integer locked);

    /**
     * 重置密码
     * @param uid
     * @param pwd
     * @param oldPwd
     */
    void updatePwd(Integer uid, String pwd,String oldPwd);

    /**
     * 重设密码
     * @param uid
     */
    void resetPwd(Integer uid);
}
