package com.module.common.constant;

/**
 * 正则公式
 * @author wangdong
 * @date: 2019/6/28 14:04
 */
public class RegexPattern {
    /**
     * 手机号规则
     */
    public static final String MOBILE ="^\\d{11}$";

    /**
     * 用户名校验 中英文数字  1-12位
     */
    public static final String REGEX_USERNAME = "[\\u4e00-\\u9fa5]*|\\w*|\\d*|_*";
}
