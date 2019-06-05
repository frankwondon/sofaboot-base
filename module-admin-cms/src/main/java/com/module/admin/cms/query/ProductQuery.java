package com.module.admin.cms.query;

import com.module.common.bean.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询产品用
 *@author wangdong
 *@date 2019/6/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProductQuery extends PageQuery {
    private Integer productId;

    public ProductQuery(int page, int limit) {
       super(page,limit);
    }
}
