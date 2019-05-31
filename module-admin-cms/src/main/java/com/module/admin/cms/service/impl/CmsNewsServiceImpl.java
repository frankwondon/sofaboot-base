package com.module.admin.cms.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.admin.cms.entity.CmsNews;
import com.module.admin.cms.entity.CmsProductType;
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
        Page page=new Page(pageQuery.getPage(),pageQuery.getLimit());
        return newsMapper.listPage(page);
    }

    @Override
    public void addOrUpdate(CmsNews news, CurrentUser user) {
        if (news.getId()!=null){
            news.setUpdateBy(user.getId());
            news.setUpdateTime(LocalDateTime.now());
            newsMapper.updateById(news);
        }else {
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
    public void disable(Integer id, CurrentUser user) {
        newsMapper.disable(id);
    }
}
