package com.module.common.constant;

/**
 * 时间格式化常量
 * 注意格式的定义,方便开发者快速定位格式寓意
 * 只包含年月日的用DATE进行开头
 * 只包含时分秒的用TIME开头
 * 都包含的用DATA_TIME开头
 * @author wangdong
 * @date: 2019/6/24 11:06
 */
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
