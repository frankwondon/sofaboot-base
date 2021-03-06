package com.api.back.controller.admin;

import cn.hutool.core.util.StrUtil;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.common.bean.AdminCurrentUser;
import com.module.admin.back.entity.BackRole;
import com.module.admin.back.service.BackRoleService;
import com.module.common.Response;
import com.module.common.bean.PageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "角色管理")
@RestController
@RequestMapping("/backadmin/role/role")
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
    public Response<List<Integer>> findRoleMenus(Integer roleId) {
        return Response.success(backRoleService.findRoleMenu(roleId));
    }

    @ApiOperation("分配菜单")
    @PostMapping("allotMenus")
    public Response<Boolean> allotMenus(String menuIds,Integer roleId) {
        List<String> split = StrUtil.split(menuIds, ',', true, true);
        backRoleService.allotMenus(roleId,split);
        return Response.success(true);
    }

    @ApiOperation("添加角色")
    @PostMapping("add")
    public Response<Boolean> add(BackRole backRole, AdminCurrentUser user) {
        backRole.setCreateBy(user.getId());
        backRoleService.insertRole(backRole);
        return Response.success(true);
    }

    @ApiOperation("修改角色")
    @PostMapping("update")
    public Response<Boolean> update(BackRole backRole, AdminCurrentUser user) {
        backRole.setUpdateBy(user.getId());
        backRoleService.updateRole(backRole);
        return Response.success(true);
    }

    @ApiOperation("启用/禁用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "disable" ,paramType = "query",required = true,value = "true启用fasle禁用"),
            @ApiImplicitParam(name = "id" ,paramType = "query",required = true,value = "ID"),
    })
    @PostMapping("disable")
    public Response<Boolean> locked(Boolean disable, Integer id){
        backRoleService.locked(disable?0:1,id);
        return Response.success(true);
    }

}
