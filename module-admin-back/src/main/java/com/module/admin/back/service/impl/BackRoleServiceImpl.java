package com.module.admin.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.admin.back.entity.BackMenu;
import com.module.admin.back.entity.BackRole;
import com.module.admin.back.entity.BackRoleMenu;
import com.module.admin.back.mapper.BackMenuMapper;
import com.module.admin.back.mapper.BackRoleMapper;
import com.module.admin.back.mapper.BackRoleMenuMapper;
import com.module.admin.back.result.PermissionTreeResult;
import com.module.admin.back.service.BackRoleService;
import com.module.common.ResponseCode;
import com.module.common.bean.PageQuery;
import com.module.common.exception.DBOperationException;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BackRoleServiceImpl implements BackRoleService {


    @Resource
    private BackRoleMapper backRoleMapper;

    @Resource
    private BackMenuMapper backMenuMapper;

    @Resource
    private BackRoleMenuMapper backRoleMenuMapper;

    @Override
    public void insertRole(BackRole backRole) {
        backRole.setCreateTime(LocalDateTime.now());
        backRole.setDeleted(0);
        if (backRoleMapper.countCodeExist(backRole.getCode()) >0) {
            throw new DBOperationException(ResponseCode.C_500001);
        }
        backRoleMapper.insert(backRole);
    }

    @Override
    public void updateRole(BackRole backRole) {
        backRole.setUpdateTime(LocalDateTime.now());
        backRoleMapper.updateById(backRole);
    }

    @Override
    public IPage listRole(PageQuery pageQuery) {
        Page page = new Page(pageQuery.getPage(), pageQuery.getLimit());
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("deleted", 0);
        return backRoleMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<Integer> findRoleMenu(Integer roleId) {
        List<Integer> ownerMenus = backRoleMapper.findRoleMenus(roleId);
        if (ownerMenus==null){
            return Collections.emptyList();
        }else {
            return ownerMenus;
        }
    }


    @Override
    @Transactional
    public void allotMenus(Integer roleId, List<String> menuIds) {
        if (roleId == 1) {
            throw new DBOperationException(ResponseCode.C_500002);
        }else{
            List<BackRoleMenu> roleMenus=new ArrayList<>();
            for (String menuId : menuIds) {
                BackRoleMenu roleMenu=new BackRoleMenu(Integer.valueOf(menuId),roleId);
                roleMenus.add(roleMenu);
            }
            backRoleMapper.deleteRoleMenus(roleId);
            backRoleMapper.insertBatchRoleMenus(roleMenus);
        }
    }

    @Override
    public List<BackRole> listRole() {
        return backRoleMapper.selectList(null);
    }

    @Override
    public void locked(Integer locked, Integer id) {
        backRoleMapper.locked(locked,id);
    }
}
