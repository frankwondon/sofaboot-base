package com.module.admin.cms.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangdong
 * @since 2019-05-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CmsProduct extends BaseEntity {

private static final long serialVersionUID=1L;

    /**
     * 名称
     */
    private String name;

    private String imgUrl;

    /**
     * 0普通产品 1热门产品
     */
    private Integer type;

    private String videoUrl;

    /**
     * 描述标题
     */
    private String descTitle;

    /**
     * 描述内容
     */
    private String descText;

    private Integer locked;

    private Integer deleted;

    /**
     * 乐观锁
     */
    private Integer version;


}
