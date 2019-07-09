package com.module.admin.back.entity;
import com.module.common.db.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * back_role 
 * </p>
 *
 * @author wangdong
 * @since 2019-05-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class BackRole extends BaseEntity {

private static final long serialVersionUID=1L;

    /**
     * 角色名称
     */
    private String title;

    private String code;

    private String remark1;
    /**
     * 是否删除 默认0 锁定1
     */
    private Integer deleted;
    private Integer locked;


}
