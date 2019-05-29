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
 * @since 2019-05-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CmsBanner extends BaseEntity {

private static final long serialVersionUID=1L;

    /**
     * 名称
     */
    private String name;

    /**
     * 跳转链接
     */
    private String link;

    /**
     * 图片路径
     */
    private String imgUrl;

    /**
     * 分组
     */
    private Integer group;

    /**
     * 描述标题
     */
    private String descTitle;

    /**
     * 描述内容
     */
    private String descText;

    /**
     * 0正常1锁定
     */
    private Integer locked;

    private Integer deleted;




}
