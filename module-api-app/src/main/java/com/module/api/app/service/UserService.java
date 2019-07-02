package com.module.api.app.service;

import com.module.api.app.dto.UserDto;
import com.module.api.app.query.LoginQuery;
import com.module.api.app.result.LoginResult;
import com.module.common.bean.AppCurrentUser;
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

    /**
     * 验证token
     * @param dto
     */
    AppCurrentUser getTokenUser(AppTokenDto dto);

    /**
     * 发送登陆验证码
     * @param phone
     */
    void sendLoginVerifyCode(String phone);

    /**
     * 执行登陆
     * @param query
     * @return
     */
    LoginResult login(LoginQuery query);

    /**
     * 登出
     * @param userDto
     */
    void loginOut(UserDto userDto);
}
