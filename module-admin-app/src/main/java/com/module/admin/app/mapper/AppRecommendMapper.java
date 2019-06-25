package com.module.admin.app.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.module.admin.app.entity.AppRecommend;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.module.admin.app.result.AppRecommendResult;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangdong
 * @since 2019-06-19
 */
public interface AppRecommendMapper extends BaseMapper<AppRecommend> {
    /**
     * 分页查询
     * @param page
     * @param keyWord
     * @return
     */
    IPage<AppRecommendResult> pageQuery(Page page, @Param("keyWord") String keyWord);
    /**
     * 禁用
     * @param id
     * @param locked
     * @return
     */
    int disable(@Param("id") int id, @Param("locked") int locked);

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    int  delLogic(@Param("id") int id);
}
