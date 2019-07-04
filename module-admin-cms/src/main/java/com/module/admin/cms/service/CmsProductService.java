package com.module.admin.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.admin.cms.entity.CmsProduct;
import com.module.admin.cms.query.ProductQuery;
import com.module.admin.cms.result.CmsProductResult;
import com.module.common.bean.AdminCurrentUser;
import com.module.common.bean.PageQuery;

/**
 * Banner管理的
 *@author wangdong
 *@date 2019/5/30
 */
public interface CmsProductService {

    /**
     * 分页查询全部
     * */
    IPage<CmsProductResult> list(PageQuery pageQuery);

    /**
     * 分页查询热门产品
     * */
    IPage<CmsProductResult> listHot(PageQuery pageQuery);

    void addOrUpdate(CmsProduct product, AdminCurrentUser user);

    void del(Integer id);

    /**
     * 禁用Banner
     * @param id
     */
    void disable(Integer id,Boolean disable, AdminCurrentUser user);


    /**
     * 前台-分页查询全部
     * */
    IPage<CmsProduct> showList(ProductQuery pageQuery);

    /**
     * 前台-查询带视频的热门产品
     * @return
     */
    CmsProduct hotProduct();

    CmsProduct findById(Integer id);

    IPage<CmsProductResult> searchKeyWord(PageQuery query);
}
