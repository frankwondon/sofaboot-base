package com.module.admin.app.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.admin.app.entity.AppBanner;
import com.module.admin.app.mapper.AppBannerMapper;
import com.module.admin.app.result.AppBannerResult;
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


    @Override
    public IPage<AppBannerResult> list(PageQuery pageQuery){
        Page page=new Page<>(pageQuery.getPage(),pageQuery.getLimit());
        return  appBannerMapper.pageQuery(page, pageQuery.getKeyWord());
    }

    @Override
    public void add(AppBanner appBanner){
        appBannerMapper.insert(appBanner);
    }

    @Override
    public void update(AppBanner appBanner){
        appBannerMapper.updateById(appBanner);
    }

    @Override
    public void del(Integer id){
        appBannerMapper.deleteById(id);
    }

    @Override
    public void disable(Integer id , Integer locked){
        appBannerMapper.disable(id,locked);
    }
}
