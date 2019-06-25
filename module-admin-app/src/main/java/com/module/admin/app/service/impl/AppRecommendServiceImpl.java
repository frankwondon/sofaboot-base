package com.module.admin.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.admin.app.entity.AppBanner;
import com.module.admin.app.entity.AppRecommend;
import com.module.admin.app.mapper.AppRecommendMapper;
import com.module.admin.app.result.AppRecommendResult;
import com.module.admin.app.service.AppRecommendService;
import com.module.common.bean.PageQuery;

import javax.annotation.Resource;

/**
 * @author wangdong
 * @date: 2019/6/21 9:30
 */
public class AppRecommendServiceImpl implements AppRecommendService {
    @Resource
    private AppRecommendMapper appRecommendMapper;
    @Override
    public IPage<AppRecommendResult> list(PageQuery pageQuery) {
        Page page=new Page<>(pageQuery.getPage(),pageQuery.getLimit());
        IPage<AppRecommendResult> iPage =appRecommendMapper.pageQuery(page,pageQuery.getKeyWord());
        return iPage;
    }

    @Override
    public void add(AppRecommend appRecommend) {
        appRecommendMapper.insert(appRecommend);
    }

    @Override
    public void update(AppRecommend appBanner) {
        appRecommendMapper.updateById(appBanner);
    }

    @Override
    public void del(Integer id) {
        appRecommendMapper.delLogic(id);
    }

    @Override
    public void disable(Integer id, Integer locked) {
        appRecommendMapper.disable(id,locked);
    }
}
