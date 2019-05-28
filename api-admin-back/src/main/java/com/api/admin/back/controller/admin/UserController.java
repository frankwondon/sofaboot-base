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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(description = "用户管理")
@RestController
@RequestMapping("/backadmin/user")
public class UserController {
    @SofaReference
    private BackUserService backUserService;

    @ApiOperation("获取用户列表")
    @GetMapping("/list")
    public Response<IPage<BackUserResult>> list(BackUserQuery query) {
        return Response.Builder.success(backUserService.listUser(query));
    }

    @ApiOperation("获取单个用户")
    @GetMapping("/getUser")
    public Response<BackUserResult> getUser(Integer uid){
        return Response.Builder.success( backUserService.getUser(uid));
    }

    @ApiOperation("添加用户")
    @PostMapping("/addUser")
    public Response<Boolean> addBackUser(BackUser backUser,@ApiIgnore CurrentUser user) {
        if (backUser.getId()!=null){
            backUser.setCreateBy(user.getId());
            backUserService.updateUser(backUser);
        }else{
            backUser.setCreateBy(user.getId());
            backUserService.insertUser(backUser);
        }
        return Response.Builder.success(true);
    }

    @ApiOperation("分配权限")
    @PostMapping("/allotRole")
    public Response<Boolean> allotRole(Integer userId,Integer roleId) {
        backUserService.allotUserRole(userId,roleId);
        return Response.Builder.success(true);
    }
    @ApiOperation("锁定用户")
    @PostMapping("/locked")
    public Response<Boolean> locked(Integer userId,Integer locked) {
        backUserService.lockedUser(userId, locked);
        return Response.Builder.success(true);
    }


    @ApiOperation("修改密码")
    @PostMapping("updatePwd")
    public Response<Boolean> updatePwd(@ApiIgnore CurrentUser currentUser, String newPwd, String oldPwd){
        backUserService.updatePwd(currentUser.getId(),newPwd,oldPwd);
        //登出
        SecurityUtils.getSubject().logout();
        return Response.Builder.success(true);
    }
}
