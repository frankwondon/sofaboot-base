package com.module.admin.app.entity;

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
public class AppExpressTemplate extends BaseEntity {

private static final long serialVersionUID=1L;

    /**
     * 模板名字
     */
    private String name;

    /**
     * 0按件计费1按KG计费
     */
    private Integer priceRule;

    /**
     * 0卖家承担运费1买家承担运费
     */
    private Integer type;

    /**
     * 0采用非默认运费，1采用默认运费
     */
    private Integer defaultArea;


}
