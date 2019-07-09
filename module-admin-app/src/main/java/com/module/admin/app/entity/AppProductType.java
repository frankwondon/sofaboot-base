package com.module.admin.app.entity;

import com.module.common.db.BaseEntity;
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


}
