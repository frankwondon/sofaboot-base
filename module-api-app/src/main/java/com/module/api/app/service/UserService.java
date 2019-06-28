package com.module.api.app.service;

import com.module.api.app.dto.UserDto;
import com.module.api.app.query.LoginQuery;
import com.module.common.bean.AppTokenDto;

/**
 * @author wangdong
 * @date: 2019/6/25 10:53
 */
public interface UserService {

    /**
     * 获取token
     * @param dto
     * @return
     */
    String getToken(AppTokenDto dto);

    void sendLoginVerifyCode(String phone);

    void login(LoginQuery query);

    void loginOut(UserDto userDto);
}
