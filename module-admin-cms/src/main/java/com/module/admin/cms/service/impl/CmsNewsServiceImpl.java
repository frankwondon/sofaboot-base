package com.module.admin.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.admin.cms.entity.CmsNews;
import com.module.admin.cms.entity.CmsProduct;
import com.module.admin.cms.mapper.CmsNewsMapper;
import com.module.admin.cms.service.CmsNewsService;
import com.module.common.bean.CurrentUser;
import com.module.common.bean.PageQuery;

import javax.annotation.Resource;
import java.time.LocalDateTime;

public class CmsNewsServiceImpl implements CmsNewsService {
    @Resource
    private CmsNewsMapper newsMapper;


    @Override
    public IPage<CmsNews> list(PageQuery pageQuery) {
        Page page = new Page(pageQuery.getPage(), pageQuery.getLimit());
        return newsMapper.listPage(page,pageQuery.getKeyWord());
    }

    @Override
    public void addOrUpdate(CmsNews news, CurrentUser user) {
        if (news.getId() != null) {
            news.setUpdateBy(user.getId());
            news.setUpdateTime(LocalDateTime.now());
            newsMapper.updateById(news);
        } else {
            //默认正常
            news.setDeleted(0);
            news.setCreateBy(user.getId());
            news.setCreateTime(LocalDateTime.now());
            newsMapper.insert(news);
        }
    }

    @Override
    public void del(Integer id) {
        newsMapper.del(id);
    }

    @Override
    public void disable(Integer id, Boolean disable, CurrentUser user) {
        if (disable) {
            newsMapper.disable(id,0);
        } else {
            newsMapper.disable(id,1);
        }

    }

    @Override
    public IPage<CmsNews> showList(PageQuery pageQuery) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("deleted",0);
        queryWrapper.eq("locked",0);
        queryWrapper.orderByAsc("sort");
        Page<CmsNews> page=new Page(pageQuery.getPage(),pageQuery.getLimit());
        return newsMapper.selectPage(page,queryWrapper);
    }

    @Override
    public CmsNews findById(Integer id) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("id",id);
        queryWrapper.eq("locked",0);
        queryWrapper.eq("deleted",0);
        return newsMapper.selectOne(queryWrapper);
    }
}
