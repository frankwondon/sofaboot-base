package com.module.api.app.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.api.app.entity.AppProductType;
import com.module.common.bean.PageQuery;

import java.util.List;

/**
 *
 *@author wangdong
 *@date 2019/6/14
 */
public interface AppProductTypeService {
    /**
     * 分页列表
     * @param query
     * @return
     */
    IPage<AppProductType> list(PageQuery query);

    void addOrUpdateProductType(AppProductType productType);

    /**
     * 禁用/启用
     * @param id
     * @param locked
     */
    void disable(Integer id,Integer locked);

    /**
     * 查询所有可用商品类型
     * @return
     */
    List<AppProductType> listUse();
}
