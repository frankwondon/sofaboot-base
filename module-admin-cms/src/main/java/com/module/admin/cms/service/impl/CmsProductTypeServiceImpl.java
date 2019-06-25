package com.module.admin.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.admin.cms.entity.CmsProductType;
import com.module.admin.cms.mapper.CmsProductTypeMapper;
import com.module.admin.cms.query.ProductQuery;
import com.module.admin.cms.result.CmsProductTypeResult;
import com.module.admin.cms.service.CmsProductService;
import com.module.admin.cms.service.CmsProductTypeService;
import com.module.common.bean.AdminCurrentUser;
import com.module.common.bean.PageQuery;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

public class CmsProductTypeServiceImpl implements CmsProductTypeService {
    @Resource
    private CmsProductTypeMapper productTypeMapper;
    @Resource
    private CmsProductService cmsProductService;

    @Override
    public IPage<CmsProductType> list(PageQuery pageQuery) {
        Page page = new Page(pageQuery.getPage(), pageQuery.getLimit());
        return productTypeMapper.listPage(page,pageQuery.getKeyWord());
    }

    @Override
    public List<CmsProductType> listSelect() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("deleted", 0);
        return productTypeMapper.selectList(wrapper);
    }

    @Override
    public void addOrUpdate(CmsProductType product, AdminCurrentUser user) {
        if (product.getId() != null) {
            product.setUpdateBy(user.getId());
            product.setUpdateTime(LocalDateTime.now());
            productTypeMapper.updateById(product);
        } else {
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
    public void disable(Integer id, Boolean disable, AdminCurrentUser user) {
        if (disable) {
            productTypeMapper.disable(id, 0);
        } else {
            productTypeMapper.disable(id, 1);
        }

    }

    @Override
    public List<CmsProductTypeResult> showList(ProductQuery query) {
        List<CmsProductTypeResult> cmsProductTypeResults = productTypeMapper.showList();
        if (cmsProductTypeResults != null){
            //把tab1的产品列表查出来
            CmsProductTypeResult cmsProductTypeResult = cmsProductTypeResults.get(0);
            query.setProductId(cmsProductTypeResult.getId());
            cmsProductTypeResult.setProductPage(cmsProductService.showList(query));
        }
        return cmsProductTypeResults;
    }
}
