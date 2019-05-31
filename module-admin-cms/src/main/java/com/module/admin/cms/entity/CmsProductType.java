package com.module.admin.cms.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangdong
 * @since 2019-05-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CmsProductType extends BaseEntity {

private static final long serialVersionUID=1L;

    private String name;

    private Integer sort;

    private Integer locked;

    private Integer deleted;



}
