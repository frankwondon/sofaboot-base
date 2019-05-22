package com.api.admin.back.controller.admin;


import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.module.common.bean.CurrentUser;
import com.module.admin.back.entity.BackMenu;
import com.module.admin.back.service.BackMenuService;
import com.module.common.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/backadmin/menu")
public class MenuController {

    @SofaReference
    private BackMenuService backMenuService;


    @RequestMapping("loadMenu")
    public Response<List<BackMenu>> loadMenu(CurrentUser user,String site) {
        List<BackMenu> backRoles = backMenuService.loadMenu(user,site);
        return Response.Builder.success(backRoles);
    }

    @RequestMapping("loadSite")
    public Response<List<BackMenu>> loadSite(CurrentUser user) {
        List<BackMenu> backRoles = backMenuService.loadSite(user);
        return Response.Builder.success(backRoles);
    }


    @RequestMapping("list")
    public Response<List<BackMenu>> list() {
        List<BackMenu> backRoles = backMenuService.listMenu();
        return Response.Builder.success(backRoles);
    }

    @RequestMapping("add")
    public Response<Boolean> add(BackMenu backMenu, CurrentUser user) {
        backMenu.setCreateBy(user.getId());
        backMenuService.insertMenu(backMenu);
        return Response.Builder.success(true);
    }


    @RequestMapping("update")
    public Response<Boolean> update(BackMenu backMenu, CurrentUser user) {
        backMenu.setUpdateBy(user.getId());
        backMenuService.updateMenu(backMenu);
        return Response.Builder.success(true);
    }

}
