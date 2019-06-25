package com.module.admin.app.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.admin.app.entity.AppBanner;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.module.admin.app.result.AppBannerResult;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangdong
 * @since 2019-06-19
 */
public interface AppBannerMapper extends BaseMapper<AppBanner> {
    /**
     * 分页查询Banner
     * @param page
     * @param keyWord
     * @return
     */
    IPage<AppBannerResult> pageQuery(Page page, @Param("keyWord") String keyWord);
    /**
     * 禁用Banner
     * @param id
     * @param locked
     * @return
     */
    int disable(@Param("id") int id, @Param("locked") int locked);
}
