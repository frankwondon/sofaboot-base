package com.module.admin.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.admin.cms.entity.CmsBanner;
import com.module.admin.cms.mapper.CmsBannerMapper;
import com.module.admin.cms.service.CmsBannerService;
import com.module.common.bean.CurrentUser;
import com.module.common.bean.PageQuery;

import javax.annotation.Resource;
import java.time.LocalDateTime;

public class CmsBannerServiceImpl implements CmsBannerService {

    @Resource
    private CmsBannerMapper bannerMapper;

    @Override
    public IPage<CmsBanner> list(PageQuery pageQuery) {
        Page page = new Page(pageQuery.getPage(), pageQuery.getLimit());
        return bannerMapper.listPage(page);
    }

    @Override
    public void addOrUpdate(CmsBanner banner, CurrentUser user) {
        if (banner.getId()!=null){
            banner.setUpdateBy(user.getId());
            banner.setUpdateTime(LocalDateTime.now());
            bannerMapper.updateById(banner);
        }else {
            banner.setCreateBy(user.getId());
            banner.setCreateTime(LocalDateTime.now());
            bannerMapper.insert(banner);
        }
    }

    @Override
    public void del(Integer id) {
        bannerMapper.deleteById(id);
    }

    @Override
    public void disable(Integer id, CurrentUser user) {
        bannerMapper.disable(id);
    }



}
