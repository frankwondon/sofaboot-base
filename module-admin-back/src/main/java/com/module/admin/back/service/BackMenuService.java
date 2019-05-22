package com.module.admin.back.service;

import com.alibaba.fastjson.JSONArray;
import com.module.admin.back.entity.BackMenu;
import com.module.common.bean.CurrentUser;

import java.util.List;

public interface BackMenuService {

    List<BackMenu> listMenu();

    List<BackMenu> loadSite(CurrentUser user);

    List<BackMenu> loadMenu(CurrentUser user,String site);

    void updateMenu(BackMenu menu);

    void insertMenu(BackMenu menu);

}
