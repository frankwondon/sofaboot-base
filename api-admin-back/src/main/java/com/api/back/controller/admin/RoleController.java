package com.api.back.controller.admin;

import cn.hutool.core.util.StrUtil;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.admin.back.result.PermissionTreeResult;
import com.module.common.bean.CurrentUser;
import com.module.admin.back.entity.BackRole;
import com.module.admin.back.service.BackRoleService;
import com.module.common.Response;
import com.module.common.bean.PageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Api(tags = "角色管理")
@RestController
@RequestMapping("/backadmin/role")
public class RoleController {

    @SofaReference
    private BackRoleService backRoleService;

    @ApiOperation("分页获取角色列表")
    @GetMapping("list")
    public Response<IPage<BackRole>> list(PageQuery query) {
        return Response.success(backRoleService.listRole(query));
    }

    @ApiOperation("获取所有角色")
    @GetMapping("listAll")
    public Response<List<BackRole>> listAll(){
        return Response.success(backRoleService.listRole());
    }

    @ApiOperation("获取角色下的拥有的菜单")
    @GetMapping("findRoleMenus")
    public Response<List<PermissionTreeResult>> findRoleMenus(Integer roleId) {
        return Response.success(backRoleService.findRoleMenu(roleId));
    }

    @ApiOperation("分配惨淡")
    @PostMapping("allotMenus")
    public Response<Boolean> allotMenus(String menuIds,Integer roleId) {
        List<String> split = StrUtil.split(menuIds, ',', true, true);
        backRoleService.allotMenus(roleId,split);
        return Response.success(true);
    }

    @ApiOperation("添加角色")
    @PostMapping("add")
    public Response<Boolean> add(BackRole backRole, CurrentUser user) {
        backRole.setCreateBy(user.getId());
        backRoleService.insertRole(backRole);
        return Response.success(true);
    }

    @ApiOperation("修改角色")
    @PostMapping("update")
    public Response<Boolean> update(BackRole backRole, CurrentUser user) {
        backRole.setUpdateBy(user.getId());
        backRoleService.updateRole(backRole);
        return Response.success(true);
    }

}
