package com.module.api.app.entity;

import com.module.api.app.entity.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
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
 * @since 2019-06-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AppBanner extends BaseEntity {
    @ApiModelProperty("商品ID")
    private static final long serialVersionUID=1L;

    /**
     * 名称
     */
    @ApiModelProperty("商品名称")
    private String name;

    /**
     * 跳转链接
     */
    @ApiModelProperty("跳转链接")
    private String link;

    /**
     * 图片路径
     */
    @ApiModelProperty("图片路径")
    private String imgUrl;

    /**
     * 0首页 1商城
     */
    @ApiModelProperty("0首页1商城")
    private Integer belong;

    /**
     * 描述标题
     */
    @ApiModelProperty("标题")
    private String descTitle;

    /**
     * 描述内容
     */
    @ApiModelProperty("内容")
    private String descText;

    /**
     * 关联的商品Id
     */
    @ApiModelProperty("商品Id")
    private Integer productId;

    /**
     * 0商品banner1其他普通直接跳转H5
     */
    @ApiModelProperty("首页，商城")
    private Integer type;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 0正常1锁定
     */
    @ApiModelProperty("状态")
    private Integer locked;

    /**
     * 软删除
     */
    @ApiModelProperty("启用")
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
