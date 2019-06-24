package com.api.app.controller.auth;

import com.module.api.app.query.LoginQuery;
import com.module.common.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangdong
 * @date: 2019/6/24 10:20
 */
@RestController
@RequestMapping("auth")
public class LoginController {

    public Response login(LoginQuery login){
        return null;
    }


    public Response getLoginVerifyCode(String mobile){
        return null;
    }
}
