package com.module.api.app.entity;

import com.module.common.db.BaseEntity;
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
     * 上架类型0立即上架1下架状态2自动上架
     */
    private Integer shelfType;

    /**
     * 自动上架时间
     */
    private LocalDateTime autoShelfTime;

    /**
     * 定义的skus格式类似{name:克重,values:[0.5,1,1]}
     */
    private String skus;

    /**
     * 乐观锁
     */
    private Integer version;


}
