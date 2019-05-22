package com.module.admin.back.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.module.admin.back.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * back_menu 
 * </p>
 *
 * @author wangdong
 * @since 2019-05-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class BackMenu extends BaseEntity {

private static final long serialVersionUID=1L;

    /**
     * 标题
     */
    private String title;

    /**
     * 菜单路径
     */
    private String url;

    /**
     * 层级
     */
    private Integer depth;

    /**
     * 父ID
     */
    private Integer pId;


    /**
     * 是否锁定
     */
    private Integer deleted;

    /*子集合*/
    @TableField(exist = false)
    private List<BackMenu> child;
}
