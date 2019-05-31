package com.module.admin.cms.entity;

import java.time.LocalDateTime;
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
public class CmsProduct extends BaseEntity {

private static final long serialVersionUID=1L;

    /**
     * 名称
     */
    private String name;

    private String imgUrl;

    /**
     * 所属分类ID
     */
    private Integer typeId;

    /**
     * 0普通产品 1热门产品 2推荐产品
     */
    private Integer showType;

    private String videoUrl;

    /**
     * 描述标题
     */
    private String descTitle;

    /**
     * 简单描述
     */
    private String descDesc;
    /**
     * 描述内容
     */
    private String descText;

    /**
     * 排序
     */
    private Integer sort;

    private Integer locked;

    private Integer deleted;


}
