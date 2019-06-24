package com.module.api.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.api.app.entity.AppProductType;
import com.module.api.app.mapper.AppProductTypeMapper;
import com.module.api.app.service.AppProductTypeService;
import com.module.common.bean.PageQuery;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangdong
 * @date: 2019/6/18 17:31
 */
public class AppProductTypeServiceImpl implements AppProductTypeService {
    @Resource
    private AppProductTypeMapper productTypeMapper;
    @Override
    public IPage<AppProductType> list(PageQuery query){
        Page page=new Page<>(query.getPage(),query.getLimit());
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.like("name",query.getKeyWord());
        return productTypeMapper.selectPage(page, queryWrapper);
    }

    @Override
    public void addOrUpdateProductType(AppProductType productType) {
        //更新
        if (productType.getId()!=null){
            productTypeMapper.updateById(productType);
        //插入
        }else {
            productTypeMapper.insert(productType);
        }

    }

    @Override
    public void disable(Integer productId, Integer locked){
        productTypeMapper.disable(productId,locked);
    }

    @Override
    public List<AppProductType> listUse() {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.like("deleted",0);
        queryWrapper.like("locked",0);
        return productTypeMapper.selectList( queryWrapper);
    }

}
