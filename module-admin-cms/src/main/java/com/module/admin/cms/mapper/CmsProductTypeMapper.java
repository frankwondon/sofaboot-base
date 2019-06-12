package com.module.admin.cms.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.admin.cms.entity.CmsProductType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.module.admin.cms.result.CmsProductTypeResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangdong
 * @since 2019-05-30
 */
public interface CmsProductTypeMapper extends BaseMapper<CmsProductType> {

        IPage<CmsProductType> listPage(Page page,@Param("keyWord") String keyWord);

        void del(Integer id);

        void disable(@Param("id") Integer id, @Param("disable") Integer disable);

        List<CmsProductTypeResult> showList();
}
