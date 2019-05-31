package com.module.admin.cms.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.admin.cms.entity.CmsProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangdong
 * @since 2019-05-29
 */
public interface CmsProductMapper extends BaseMapper<CmsProduct> {
        IPage<CmsProduct> listPage(Page page);

        IPage<CmsProduct> listHotPage(Page page);

        void disable(@Param("id") Integer id,@Param("disable") Integer disable);
}
