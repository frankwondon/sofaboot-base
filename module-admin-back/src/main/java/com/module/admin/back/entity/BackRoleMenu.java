package com.module.admin.back.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author wangdong
 * @since 2019-05-23
 */
@Data
@Accessors(chain = true)
public class BackRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer menuId;

    private Integer roleId;

    public BackRoleMenu() {
    }

    public BackRoleMenu(Integer menuId, Integer roleId) {
        this.menuId = menuId;
        this.roleId = roleId;
    }
}
