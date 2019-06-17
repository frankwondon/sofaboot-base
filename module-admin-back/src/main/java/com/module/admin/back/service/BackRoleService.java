package com.module.admin.back.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.module.admin.back.entity.BackRole;
import com.module.admin.back.result.PermissionTreeResult;
import com.module.common.bean.PageQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wangdong
 * @date 2019/5/10
 */
public interface BackRoleService {

    void insertRole(BackRole backRole);

    void updateRole(BackRole backRole);

    IPage listRole(PageQuery pageQuery);

    List<BackRole> listRole();

    List<Integer> findRoleMenu(Integer roleId);

    void allotMenus(Integer roleId,List<String> MenuIds);

    void locked(@Param("locked")Integer locked,@Param("id") Integer id);
}
