package com.module.admin.back.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.admin.back.entity.BackRole;
import com.module.admin.back.mapper.BackRoleMapper;
import com.module.admin.back.service.BackRoleService;
import com.module.common.bean.PageQuery;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

public class BackRoleServiceImpl implements BackRoleService {


    @Resource
    private BackRoleMapper backRoleMapper;

    @Override
    public void insertRole(BackRole backRole) {
        backRole.setCreateTime(LocalDateTime.now());
        backRoleMapper.insert(backRole);
    }

    @Override
    public void updateRole(BackRole backRole) {
        backRole.setUpdateTime(LocalDateTime.now());
        backRoleMapper.updateById(backRole);
    }

    @Override
    public IPage listRole(PageQuery pageQuery) {
        Page page=new Page(pageQuery.getPage(),pageQuery.getLimit());
        return backRoleMapper.selectPage(page, null);
    }
}
