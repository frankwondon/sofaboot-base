package com.module.admin.cms.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.admin.cms.entity.CmsProduct;
import com.module.admin.cms.mapper.CmsProductMapper;
import com.module.admin.cms.service.CmsProductService;
import com.module.common.bean.CurrentUser;
import com.module.common.bean.PageQuery;

import javax.annotation.Resource;
import java.time.LocalDateTime;

public class CmsProductServiceImpl implements CmsProductService {
    @Resource
    private CmsProductMapper productMapper;


    @Override
    public IPage<CmsProduct> list(PageQuery pageQuery) {
        Page page=new Page(pageQuery.getPage(),pageQuery.getLimit());
        return productMapper.listPage(page);
    }

    @Override
    public IPage<CmsProduct> listHot(PageQuery pageQuery) {
        Page page=new Page(pageQuery.getPage(),pageQuery.getLimit());
        return productMapper.listHotPage(page);
    }

    @Override
    public void addOrUpdate(CmsProduct product, CurrentUser user) {
        if (product.getId()!=null){
            product.setUpdateBy(user.getId());
            product.setUpdateTime(LocalDateTime.now());
            productMapper.updateById(product);
        }else {
            product.setCreateBy(user.getId());
            product.setCreateTime(LocalDateTime.now());
            productMapper.insert(product);
        }
    }

    @Override
    public void del(Integer id) {
        productMapper.deleteById(id);
    }

    @Override
    public void disable(Integer id,Boolean disable, CurrentUser user) {
        if (disable){
            productMapper.disable(id,0);
        }else {
            productMapper.disable(id,1);
        }

    }
}
