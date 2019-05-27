package com.api.admin.back.controller.admin;


import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.module.admin.back.result.PermissionTreeResult;
import com.module.common.bean.CurrentUser;
import com.module.admin.back.entity.BackMenu;
import com.module.admin.back.service.BackMenuService;
import com.module.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
@Api(description = "权限管理")
@RestController
@RequestMapping("/backadmin/menu")
public class MenuController {

    @SofaReference
    private BackMenuService backMenuService;

    @ApiOperation(value="获取站点下的菜单")
    @GetMapping("loadMenu")
    public Response<List<BackMenu>> loadMenu(CurrentUser user,String site) {
        List<BackMenu> backRoles = backMenuService.loadMenu(user,site);
        return Response.Builder.success(backRoles);
    }
    @ApiOperation(value="获取站点")
    @GetMapping("loadSite")
    public Response<List<BackMenu>> loadSite(CurrentUser user) {
        List<BackMenu> backRoles = backMenuService.loadSite(user);
        return Response.Builder.success(backRoles);
    }


    @ApiOperation(value="获取Tree结构的菜单")
    @GetMapping("list")
    public Response<List<PermissionTreeResult>> list() {
        List<PermissionTreeResult> backRoles = backMenuService.listMenu();
        return Response.Builder.success(backRoles);
    }
    @ApiOperation(value="添加菜单")
    @PostMapping("add")
    public Response<Integer> add(BackMenu backMenu, @ApiIgnore CurrentUser user) {
        backMenu.setCreateBy(user.getId());
        return Response.Builder.success(backMenuService.insertMenu(backMenu));
    }

    @ApiOperation(value="更新菜单")
    @PostMapping("update")
    public Response<Boolean> update(BackMenu backMenu, CurrentUser user) {
        backMenu.setUpdateBy(user.getId());
        backMenuService.updateMenu(backMenu);
        return Response.Builder.success(true);
    }


    @PostMapping("del")
    public Response<Boolean> del(Integer id, CurrentUser user) {
        backMenuService.delMenu(id);
        return Response.Builder.success(true);
    }

}
