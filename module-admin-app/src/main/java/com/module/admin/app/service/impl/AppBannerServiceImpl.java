package com.module.admin.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.admin.app.entity.AppBanner;
import com.module.admin.app.mapper.AppBannerMapper;
import com.module.admin.app.service.AppBannerService;
import com.module.common.bean.PageQuery;

import javax.annotation.Resource;

/**
 * @author wangdong
 * @date: 2019/6/20 11:48
 */
public class AppBannerServiceImpl implements AppBannerService {
    @Resource
    private AppBannerMapper appBannerMapper;


    public IPage<AppBanner> list(PageQuery pageQuery){
        Page page=new Page<>(pageQuery.getPage(),pageQuery.getLimit());
        IPage<AppBanner> iPage = appBannerMapper.selectPage(page, new QueryWrapper<>());
        return iPage;
    }
}
