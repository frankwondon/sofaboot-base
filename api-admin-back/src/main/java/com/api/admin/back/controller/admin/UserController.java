package com.api.admin.back.controller.admin;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.admin.back.BackUserResult;
import com.module.admin.back.entity.BackRole;
import com.module.admin.back.query.BackUserQuery;
import com.module.common.bean.CurrentUser;
import com.module.admin.back.entity.BackUser;
import com.module.admin.back.service.BackUserService;
import com.module.common.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/backadmin/user")
public class UserController {
    @SofaReference
    private BackUserService backUserService;

    @GetMapping("/list")
    public Response<IPage<BackUserResult>> list(BackUserQuery query) {
        return Response.Builder.success(backUserService.listUser(query));
    }

    @GetMapping("/getUser")
    public Response<BackUserResult> getUser(Integer uid){
        return Response.Builder.success( backUserService.getUser(uid));
    }

    @PostMapping("/addUser")
    public Response<Boolean> addBackUser(BackUser backUser, CurrentUser user) {
        if (backUser.getId()!=null){
            backUser.setCreateBy(user.getId());
            backUserService.updateUser(backUser);
        }else{
            backUser.setCreateBy(user.getId());
            backUserService.insertUser(backUser);
        }
        return Response.Builder.success(true);
    }


    @PostMapping("/allotRole")
    public Response<Boolean> allotRole(Integer userId,Integer roleId) {
        backUserService.allotUserRole(userId,roleId);
        return Response.Builder.success(true);
    }
}
