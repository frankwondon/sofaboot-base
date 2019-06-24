package com.module.api.app.mapper;

import com.module.api.app.entity.AppExpressTemplateArea;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 物流模板  Mapper 接口
 * </p>
 *
 * @author wangdong
 * @since 2019-06-19
 */
@Repository
public interface AppExpressTemplateAreaMapper extends BaseMapper<AppExpressTemplateArea> {
    /**
     * 批量插入
     * @param list
     * @param tempId
     * @return
     */
    int insertList( @Param("list")List<AppExpressTemplateArea> list, @Param("tempId") Integer tempId);

    /**
     * 查询
     * @param tempId
     * @return
     */
    List<Integer> listIdsOfProduct(Integer tempId);
}
