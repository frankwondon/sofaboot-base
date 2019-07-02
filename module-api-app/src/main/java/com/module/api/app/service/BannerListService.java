package com.module.api.app.service;

import com.module.api.app.result.BannerListResult;

import java.util.List;

/**
 * @ClassName BannerListService
 * @Description 轮播图
 * @Author yjt
 * @Date 2019/7/1 0001 14:40
 * @Version 1.0
 **/
public interface BannerListService {

    /**
     * 首页轮播图
     * @return
     */
    List<BannerListResult> bannerIndexList();

    /**
     * 商城轮播图
     * @return
     */
    List<BannerListResult> bannerShopList();


}
