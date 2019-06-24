package com.module.base.common.cache;

import com.module.base.common.dto.CacheDto;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

/**
 * 缓存类 随时切换成Redis
 * @author wangdong
 * @date: 2019/6/24 16:59
 */
public interface Cache<T> {
    /**
     * 验证过期时间
     * @param dto
     * @return
     */
    default boolean expired(CacheDto<T> dto){
        if (dto != null) {
            long mill = TimeUnit.MILLISECONDS.convert(dto.getExpired(), dto.getTimeUnit());
            if (dto.getCreateTime().plus(mill, ChronoUnit.MILLIS).compareTo(LocalDateTime.now()) == 1) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }


    /**
     * 设置缓存
     * @param key
     */
    void putCache(String key,T value, long expired, TimeUnit unit);


    /**
     * 获取缓存
     * @param key
     * @return
     */
    CacheDto<T> getCache(String key);





}
