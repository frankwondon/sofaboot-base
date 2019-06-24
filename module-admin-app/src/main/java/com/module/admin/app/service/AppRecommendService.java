package com.module.admin.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.admin.app.entity.AppBanner;
import com.module.admin.app.entity.AppRecommend;
import com.module.common.bean.PageQuery;

/**
 * @author wangdong
 * @date: 2019/6/21 9:28
 */
public interface AppRecommendService {
    /**
     * 分页查询
     * @param pageQuery
     * @return
     */
    IPage<AppRecommend> list(PageQuery pageQuery);

    /**
     * 添加
     * @param appBanner
     */
    void add(AppRecommend appBanner);

    /**
     * 修改
     * @param appBanner
     */
    void update(AppRecommend appBanner);

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
