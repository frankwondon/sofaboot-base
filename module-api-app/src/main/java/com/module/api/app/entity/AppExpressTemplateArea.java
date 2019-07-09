package com.module.api.app.entity;

import java.math.BigDecimal;

import com.module.common.db.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 物流模板 
 * </p>
 *
 * @author wangdong
 * @since 2019-06-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AppExpressTemplateArea extends BaseEntity {

private static final long serialVersionUID=1L;

    /**
     * 模板的ID
     */
    private Integer tempId;


    /**
     * 区域JSON格式[{c:20001,n:'陕西'}]
     */
    private String area;

    /**
     * 首基数
     */
    private Integer firstNum;

    /**
     * 首价
     */
    private BigDecimal firstPrice;

    /**
     * 续基数
     */
    private Integer continueNum;

    /**
     * 续件价格
     */
    private BigDecimal continuePrice;

    /**
     * 是否是基础运费
     */
    private Integer defaultTemp;

}
