package com.module.admin.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.admin.cms.entity.CmsNews;
import com.module.admin.cms.entity.CmsProductType;
import com.module.common.bean.CurrentUser;
import com.module.common.bean.PageQuery;

import java.util.List;

/**
 * Banner管理的
 *@author wangdong
 *@date 2019/5/30
 */
public interface CmsNewsService {

    /**
     * 分页查询Banner
     * */
    IPage<CmsNews> list(PageQuery pageQuery);


    void addOrUpdate(CmsNews product, CurrentUser user);

    void del(Integer id);

    /**
     * 禁用Banner
     * @param id
     */
    void disable(Integer id, Boolean disable, CurrentUser user);
}