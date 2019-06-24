package com.module.admin.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.admin.app.mapper.AppUserMapper;
import com.module.admin.app.result.UserManagerResult;
import com.module.common.bean.PageQuery;

import javax.annotation.Resource;

/**
 * @author wangdong
 * @date: 2019/6/21 17:42
 */
public class AppUserServiceImpl implements AppUserService {
    @Resource
    private AppUserMapper userMapper;
    @Override
    public IPage<UserManagerResult> listOfUser(PageQuery query) {
        Page page=new Page<>(query.getPage(),query.getLimit());
        return userMapper.listOfUser(page,query.getKeyWord());
    }

    @Override
    public void disable(Integer id,Integer locked) {
        //todo 这里最好采用redis缓存 来降低APP端的请求
        userMapper.disable(id,locked);
    }
}
