package com.module.api.app.query;

import com.module.common.bean.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wangdong
 * @date: 2019/6/14 16:21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AppProductQuery  extends PageQuery {
    public AppProductQuery(int page, int limit) {
        super(page, limit);
    }

    /**0上架1下架2售完3即将卖完*/
    private Integer status;
}
