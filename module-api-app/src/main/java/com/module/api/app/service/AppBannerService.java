package com.module.api.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.api.app.entity.AppBanner;
import com.module.common.bean.PageQuery;

/**
 * @author wangdong
 * @date: 2019/6/20 11:48
 */
public interface AppBannerService {
    /**
     * 分页查询
     * @param pageQuery
     * @return
     */
    IPage<AppBanner> list(PageQuery pageQuery);

    /**
     * 添加
     * @param appBanner
     */
    void add(AppBanner appBanner);

    /**
     * 修改
     * @param appBanner
     */
    void update(AppBanner appBanner);

    /**
     * 删除
     * @param id
     */
    void del(Integer id);

    /**
     * 禁用
     * @param id
     * @param locked
     */
    void disable(Integer id ,Integer locked);
}
