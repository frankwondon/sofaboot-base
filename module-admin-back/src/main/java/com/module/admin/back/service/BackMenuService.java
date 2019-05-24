package com.module.admin.back.service;

import com.module.admin.back.entity.BackMenu;
import com.module.admin.back.result.PermissionTreeResult;
import com.module.common.bean.CurrentUser;

import java.util.List;

public interface BackMenuService {

    List<PermissionTreeResult> listMenu();

    List<BackMenu> loadSite(CurrentUser user);

    List<BackMenu> loadMenu(CurrentUser user,String site);

    void updateMenu(BackMenu menu);

    void insertMenu(BackMenu menu);

    void delMenu(Integer id);

}
