package com.module.api.app.constant;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 产品相关常量池
 * @author wangdong
 * @date: 2019/6/17 17:43
 */
public class ProductConstant implements Serializable {
    public static final Map<String, LocalDateTime> PRODUCT_CREATE_NUM=new ConcurrentHashMap<>();
}
