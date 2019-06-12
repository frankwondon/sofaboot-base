package com.module.admin.cms.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.admin.cms.entity.CmsNews;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangdong
 * @since 2019-05-30
 */
public interface CmsNewsMapper extends BaseMapper<CmsNews> {
        IPage<CmsNews> listPage(Page page,@Param("keyWord") String keyWord);

        int del(Integer id);

        int disable(@Param("id") Integer id, @Param("disable") Integer disable);
}
