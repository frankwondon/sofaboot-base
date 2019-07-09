package com.module.api.app.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.api.app.entity.AppCart;
import com.module.api.app.mapper.AppCartMapper;
import com.module.api.app.result.AppCartResult;
import com.module.api.app.service.AppCartService;
import com.module.common.bean.PageQuery;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName AppOrderServiceImpl
 * @Description app购物车
 * @Author YJT
 * @Date 2019/7/4 0004 14:12
 * @Version 1.0
 **/
public class AppCartServiceImpl implements AppCartService {


    @Resource
    private AppCartMapper appCartMapper;


    @Override
    public IPage<AppCartResult> appCartList(PageQuery query, Integer userId) {
        Page page = new Page<>(query.getPage(), query.getLimit());
        IPage<AppCartResult> appCartResultIPage = appCartMapper.appCartList(page, userId);
        if (appCartResultIPage.getRecords()!=null){
            for (AppCartResult appCartResult : appCartResultIPage.getRecords()) {
                appCartResult.setThumbImg(appCartResult.getMainImg().split(",")[0]);
                String sku = appCartResult.getSku();
                JSONArray skuList = JSON.parseArray(sku);
                appCartResult.setSkuList(skuList);
            }
        }
        return appCartResultIPage;
    }



    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void addToCart(AppCart appCart) {
        AppCart oldCartPro = appCartMapper.getOldCartByParam(appCart.getProductId(),appCart.getSkuId(),appCart.getUserId());
        if (oldCartPro!=null){
            oldCartPro.setNumber(appCart.getNumber());
            appCartMapper.updateCart(oldCartPro);
        }
        if (oldCartPro==null){
            appCartMapper.newAddToCart(appCart);
        }

    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delCartList(Integer[] skuIds, Integer userId) {
        if (skuIds!=null&&skuIds.length>0){
            appCartMapper.delCartBySkuIds(skuIds,userId);
        }
    }
}
