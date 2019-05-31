package com.module.admin.back.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.admin.back.result.BackUserResult;
import com.module.admin.back.entity.BackRole;
import com.module.admin.back.entity.BackUser;
import com.module.admin.back.mapper.BackRoleMapper;
import com.module.admin.back.mapper.BackUserMapper;
import com.module.admin.back.query.BackUserQuery;
import com.module.admin.back.service.BackUserService;
import com.module.common.ResponseCode;
import com.module.common.constant.BackAdminConstant;
import com.module.common.exception.DBOperationException;
import com.module.common.util.SaltPwdBean;
import com.module.common.util.ShiroPasswordUtil;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;


/**
 * @author wangdong
 * @date 2019/5/10
 */
public class BackUserServiceImpl implements BackUserService {

    @Resource
    private BackUserMapper userMapper;
    @Resource
    private BackRoleMapper roleMapper;

    private static final String defaultPwd="123456";

    @Override
    public BackUser getByAccount(String account) {
        if (StrUtil.isNotBlank(account)) {
            BackUser backUser = userMapper.selectByAccount(account);
            return backUser;
        }
        return null;
    }

    @Override
    public void insertUser(BackUser backUser) {
        if (userMapper.countUserName(-1,backUser.getUsername()) > 0 || userMapper.countUserPhone(-1,backUser.getCellPhoneNum()) > 0) {
            throw new DBOperationException(ResponseCode.C_500001);
        }
        //如果是商户 生成一个商户ID 否则用自己固定的商户号
        if (backUser.getUserType() == BackAdminConstant.USER_TYPE_MERCHANT) {
            backUser.setMerchantId(IdUtil.fastSimpleUUID());
        } else {
            backUser.setMerchantId(BackAdminConstant.NORMAL_ID);
        }
        backUser.setCreateTime(LocalDateTime.now());
        SaltPwdBean enpwd = ShiroPasswordUtil.enpwd(defaultPwd);
        backUser.setEncryptPwd(enpwd.getSaltPwd());
        backUser.setPassword(defaultPwd);
        backUser.setSalt(enpwd.getSalt());
        userMapper.insert(backUser);
    }

    @Override
    public void updateUser(BackUser backUser) {
        if (userMapper.countUserName(backUser.getId(),backUser.getUsername()) > 0 || userMapper.countUserPhone(backUser.getId(),backUser.getCellPhoneNum()) > 0) {
            throw new DBOperationException(ResponseCode.C_500001);
        }
        backUser.setUpdateTime(LocalDateTime.now());
        userMapper.updateById(backUser);
    }

    @Override
    @Transactional
    public void allotUserRole(Integer uid, Integer roleId) {
        BackRole backRole = roleMapper.selectById(roleId);
        if (backRole == null) {
            throw new DBOperationException(ResponseCode.C_500003);
        }
        userMapper.updateUserRole(uid, backRole.getId());
    }

    @Override
    public IPage<BackUserResult> listUser(BackUserQuery backUserQuery) {
        Page page = new Page(backUserQuery.getPage(), backUserQuery.getLimit());
        return userMapper.listBackUser(page, backUserQuery);
    }

    @Override
    public IPage<BackUserResult> listMerchantUser(BackUserQuery backUserQuery) {
        Page page = new Page(backUserQuery.getPage(), backUserQuery.getLimit());
        return userMapper.listMerchantUser(page, backUserQuery);
    }

    @Override
    public BackUserResult getUser(Integer uid) {
        return userMapper.findById(uid);
    }

    @Override
    public void lockedUser(Integer uid,Integer locked) {
        userMapper.updateLockedUser(uid,locked);
    }

    @Override
    public void updatePwd(Integer uid, String pwd,String oldPwd) {
        BackUser backUser = userMapper.selectById(uid);
        if (!backUser.getPassword().equals(oldPwd)){
            throw new DBOperationException(ResponseCode.C_500005);
        }
        updatePwd(uid,pwd);
    }

    @Override
    public void resetPwd(Integer uid) {
        updatePwd(uid,defaultPwd);
    }

    //需要更新密码
    private void updatePwd(Integer uid,String pwd){
        SaltPwdBean enpwd = ShiroPasswordUtil.enpwd(pwd);
        BackUser user=new BackUser();
        user.setId(uid);
        user.setEncryptPwd(enpwd.getSaltPwd());
        user.setPassword(pwd);
        user.setSalt(enpwd.getSalt());
        userMapper.updatePwd(user);
    }
}
