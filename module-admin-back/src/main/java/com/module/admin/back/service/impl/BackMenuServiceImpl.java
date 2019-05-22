package com.module.admin.back.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.module.admin.back.entity.BackMenu;
import com.module.admin.back.mapper.BackMenuMapper;
import com.module.admin.back.service.BackMenuService;
import com.module.common.bean.CurrentUser;
import com.module.common.constant.BackAdminConstant;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BackMenuServiceImpl implements BackMenuService {


    @Resource
    private BackMenuMapper backMenuMapper;

    @Override
    public List<BackMenu> listMenu() {
        return backMenuMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public List<BackMenu> loadSite(CurrentUser user) {
        List<BackMenu> backMenus;
        //超管查询全部
        if (user.getRole().equals(BackAdminConstant.SUPER_USER_ROLE)) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("deleted",0);
            queryWrapper.eq("depth",1);
            backMenus = backMenuMapper.selectList(queryWrapper);
        } else {
            backMenus = backMenuMapper.findRoleMenus(user.getRole(),1);
        }
        if (backMenus!=null) {
            return backMenus;
        }
        return Collections.emptyList();
    }

    @Override
    public List<BackMenu> loadMenu(CurrentUser user, String site) {
        if (StrUtil.isNotBlank(site)){
            QueryWrapper queryWrapperSite = new QueryWrapper();
            queryWrapperSite.eq("deleted",0);
            queryWrapperSite.eq("url",site);
            queryWrapperSite.eq("depth",1);
            BackMenu backMenuSite = backMenuMapper.selectOne(queryWrapperSite);
            if (backMenuSite!=null){
                List<BackMenu> backMenus;
                //超管查询全部
                if (user.getRole().equals(BackAdminConstant.SUPER_USER_ROLE)) {
                    QueryWrapper queryWrapper = new QueryWrapper();
                    queryWrapper.eq("deleted",0);
                    queryWrapper.ne("depth",1);
                    backMenus = backMenuMapper.selectList(queryWrapper);
                } else {
                    backMenus = backMenuMapper.findRoleMenus(user.getRole(),-1);
                }
                //解析成Tree
                if (backMenus!=null) {
                    List<BackMenu> result=new ArrayList<>();
                    List<BackMenu> second = backMenus.stream().filter(backMenu -> 2 == backMenu.getDepth()).collect(Collectors.toList());
                    List<BackMenu> three = backMenus.stream().filter(backMenu -> 3 == backMenu.getDepth()).collect(Collectors.toList());
                    //构造二级
                    second.forEach(parent -> {
                        if (parent.getPId().equals(backMenuSite.getId())){
                            three.forEach(child -> {
                                createTree(parent,child);
                            });
                            result.add(parent);
                        }
                    });
                    return result;
                }
            }
        }
        return Collections.emptyList();
    }

    private void createTree(BackMenu parent,BackMenu child){
        if (child.getPId().equals(parent.getId())) {
            if (parent.getChild()==null){
                List<BackMenu> child1 = new ArrayList<>();
                child1.add(child);
                parent.setChild(child1);
            }else{
                List<BackMenu> child1 = parent.getChild();
                child1.add(child);
            }
        }
    }

    @Override
    public void updateMenu(BackMenu menu) {
        menu.setUpdateTime(LocalDateTime.now());
        backMenuMapper.updateById(menu);
    }

    @Override
    public void insertMenu(BackMenu menu) {
        menu.setCreateTime(LocalDateTime.now());
        backMenuMapper.insert(menu);
    }
}
