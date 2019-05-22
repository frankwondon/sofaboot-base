package com.module.admin.back.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.module.admin.back.BackUserResult;
import com.module.admin.back.entity.BackUser;
import com.module.admin.back.mapper.BackUserMapper;
import com.module.admin.back.query.BackUserQuery;
import com.module.admin.back.service.BackUserService;
import com.module.common.constant.BackAdminConstant;
import com.module.common.util.SaltPwdBean;
import com.module.common.util.ShiroPasswordUtil;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;


/**
 * @author wangdong
 * @date 2019/5/10
 */
public class BackUserServiceImpl implements BackUserService {

    @Resource
    private BackUserMapper userMapper;

    @Override
    public BackUser getByAccount(String account) {
        if (StrUtil.isNotBlank(account)){
            BackUser backUser = userMapper.selectByAccount(account);
            return backUser;
        }
        return null;
    }

    @Override
    public void insertUser(BackUser backUser) {
        //如果是商户 生成一个商户ID 否则用自己固定的商户号
        if (backUser.getUserType() == BackAdminConstant.USER_TYPE_MERCHANT) {
            backUser.setMerchantId(IdUtil.fastSimpleUUID());
        } else {
            backUser.setMerchantId(BackAdminConstant.NORMAL_ID);
        }
        backUser.setCreateTime(LocalDateTime.now());
        //自动创建密码
        SaltPwdBean enpwd = ShiroPasswordUtil.enpwd();
        backUser.setEncryptPwd(enpwd.getSaltPwd());
        backUser.setSalt(enpwd.getSalt());
        backUser.setSalt(enpwd.getSalt());
        userMapper.insert(backUser);
    }

    @Override
    public void updateUser(BackUser backUser) {
        backUser.setUpdateTime(LocalDateTime.now());
        userMapper.updateById(backUser);
    }

    @Override
    public List<BackUserResult> listUser(BackUserQuery backUserQuery) {
        return userMapper.listBackUser(backUserQuery);
    }
}
