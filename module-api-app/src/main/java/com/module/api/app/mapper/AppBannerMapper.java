package com.module.api.app.mapper;

import com.module.api.app.entity.AppBanner;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
     * 禁用Banner
     * @param id
     * @param locked
     * @return
     */
    int disable(@Param("id") int id, @Param("locked") int locked);
}
