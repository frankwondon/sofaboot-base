package com.module.admin.back.service;

import com.module.admin.back.entity.BackMenu;
import com.module.common.bean.AdminCurrentUser;

import java.util.List;

public interface BackMenuService {

    List<BackMenu> listMenu();

    List<BackMenu> loadSite(AdminCurrentUser user);

    List<BackMenu> loadMenu(AdminCurrentUser user, String site);

    void updateMenu(BackMenu menu);

    Integer insertMenu(BackMenu menu);

    void delMenu(Integer id);

}
