package com.module.api.app.mapper;

import com.module.api.app.result.AppBannerListResult;
import com.module.api.app.result.AppProductResult;

import java.util.List;

/**
 * @ClassName BannerListMapper
 * @Description TODO
 * @Author Yjt
 * @Date 2019/7/1 0001 14:48
 * @Version 1.0
 **/
public interface BannerListMapper {

    /**
     * 首页轮播图
     * @return
     */
    List<AppBannerListResult> bannerIndexList();

    /**
     * 商城轮播图
     * @return
     */
    List<AppBannerListResult> bannerShopList();


    /**
     * 精品小件
     * @return
     */
    List<AppProductResult> competitiveList();



    /**
     * 奢华产品
     * @return
     */
    List<AppProductResult> luxuriousList();
}
