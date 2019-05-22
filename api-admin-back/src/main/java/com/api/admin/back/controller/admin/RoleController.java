package com.api.admin.back.controller.admin;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.common.bean.CurrentUser;
import com.module.admin.back.entity.BackRole;
import com.module.admin.back.service.BackRoleService;
import com.module.common.Response;
import com.module.common.bean.PageQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/backadmin/role")
public class RoleController {

    @SofaReference
    private BackRoleService backRoleService;

    @GetMapping("list")
    public Response<IPage<BackRole>> list(PageQuery query) {
        return Response.Builder.success(backRoleService.listRole(query));
    }

    @PostMapping("add")
    public Response<Boolean> add(BackRole backRole, CurrentUser user) {
        backRole.setCreateBy(user.getId());
        backRoleService.insertRole(backRole);
        return Response.Builder.success(true);
    }


    @PostMapping("update")
    public Response<Boolean> update(BackRole backRole, CurrentUser user) {
        backRole.setUpdateBy(user.getId());
        backRoleService.updateRole(backRole);
        return Response.Builder.success(true);
    }

}
