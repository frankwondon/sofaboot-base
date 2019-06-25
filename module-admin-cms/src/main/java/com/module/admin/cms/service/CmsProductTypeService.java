package com.module.admin.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.admin.cms.entity.CmsProductType;
import com.module.admin.cms.query.ProductQuery;
import com.module.admin.cms.result.CmsProductTypeResult;
import com.module.common.bean.AdminCurrentUser;
import com.module.common.bean.PageQuery;

import java.util.List;

/**
 * Banner管理的
 *@author wangdong
 *@date 2019/5/30
 */
public interface CmsProductTypeService {

    /**
     * 分页查询Banner
     * */
    IPage<CmsProductType> list(PageQuery pageQuery);

    /**
     * 查询下拉列表
     * @return
     */
    List<CmsProductType> listSelect();

    void addOrUpdate(CmsProductType product, AdminCurrentUser user);

    void del(Integer id);

    /**
     * 禁用Banner
     * @param id
     */
    void disable(Integer id, Boolean disable, AdminCurrentUser user);

    /**
     * 前台-列表
     * @param query 返回tab1的结果集
     * @return
     */
    List<CmsProductTypeResult> showList(ProductQuery query);
}
