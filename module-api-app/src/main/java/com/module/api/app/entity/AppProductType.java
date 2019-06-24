package com.module.api.app.entity;

import com.module.api.app.entity.base.BaseEntity;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品分类
 * </p>
 *
 * @author wangdong
 * @since 2019-06-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AppProductType extends BaseEntity {

private static final long serialVersionUID=1L;

    private String name;

    private String productDesc;

    private Integer sort;

    /**
     * 0正常1锁定
     */
    private Integer locked;

    private Integer deleted;

    /**
     * 乐观锁
     */
    private Integer version;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private Integer updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
