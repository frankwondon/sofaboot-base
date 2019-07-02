package com.module.api.app.service.impl;

import com.module.api.app.mapper.BannerListMapper;
import com.module.api.app.result.BannerListResult;
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


    @Override
    public List<BannerListResult> bannerIndexList() {
        return bannerListMapper.bannerIndexList();
    }

    @Override
    public List<BannerListResult> bannerShopList() {
        return bannerListMapper.bannerShopList();
    }
}
