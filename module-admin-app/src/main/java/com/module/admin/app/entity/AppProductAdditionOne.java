package com.module.admin.app.entity;

import lombok.Data;

/**
 * @author wangdong
 * @date: 2019/6/21 15:08
 */
@Data
public class AppProductAdditionOne {

    /**
     * 商品Id
     */
    private Integer productId;

    /**
     * 访问量/打开一次算一次
     */
    private Integer pv;

}
