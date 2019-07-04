package com.module.api.app.service.impl;

import com.module.api.app.entity.AppProductSku;
import com.module.api.app.mapper.BannerListMapper;
import com.module.api.app.mapper.ProductSkuMapper;
import com.module.api.app.result.AppBannerListResult;
import com.module.api.app.result.AppProductResult;
import com.module.api.app.service.BannerListService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName BannerListServiceImpl
 * @Description 轮播图提供方接口
 * @Author Administrator
 * @Date 2019/7/1 0001 14:45
 * @Version 1.0
 **/
public class BannerListServiceImpl implements BannerListService {
    @Resource
    private BannerListMapper bannerListMapper;

    @Resource
    private ProductSkuMapper productSkuMapper;

    /**
     * 首页轮播图
     * @return
     */
    @Override
    public List<AppBannerListResult> bannerIndexList() {
        return bannerListMapper.bannerIndexList();
    }

    /**
     * 商城轮播图
     * @return
     */
    @Override
    public List<AppBannerListResult> bannerShopList() {
        return bannerListMapper.bannerShopList();
    }


    /**
     * 精美
     * @return
     */
    @Override
    public List<AppProductResult> competitiveList() {
        List<AppProductResult> appProductResultList = bannerListMapper.competitiveList();
        for (AppProductResult appProductResult : appProductResultList) {
            Integer productId = appProductResult.getProductId();
            appProductResult.setAppProductSku(productSkuMapper.productSkuByIdOne(productId));
        }
        return appProductResultList;
    }


    /**
     * 奢华
     * @return
     */
    @Override
    public List<AppProductResult> luxuriousList() {
        return bannerListMapper.luxuriousList();
    }

}
