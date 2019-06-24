package com.module.base.common.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangdong
 * @date: 2019/6/24 18:01
 */
public class CacheFactory {
    //实现单例
    private static final Map<String, Cache> singletonObjects = new ConcurrentHashMap<>(256);

    public static  <T> Cache<T> getCache(Class<Cache<T>> cacheClass) throws InstantiationException, IllegalAccessException {
        Object oldObject = singletonObjects.get(cacheClass.getName());
        if (oldObject == null) {
            Cache<T> tCache = cacheClass.newInstance();
            singletonObjects.put(cacheClass.getName(),tCache);
            return tCache;
        } else {
            return singletonObjects.get(cacheClass.getName());
        }
    }
}
