package com.module.admin.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.admin.cms.entity.CmsProduct;
import com.module.admin.cms.mapper.CmsProductMapper;
import com.module.admin.cms.query.ProductQuery;
import com.module.admin.cms.result.CmsProductResult;
import com.module.admin.cms.service.CmsProductService;
import com.module.common.bean.AdminCurrentUser;
import com.module.common.bean.PageQuery;

import javax.annotation.Resource;
import java.time.LocalDateTime;

public class CmsProductServiceImpl implements CmsProductService {
    @Resource
    private CmsProductMapper productMapper;


    @Override
    public IPage<CmsProductResult> list(PageQuery pageQuery) {
        Page page=new Page(pageQuery.getPage(),pageQuery.getLimit());
        return productMapper.listPage(page,pageQuery.getKeyWord());
    }

    @Override
    public IPage<CmsProductResult> listHot(PageQuery pageQuery) {
        Page page=new Page(pageQuery.getPage(),pageQuery.getLimit());
        return productMapper.listHotPage(page,pageQuery.getKeyWord());
    }

    @Override
    public void addOrUpdate(CmsProduct product, AdminCurrentUser user) {
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
    public void disable(Integer id,Boolean disable, AdminCurrentUser user) {
        if (disable){
            productMapper.disable(id,0);
        }else {
            productMapper.disable(id,1);
        }

    }

    @Override
    public IPage<CmsProduct> showList(ProductQuery pageQuery) {
        QueryWrapper queryWrapper=new QueryWrapper();
        if (pageQuery.getProductId()!=null){
            queryWrapper.eq("type_id",pageQuery.getProductId());
        }
        queryWrapper.eq("deleted",0);
        queryWrapper.ne("show_type",1);
        queryWrapper.eq("locked",0);
        queryWrapper.orderByAsc("sort");
        Page<CmsProduct> page=new Page(pageQuery.getPage(),pageQuery.getLimit());
        productMapper.selectPage(page,queryWrapper);
        return page;
    }

    @Override
    public CmsProduct hotProduct() {
        return productMapper.hotProduct();
    }

    @Override
    public CmsProduct findById(Integer id) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("id",id);
        queryWrapper.eq("deleted",0);
        queryWrapper.eq("locked",0);
        return productMapper.selectOne(queryWrapper);
    }

    @Override
    public IPage<CmsProductResult> searchKeyWord(PageQuery query) {
        Page page= new Page(query.getPage(),query.getLimit());
        if (query.getKeyWord()!=null&&!"".equals(query.getKeyWord().trim())) {
            return productMapper.searchKeyWord(page,"%"+query.getKeyWord()+"%");

        }
        return null;


    }
}
