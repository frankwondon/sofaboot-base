package com.module.admin.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.admin.cms.entity.CmsBanner;
import com.module.admin.cms.entity.CmsProduct;
import com.module.common.bean.CurrentUser;
import com.module.common.bean.PageQuery;

/**
 * Banner管理的
 *@author wangdong
 *@date 2019/5/30
 */
public interface CmsProductService {

    /**
     * 分页查询Banner
     * */
    IPage<CmsProduct> list(PageQuery pageQuery);

    /**
     * 分页查询热门产品
     * */
    IPage<CmsProduct> listHot(PageQuery pageQuery);

    void addOrUpdate(CmsProduct product, CurrentUser user);

    void del(Integer id);

    /**
     * 禁用Banner
     * @param id
     */
    void disable(Integer id,Boolean disable, CurrentUser user);
}
