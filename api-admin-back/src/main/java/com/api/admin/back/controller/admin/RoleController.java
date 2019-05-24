package com.api.admin.back.controller.admin;

import cn.hutool.core.util.StrUtil;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.base.Strings;
import com.module.admin.back.result.PermissionTreeResult;
import com.module.common.bean.CurrentUser;
import com.module.admin.back.entity.BackRole;
import com.module.admin.back.service.BackRoleService;
import com.module.common.Response;
import com.module.common.bean.PageQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/backadmin/role")
public class RoleController {

    @SofaReference
    private BackRoleService backRoleService;

    @GetMapping("list")
    public Response<IPage<BackRole>> list(PageQuery query) {
        return Response.Builder.success(backRoleService.listRole(query));
    }

    @GetMapping("listAll")
    public Response<List<BackRole>> listAll(){
        return Response.Builder.success(backRoleService.listRole());
    }

    @PostMapping("findRoleMenus")
    public Response<List<PermissionTreeResult>> findRoleMenus(Integer roleId) {
        return Response.Builder.success(backRoleService.findRoleMenu(roleId));
    }


    @PostMapping("allotMenus")
    public Response<Boolean> allotMenus(String menuIds,Integer roleId) {
        List<String> split = StrUtil.split(menuIds, ',', true, true);
        backRoleService.allotMenus(roleId,split);
        return Response.Builder.success(true);
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
