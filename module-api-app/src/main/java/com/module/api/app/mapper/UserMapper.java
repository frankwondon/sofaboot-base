package com.module.api.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.module.api.app.entity.AppUser;

/**
 * @author wangdong
 * @date: 2019/6/27 10:48
 */
public interface UserMapper extends BaseMapper<AppUser> {
    /**
     * 手机号获取用户
     * @param phone
     * @return
     */
    AppUser getUserByCellPhone(String phone);

}
