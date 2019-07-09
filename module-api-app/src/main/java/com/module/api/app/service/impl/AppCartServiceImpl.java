package com.module.api.app.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.api.app.dto.DelCartDto;
import com.module.api.app.entity.AppCart;
import com.module.api.app.mapper.AppCartMapper;
import com.module.api.app.result.AppCartResult;
import com.module.api.app.service.AppCartService;
import com.module.common.ResponseCode;
import com.module.common.bean.PageQuery;
import com.module.common.exception.DBException;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if (appCartResultIPage.getRecords()!=null&&appCartResultIPage.getRecords().size()>0){
            for (AppCartResult appCartResult : appCartResultIPage.getRecords()) {
                appCartResult.setThumbImg(appCartResult.getMainImg().split(",")[0]);
                String sku = appCartResult.getSku();
                JSONArray skuList = JSON.parseArray(sku);
                appCartResult.setSkuList(skuList);
                //todo 店铺名称后期可拓展
                appCartResult.setShopName("陕西金韵文化有限公司");
            }
        }
            return appCartResultIPage;
    }



    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public long addToCart(AppCart appCart) {
        AppCart oldCartPro = appCartMapper.getOldCartByParam(appCart.getProductId(),appCart.getSkuId(),appCart.getUserId());
        int successCount=0;
        if (oldCartPro!=null){
            oldCartPro.setNumber(appCart.getNumber());
            successCount=appCartMapper.updateCart(oldCartPro);
        }
        if (oldCartPro==null){
            appCart.setCreateTime(LocalDateTime.now());
            successCount=appCartMapper.newAddToCart(appCart);
        }
        if (successCount>0){
            return getCartSize(appCart.getUserId());
        }else {
            throw new DBException(ResponseCode.C_520004);
        }
    }

    @Override
    public long putToCart(AppCart appCart) {
        AppCart oldCartPro = appCartMapper.getOldCartByParam(appCart.getProductId(),appCart.getSkuId(),appCart.getUserId());
        int successCount=0;
        if (oldCartPro!=null){
            oldCartPro.setNumber(appCart.getNumber()+oldCartPro.getNumber());
            successCount=appCartMapper.updateCart(oldCartPro);
        }
        if (oldCartPro==null){
            appCart.setCreateTime(LocalDateTime.now());
            successCount=appCartMapper.newAddToCart(appCart);
        }
        if (successCount>0){
            return getCartSize(appCart.getUserId());
        }else {
            throw new DBException(ResponseCode.C_520004);
        }
    }

    /**
     * 批量删除 购物车
     * @param
     * @param
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public long delCartList(DelCartDto delCartDto) {
        int successCount=0;
        if (delCartDto.getSkuIds()!=null&&delCartDto.getSkuIds().size()>0){
            Map<String, Object> map = new HashMap<>(5);
            map.put("skuIds",delCartDto.getSkuIds());
            map.put("userId", delCartDto.getUserId());
            successCount=appCartMapper.delCartBySkuIds(map);
        }
        if (successCount>0){
            return getCartSize(delCartDto.getUserId());
        }else {
            throw  new DBException(ResponseCode.C_520005);
        }
    }


    /**
     * 获取购物车中的数量
     * @param userId
     * @return
     */
    private long getCartSize(Integer userId){
        long cartCount = appCartMapper.getCartCount(userId);
        if (cartCount==0){
            return 0L;
        }
        return cartCount;
    }
}
