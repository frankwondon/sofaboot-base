package com.api.app.intercept;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * QPS速率限制
 * @author wangdong
 * @date: 2019/7/16 9:31
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface RateLimiter {
    double limit() default 1.0;
}
