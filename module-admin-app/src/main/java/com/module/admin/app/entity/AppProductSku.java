package com.module.admin.app.entity;

import java.math.BigDecimal;
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
 * @since 2019-06-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AppProductSku extends BaseEntity {

private static final long serialVersionUID=1L;

    /**
     * 定义的单项格式类似[{name:'克重',value:0.5},{name:'材质',value:‘Au999’}]
     */
    private String sku;

    /**
     * 商品ID
     */
    private Integer productId;

    /**
     * 标价
     */
    private BigDecimal markedPrice;

    /**
     * 定价
     */
    private BigDecimal fixedPrice;

    /**
     * 库存
     */
    private Integer reserve;

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
