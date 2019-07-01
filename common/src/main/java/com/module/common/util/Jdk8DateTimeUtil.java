package com.module.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author wangdong
 * @date: 2019/6/24 11:20
 */
public class JDK8DateTimeUtil {
    public class DateFormatConstant {
        /**
         * 例如:2018-12-28
         */
        public static final String DATE = "yyyy-MM-dd";
        /**
         * 例如:2018-12-28 10:00:00
         */
        public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
        /**
         * 例如:2018-12-28 10:00
         */
        public static final String DATE_TIME_WITHOUT_SECOND = "yyyy-MM-dd HH:mm";
        /**
         * 例如:10:00:00
         */
        public static final String TIME = "HHmmss";
        /**
         * 例如:10:00
         */
        public static final String TIME_WITHOUT_SECOND = "HH:mm";


    }
        public static LocalDateTime parseOfStr(String time,String pattern){
        return LocalDateTime.parse(time, DateTimeFormatter.ofPattern(pattern));
    }
}
