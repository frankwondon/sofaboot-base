package com.module.admin.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.module.admin.app.entity.base.BaseEntity;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wangdong
 * @since 2019-06-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AppRecommend extends BaseEntity {

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
     * 0奢华享受 1精品小件
     */
    private Integer belong;

    /**
     * 描述标题
     */
    private String descTitle;

    /**
     * 描述内容
     */
    private String descText;

    /**
     * 关联的商品Id
     */
    private Integer productId;

    /**
     * 0商品banner1其他普通直接跳转H5
     */
    private Integer type;

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
