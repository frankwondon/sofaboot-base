package com.module.admin.back.mapper;

import com.module.admin.back.entity.BackMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * back_menu  Mapper 接口
 * </p>
 *
 * @author wangdong
 * @since 2019-05-20
 */
@Repository
public interface BackMenuMapper extends BaseMapper<BackMenu> {
    List<BackMenu> findRoleMenus(@Param("roleId") Integer roleId, @Param("depth")int depth);

    int countMenuUrl(@Param("id")Integer id,@Param("url")String url,@Param("pid")Integer pid);

    int countMenuName(@Param("id")Integer id,@Param("name")String name);

    /**
     * 查询子集的数量
     * @param id
     * @return
     */
    int countChild(Integer id);
}
