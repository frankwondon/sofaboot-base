package com.module.api.app.service;

import com.module.api.app.dto.UserDto;
import com.module.api.app.query.LoginQuery;

/**
 * @author wangdong
 * @date: 2019/6/25 10:53
 */
public interface UserService {
    void login(LoginQuery query);

    void loginOut(UserDto userDto);
}
