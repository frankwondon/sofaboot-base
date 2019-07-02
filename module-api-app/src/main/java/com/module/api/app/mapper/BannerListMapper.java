package com.module.api.app.mapper;

import com.module.api.app.result.BannerListResult;

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
    List<BannerListResult> bannerIndexList();

    /**
     * 商城轮播图
     * @return
     */
    List<BannerListResult> bannerShopList();
}
