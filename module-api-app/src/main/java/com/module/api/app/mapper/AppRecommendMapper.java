package com.module.api.app.mapper;

import com.module.api.app.entity.AppRecommend;
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
public interface AppRecommendMapper extends BaseMapper<AppRecommend> {
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
