package com.module.api.app.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.api.app.mapper.ProductMapper;
import com.module.api.app.query.ProductQuery;
import com.module.api.app.result.AppProductResult;
import com.module.api.app.result.AppProductTypeResult;
import com.module.api.app.service.ProductService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangdong
 * @date: 2019/6/25 10:20
 */
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;
    @Override
    public List<AppProductResult> listProduct(ProductQuery query) {
        Page page=new Page<>(query.getPage(),query.getLimit());
        return productMapper.pageQuery(page, query.getTypeId());
    }

    @Override
    public List<AppProductTypeResult> listType(Integer limit) {
        List<AppProductTypeResult> appProductTypeResults = productMapper.listProductType();
        if (appProductTypeResults!=null){
            AppProductTypeResult appProductTypeResult = appProductTypeResults.get(0);
            Page page=new Page<>(1,limit);
            appProductTypeResult.setFirstDefaultPage( productMapper.pageQuery(page,appProductTypeResult.getTypeId()));
        }
        return appProductTypeResults;
    }
}
