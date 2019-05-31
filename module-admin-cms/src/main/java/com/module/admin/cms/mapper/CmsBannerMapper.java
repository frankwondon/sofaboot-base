package com.module.admin.cms.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.admin.cms.entity.CmsBanner;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangdong
 * @since 2019-05-29
 */
@Repository
public interface CmsBannerMapper extends BaseMapper<CmsBanner> {
        int disable(Integer id);
        IPage<CmsBanner> listPage(Page page);
}
