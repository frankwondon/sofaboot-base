package com.module.admin.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.admin.cms.entity.CmsProductType;
import com.module.admin.cms.mapper.CmsProductTypeMapper;
import com.module.admin.cms.service.CmsProductTypeService;
import com.module.common.bean.CurrentUser;
import com.module.common.bean.PageQuery;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

public class CmsProductTypeServiceImpl implements CmsProductTypeService {
    @Resource
    private CmsProductTypeMapper productTypeMapper;
    @Override
    public IPage<CmsProductType> list(PageQuery pageQuery) {
        Page page=new Page(pageQuery.getPage(),pageQuery.getLimit());
        return productTypeMapper.listPage(page);
    }

    @Override
    public List<CmsProductType> listSelect() {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("deleted",0);
        return productTypeMapper.selectList(wrapper);
    }

    @Override
    public void addOrUpdate(CmsProductType product, CurrentUser user) {
        if (product.getId()!=null){
            product.setUpdateBy(user.getId());
            product.setUpdateTime(LocalDateTime.now());
            productTypeMapper.updateById(product);
        }else {
            product.setCreateBy(user.getId());
            product.setCreateTime(LocalDateTime.now());
            productTypeMapper.insert(product);
        }
    }

    @Override
    public void del(Integer id) {
        productTypeMapper.del(id);
    }

    @Override
    public void disable(Integer id, CurrentUser user) {
        productTypeMapper.disable(id);
    }
}
