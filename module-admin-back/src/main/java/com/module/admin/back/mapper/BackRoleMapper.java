package com.module.admin.back.mapper;

import com.module.admin.back.entity.BackRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.module.admin.back.entity.BackRoleMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * back_role  Mapper 接口
 * </p>
 *
 * @author wangdong
 * @since 2019-05-20
 */
@Repository
public interface BackRoleMapper extends BaseMapper<BackRole> {

    /**
     * 查询是否存在状态码
     * @param roleCode
     * @return
     */
    int countCodeExist(String roleCode);

    /**
     * 查询角色拥有那些功能点
     * @param roleId
     * @return
     */
    List<Integer> findRoleMenus(Integer roleId);


    int deleteRoleMenus(Integer roleId);


    void insertBatchRoleMenus(List<BackRoleMenu> list);


    void locked(@Param("locked") Integer locked,@Param("id") Integer id);

}
