package com.module.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author wangdong
 * @date: 2019/6/24 11:20
 */
public class Jdk8DateTimeUtil {
    /**
     * 转换字符串的时间-LocalDateTime时间
      * @param time String
     * @param pattern
     * @return
     */
    public static LocalDateTime parseOfStr(String time, String pattern) {
        return LocalDateTime.parse(time, DateTimeFormatter.ofPattern(pattern));
    }
}
