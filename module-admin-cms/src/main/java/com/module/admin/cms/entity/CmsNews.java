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
public class CmsNews extends BaseEntity {

private static final long serialVersionUID=1L;

    private String name;

    /**
     * 标题图
     */
    private String imgUrl;

    private String descTitle;

    private String descText;

    /**
     * 排序
     */
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
