package com.module.admin.app.entity;

import com.module.admin.app.entity.base.BaseEntity;
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
 * @since 2019-06-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AppProduct extends BaseEntity {

private static final long serialVersionUID=1L;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品编号自动生成
     */
    private String num;

    /**
     * 0上架中1下架
     */
    private Integer status;

    /**
     * 产品重量
     */
    private Integer productGram;

    /**
     * 首页展示图片
     */
    private String mainImg;

    /**
     * 商品类型ID
     */
    private Integer productType;

    /**
     * 产品详情图
     */
    private String descImg;

    /**
     * 运费模板
     */
    private Integer expressTemplateId;

    /**
     * 发货方式0物流1无需物流
     */
    private Integer shipType;

    /**
     * 支付方式0在线支付
     */
    private Integer payType;

    /**
     * 上架类型0立即上架1下架状态3自动上架
     */
    private Integer shelfType;

    /**
     * 定义的skus格式类似{name:克重,values:[0.5,1,1]}
     */
    private String skus;

    /**前端生成的SKU*/
    private String finalSkus;

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
