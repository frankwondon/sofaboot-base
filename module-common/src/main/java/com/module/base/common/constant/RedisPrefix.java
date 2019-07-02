package com.module.base.common.constant;

/**
 * @author wangdong
 * @date: 2019/6/25 9:17
 */
public enum RedisPrefix {
    RATE_LIMIT("RATE_LIMIT:","流量限制"),
    LOGIN_VERIFY_CODE("LOGIN_VERIFY_CODE:","登陆验证码"),
    REAL_TIME_GOLD_PRICE("REAL_TIME_GOLD_PRICE","实时金价"),
    CACHE_GOLD_PRICE("REAL_TIME_GOLD_PRICE","未开盘时的金价"),
    RPWD_CODE("RPWD_CODE:","修改密码验证码"),
    TOKEN_USER_S("TOKEN_USERS","用户token"),
    TOKEN_USER_VISITOR_S("TOKEN_USERS","游客token"),
    SESSION_USER_S("SESSION_USERS","用户对象");
    private String key;
    private String desc;
    RedisPrefix(String key, String desc){
        this.key=key;
        this.desc=desc;
    }
    public String key(){
        return key;
    }
}
