package com.module.base.common.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author wangdong
 * @date: 2019/6/24 16:59
 */
@Data
public class CacheDto<T> {
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 失效时间
     */
    private long expired;

    /**
     * 失效时间单位
     */
    private TimeUnit timeUnit;

    /**
     * 保存的对象
     */
    private T value;
}
