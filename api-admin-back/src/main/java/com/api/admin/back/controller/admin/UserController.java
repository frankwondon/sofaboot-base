package com.api.admin.back.controller.admin;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.module.common.bean.CurrentUser;
import com.module.admin.back.entity.BackUser;
import com.module.admin.back.service.BackUserService;
import com.module.common.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/backadmin/user")
public class UserController {
    @SofaReference
    private BackUserService backUserService;

    @GetMapping("/list")
    public Response<Boolean> list(BackUser backUser, CurrentUser user) {
        backUser.setCreateBy(user.getId());
        backUserService.insertUser(backUser);
        return Response.Builder.success(true);
    }

    @PostMapping("/addUser")
    public Response<Boolean> addBackUser(BackUser backUser, CurrentUser user) {
        backUser.setCreateBy(user.getId());
        backUserService.insertUser(backUser);
        return Response.Builder.success(true);
    }

    @PostMapping("/updateUser")
    public Response<Boolean> updateBackUser(BackUser backUser, CurrentUser user) {
        backUser.setCreateBy(user.getId().intValue());
        backUserService.insertUser(backUser);
        return Response.Builder.success(true);
    }
}
